package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayClientFactory;
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
    public WeChatPayClient weChatPayClient()
    {
        return new WeChatPayClientFactory().create();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public WeChatPayService weChatPayService(final WeChatPayClient weChatPayClient)
    {
        return new DefaultWeChatPayService(this.weChatPayProperties, weChatPayClient);
    }
}
