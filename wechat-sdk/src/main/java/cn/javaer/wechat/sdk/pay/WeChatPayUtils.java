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

import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

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

    public static String sign(Object data, String key) {

        Class<?> clazz = data.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, String> dataMap = new TreeMap<>();

        try {
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                Object objVal;
                objVal = field.get(data);

                if (null != objVal && !objVal.toString().isEmpty()) {
                    String dataKey = Optional.ofNullable(field.getAnnotation(XmlElement.class))
                            .map(XmlElement::name)
                            .orElse(field.getName());
                    if (!"sign".equals(dataKey)) {
                        dataMap.put(dataKey, objVal.toString());
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        sb.append("key").append('=').append(key);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
    }

    public static void checkResponseBody(AbstractWeChatPayResponse response, @NotNull String mchKey) {
        if (null == response) {
            throw new WeChatPayException("WeChat pay response is null");
        }
        if (!response.getSign().equals(WeChatPayUtils.sign(response, mchKey))) {
            throw new WeChatPayException("WeChat pay response 'sign' error");
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
}
