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
import cn.javaer.wechat.sdk.mp.WeChatMpUtils;
import cn.javaer.wechat.sdk.mp.model.AuthorizeScope;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 微信公众号 Controller.
 *
 * @author zhangpeng
 */
@RestController
public class WeChatMpController {
    private final WeChatMpProperties weChatMpProperties;
    private final WeChatMpClient weChatMpClient;
    private final ApplicationEventPublisher publisher;

    /**
     * 微信公众号 Controller.
     *
     * @param weChatMpProperties weChatMpProperties
     * @param weChatMpClient weChatMpClient
     * @param publisher publisher
     */
    public WeChatMpController(
        @NotNull final WeChatMpProperties weChatMpProperties,
        @NotNull final WeChatMpClient weChatMpClient,
        @NotNull final ApplicationEventPublisher publisher) {
        this.weChatMpProperties = weChatMpProperties;
        this.weChatMpClient = weChatMpClient;
        this.publisher = publisher;
    }

    /**
     * 重定向到微信授权地址.
     *
     * @param redirect 应用自身回调地址
     */
    @GetMapping(path = "${wechat.mp.authorize-path:" + WeChatMpProperties.AUTHORIZE_PATH + "}")
    public RedirectView authorize(@RequestParam("redirect") final String redirect) {
        final String redirectUri = WeChatUtils.joinPath(this.weChatMpProperties.getNotifyAddress(), redirect);
        return new RedirectView(WeChatMpUtils.generateAuthorizeUrl(
            this.weChatMpProperties.getAppid(), redirectUri, AuthorizeScope.BASE));
    }

    /**
     * 微信回调接口, 提供给微信在授权之后的重定向.
     */
    @GetMapping(path = "${wechat.mp.authorize-code-path:" + WeChatMpProperties.AUTHORIZE_CODE_PATH + '}')
    public void authorizeCode(
        @RequestParam("code") final String code) {

    }
}
