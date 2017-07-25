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

import cn.javaer.wechat.sdk.pay.WeChatPayNotifyResult;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;
import jodd.bean.BeanCopy;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpeng
 */
@RestController
public class WeChatPayController
{
    private final ApplicationEventPublisher publisher;
    private final WeChatPayProperties       weChatPayProperties;
    
    @SuppressWarnings("WeakerAccess")
    public WeChatPayController(
        @NotNull final ApplicationEventPublisher publisher,
        @NotNull final WeChatPayProperties weChatPayProperties)
    {
        this.publisher = publisher;
        this.weChatPayProperties = weChatPayProperties;
    }
    
    /**
     * 接收支付结果通知，将其发布为事件。
     */
    @RequestMapping(path = "${wechat.pay.notify-result-path:/public/wechat/pay/notify_result}", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public NotifyResultReturn notifyResult(@RequestBody final WeChatPayNotifyResult weChatPayNotifyResult)
    {
        // TODO 不抛出异常
        WeChatPayUtils.checkResponseBody(weChatPayNotifyResult, this.weChatPayProperties.getMchKey());
        final NotifyResult notifyResult = new NotifyResult();
        BeanCopy.beans(weChatPayNotifyResult, notifyResult).copy();
        
        this.publisher.publishEvent(new WeChatPayNotifyResultEvent(notifyResult));
        return NotifyResultReturn.SUCCESS;
    }
}
