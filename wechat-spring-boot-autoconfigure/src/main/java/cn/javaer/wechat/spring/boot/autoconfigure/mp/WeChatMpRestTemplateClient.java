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
import cn.javaer.wechat.sdk.mp.model.WeChatMpAccessTokenResponse;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 微信支付客户端-RestTemplate实现.
 *
 * @author zhangpeng
 */
public class WeChatMpRestTemplateClient implements WeChatMpClient {

    private final RestTemplate restTemplate;

    private final WeChatMpProperties weChatMpProperties;

    /**
     * Instantiates a new WeChatPayRestTemplateClient.
     *
     * @param restTemplate RestTemplate
     * @param weChatMpProperties WeChatPayProperties
     */
    public WeChatMpRestTemplateClient(
        final RestTemplate restTemplate, final WeChatMpProperties weChatMpProperties) {
        Objects.requireNonNull(restTemplate);
        Objects.requireNonNull(weChatMpProperties);

        this.restTemplate = restTemplate;
        this.weChatMpProperties = weChatMpProperties;
    }

    @Override
    public WeChatMpAccessTokenResponse accessToken(final String code) {
        final Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", this.weChatMpProperties.getAppid());
        paramMap.put("secret", this.weChatMpProperties.getSecret());
        paramMap.put("code", code);
        paramMap.put("grant_type", "authorization_code");
        final String url = WeChatUtils.joinPath(this.weChatMpProperties.getApiBasePath(),
            WeChatMpClient.ACCESS_TOKEN_PATH);
        return this.restTemplate.getForObject(url, WeChatMpAccessTokenResponse.class, paramMap);
    }
}
