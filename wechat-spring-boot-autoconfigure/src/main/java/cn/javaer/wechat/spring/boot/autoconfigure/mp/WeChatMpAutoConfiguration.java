/*
 * Copyright (c) 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.javaer.wechat.spring.boot.autoconfigure.mp;

import cn.javaer.wechat.sdk.mp.WeChatMpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 微信公众号自动配置.
 *
 * @author zhangpeng
 */
@Configuration
@ConditionalOnClass(name = "cn.javaer.wechat.spring.boot.starter.mp.ConditionalOnClassTrigger")
@ConditionalOnWebApplication
@AutoConfigureAfter({WebClientAutoConfiguration.class})
@EnableConfigurationProperties(WeChatMpProperties.class)
public class WeChatMpAutoConfiguration {
    private final WeChatMpProperties weChatMpProperties;

    public WeChatMpAutoConfiguration(final WeChatMpProperties weChatMpProperties) {
        this.weChatMpProperties = weChatMpProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public WeChatMpController weChatMpController(final WeChatMpClient weChatMpClient,
                                                 final ApplicationEventPublisher publisher) {
        return new WeChatMpController(this.weChatMpProperties, weChatMpClient, publisher);
    }

    /**
     * weChatMpClient.
     *
     * @param restTemplateBuilder the rest template builder
     *
     * @return the WeChatMpClient
     */
    @Bean
    @ConditionalOnMissingBean
    public WeChatMpClient weChatMpClient(
        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
        @Autowired(required = false) final RestTemplateBuilder restTemplateBuilder) {

        if (null == restTemplateBuilder) {
            return new WeChatMpRestTemplateClient(new RestTemplate(), this.weChatMpProperties);
        }
        return new WeChatMpRestTemplateClient(restTemplateBuilder.build(), this.weChatMpProperties);
    }

}
