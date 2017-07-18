package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangpeng
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "wechat.pay")
public class WeChatPayProperties
{
    private String appId;
    private String mchId;
    private String mchKey;
    private String notifyUrl;
    private String clientIp;
}
