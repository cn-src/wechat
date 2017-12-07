package cn.javaer.wechat.sdk.pay;

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

    // $

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

}
