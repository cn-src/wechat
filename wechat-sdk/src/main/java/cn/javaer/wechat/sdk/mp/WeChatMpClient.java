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

package cn.javaer.wechat.sdk.mp;

import cn.javaer.wechat.sdk.mp.model.WeChatMpAccessTokenResponse;

public interface WeChatMpClient {

    String BASE_PATH = "https://api.weixin.qq.com";
    String ACCESS_TOKEN_PATH = "/sns/oauth2/access_token";

    WeChatMpAccessTokenResponse snsOauth2AccessToken(String appid, String secret, String code, String grantType);
}