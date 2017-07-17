package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.WeChatException;

/**
 * @author zhangpeng
 */
public class WeChatPayException extends WeChatException
{
    public WeChatPayException(final String message)
    {
        super(message);
    }
    
    public WeChatPayException(final Throwable cause)
    {
        super(cause);
    }
}
