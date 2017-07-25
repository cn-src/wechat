/*
 *    Copyright 2017 zhangpeng
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

package cn.javaer.wechat.spring.boot.autoconfigure.mp;

import cn.javaer.wechat.sdk.mp.AuthorizeScope;
import cn.javaer.wechat.sdk.mp.WeChatMpAccessTokenResponse;
import cn.javaer.wechat.sdk.mp.WeChatMpClient;
import cn.javaer.wechat.sdk.mp.WeChatMpException;
import cn.javaer.wechat.sdk.mp.WeChatMpUtils;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import io.vavr.control.Try;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author zhangpeng
 */
@RestController
public class WeChatMpController
{
    private final WeChatMpProperties        weChatMpProperties;
    private final WeChatMpClient            weChatMpClient;
    private final ApplicationEventPublisher publisher;
    
    @SuppressWarnings("WeakerAccess")
    public WeChatMpController(
        @NotNull final WeChatMpProperties weChatMpProperties,
        @NotNull final WeChatMpClient weChatMpClient,
        @NotNull final ApplicationEventPublisher publisher)
    {
        this.weChatMpProperties = weChatMpProperties;
        this.weChatMpClient = weChatMpClient;
        this.publisher = publisher;
    }
    
    /**
     * 生成授权引导url。
     */
    @GetMapping(path = "${wechat.mp.generate-authorize-url-path:/public/wechat/mp/generate_authorize_url}")
    public String generateAuthorizeUrl()
    {
        final String path = StringUtils.hasText(this.weChatMpProperties.getAuthorizeCodePath())
            ? this.weChatMpProperties.getAuthorizeCodePath()
            : "/public/wechat/mp/authorize_code";
        final String redirectUri = WeChatUtils.joinPath(this.weChatMpProperties.getNotifyAddress(), path);
        return WeChatMpUtils.generateAuthorizeUrl(this.weChatMpProperties.getAppId(), redirectUri, AuthorizeScope.BASE);
    }
    
    @GetMapping(path = "${wechat.mp.authorize-code-path:/public/wechat/mp/authorize_code}")
    public RedirectView authorizeCode(
        @RequestParam("code") final String code,
        @RequestParam(value = "state", required = false) final String state)
    {
        final Call<WeChatMpAccessTokenResponse> responseCall
            = this.weChatMpClient.snsOauth2AccessToken(
            this.weChatMpProperties.getAppId(), this.weChatMpProperties.getAppSecret(), code, "authorization_code");
    
        final Response<WeChatMpAccessTokenResponse> response = Try.of(responseCall::execute).getOrElseThrow(WeChatMpException::new);
        WeChatMpUtils.checkResponse(response);
        final WeChatMpAccessTokenResponse body = response.body();
        WeChatMpUtils.checkResponseBody(body);
    
        this.publisher.publishEvent(new WeChatMpAuthenticationSuccessEvent(body));
        return new RedirectView(this.weChatMpProperties.getRedirectView());
    }
}
