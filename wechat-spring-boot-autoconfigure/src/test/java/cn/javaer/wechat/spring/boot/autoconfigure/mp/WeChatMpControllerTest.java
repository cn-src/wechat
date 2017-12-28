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
import cn.javaer.wechat.sdk.mp.model.WeChatMpAccessTokenResponse;
import cn.javaer.wechat.spring.boot.autoconfigure.mp.event.WeChatMpAuthenticationSuccessEvent;
import javassist.ClassPool;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangpeng
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WeChatMpAutoConfiguration.class, WeChatMpControllerTest.EventHandler.class})
@WebMvcTest(controllers = WeChatMpController.class, secure = false)
public class WeChatMpControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private WeChatMpClient weChatMpClient;


    @BeforeClass
    public static void setUp() throws Exception {
        ClassPool.getDefault().makeClass("cn.javaer.wechat.spring.boot.starter.mp.ConditionalOnClassTrigger").toClass();
    }

    @Test
    public void authorizeCode() throws Exception {
        final WeChatMpAccessTokenResponse accessTokenResponse = new WeChatMpAccessTokenResponse();
        accessTokenResponse.setErrCode("0");
        given(this.weChatMpClient.accessToken("code")).willReturn(accessTokenResponse);

        this.mvc.perform(get(WeChatMpProperties.AUTHORIZE_CODE_PATH)
            .param("code", "code")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    public static class EventHandler {
        @EventListener
        public void event(final WeChatMpAuthenticationSuccessEvent event) {
            Assert.assertEquals("0", event.getAccessTokenResponse().getErrCode());
        }
    }
}