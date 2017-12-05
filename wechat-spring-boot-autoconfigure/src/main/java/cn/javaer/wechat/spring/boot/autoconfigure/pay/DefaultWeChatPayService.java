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
import io.vavr.control.Try;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Response;

/**
 * 微信支付服务
 *
 * @author zhangpeng
 */
public class DefaultWeChatPayService implements WeChatPayService {
    private final WeChatPayProperties weChatPayProperties;
    private final WeChatPayClient weChatPayClient;

    public DefaultWeChatPayService(
            @NotNull WeChatPayProperties weChatPayProperties,
            @NotNull WeChatPayClient weChatPayClient) {
        this.weChatPayProperties = weChatPayProperties;
        this.weChatPayClient = weChatPayClient;
    }

    /**
     * @param request 微信统一下单 request
     * @return UnifiedOrderWithNativeResponse
     * @throws WeChatPayException WeChatPayException
     */
    public WeChatPayUnifiedOrderResponse unifiedOrder(
            @NotNull WeChatPayUnifiedOrderRequest request) throws WeChatPayException {

        request.setAppid(weChatPayProperties.getAppId());
        request.setMchId(weChatPayProperties.getMchId());
        request.setSpbillCreateIp(weChatPayProperties.getClientIp());

        WeChatPayUtils.checkAndSignRequest(request, weChatPayProperties.getMchKey());

        Call<WeChatPayUnifiedOrderResponse> responseCall = weChatPayClient.unifiedOrder(request);
        Response<WeChatPayUnifiedOrderResponse> response = Try.of(responseCall::execute).getOrElseThrow(WeChatPayException::new);
        WeChatPayUtils.checkResponse(response);
        WeChatPayUnifiedOrderResponse successfulBody = response.body();
        WeChatPayUtils.checkResponseBody(successfulBody, weChatPayProperties.getMchKey());

        return successfulBody;
    }

    /**
     * 微信统一下单 NATIVE 类型
     */
    @Override
    public UnifiedOrderWithNativeResponse unifiedOrderWithNative(
            @NotNull String body,
            @NotNull String outTradeNo,
            @NotNull Integer totalFee,
            @NotNull String productId) throws WeChatPayException {
        String notifyUrl = WeChatUtils.joinPath(weChatPayProperties.getNotifyAddress(), weChatPayProperties.getNotifyResultPath());

        WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder()
                .body(body)
                .nonceStr(WeChatUtils.uuid())
                .outTradeNo(outTradeNo)
                .productId(productId)
                .totalFee(totalFee)
                .notifyUrl(notifyUrl)
                .tradeType("NATIVE")
                .build();

        WeChatPayUnifiedOrderResponse weChatPayUnifiedOrderResponse = unifiedOrder(request);

        UnifiedOrderWithNativeResponse unifiedOrderWithNativeResponse = new UnifiedOrderWithNativeResponse();
        unifiedOrderWithNativeResponse.setCodeUrl(weChatPayUnifiedOrderResponse.getCodeUrl());
        unifiedOrderWithNativeResponse.setPrepayId(weChatPayUnifiedOrderResponse.getPrepayId());
        return unifiedOrderWithNativeResponse;
    }
}
