package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayException;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;

/**
 * @author zhangpeng
 */
public class RestTemplateWeChatPayClient implements WeChatPayClient {
    @Override
    public WeChatPayUnifiedOrderResponse unifiedOrder(WeChatPayUnifiedOrderRequest request) throws WeChatPayException {
        return null;
    }
}
