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

package cn.javaer.wechat.spring.boot.autoconfigure.pay.event;

import cn.javaer.wechat.sdk.pay.WeChatPayNotifyResult;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;

/**
 * 微信支付-支付结果通知事件.
 *
 * @author zhangpeng
 */

public class WeChatPayNotifyResultEvent {

    private final WeChatPayNotifyResult notifyResult;
    private final boolean successful;


    public WeChatPayNotifyResultEvent(WeChatPayNotifyResult notifyResult) {
        this.notifyResult = notifyResult;
        this.successful = WeChatPayUtils.isSuccessfulResponseBody(notifyResult);
    }

    /**
     * 获取支付结果通知事件的响应.
     *
     * @return WeChatPayNotifyResult
     */
    public WeChatPayNotifyResult getNotifyResult() {
        return notifyResult;
    }

    /**
     * 判断支付结果通知是否支付成功.
     *
     * @return 支付成功为 true
     */
    public boolean isPaySuccessful() {
        return successful;
    }
}
