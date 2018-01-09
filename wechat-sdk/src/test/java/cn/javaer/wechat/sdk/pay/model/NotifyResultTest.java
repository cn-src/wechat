package cn.javaer.wechat.sdk.pay.model;

import cn.javaer.wechat.test.WeChatTestUtils;
import org.junit.Test;

import static cn.javaer.wechat.test.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * @author zhangpeng
 */
public class NotifyResultTest {

    @Test
    public void testParse() {
        final String xml = WeChatTestUtils.readClassPathFileToUTFString("/WeChatPayNotifyResult.xml", this.getClass());
        final NotifyResult notifyResult = WeChatTestUtils.jaxbUnmarshal(xml, NotifyResult.class);
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
                .hasSize(5)
                .extracting("id", "type", "fee")
                .containsOnly(
                        tuple("coupon_id_0", Coupon.Type.CASH, 0),
                        tuple("coupon_id_1", Coupon.Type.CASH, 1),
                        tuple("coupon_id_2", Coupon.Type.CASH, 2),
                        tuple("coupon_id_3", Coupon.Type.NO_CASH, 3),
                        tuple("coupon_id_4", Coupon.Type.CASH, 4)
                );

    }
}