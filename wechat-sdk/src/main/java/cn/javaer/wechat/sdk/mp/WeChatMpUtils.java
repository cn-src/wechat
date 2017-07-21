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

package cn.javaer.wechat.sdk.mp;

import io.vavr.control.Try;
import org.jetbrains.annotations.NotNull;

import java.net.URLEncoder;

/**
 * @author zhangpeng
 */
public class WeChatMpUtils
{
    /**
     * 微信网页授权，生成授权url。<a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842">官方文档<a/>
     * <p>
     * 用户在微信客户端中访问第三方网页，公众号可以通过微信网页授权机制，来获取用户基本信息，进而实现业务逻辑。
     *
     * @param appId       公众号的唯一标识
     * @param redirectUri 授权后重定向的回调链接地址
     * @param scope       应用授权作用域，snsapi_base（不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * @param state       重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return 样例：
     * <p> scope为snsapi_base
     * <br/> https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect
     * <p> scope为snsapi_userinfo
     * <br/> https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxf0e81c3bee622d60&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect
     */
    public static String generateAuthorizeUrl(
        @NotNull final String appId,
        @NotNull final String redirectUri,
        @NotNull final AuthorizeScope scope,
        @NotNull final String state)
    {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
               + "&redirect_uri=" + Try.of(() -> URLEncoder.encode(redirectUri, "UTF-8")).get()
               + "&response_type=code&scope=" + scope.getScope()
               + "&state=" + state + "#wechat_redirect";
    }
    
    /**
     * @see #generateAuthorizeUrl(String, String, AuthorizeScope, String)
     */
    public static String generateAuthorizeUrl(
        @NotNull final String appId,
        @NotNull final String redirectUri,
        @NotNull final AuthorizeScope scope)
    {
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
               + "&redirect_uri=" + Try.of(() -> URLEncoder.encode(redirectUri, "UTF-8")).get()
               + "&response_type=code&scope=" + scope.getScope()
               + "#wechat_redirect";
    }
}
