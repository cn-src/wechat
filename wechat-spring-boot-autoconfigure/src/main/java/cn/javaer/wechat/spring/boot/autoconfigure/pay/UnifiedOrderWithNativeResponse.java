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

import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;
import lombok.Data;

/**
 * 扫码支付模式二，响应结果
 *
 * @author zhangpeng
 */
@Data
public class UnifiedOrderWithNativeResponse {
    private String prepayId;
    private String codeUrl;

    public static UnifiedOrderWithNativeResponse createWith(WeChatPayUnifiedOrderResponse response) {
        UnifiedOrderWithNativeResponse unifiedOrderWithNativeResponse = new UnifiedOrderWithNativeResponse();
        unifiedOrderWithNativeResponse.setCodeUrl(response.getCodeUrl());
        unifiedOrderWithNativeResponse.setPrepayId(response.getPrepayId());
        return unifiedOrderWithNativeResponse;
    }
}
