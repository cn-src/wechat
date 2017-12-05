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

package cn.javaer.wechat.spring.boot.autoconfigure.mp;

import cn.javaer.wechat.sdk.mp.AuthorizeScope;
import cn.javaer.wechat.sdk.mp.WeChatMpClient;
import cn.javaer.wechat.sdk.mp.WeChatMpUtils;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author zhangpeng
 */
@RestController
public class WeChatMpController {
    private static final String AUTHORIZE_CODE_PATH = "/public/wechat/mp/authorize_code";
    private final WeChatMpProperties        weChatMpProperties;
    private final WeChatMpClient            weChatMpClient;
    private final ApplicationEventPublisher publisher;
    
    public WeChatMpController(
        @NotNull WeChatMpProperties weChatMpProperties,
        @NotNull WeChatMpClient weChatMpClient,
        @NotNull ApplicationEventPublisher publisher) {
        this.weChatMpProperties = weChatMpProperties;
        this.weChatMpClient = weChatMpClient;
        this.publisher = publisher;
    }
    
    /**
     * 重定向到微信授权地址。
     *
     * @param redirect 应用自身回调地址
     */
    @GetMapping(path = "${wechat.mp.access-authorize-path:/public/wechat/mp/access_authorize}")
    public RedirectView accessAuthorize(@RequestParam("redirect") String redirect) {
        // final String path = StringUtils.hasText(this.weChatMpProperties.getAuthorizeCodePath())
        //     ? this.weChatMpProperties.getAuthorizeCodePath()
        //     : AUTHORIZE_CODE_PATH;
        String redirectUri = WeChatUtils.joinPath(weChatMpProperties.getNotifyAddress(), redirect);
        return new RedirectView(WeChatMpUtils.generateAuthorizeUrl(weChatMpProperties.getAppId(), redirectUri, AuthorizeScope.BASE));
    }
    
    /**
     * 微信回调接口，提供给微信在授权之后的重定向
     *
     * @return 重定向到指定地址, {@link #accessAuthorize(String)}中指定的 redirect
     */
    @GetMapping(path = "${wechat.mp.authorize-code-path:" + AUTHORIZE_CODE_PATH + '}')
    public RedirectView authorizeCode(
        @RequestParam("code") String code,
        @RequestParam("redirect") String redirect) {
        //TODO
//        Call<WeChatMpAccessTokenResponse> responseCall
//            = weChatMpClient.snsOauth2AccessToken(
//            weChatMpProperties.getAppId(), weChatMpProperties.getAppSecret(), code, "authorization_code");
//
//        Response<WeChatMpAccessTokenResponse> response = Try.of(responseCall::execute).getOrElseThrow(WeChatMpException::new);
//        WeChatMpUtils.checkResponse(response);
//        WeChatMpAccessTokenResponse body = response.body();
//        WeChatMpUtils.checkResponseBody(body);
//
//        WeChatMpAuthenticationSuccessEvent successEvent = new WeChatMpAuthenticationSuccessEvent(body);
//        successEvent.setRedirect(redirect);
//        publisher.publishEvent(successEvent);
        return new RedirectView(redirect);
    }
}
