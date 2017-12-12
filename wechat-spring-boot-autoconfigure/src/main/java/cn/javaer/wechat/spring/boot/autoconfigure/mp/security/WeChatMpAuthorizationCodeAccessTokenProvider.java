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

package cn.javaer.wechat.spring.boot.autoconfigure.mp.security;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserApprovalRequiredException;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;
import java.util.Map;

/**
 * 公众号认证令牌提供者.
 *
 * @author zhangpeng
 */
public class WeChatMpAuthorizationCodeAccessTokenProvider extends AuthorizationCodeAccessTokenProvider {

    /**
     * 构造公众号认证令牌提供者.
     *
     * @param messageConverters messageConverters
     */
    public WeChatMpAuthorizationCodeAccessTokenProvider(final List<HttpMessageConverter<?>> messageConverters) {
        setMessageConverters(messageConverters);
        setTokenRequestEnhancer((request, resource, form, headers) -> {
            final String clientId = form.getFirst("client_id");
            final String clientSecret = form.getFirst("client_secret");
            form.set("appid", clientId);
            form.set("secret", clientSecret);
            form.remove("client_id");
            form.remove("client_secret");
        });
    }

    @Override
    public OAuth2AccessToken obtainAccessToken(final OAuth2ProtectedResourceDetails details,
                                               final AccessTokenRequest request)
            throws UserRedirectRequiredException, UserApprovalRequiredException,
            AccessDeniedException, OAuth2AccessDeniedException {
        try {
            return super.obtainAccessToken(details, request);
        } catch (final UserRedirectRequiredException e) {
            final Map<String, String> params = e.getRequestParams();
            final String clientId = params.get("client_id");
            params.put("appid", clientId);
            params.remove("client_id");
            throw e;
        }
    }
}
