package cn.javaer.wechat.spring.boot.autoconfigure.pay.event;

import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;

/**
 * 微信支付-统一下单事件.
 *
 * @author zhangpeng
 */
public class WeChatPayUnifiedOrderEvent {

    private final WeChatPayUnifiedOrderResponse unifiedOrderResponse;
    private final boolean successful;


    public WeChatPayUnifiedOrderEvent(WeChatPayUnifiedOrderResponse unifiedOrderResponse) {
        this.unifiedOrderResponse = unifiedOrderResponse;
        this.successful = WeChatPayUtils.isSuccessfulResponseBody(unifiedOrderResponse);
    }

    /**
     * 获取统一下单的响应.
     *
     * @return WeChatPayUnifiedOrderResponse
     */
    public WeChatPayUnifiedOrderResponse getUnifiedOrderResponse() {
        return unifiedOrderResponse;
    }

    /**
     * 判断下单是否成功.
     *
     * @return 成功为 true
     */
    public boolean isPlaceOrderSuccessful() {
        return successful;
    }
}
