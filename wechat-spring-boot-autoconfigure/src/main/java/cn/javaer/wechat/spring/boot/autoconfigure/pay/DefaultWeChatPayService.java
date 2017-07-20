/*
 *    Copyright 2017 zhangpeng
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
 * @author zhangpeng
 */
public class DefaultWeChatPayService implements WeChatPayService
{
    private final WeChatPayProperties weChatPayProperties;
    private final WeChatPayClient     weChatPayClient;
    
    @SuppressWarnings("WeakerAccess")
    public DefaultWeChatPayService(
        @NotNull final WeChatPayProperties weChatPayProperties,
        @NotNull final WeChatPayClient weChatPayClient)
    {
        this.weChatPayProperties = weChatPayProperties;
        this.weChatPayClient = weChatPayClient;
    }
    
    @Override
    public ScanQrCodePayTwoUnifiedOrderResponse unifiedOrder(
        @NotNull final ScanQrCodePayTwoUnifiedOrderRequest unifiedOrderRequest) throws WeChatPayException
    {
        final WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder()
            .body(unifiedOrderRequest.getBody())
            .nonceStr(WeChatUtils.uuid())
            .outTradeNo(unifiedOrderRequest.getOutTradeNo())
            .productId(unifiedOrderRequest.getProductId())
            .totalFee(unifiedOrderRequest.getTotalFee())
            .appId(this.weChatPayProperties.getAppId())
            .mchId(this.weChatPayProperties.getMchId())
            .notifyUrl(this.weChatPayProperties.getNotifyUrl())
            .spbillCreateIp(this.weChatPayProperties.getClientIp())
            .tradeType("NATIVE")
            .build();
        WeChatPayUtils.checkAndSignRequest(request, this.weChatPayProperties.getMchKey());
        
        final Call<WeChatPayUnifiedOrderResponse> responseCall = this.weChatPayClient.unifiedOrder(request);
        final Response<WeChatPayUnifiedOrderResponse> response = Try.of(responseCall::execute).getOrElseThrow(WeChatPayException::new);
        WeChatPayUtils.checkResponse(response);
        final WeChatPayUnifiedOrderResponse successfulBody = response.body();
        WeChatPayUtils.checkResponseBody(successfulBody, this.weChatPayProperties.getMchKey());
        
        final ScanQrCodePayTwoUnifiedOrderResponse scanQrCodePayTwoUnifiedOrderResponse = new ScanQrCodePayTwoUnifiedOrderResponse();
        scanQrCodePayTwoUnifiedOrderResponse.setCodeUrl(successfulBody.getCodeUrl());
        scanQrCodePayTwoUnifiedOrderResponse.setNonceStr(successfulBody.getNonceStr());
        scanQrCodePayTwoUnifiedOrderResponse.setPrepayId(successfulBody.getPrepayId());
        return scanQrCodePayTwoUnifiedOrderResponse;
    }
}
