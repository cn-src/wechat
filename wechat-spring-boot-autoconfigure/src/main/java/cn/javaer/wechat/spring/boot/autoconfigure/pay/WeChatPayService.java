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

package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayException;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;
import org.jetbrains.annotations.NotNull;

/**
 * 微信支付服务
 *
 * @author zhangpeng
 */
public interface WeChatPayService {

    /**
     * 微信统一下单，除配置参数外，其它参数自行决定使用。
     */
    WeChatPayUnifiedOrderResponse unifiedOrder(
            @NotNull WeChatPayUnifiedOrderRequest request) throws WeChatPayException;

    /**
     * 微信统一下单 NATIVE 类型简化。
     */
    UnifiedOrderWithNativeResponse unifiedOrderWithNative(@NotNull String body,
                                                          @NotNull String outTradeNo,
                                                          @NotNull Integer totalFee,
                                                          @NotNull String productId) throws WeChatPayException;

    /**
     * 微信统一下单 JSAPI 类型简化。
     */
    UnifiedOrderWithJsApiResponse unifiedOrderWithJsApi(
            @NotNull String openid,
            @NotNull String body,
            @NotNull String outTradeNo,
            @NotNull Integer totalFee,
            @NotNull String productId) throws WeChatPayException;
}
