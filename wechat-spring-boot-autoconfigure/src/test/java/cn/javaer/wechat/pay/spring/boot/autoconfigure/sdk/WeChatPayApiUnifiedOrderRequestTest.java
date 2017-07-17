package cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk;

import org.junit.Test;

import java.io.StringWriter;

/**
 * @author zhangpeng
 */
public class WeChatPayApiUnifiedOrderRequestTest
{
    @Test
    public void testXml() throws Exception
    {
        // final Persister persister = new Persister();
        final StringWriter sw = new StringWriter();
        final WeChatPayApiUnifiedOrderRequest request = WeChatPayApiUnifiedOrderRequest.builder()
            .appId("appId")
            .mchId("mchId")
            .body("body")
            .nonceStr("nonceStr")
            .notifyUrl("notifyUrl")
            .outTradeNo("outTradeNo")
            .spbillCreateIp("spbillCreateIp")
            .totalFee(1)
            .tradeType("tradeType")
            .productId("productId")
            .sign("sign")
            .build();
        
        // persister.write(request, sw);
        
        //language=XML
        // final String ex = "<xml>\n" +
        //                   "   <appid>appId</appid>\n" +
        //                   "   <mch_id>mchId</mch_id>\n" +
        //                   "   <nonce_str>nonceStr</nonce_str>\n" +
        //                   "   <sign>sign</sign>\n" +
        //                   "   <body>body</body>\n" +
        //                   "   <out_trade_no>outTradeNo</out_trade_no>\n" +
        //                   "   <total_fee>1</total_fee>\n" +
        //                   "   <spbill_create_ip>spbillCreateIp</spbill_create_ip>\n" +
        //                   "   <notify_url>notifyUrl</notify_url>\n" +
        //                   "   <trade_type>tradeType</trade_type>\n" +
        //                   "   <product_id>productId</product_id>\n" +
        //                   "</xml>";
        // assertEquals(ex, sw.toString());
    }
    
}