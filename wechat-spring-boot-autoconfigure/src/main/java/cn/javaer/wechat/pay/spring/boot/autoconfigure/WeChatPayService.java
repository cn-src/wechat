package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk.WeChatPayApiNotifyResult;

/**
 * @author zhangpeng
 */
public interface WeChatPayService
{
    UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest unifiedOrderRequest);
    
    NotifyResult notifyResult(WeChatPayApiNotifyResult apiNotifyResult);
}
