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

import javassist.ClassPool;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author zhangpeng
 */
public class WeChatMpAutoConfigurationTest {
    @Test
    public void weChatMpControllerMissBean() throws Exception {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
            context.register(WeChatMpAutoConfiguration.class);
            context.refresh();
            assertThatThrownBy(() -> context.getBean(WeChatMpController.class)).hasMessageStartingWith("No qualifying bean of type");
        }
    }

    @Test
    public void weChatMpController() throws Exception {
        ClassPool.getDefault().makeClass("cn.javaer.wechat.spring.boot.starter.mp.ConditionalOnClassTrigger").toClass();
        try (AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext()) {
            context.register(WeChatMpAutoConfiguration.class);
            context.refresh();
            context.getBean(WeChatMpController.class);
        }
    }

}