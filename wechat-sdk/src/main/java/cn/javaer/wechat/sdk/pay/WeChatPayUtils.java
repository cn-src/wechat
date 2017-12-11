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

import cn.javaer.wechat.sdk.pay.model.AbstractWeChatPayResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.WordUtils;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author zhangpeng
 */
public class WeChatPayUtils {
    public static void checkAndSignRequest(WeChatPayUnifiedOrderRequest request, String mchKey) {
        if (WeChatPayUnifiedOrderRequest.TRADE_TYPE_NATIVE.equals(request.getTradeType()) && (request.getProductId() == null || request.getProductId().isEmpty())) {
            throw new IllegalArgumentException("When 'TradeType' is 'NATIVE', 'ProductId' must has value.");
        }
        request.setSign(WeChatPayUtils.sign(request, mchKey));
    }

    public static String sign(Object obj, String key) {

        final Class<?> aClass = obj.getClass();
        final MethodAccess methodAccess = MethodAccess.get(aClass);
        final Class[] returnTypes = methodAccess.getReturnTypes();
        final Class[][] parameterTypes = methodAccess.getParameterTypes();
        final String[] methodNames = methodAccess.getMethodNames();

        final List<Field> fields = FieldUtils.getFieldsListWithAnnotation(aClass, XmlElement.class);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < methodNames.length; i++) {
            final String methodName = methodNames[i];

            final boolean readMethodNonParam = parameterTypes[i] == null || parameterTypes[i].length == 0;
            final boolean readWithBool = methodName.startsWith("is") && (
                    (returnTypes[i] == boolean.class) || (returnTypes[i] == Boolean.class));
            final boolean readMethod = methodName.startsWith("get") || readWithBool;
            final boolean notIgnoreMethod = !"sign".endsWith(methodName);

            if (readMethod && readMethodNonParam && notIgnoreMethod) {
                String k = methodName;
                for (final Field field : fields) {
                    if (field.getName().equals(beanMethodNameToFieldName(methodName, returnTypes[i]))) {
                        final XmlElement[] xmlElements = field.getAnnotationsByType(XmlElement.class);
                        if (null != xmlElements && xmlElements.length > 0) {
                            final String xmlElementName = xmlElements[0].name();
                            if (StringUtils.isNotEmpty(xmlElementName)) {
                                k = xmlElementName;
                            }
                        }
                    }
                }

                final Object value = methodAccess.invoke(obj, i, methodName);
                if (null != value && !value.toString().isEmpty()) {
                    sb.append(k).append('=').append(value).append('&');
                }
            }
        }

        sb.append("key").append('=').append(key);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }

    public static void checkResponseBody(AbstractWeChatPayResponse response, @NotNull String mchKey) {
        if (null == response) {
            throw new WeChatPayException("WeChat pay response is null");
        }
        if (!response.getSign().equals(WeChatPayUtils.sign(response, mchKey))) {
            throw new WeChatPayException("WeChat pay response 'sign' error, response:" + response.toString());
        }
        if (!AbstractWeChatPayResponse.SUCCESS.equals(response.getReturnCode())) {
            throw new WeChatPayException("WeChat pay response error, response:" + response.toString());
        }
        if (!AbstractWeChatPayResponse.SUCCESS.equals(response.getResultCode())) {
            throw new WeChatPayException("WeChat pay response error, response:" + response.toString());
        }
    }

    public static boolean isSuccessfulResponseBody(AbstractWeChatPayResponse response) {

        return (null != response) &&
                (AbstractWeChatPayResponse.SUCCESS.equals(response.getReturnCode())) &&
                (AbstractWeChatPayResponse.SUCCESS.equals(response.getResultCode()));
    }

    private static String beanMethodNameToFieldName(final String methodName, final Class returnType) {
        if (methodName.startsWith("is") && (returnType == boolean.class || returnType == Boolean.class)) {
            return WordUtils.uncapitalize(methodName.substring(2));
        }

        if (methodName.startsWith("get")) {
            return WordUtils.uncapitalize(methodName.substring(3));
        }

        return methodName;
    }
}
