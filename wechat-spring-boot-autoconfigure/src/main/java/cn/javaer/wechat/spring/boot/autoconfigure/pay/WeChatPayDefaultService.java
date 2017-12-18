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

package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayException;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
import cn.javaer.wechat.spring.boot.autoconfigure.pay.event.WeChatPayUnifiedOrderEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 微信支付服务.
 *
 * @author zhangpeng
 */
public class WeChatPayDefaultService implements WeChatPayService {

    private final WeChatPayClient weChatPayClient;

    private final ApplicationEventPublisher publisher;

    /**
     * 创建 WeChatPayDefaultService.
     *
     * @param weChatPayClient WeChatPayClient
     * @param publisher ApplicationEventPublisher
     */
    public WeChatPayDefaultService(
            @NotNull final WeChatPayClient weChatPayClient,
            @NotNull final ApplicationEventPublisher publisher) {
        this.weChatPayClient = weChatPayClient;
        this.publisher = publisher;
    }

    @Override
    public WeChatPayUnifiedOrderWithNativeResult unifiedOrderWithNative(
            @NotNull final String body,
            @NotNull final String outTradeNo,
            @NotNull final Integer totalFee) throws WeChatPayException {

        final WeChatPayUnifiedOrderRequest request
                = WeChatPayUnifiedOrderRequest.createWithNative(body, outTradeNo, totalFee);

        final WeChatPayUnifiedOrderResponse responseBody = requestAndPublishEvent(request);

        return WeChatPayUnifiedOrderWithNativeResult.createWith(responseBody);
    }

    @Override
    public void unifiedOrderWithJsApi(
            @NotNull final String openid,
            @NotNull final String body,
            @NotNull final String outTradeNo,
            @NotNull final Integer totalFee) throws WeChatPayException {

        final WeChatPayUnifiedOrderRequest request
                = WeChatPayUnifiedOrderRequest.createWithJsApi(openid, body, outTradeNo, totalFee);

        requestAndPublishEvent(request);
    }

    private WeChatPayUnifiedOrderResponse requestAndPublishEvent(final WeChatPayUnifiedOrderRequest request) {

        final WeChatPayUnifiedOrderResponse responseBody
                = this.weChatPayClient.unifiedOrder(request);

        this.publisher.publishEvent(new WeChatPayUnifiedOrderEvent(responseBody));

        responseBody.checkSignAndSuccessful();

        return responseBody;
    }
}
