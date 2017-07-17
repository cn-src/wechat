package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import org.junit.Test;

/**
 * @author zhangpeng
 */
public class QrCodeUtilTest
{
    @Test
    public void strToQrCodeBase64() throws Exception
    {
        System.out.println(QrCodeUtil.strToQrCodeImageBase64("demo"));
    }
    
}