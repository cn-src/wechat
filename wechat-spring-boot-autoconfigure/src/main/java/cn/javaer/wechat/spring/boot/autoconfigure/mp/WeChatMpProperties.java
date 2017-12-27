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

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众号配置.
 *
 * @author zhangpeng
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "wechat.mp")
public class WeChatMpProperties {

    public static final String AUTHORIZE_CODE_PATH = "/public/wechat/mp/authorize_code";
    public static final String AUTHORIZE_PATH = "/public/wechat/mp/authorize";


    /**
     * 公众号的唯一标识.
     */
    @NonNull
    private String appid;

    @NonNull
    private String secret;

    @NonNull
    private String notifyAddress;

    /**
     * 授权后重定向的回调链接 path.
     */
    private String authorizeCodePath;

    private String authorizePath;

    private String apiBasePath = WeChatPayClient.BASE_PATH;
}
