package cn.javaer.wechat.sdk.pay.model;

import cn.javaer.wechat.sdk.pay.AbstractWeChatPayResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhangpeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayOrderQueryResponse extends AbstractWeChatPayResponse {

    @XmlElement(name = "openid")
    private String openid;

    @XmlElement(name = "is_subscribe")
    private String isSubscribe;

    @XmlElement(name = "sub_openid")
    private String subOpenid;

    @XmlElement(name = "sub_is_subscribe")
    private String subIsSubscribe;

    @XmlElement(name = "trade_state")
    private String tradeState;

    @XmlElement(name = "bank_type")
    private String bankType;

    @XmlElement(name = "detail")
    private String detail;

    @XmlElement(name = "total_fee")
    private String totalFee;

    @XmlElement(name = "fee_type")
    private String feeType;

    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;

    @XmlElement(name = "cash_fee")
    private String cashFee;

    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;

    @XmlElement(name = "coupon_fee")
    private String couponFee;

    @XmlElement(name = "coupon_count")
    private String couponCount;

    @XmlElement(name = "transaction_id")
    private String transactionId;

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    @XmlElement(name = "attach")
    private String attach;

    @XmlElement(name = "time_end")
    private String timeEnd;

    @XmlElement(name = "trade_state_desc")
    private String tradeStateDesc;

    // ---- 代金券
    // ----  0

    @XmlElement(name = "coupon_type_0")
    private String couponType0;

    @XmlElement(name = "coupon_id_0")
    private String couponId0;

    @XmlElement(name = "coupon_fee_0")
    private String couponFee0;

    // ----  1

    @XmlElement(name = "coupon_type_1")
    private String couponType1;

    @XmlElement(name = "coupon_id_1")
    private String couponId1;

    @XmlElement(name = "coupon_fee_1")
    private String couponFee1;

    // ----  2

    @XmlElement(name = "coupon_type_2")
    private String couponType2;

    @XmlElement(name = "coupon_id_2")
    private String couponId2;

    @XmlElement(name = "coupon_fee_2")
    private String couponFee2;

    // ----  3

    @XmlElement(name = "coupon_type_3")
    private String couponType3;

    @XmlElement(name = "coupon_id_3")
    private String couponId3;

    @XmlElement(name = "coupon_fee_3")
    private String couponFee3;

    // ----  4

    @XmlElement(name = "coupon_type_4")
    private String couponType4;

    @XmlElement(name = "coupon_id_4")
    private String couponId4;

    @XmlElement(name = "coupon_fee_4")
    private String couponFee4;
}
