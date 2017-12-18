package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.WeChatPayCoupon;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

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
        assertEquals("x", WeChatPayUtils.toFieldName("getX", String.class));
        assertEquals("xxx", WeChatPayUtils.toFieldName("getXxx", String.class));
        assertEquals(null, WeChatPayUtils.toFieldName("xxx", String.class));
        assertEquals(null, WeChatPayUtils.toFieldName("isXxx", String.class));
        assertEquals("xxx", WeChatPayUtils.toFieldName("isXxx", boolean.class));
        assertEquals("xxx", WeChatPayUtils.toFieldName("isXxx", Boolean.class));
    }

    @Test
    public void dynamicMapping() {
        final Map<String, String> otherMap = new HashMap<>();
        otherMap.put("coupon_refund_id_0", "BC884153761883FE608EA956BD05A6F5");
        otherMap.put("coupon_type_0", "CASH");
        otherMap.put("coupon_refund_fee_0", "100");

        otherMap.put("coupon_refund_id_1", "16BE80B8FD1044069950ADAEDEB812C5");
        otherMap.put("coupon_type_1", "CASH");
        otherMap.put("coupon_refund_fee_1", "100");

        final Map<String, BiConsumer<String, WeChatPayCoupon>> mappingMap = new HashMap<>(3);
        mappingMap.put("coupon_refund_id_", (val, coupon) -> coupon.setId(val));
        mappingMap.put("coupon_type_", (val, coupon) -> coupon.setType(WeChatPayCoupon.Type.valueOf(val)));
        mappingMap.put("coupon_refund_fee_", (val, coupon) -> coupon.setFee(Integer.valueOf(val)));
        final Map<String, WeChatPayCoupon> couponMap = WeChatPayUtils.dynamicMapping(
                otherMap, Collections.unmodifiableMap(mappingMap), WeChatPayCoupon::new);
    }
}