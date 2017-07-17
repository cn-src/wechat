package cn.javaer.wechat.sdk.pay;

import cn.javaer.retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.Retrofit;

/**
 * @author zhangpeng
 */
public class WeChatPayClientFactory
{
    public WeChatPayClient create()
    {
        final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(WeChatPayClient.baseUrl)
            .addConverterFactory(JaxbConverterFactory.create())
            .build();
        return retrofit.create(WeChatPayClient.class);
    }
}
