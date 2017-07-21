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
import cn.javaer.wechat.sdk.mp.WeChatMpUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpeng
 */
@RestController
public class WeChatMpController
{
    private final WeChatMpProperties weChatMpProperties;
    
    @SuppressWarnings("WeakerAccess")
    public WeChatMpController(@NotNull final WeChatMpProperties weChatMpProperties)
    {
        this.weChatMpProperties = weChatMpProperties;
    }
    
    /**
     * 生成授权引导url。
     */
    @GetMapping(path = "${wechat.mp.generate-authorize-url:/public/wechat/mp/generate_authorize_url}")
    public String generateAuthorizeUrl()
    {
        return WeChatMpUtils.generateAuthorizeUrl(this.weChatMpProperties.getAppId(), this.weChatMpProperties.getRedirectUri(), AuthorizeScope.BASE);
    }
}
