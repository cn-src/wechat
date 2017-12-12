/*
 *    Copyright 2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.WeChatPayResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.WordUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 微信支付工具类.
 *
 * @author zhangpeng
 */
public class WeChatPayUtils {

    private static final Logger log = LoggerFactory.getLogger(WeChatPayUtils.class);

    private static final Map<Class, Map<String, NameIndex>> CACHE_FOR_SIGN = new ConcurrentHashMap<>();

    /**
     * 根据 TradeType 校验参数, 并签名.
     *
     * @param request WeChatPayUnifiedOrderRequest
     * @param mchKey 商户key
     */
    public static void checkAndSignRequest(WeChatPayUnifiedOrderRequest request, String mchKey) {
        if (WeChatPayUnifiedOrderRequest.TRADE_TYPE_NATIVE.equals(request.getTradeType())
                && (request.getProductId() == null || request.getProductId().isEmpty())) {
            throw new IllegalArgumentException("When 'TradeType' is 'NATIVE', 'ProductId' must has value.");
        }
        request.setSign(WeChatPayUtils.sign(request, mchKey));
    }

    /**
     * 微信支付-签名.
     *
     * @param obj 要签名的数据对象.
     * @param key key
     *
     * @return 返回签名 String
     */
    public static String sign(Object obj, String key) {

        Class<?> clazz = obj.getClass();

        Map<String, NameIndex> cache = CACHE_FOR_SIGN.get(clazz);

        MethodAccess methodAccess = MethodAccess.get(clazz);

        if (null != cache) {
            log.debug("Sign '{}' from cache", clazz.getName());

            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, NameIndex> entry : cache.entrySet()) {
                NameIndex nameIndex = entry.getValue();
                Object value = methodAccess.invoke(obj, nameIndex.getMethodIndex(), nameIndex.getMethodName());
                if (null != value && !value.toString().isEmpty()) {
                    sb.append(entry.getKey()).append('=').append(value).append('&');
                }
            }
            sb.append("key").append('=').append(key);
            return DigestUtils.md5Hex(sb.toString()).toUpperCase();
        } else {
            cache = new TreeMap<>();
            CACHE_FOR_SIGN.put(clazz, cache);
        }

        String[] methodNames = methodAccess.getMethodNames();
        Class[] returnTypes = methodAccess.getReturnTypes();
        Class[][] parameterTypes = methodAccess.getParameterTypes();

        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, XmlElement.class);

        Map<String, Object> sortedMap = new TreeMap<>();

        for (int i = 0; i < methodNames.length; i++) {
            String methodName = methodNames[i];

            boolean readMethodNonParam = parameterTypes[i] == null || parameterTypes[i].length == 0;
            boolean readWithBool = methodName.startsWith("is") && (
                    (returnTypes[i] == boolean.class) || (returnTypes[i] == Boolean.class));
            boolean readMethod = methodName.startsWith("get") || readWithBool;
            boolean notIgnoreMethod = !"getSign".equals(methodName) && !"getClass".equals(methodName);

            if (readMethod && readMethodNonParam && notIgnoreMethod) {
                String k = methodName;
                for (Field field : fields) {
                    if (field.getName().equals(beanMethodNameToFieldName(methodName, returnTypes[i]))) {
                        XmlElement[] xmlElements = field.getAnnotationsByType(XmlElement.class);
                        if (null != xmlElements && xmlElements.length > 0) {
                            String xmlElementName = xmlElements[0].name();
                            if (StringUtils.isNotEmpty(xmlElementName)) {
                                k = xmlElementName;
                                cache.put(k, new NameIndex(methodName, i));
                            }
                        }
                    }
                }

                Object value = methodAccess.invoke(obj, i, methodName);
                if (null != value && !value.toString().isEmpty()) {
                    sortedMap.put(k, value);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        sb.append("key").append('=').append(key);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }

    /**
     * 校验响应信息是否为成功.
     *
     * @param response WeChatPayResponse
     * @param mchKey 商户key
     *
     * @throws WeChatPayException 没有响应信息, 签名错误, 响应信息标示不成功时抛出此异常.
     */
    public static void checkResponseBody(WeChatPayResponse response, @NotNull String mchKey) {
        if (null == response) {
            throw new WeChatPayException("WeChat pay response is null");
        }
        if (!response.getSign().equals(WeChatPayUtils.sign(response, mchKey))) {
            throw new WeChatPayException("WeChat pay response 'sign' error, response:" + response.toString());
        }
        if (!WeChatPayResponse.SUCCESS.equals(response.getReturnCode())) {
            throw new WeChatPayException("WeChat pay response error, response:" + response.toString());
        }
        if (!WeChatPayResponse.SUCCESS.equals(response.getResultCode())) {
            throw new WeChatPayException("WeChat pay response error, response:" + response.toString());
        }
    }

    /**
     * 判断响应信息是否为成功.
     *
     * @param response WeChatPayResponse
     *
     * @return 有响应信息, 并且完全成功返回 true
     */
    public static boolean isSuccessfulResponseBody(WeChatPayResponse response) {

        return (null != response)
                && (WeChatPayResponse.SUCCESS.equals(response.getReturnCode()))
                && (WeChatPayResponse.SUCCESS.equals(response.getResultCode()));
    }

    private static String beanMethodNameToFieldName(String methodName, Class returnType) {
        if (methodName.startsWith("is") && (returnType == boolean.class || returnType == Boolean.class)) {
            return WordUtils.uncapitalize(methodName.substring(2));
        }

        if (methodName.startsWith("get")) {
            return WordUtils.uncapitalize(methodName.substring(3));
        }

        return methodName;
    }

    private static class NameIndex {
        private final String methodName;
        private final int methodIndex;

        public NameIndex(String methodName, int methodIndex) {
            this.methodName = methodName;
            this.methodIndex = methodIndex;
        }

        public String getMethodName() {
            return methodName;
        }

        public int getMethodIndex() {
            return methodIndex;
        }
    }
}
