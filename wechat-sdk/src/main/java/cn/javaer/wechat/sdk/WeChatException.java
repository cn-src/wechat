package cn.javaer.wechat.sdk;

/**
 * @author zhangpeng
 */
public class WeChatException extends RuntimeException
{
    public WeChatException(final String message)
    {
        super(message);
    }
    
    public WeChatException(final Throwable cause)
    {
        super(cause);
    }
}
