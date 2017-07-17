package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import cn.javaer.wechat.sdk.pay.WeChatPayNotifyResult;

/**
 * @author zhangpeng
 */
public interface WeChatPayService
{
    UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest unifiedOrderRequest);
    
    NotifyResult notifyResult(WeChatPayNotifyResult apiNotifyResult);
}
