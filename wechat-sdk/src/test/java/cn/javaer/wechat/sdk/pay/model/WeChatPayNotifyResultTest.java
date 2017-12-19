package cn.javaer.wechat.sdk.pay.model;

import cn.javaer.wechat.test.WeChatTestUtils;
import org.junit.Test;

import static cn.javaer.wechat.test.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author zhangpeng
 */
public class WeChatPayNotifyResultTest {

    @Test
    public void testParse() {
        final String xml = WeChatTestUtils.readClassPathFileToUTFString("/WeChatPayNotifyResult.xml", this.getClass());
        final WeChatPayNotifyResult notifyResult = WeChatTestUtils.jaxbUnmarshal(xml, WeChatPayNotifyResult.class);
        assertThat(notifyResult)
            .hasAppid("wx2421b1c4370ec43b")
            .hasAttach("支付测试")
            .hasBankType("CFT")
            .hasFeeType("CNY")
            .hasIsSubscribe("Y")
            .hasMchId("10000100")
            .hasNonceStr("5d2b6c2a8db53831f7eda20af46e531c")
            .hasOpenid("oUpF8uMEb4qRXf22hE3X68TekukE")
            .hasOutTradeNo("1409811653")
            .hasResultCode("SUCCESS")
            .hasReturnCode("SUCCESS")
            .hasSign("B552ED6B279343CB493C5DD0D78AB241")
            .hasSubMchId("10000100")
            .hasTimeEnd("20140903131540")
            .hasTotalFee(1)
            .hasCouponFee(10)
            .hasCouponCount(1)
            .hasTradeType("JSAPI")
            .hasTransactionId("1004400740201409030005092168");

        notifyResult.beforeSign();

        assertThat(notifyResult.getCoupons())
            .containsOnlyKeys("0", "1", "2", "3", "4");

        assertThat(notifyResult.getCoupons().get("0"))
            .hasId("coupon_id_0")
            .hasType(WeChatPayCoupon.Type.CASH)
            .hasFee(0);
        assertThat(notifyResult.getCoupons().get("1"))
            .hasId("coupon_id_1")
            .hasType(WeChatPayCoupon.Type.CASH)
            .hasFee(1);
        assertThat(notifyResult.getCoupons().get("2"))
            .hasId("coupon_id_2")
            .hasType(WeChatPayCoupon.Type.CASH)
            .hasFee(2);
        assertThat(notifyResult.getCoupons().get("3"))
            .hasId("coupon_id_3")
            .hasType(WeChatPayCoupon.Type.NO_CASH)
            .hasFee(3);
        assertThat(notifyResult.getCoupons().get("4"))
            .hasId("coupon_id_4")
            .hasType(WeChatPayCoupon.Type.CASH)
            .hasFee(4);

    }
}