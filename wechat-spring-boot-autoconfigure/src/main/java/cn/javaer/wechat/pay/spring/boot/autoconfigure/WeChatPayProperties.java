package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangpeng
 */
@Data
@ConfigurationProperties(prefix = "wechat.pay")
public class WeChatPayProperties
{
    private String appId;
    private String mchId;
    private String mchKey;
    private String notifyUrl;
    private String clientIp;
}
