package cn.javaer.wechat.spring.boot.autoconfigure.pay.event;

import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;

/**
 * @author zhangpeng
 */
public class WeChatPayUnifiedOrderEvent {

    private final WeChatPayUnifiedOrderResponse unifiedOrderResponse;
    private final boolean successful;


    public WeChatPayUnifiedOrderEvent(WeChatPayUnifiedOrderResponse unifiedOrderResponse) {
        this.unifiedOrderResponse = unifiedOrderResponse;
        this.successful = WeChatPayUtils.isSuccessfulResponseBody(unifiedOrderResponse);
    }

    public WeChatPayUnifiedOrderResponse getUnifiedOrderResponse() {
        return unifiedOrderResponse;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
