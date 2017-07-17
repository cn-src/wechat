package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.SignUtil;
import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.WeChatPayApiNotifyResult;
import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.WeChatPayApiService;
import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.WeChatPayApiUnifiedOrderRequest;
import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.WeChatPayApiUnifiedOrderResponse;
import jodd.bean.BeanCopy;
import org.springframework.validation.annotation.Validated;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author zhangpeng
 */
@Validated
public class DefaultWeChatPayService implements WeChatPayService
{
    private final WeChatPayProperties weChatPayProperties;
    private final WeChatPayApiService weChatPayApiService;
    
    public DefaultWeChatPayService(final WeChatPayProperties weChatPayProperties,
        final WeChatPayApiService weChatPayApiService)
    {
        this.weChatPayProperties = weChatPayProperties;
        this.weChatPayApiService = weChatPayApiService;
    }
    
    @Override
    public UnifiedOrderResponse unifiedOrder(final UnifiedOrderRequest unifiedOrderRequest)
    {
        final WeChatPayApiUnifiedOrderRequest request = WeChatPayApiUnifiedOrderRequest.builder()
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
        final Call<WeChatPayApiUnifiedOrderResponse> responseCall = this.weChatPayApiService.unifiedOrder(request);
        try
        {
            final Response<WeChatPayApiUnifiedOrderResponse> response = responseCall.execute();
            if (response.isSuccessful())
            {
                final WeChatPayApiUnifiedOrderResponse successfulBody = response.body();
                if (null == successfulBody)
                {
                    throw new WeChatPayException(String.format("Call unifiedOrder successful body is null, orderId:%s",
                                                               unifiedOrderRequest.getOrderId()));
                }
                if (!successfulBody.getSign().equals(SignUtil.sign(successfulBody, this.weChatPayProperties.getMchKey())))
                {
                    throw new WeChatPayException(String.format("Call unifiedOrder result sign error, orderId: %s, err_code: %s, err_code_des: %s",
                                                               unifiedOrderRequest.getOrderId(),
                                                               successfulBody.getErrCode(),
                                                               successfulBody.getErrCodeDes()));
                }
                if ("SUCCESS".equals(successfulBody.getReturnCode()))
                {
                    if ("SUCCESS".equals(successfulBody.getResultCode()))
                    {
                        final UnifiedOrderResponse unifiedOrderResponse = new UnifiedOrderResponse();
                        unifiedOrderResponse.setCodeUrl(successfulBody.getCodeUrl());
                        unifiedOrderResponse.setNonceStr(successfulBody.getNonceStr());
                        unifiedOrderResponse.setPrepayId(successfulBody.getPrepayId());
                        return unifiedOrderResponse;
                    } else
                    {
                        throw new WeChatPayException(String.format("Call unifiedOrder result error, orderId: %s, err_code: %s, err_code_des: %s",
                                                                   unifiedOrderRequest.getOrderId(),
                                                                   successfulBody.getErrCode(),
                                                                   successfulBody.getErrCodeDes()));
                    }
                    
                } else
                {
                    throw new WeChatPayException(String.format("Call unifiedOrder return error, orderId: %s, return_code: %s, return_msg: %s",
                                                               unifiedOrderRequest.getOrderId(),
                                                               successfulBody.getReturnCode(),
                                                               successfulBody.getReturnMsg()));
                }
            } else
            {
                throw new WeChatPayException(String.format("Request error, orderId: %s, code:%d, errorBody:%s, message:%s",
                                                           unifiedOrderRequest.getOrderId(),
                                                           response.code(),
                                                           response.errorBody(),
                                                           response.message()));
                
            }
        } catch (final IOException e)
        {
            throw new WeChatPayException(e);
        }
    }
    
    @Override
    public NotifyResult notifyResult(final WeChatPayApiNotifyResult apiNotifyResult)
    {
        final NotifyResult notifyResult = new NotifyResult();
        notifyResult.setHasSuccessful(false);
        
        if (null == apiNotifyResult)
        {
            notifyResult.setMessage("NOTIFY_RESULT_EMPTY");
            return notifyResult;
        }
        
        final String sign = SignUtil.sign(apiNotifyResult, weChatPayProperties.getMchKey());
        if (!sign.equals(apiNotifyResult.getSign()))
        {
            notifyResult.setMessage("SIGN_ERROR");
            return notifyResult;
        }
        
        BeanCopy.beans(apiNotifyResult, notifyResult).copy();
        
        if ("SUCCESS".equals(apiNotifyResult.getReturnCode()))
        {
            if ("SUCCESS".equals(apiNotifyResult.getReturnCode()))
            {
                notifyResult.setHasSuccessful(true);
            } else
            {
                // noting
            }
        } else
        {
            notifyResult.setMessage(apiNotifyResult.getReturnMsg());
        }
        return notifyResult;
    }
}
