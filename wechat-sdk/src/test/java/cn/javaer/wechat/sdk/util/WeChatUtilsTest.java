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

package cn.javaer.wechat.sdk.util;

import cn.javaer.wechat.sdk.pay.WeChatPayConfigurator;
import cn.javaer.wechat.sdk.pay.model.Coupon;
import cn.javaer.wechat.sdk.pay.model.OrderQueryResponse;
import cn.javaer.wechat.test.WeChatTestUtils;
import org.junit.Test;

import java.util.Map;

import static cn.javaer.wechat.test.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author zhangpeng
 */
public class WeChatUtilsTest {

    @Test
    public void elementsToMap() {
        WeChatPayConfigurator.DEFAULT.setMchKey("key");

        final OrderQueryResponse response = WeChatTestUtils.jaxbUnmarshal(
            "<xml><sign>d</sign><nonce_str>nonce_str_value</nonce_str><coupon_type_0>CASH</coupon_type_0></xml>",
            OrderQueryResponse.class);
        response.beforeSign();

        final Map<String, Coupon> coupons = response.getCoupons();
        assertThat(coupons)
            .hasSize(1)
            .containsOnlyKeys("0");
        assertThat(coupons.get("0")).hasType(Coupon.Type.CASH);
    }

    @Test
    public void joinPath() {
        assertEquals("http://demo.com/demo", WeChatUtils.joinPath("http://demo.com", "/demo"));
        assertEquals("http://demo.com/demo", WeChatUtils.joinPath("http://demo.com", "/demo/"));
        assertEquals("http://demo.com/demo", WeChatUtils.joinPath("http://demo.com", "demo/"));
        assertEquals("http://demo.com/demo", WeChatUtils.joinPath("http://demo.com/", "/demo"));
        assertEquals("http://demo.com/demo", WeChatUtils.joinPath("http://demo.com/", "/demo/"));
        assertEquals("http://demo.com/demo", WeChatUtils.joinPath("http://demo.com/", "demo/"));
    }
}