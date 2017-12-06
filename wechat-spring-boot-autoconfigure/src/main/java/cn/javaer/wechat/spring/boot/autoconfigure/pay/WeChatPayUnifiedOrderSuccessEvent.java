package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;

/**
 * @author zhangpeng
 */
public class WeChatPayUnifiedOrderSuccessEvent {

    private final WeChatPayUnifiedOrderResponse unifiedOrderResponse;

    public WeChatPayUnifiedOrderSuccessEvent(WeChatPayUnifiedOrderResponse unifiedOrderResponse) {
        this.unifiedOrderResponse = unifiedOrderResponse;
    }

    public WeChatPayUnifiedOrderResponse getUnifiedOrderResponse() {
        return unifiedOrderResponse;
    }
}
