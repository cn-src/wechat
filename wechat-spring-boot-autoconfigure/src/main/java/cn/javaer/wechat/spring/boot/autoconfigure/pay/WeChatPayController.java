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

package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.model.WeChatPayNotifyResult;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;
import cn.javaer.wechat.spring.boot.autoconfigure.pay.event.WeChatPayNotifyResultEvent;
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
public class WeChatPayController {
    private final ApplicationEventPublisher publisher;
    private final WeChatPayProperties weChatPayProperties;

    public WeChatPayController(
            @NotNull ApplicationEventPublisher publisher,
            @NotNull WeChatPayProperties weChatPayProperties) {
        this.publisher = publisher;
        this.weChatPayProperties = weChatPayProperties;
    }

    /**
     * 接收支付结果通知，将其发布为事件。
     */
    @RequestMapping(path = "${wechat.pay.notify-result-path}", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public NotifyResultResponse notifyResult(@RequestBody WeChatPayNotifyResult weChatPayNotifyResult) {
        WeChatPayUtils.checkResponseBody(weChatPayNotifyResult, weChatPayProperties.getMchKey());
        publisher.publishEvent(new WeChatPayNotifyResultEvent(weChatPayNotifyResult));
        return NotifyResultResponse.SUCCESS;
    }
}
