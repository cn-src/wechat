package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;

/**
 * @author zhangpeng
 */
public class WeChatPayUtilsTest {

    @Test
    public void sign() {
        final WeChatPayUnifiedOrderResponse response = new WeChatPayUnifiedOrderResponse();
        response.setReturnCode("SUCCESS");
        response.setReturnMsg("OK");
        response.setAppid("wx2421b1c4370ec43b");
        response.setMchId("10000100");
        response.setNonceStr("IITRi8Iabbblz1Jc");
        response.setResultCode("SUCCESS");
        response.setPrepayId("wx201411101639507cbf6ffd8b0779950874");
        response.setTradeType("JSAPI");

        response.setSign(WeChatPayUtils.generateSign(response, "key"));

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
        response.setSign(WeChatPayUtils.generateSign(response, "key"));
        response.setSign(WeChatPayUtils.generateSign(response, "key"));

        assertEquals("BC884153761883FE608EA956BD05A6F5", response.getSign());
    }

    @Test
    public void beanMethodNameToFieldName() {
        assertEquals("x", WeChatPayUtils.toFieldName("getX"));
        assertEquals("xxx", WeChatPayUtils.toFieldName("getXxx"));
        assertThatThrownBy(() -> WeChatPayUtils.toFieldName("xxx"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}