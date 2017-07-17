package cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk;

import cn.javaer.retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.Retrofit;

/**
 * @author zhangpeng
 */
public class WeChatPayApiServiceFactory
{
    public WeChatPayApiService create()
    {
        final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(WeChatPayApiService.baseUrl)
            .addConverterFactory(JaxbConverterFactory.create())
            .build();
        return retrofit.create(WeChatPayApiService.class);
    }
}
