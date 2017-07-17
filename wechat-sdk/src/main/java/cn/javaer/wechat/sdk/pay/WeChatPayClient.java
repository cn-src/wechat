package cn.javaer.wechat.sdk.pay;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WeChatPayClient
{
    String baseUrl = "https://api.mch.weixin.qq.com";
    
    @POST("/pay/unifiedorder")
    Call<WeChatPayUnifiedOrderResponse> unifiedOrder(@Body WeChatPayUnifiedOrderRequest request);
}