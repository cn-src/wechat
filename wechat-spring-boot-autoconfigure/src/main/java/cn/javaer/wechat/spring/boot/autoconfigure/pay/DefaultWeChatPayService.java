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

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayException;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import cn.javaer.wechat.spring.boot.autoconfigure.pay.event.WeChatPayUnifiedOrderEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 微信支付服务
 *
 * @author zhangpeng
 */
public class DefaultWeChatPayService implements WeChatPayService {
    private final WeChatPayProperties weChatPayProperties;
    private final WeChatPayClient weChatPayClient;

    private final ApplicationEventPublisher publisher;

    public DefaultWeChatPayService(
            @NotNull WeChatPayProperties weChatPayProperties,
            @NotNull WeChatPayClient weChatPayClient,
            @NotNull ApplicationEventPublisher publisher) {
        this.weChatPayProperties = weChatPayProperties;
        this.weChatPayClient = weChatPayClient;
        this.publisher = publisher;
    }

    @Override
    public WeChatPayUnifiedOrderResponse unifiedOrder(
            @NotNull WeChatPayUnifiedOrderRequest request) throws WeChatPayException {

        String notifyUrl = WeChatUtils.joinPath(weChatPayProperties.getNotifyAddress(), weChatPayProperties.getNotifyResultPath());

        request.setNotifyUrl(notifyUrl);
        request.setAppid(weChatPayProperties.getAppId());
        request.setMchId(weChatPayProperties.getMchId());
        request.setSpbillCreateIp(weChatPayProperties.getClientIp());

        WeChatPayUtils.checkAndSignRequest(request, weChatPayProperties.getMchKey());

        WeChatPayUnifiedOrderResponse responseBody = weChatPayClient.unifiedOrder(request);
        WeChatPayUtils.checkResponseBody(responseBody, weChatPayProperties.getMchKey());

        publisher.publishEvent(new WeChatPayUnifiedOrderEvent(responseBody));

        return responseBody;
    }


    @Override
    public UnifiedOrderWithNativeResponse unifiedOrderWithNative(
            @NotNull String body,
            @NotNull String outTradeNo,
            @NotNull Integer totalFee,
            @NotNull String productId) throws WeChatPayException {

        WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder()
                .body(body)
                .nonceStr(WeChatUtils.uuid())
                .outTradeNo(outTradeNo)
                .productId(productId)
                .totalFee(totalFee)
                .tradeType(WeChatPayUnifiedOrderRequest.TRADE_TYPE_NATIVE)
                .build();

        WeChatPayUnifiedOrderResponse weChatPayUnifiedOrderResponse = unifiedOrder(request);

        return UnifiedOrderWithNativeResponse.createWith(weChatPayUnifiedOrderResponse);
    }

    @Override
    public void unifiedOrderWithJsApi(
            @NotNull String openid,
            @NotNull String body,
            @NotNull String outTradeNo,
            @NotNull Integer totalFee,
            @NotNull String productId) throws WeChatPayException {

        WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder()
                .openid(openid)
                .body(body)
                .nonceStr(WeChatUtils.uuid())
                .outTradeNo(outTradeNo)
                .productId(productId)
                .totalFee(totalFee)
                .tradeType(WeChatPayUnifiedOrderRequest.TRADE_TYPE_JSAPI)
                .build();

        unifiedOrder(request);
    }
}
