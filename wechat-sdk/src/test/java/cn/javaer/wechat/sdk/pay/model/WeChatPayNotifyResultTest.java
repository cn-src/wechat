package cn.javaer.wechat.sdk.pay.model;

import cn.javaer.wechat.test.WeChatTestUtils;
import org.junit.Test;

import static cn.javaer.wechat.sdk.pay.model.WeChatPayNotifyResultAssert.assertThat;

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
                .hasTotalFee("1")
                .hasCouponFee("10")
                .hasCouponCount("1")
                .hasTradeType("JSAPI")
                .hasTransactionId("1004400740201409030005092168")

                .hasCouponType0("coupon_type_0")
                .hasCouponId0("coupon_id_0")
                .hasCouponFee0("0")

                .hasCouponType1("coupon_type_1")
                .hasCouponId1("coupon_id_1")
                .hasCouponFee1("1")

                .hasCouponType2("coupon_type_2")
                .hasCouponId2("coupon_id_2")
                .hasCouponFee2("2")

                .hasCouponType3("coupon_type_3")
                .hasCouponId3("coupon_id_3")
                .hasCouponFee3("3")

                .hasCouponType4("coupon_type_4")
                .hasCouponId4("coupon_id_4")
                .hasCouponFee4("4");

    }
}