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

package cn.javaer.wechat.sdk.mp

import spock.lang.Specification

/**
 * @author zhangpeng
 */
class WeChatMpUtilsTest extends Specification {
    def "GenerateAuthorizeUrl"() {
        when:
        final String authorizeUrl = WeChatMpUtils.generateAuthorizeUrl("wx520c15f417810387",
                "https://chong.qq.com/php/index.php?d=&c=wxAdapter&m=mobileDeal&showwxpaytitle=1&vb2ctag=4_2030_5_1194_60",
                AuthorizeScope.BASE,
                "123")
        and:
        final String expectedBASE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect"

        then:
        expectedBASE == authorizeUrl
    }

    def "GenerateAuthorizeUrl::no state"() {
        when:
        final String authorizeUrl = WeChatMpUtils.generateAuthorizeUrl("wx520c15f417810387",
                "https://chong.qq.com/php/index.php?d=&c=wxAdapter&m=mobileDeal&showwxpaytitle=1&vb2ctag=4_2030_5_1194_60",
                AuthorizeScope.BASE);
        and:
        final String expected = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base#wechat_redirect";

        then:
        expected == authorizeUrl
    }

    def "CheckResponse"() {
    }

    def "CheckResponseBody"() {
    }
}
