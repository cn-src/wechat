package cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WeChatPayApiService
{
    String baseUrl = "https://api.mch.weixin.qq.com";
    
    @POST("/pay/unifiedorder")
    Call<WeChatPayApiUnifiedOrderResponse> unifiedOrder(@Body WeChatPayApiUnifiedOrderRequest request);
}