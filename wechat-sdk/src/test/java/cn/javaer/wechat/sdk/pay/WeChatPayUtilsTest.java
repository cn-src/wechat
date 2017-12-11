package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangpeng
 */
public class WeChatPayUtilsTest {

    @Test
    public void sign() {
        final WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder()
                .body("body")
                .nonceStr("nonceStr")
                .outTradeNo("outTradeNo")
                .productId("productId")
                .totalFee(100)
                .appid("appId")
                .mchId("mchId")
                .notifyUrl("notifyUrl")
                .spbillCreateIp("spbillCreateIp")
                .tradeType("NATIVE")
                .sign("") // 不参与签名算法
                .signType("MD5") // 参与签名算法
                .build();
        request.setSign(WeChatPayUtils.sign(request, "key"));

       assertEquals("EFAF8A5CD089B3749AF9EAEE6C6B4156",request.getSign());
    }
}