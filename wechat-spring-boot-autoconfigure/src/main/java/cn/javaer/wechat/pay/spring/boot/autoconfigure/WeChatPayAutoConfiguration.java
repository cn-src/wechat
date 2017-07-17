package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.WeChatPayApiService;
import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.WeChatPayApiServiceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangpeng
 */
@Configuration
@EnableConfigurationProperties(WeChatPayProperties.class)
public class WeChatPayAutoConfiguration
{
    private final WeChatPayProperties weChatPayProperties;
    
    public WeChatPayAutoConfiguration(final WeChatPayProperties weChatPayProperties)
    {
        this.weChatPayProperties = weChatPayProperties;
    }
    
    @Bean
    @ConditionalOnMissingBean
    
    public WeChatPayApiService wxpayApiService()
    {
        return new WeChatPayApiServiceFactory().create();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public WeChatPayService wxpayService(final WeChatPayApiService weChatPayApiService)
    {
        return new DefaultWeChatPayService(this.weChatPayProperties, weChatPayApiService);
    }
}
