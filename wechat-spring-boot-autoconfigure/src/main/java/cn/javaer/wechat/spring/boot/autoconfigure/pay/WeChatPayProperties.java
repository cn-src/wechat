/*
 *    Copyright 2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

/**
 * 微信支付-配置项
 *
 * @author zhangpeng
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "wechat.pay")
public class WeChatPayProperties implements EnvironmentAware, InitializingBean {
    private Environment environment;


    /**
     * 公众号的唯一标识
     */
    @NonNull
    private String appId;

    /**
     * 商户号
     */
    @NonNull
    private String mchId;

    /**
     * 商户的key，需要去微信支付平台设置
     */
    @NonNull
    private String mchKey;

    /**
     * 接收微信异步通知消息的地址
     * <p>
     * 如：http://www.demo.com:8080
     */
    @NonNull
    private String notifyAddress;

    /**
     * 服务器的公网ip
     */
    @NonNull
    private String clientIp;

    /**
     * 接收支付结果通知 Controller 的 path
     */
    private String notifyResultPath = "/public/wechat/pay/notify_result";

    private String apiBasePath = "https://api.mch.weixin.qq.com";

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        final String mpAppId = environment.getProperty("wechat.mp.appId");
        if (!StringUtils.hasText(this.appId) && StringUtils.hasText(mpAppId)) {
            this.appId = mpAppId;
        }

        final String mpNotifyAddress = environment.getProperty("wechat.mp.notifyAddress");
        if (!StringUtils.hasText(this.notifyAddress) && StringUtils.hasText(mpNotifyAddress)) {
            this.notifyAddress = mpNotifyAddress;
        }
    }
}
