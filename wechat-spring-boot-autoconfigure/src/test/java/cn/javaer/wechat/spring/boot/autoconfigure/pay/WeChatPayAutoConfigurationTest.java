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

package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhangpeng
 */
public class WeChatPayAutoConfigurationTest
{
    private AnnotationConfigApplicationContext context;
    
    @Before
    public void setUp() throws Exception
    {
        this.context = new AnnotationConfigApplicationContext();
        this.context.register(WeChatPayAutoConfiguration.class);
    }
    
    @After
    public void close()
    {
        if (this.context != null)
        {
            this.context.close();
        }
    }
    
    // @Test
    // public void weChatPayClientMissBean() throws Exception
    // {
    //     this.context.refresh();
    //     assertThatThrownBy(() -> this.context.getBean(WeChatPayClient.class)).hasMessage("No qualifying bean of type 'cn.javaer.wechat.sdk.pay.WeChatPayClient' available");
    // }
    //
    @Test
    public void weChatPayClient() throws Exception
    {
        // final ClassPool pool = ClassPool.getDefault();
        // // pool.insertClassPath(new ClassClassPath(this.getClass()));
        // final CtClass ctClass = pool.makeClass("cn.javaer.wechat.spring.boot.starter.pay.ConditionalOnClassTrigger");
        // System.out.println(ctClass.toClass());
        // this.getClass().getClassLoader().loadClass("cn.javaer.wechat.spring.boot.starter.pay.ConditionalOnClassTrigger");
        // this.context = new AnnotationConfigApplicationContext();
        // this.context.register(WeChatPayAutoConfiguration.class);
        this.context.refresh();
        this.context.getBean(WeChatPayClient.class);
    }
}