/*
 * Copyright (c) 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.BasePayRequest;
import cn.javaer.wechat.sdk.pay.model.BasePayResponse;
import cn.javaer.wechat.sdk.pay.model.Coupon;
import cn.javaer.wechat.sdk.pay.support.SignIgnore;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * 微信支付工具类.
 *
 * @author zhangpeng
 */
public class WeChatPayUtils {

    private static final Map<Class, List<Field>> CACHE_FOR_SIGN = new ConcurrentHashMap<>();

    /**
     * 微信支付-生成签名.
     *
     * @param request 要签名的数据对象.
     * @param key key
     *
     * @return 返回签名 String
     */
    @NotNull
    public static String generateSign(
        @NotNull final BasePayRequest request, @NotNull final String key) {

        return generateSign(signParamsFrom(request), key);
    }

    /**
     * 微信支付-生成签名.
     *
     * @param response 要签名的数据对象.
     * @param mchKey mchKey
     *
     * @return 返回签名 String
     */
    @NotNull
    public static String generateSign(
        @NotNull final BasePayResponse response, @NotNull final String mchKey) {
        final Map<String, String> sortedMap = signParamsFrom(response);
        final Map<String, String> otherMap = response.getOtherElements();
        if (null != otherMap && !otherMap.isEmpty()) {
            sortedMap.putAll(otherMap);
        }
        return generateSign(sortedMap, mchKey);
    }

    /**
     * 微信支付-生成签名.
     *
     * @param sortedMap 要签名的数据对象.
     * @param mchKey mchKey
     *
     * @return 返回签名 String
     */
    @NotNull
    public static String generateSign(
        @NotNull final Map<String, String> sortedMap, @NotNull final String mchKey) {

        final StringBuilder sb = new StringBuilder();

        for (final Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        sb.append("mchKey").append('=').append(mchKey);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase(Locale.ENGLISH);
    }

    /**
     * 校验响应信息是否为成功.
     *
     * @param response BasePayResponse
     *
     * @throws WeChatPayException 没有响应信息, 响应信息标示不成功时抛出此异常.
     */
    public static void checkSuccessful(@NotNull final BasePayResponse response) {
        if (!BasePayResponse.SUCCESS.equals(response.getReturnCode())) {
            throw new WeChatPayException("WeChat pay response 'return_code' is '" + response.getReturnCode()
                + "', response:" + response.toString());
        }
        if (!BasePayResponse.SUCCESS.equals(response.getResultCode())) {
            throw new WeChatPayException("WeChat pay response 'result_code' is '" + response.getResultCode()
                + "', response:" + response.toString());
        }
    }

    /**
     * 校验响应信息的签名.
     *
     * @param response BasePayResponse
     *
     * @throws WeChatPayException 签名错误时抛出此异常.
     */
    public static void checkSign(@NotNull final BasePayResponse response, @NotNull final String mchKey) {
        if (!response.getSign().equals(WeChatPayUtils.generateSign(response, mchKey))) {
            throw new WeChatPayException("WeChat pay response 'sign' error, response:" + response.toString());
        }
    }

    /**
     * 判断响应信息是否为成功.
     *
     * @param response BasePayResponse
     *
     * @return 有响应信息, 并且完全成功返回 true
     */
    public static boolean isSuccessful(@NotNull final BasePayResponse response, @NotNull final String mchKey) {

        return response.getSign().equals(WeChatPayUtils.generateSign(response, mchKey))
            && (BasePayResponse.SUCCESS.equals(response.getReturnCode()))
            && (BasePayResponse.SUCCESS.equals(response.getResultCode()));
    }

    /**
     * 动态数据的映射转换.
     *
     * <p>针对如: coupon_id_$n, coupon_type_$n, coupon_fee_$n 等.
     *
     * <h3>样例:</h3>
     * <pre>
     * final Map&lt;String, BiConsumer&lt;String, Coupon&gt;&gt; mappingMap = new HashMap&lt;&gt;();
     * mappingMap.put("coupon_id_", (val, coupon) -> coupon.setId(val));
     * mappingMap.put("coupon_type_", (val, coupon) -> coupon.setType(val));
     * mappingMap.put("coupon_fee_", (val, coupon) -> coupon.setFee(Integer.valueOf(val)));
     * WeChatPayUtils.dynamicMapping(this.otherElements, mappingMap, Coupon::new);
     * </pre>
     *
     * @param extraSignParams 已存放的动态数据
     * @param mappingMap 转换函数的Map, 每一个 entry 的 key 为不带数字部分的前缀, 如 'coupon_id_'.
     *     value 为转换函数 BiConsumer&lt;V, T&gt; V 为 otherElements 的 value.
     * @param newT 新对象的创建函数
     * @param <T> 要转换的目标对象的类型
     *
     * @return 转换后的 Map, key 为 末尾数字, value 为转换后的对象.
     */
    public static <T> Map<String, T> dynamicMapping(
        @NotNull final Map<String, String> extraSignParams,
        @NotNull final Map<String, BiConsumer<String, T>> mappingMap,
        @NotNull final Supplier<T> newT) {

        final Map<String, T> rtMap = new TreeMap<>();
        for (final Map.Entry<String, String> entry : extraSignParams.entrySet()) {

            final String key = entry.getKey();
            final String value = entry.getValue();
            if (null == value || value.isEmpty()) {
                continue;
            }
            for (final Map.Entry<String, BiConsumer<String, T>> mappingEntry : mappingMap.entrySet()) {
                final String keyStart = mappingEntry.getKey();
                if (key.matches(keyStart + "\\d+")) {
                    final String rtKey = key.substring(keyStart.length());
                    final T t = rtMap.computeIfAbsent(rtKey, k -> newT.get());
                    mappingEntry.getValue().accept(value, t);
                }
            }

        }

        return rtMap;
    }

    /**
     * 提取转换代金券信息.
     *
     * @param extraSignParams otherElements
     *
     * @return <code>Map&lt;String, Coupon&gt;</code>
     */
    public static Map<String, Coupon> couponsFrom(final Map<String, String> extraSignParams) {
        final Map<String, BiConsumer<String, Coupon>> mappingMap = new HashMap<>(3);
        mappingMap.put("coupon_id_", (val, coupon) -> coupon.setId(val));
        mappingMap.put("coupon_type_", (val, coupon) -> coupon.setType(Coupon.Type.valueOf(val)));
        mappingMap.put("coupon_fee_", (val, coupon) -> coupon.setFee(Integer.valueOf(val)));

        return WeChatPayUtils.dynamicMapping(extraSignParams, Collections.unmodifiableMap(mappingMap), Coupon::new);
    }

    private static String asString(final Field field, final Object obj) {
        try {
            field.setAccessible(true);
            final Object val = field.get(obj);
            return (null == val) ? null : val.toString();
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> signParamsFrom(final Object obj) {
        final Class<?> clazz = obj.getClass();
        final List<Field> fields = CACHE_FOR_SIGN.computeIfAbsent(
            clazz,
            clazz0 -> FieldUtils.getFieldsListWithAnnotation(clazz0, XmlElement.class));

        Validate.notEmpty(fields);

        final Map<String, String> sortedMap = new TreeMap<>();
        for (final Field field : fields) {
            if (null != field.getAnnotation(SignIgnore.class)) {
                continue;
            }
            final String val = asString(field, obj);
            if (null == val || val.isEmpty()) {
                continue;
            }
            final String name = field.getAnnotation(XmlElement.class).name();
            sortedMap.put(name, val);
        }
        return sortedMap;
    }
}
