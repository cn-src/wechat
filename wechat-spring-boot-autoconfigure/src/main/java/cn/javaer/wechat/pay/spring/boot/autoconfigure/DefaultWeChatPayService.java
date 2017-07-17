package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import cn.javaer.wechat.sdk.pay.AbstractWeChatPayResponse;
import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayException;
import cn.javaer.wechat.sdk.pay.WeChatPayNotifyResult;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;
import cn.javaer.wechat.sdk.util.SignUtil;
import io.vavr.control.Try;
import jodd.bean.BeanCopy;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author zhangpeng
 */
public class DefaultWeChatPayService implements WeChatPayService
{
    private final WeChatPayProperties weChatPayProperties;
    private final WeChatPayClient     weChatPayApiService;
    
    public DefaultWeChatPayService(final WeChatPayProperties weChatPayProperties,
        final WeChatPayClient weChatPayApiService)
    {
        this.weChatPayProperties = weChatPayProperties;
        this.weChatPayApiService = weChatPayApiService;
    }
    
    @Override
    public UnifiedOrderResponse unifiedOrder(final UnifiedOrderRequest unifiedOrderRequest)
    {
        final WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder()
            .body(unifiedOrderRequest.getGoodsName())
            .nonceStr(SignUtil.uuid())
            .outTradeNo(unifiedOrderRequest.getOrderId())
            .productId(unifiedOrderRequest.getProductId())
            .totalFee(unifiedOrderRequest.getTotalFee())
            .appId(this.weChatPayProperties.getAppId())
            .mchId(this.weChatPayProperties.getMchId())
            .notifyUrl(this.weChatPayProperties.getNotifyUrl())
            .spbillCreateIp(this.weChatPayProperties.getClientIp())
            .tradeType("NATIVE")
            .build();
        request.setSign(SignUtil.sign(request, this.weChatPayProperties.getMchKey()));
        
        final Call<WeChatPayUnifiedOrderResponse> responseCall = this.weChatPayApiService.unifiedOrder(request);
        final Response<WeChatPayUnifiedOrderResponse> response = Try.of(responseCall::execute).getOrElseThrow(WeChatPayException::new);
        checkResponse(response);
        final WeChatPayUnifiedOrderResponse successfulBody = response.body();
        checkResponseBody(successfulBody);
        
        final UnifiedOrderResponse unifiedOrderResponse = new UnifiedOrderResponse();
        unifiedOrderResponse.setCodeUrl(successfulBody.getCodeUrl());
        unifiedOrderResponse.setNonceStr(successfulBody.getNonceStr());
        unifiedOrderResponse.setPrepayId(successfulBody.getPrepayId());
        return unifiedOrderResponse;
    }
    
    @Override
    public NotifyResult notifyResult(final WeChatPayNotifyResult apiNotifyResult)
    {
        checkResponseBody(apiNotifyResult);
        final NotifyResult notifyResult = new NotifyResult();
        BeanCopy.beans(apiNotifyResult, notifyResult).copy();
        return notifyResult;
    }
    
    private void checkResponse(final Response response)
    {
        if (!response.isSuccessful())
        {
            throw new WeChatPayException("Response error, response:" + response.toString());
        }
    }
    
    private void checkResponseBody(final AbstractWeChatPayResponse response)
    {
        if (null == response)
        {
            throw new WeChatPayException("WeChat pay response is null");
        }
        if (!response.getSign().equals(SignUtil.sign(response, this.weChatPayProperties.getMchKey())))
        {
            throw new WeChatPayException("WeChat pay response 'sign' error");
        }
        if (!"SUCCESS".equals(response.getReturnCode()))
        {
            throw new WeChatPayException("WeChat pay response error, response:" + response.toString());
        }
        if (!"SUCCESS".equals(response.getResultCode()))
        {
            throw new WeChatPayException("WeChat pay response error, response:" + response.toString());
        }
    }
}
