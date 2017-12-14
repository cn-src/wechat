package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangpeng
 */
public class WeChatPayUtilsTest {

    @Test
    public void sign1() {
        final WeChatPayUnifiedOrderRequest request =
                WeChatPayUnifiedOrderRequest.createWithNative("body", "outTradeNo", 100);
//        assertEquals("EFAF8A5CD089B3749AF9EAEE6C6B4156", request.getSign());
    }

    @Test
    public void sign2() {
        final WeChatPayUnifiedOrderResponse response = new WeChatPayUnifiedOrderResponse();
        response.setReturnCode("SUCCESS");
        response.setReturnMsg("OK");
        response.setAppid("wx2421b1c4370ec43b");
        response.setMchId("10000100");
        response.setNonceStr("IITRi8Iabbblz1Jc");
        response.setResultCode("SUCCESS");
        response.setPrepayId("wx201411101639507cbf6ffd8b0779950874");
        response.setTradeType("JSAPI");

        response.setSign(WeChatPayUtils.sign(response, "key"));

        assertEquals("BC884153761883FE608EA956BD05A6F5", response.getSign());
    }

    @Test
    public void sign4Cache() {
        final WeChatPayUnifiedOrderResponse response = new WeChatPayUnifiedOrderResponse();
        response.setReturnCode("SUCCESS");
        response.setReturnMsg("OK");
        response.setAppid("wx2421b1c4370ec43b");
        response.setMchId("10000100");
        response.setNonceStr("IITRi8Iabbblz1Jc");
        response.setResultCode("SUCCESS");
        response.setPrepayId("wx201411101639507cbf6ffd8b0779950874");
        response.setTradeType("JSAPI");

        // 2次调用测试缓存
        response.setSign(WeChatPayUtils.sign(response, "key"));
        response.setSign(WeChatPayUtils.sign(response, "key"));

        assertEquals("BC884153761883FE608EA956BD05A6F5", response.getSign());
    }
}