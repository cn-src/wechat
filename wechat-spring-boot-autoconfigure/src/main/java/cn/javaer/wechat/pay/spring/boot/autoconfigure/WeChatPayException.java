package cn.javaer.wechat.pay.spring.boot.autoconfigure;

/**
 * @author zhangpeng
 */
public class WeChatPayException extends RuntimeException
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
