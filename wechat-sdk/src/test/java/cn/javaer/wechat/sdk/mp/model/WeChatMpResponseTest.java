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

package cn.javaer.wechat.sdk.mp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangpeng
 */
public class WeChatMpResponseTest {

    @Test
    public void setOtherProperties() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final Demo demo = mapper.readValue("{\"errcode\":\"d1\",\"any1\":\"any1\",\"any2\":\"any2\"}", Demo.class);
        Assert.assertEquals("d1", demo.getErrCode());
        Assert.assertEquals("any1", demo.getOtherProperties().get("any1"));
        Assert.assertEquals("any2", demo.getOtherProperties().get("any2"));
    }
}

class Demo extends WeChatMpResponse {}