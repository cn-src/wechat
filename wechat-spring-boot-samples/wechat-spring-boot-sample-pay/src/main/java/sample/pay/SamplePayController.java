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

package sample.pay;

import cn.javaer.wechat.spring.boot.autoconfigure.pay.WeChatPayUnifiedOrderWithNativeResult;
import cn.javaer.wechat.spring.boot.autoconfigure.pay.WeChatPayService;
import cn.javaer.wechat.spring.boot.autoconfigure.pay.event.WeChatPayNotifyResultEvent;
import cn.javaer.wechat.spring.boot.autoconfigure.pay.event.WeChatPayUnifiedOrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangpeng
 */
@RestController
public class SamplePayController {

    private final WeChatPayService payService;

    @Autowired
    public SamplePayController(WeChatPayService payService) {
        this.payService = payService;
    }

    @GetMapping("/unifiedOrderWithNative")
    public WeChatPayUnifiedOrderWithNativeResult index() {
        return payService.unifiedOrderWithNative("body", "outTradeNo", 1, "productId");
    }

    @EventListener
    public void unifiedOrderEvent(final WeChatPayUnifiedOrderEvent unifiedOrderEvent) {
        System.out.println("==== unifiedOrderEvent ==== \n" + unifiedOrderEvent + "\n");
    }

    @EventListener
    public void notifyResultEvent(final WeChatPayNotifyResultEvent notifyResultEvent) {
        System.out.println("==== notifyResultEvent ==== \n" + notifyResultEvent + "\n");
    }
}
