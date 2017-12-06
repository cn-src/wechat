package cn.javaer.wechat.spring.boot.autoconfigure.pay.event;

import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;

/**
 * @author zhangpeng
 */
public class WeChatPayUnifiedOrderEvent {

    private final WeChatPayUnifiedOrderResponse unifiedOrderResponse;

    public WeChatPayUnifiedOrderEvent(WeChatPayUnifiedOrderResponse unifiedOrderResponse) {
        this.unifiedOrderResponse = unifiedOrderResponse;
    }

    public WeChatPayUnifiedOrderResponse getUnifiedOrderResponse() {
        return unifiedOrderResponse;
    }
}
