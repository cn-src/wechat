package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.Coupon;
import cn.javaer.wechat.sdk.pay.model.UnifiedOrderResponse;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static cn.javaer.wechat.test.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @author zhangpeng
 */
public class WeChatPayUtilsTest {

    @Test
    public void sign() {
        final UnifiedOrderResponse response = new UnifiedOrderResponse();
        response.setReturnCode("SUCCESS");
        response.setReturnMsg("OK");
        response.setAppid("wx2421b1c4370ec43b");
        response.setMchId("10000100");
        response.setNonceStr("IITRi8Iabbblz1Jc");
        response.setResultCode("SUCCESS");
        response.setPrepayId("wx201411101639507cbf6ffd8b0779950874");
        response.setTradeType("JSAPI");

        response.beforeSign();

        assertEquals("BC884153761883FE608EA956BD05A6F5", WeChatPayUtils.generateSign(response, "key"));
    }

    @Test
    public void sign4Cache() {
        final UnifiedOrderResponse response = new UnifiedOrderResponse();
        response.setReturnCode("SUCCESS");
        response.setReturnMsg("OK");
        response.setAppid("wx2421b1c4370ec43b");
        response.setMchId("10000100");
        response.setNonceStr("IITRi8Iabbblz1Jc");
        response.setResultCode("SUCCESS");
        response.setPrepayId("wx201411101639507cbf6ffd8b0779950874");
        response.setTradeType("JSAPI");

        // 2次调用测试缓存
        WeChatPayUtils.generateSign(response, "key");

        assertEquals("BC884153761883FE608EA956BD05A6F5", WeChatPayUtils.generateSign(response, "key"));
    }

    @Test
    public void dynamicMapping() {
        final Map<String, String> otherMap = new HashMap<>();
        otherMap.put("coupon_refund_id_0", "BC884153761883FE608EA956BD05A6F5");
        otherMap.put("coupon_type_0", "CASH");
        otherMap.put("coupon_refund_fee_0", "100");

        otherMap.put("coupon_refund_id_1", "16BE80B8FD1044069950ADAEDEB812C5");
        otherMap.put("coupon_type_1", "NO_CASH");
        otherMap.put("coupon_refund_fee_1", "1");

        final Map<String, BiConsumer<String, Coupon>> mappingMap = new HashMap<>(3);
        mappingMap.put("coupon_refund_id_", (val, coupon) -> coupon.setId(val));
        mappingMap.put("coupon_type_", (val, coupon) -> coupon.setType(Coupon.Type.valueOf(val)));
        mappingMap.put("coupon_refund_fee_", (val, coupon) -> coupon.setFee(Integer.valueOf(val)));
        final Map<String, Coupon> couponMap = WeChatPayUtils.dynamicMapping(
            otherMap, Collections.unmodifiableMap(mappingMap), Coupon::new);

        assertThat(couponMap)
            .containsOnlyKeys("0", "1");

        assertThat(couponMap.get("0"))
            .hasId("BC884153761883FE608EA956BD05A6F5")
            .hasType(Coupon.Type.CASH)
            .hasFee(100);

        assertThat(couponMap.get("1"))
            .hasId("16BE80B8FD1044069950ADAEDEB812C5")
            .hasType(Coupon.Type.NO_CASH)
            .hasFee(1);
    }
}