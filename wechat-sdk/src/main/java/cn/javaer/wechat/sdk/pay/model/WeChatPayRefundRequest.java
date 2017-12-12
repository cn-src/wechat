package cn.javaer.wechat.sdk.pay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信支付-关闭订单-请求.
 *
 * @author zhangpeng
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayRefundRequest {

    /**
     * 公众账号ID.
     */
    @XmlElement(name = "appid")
    private String appid;

    /**
     * 商户号.
     */
    @XmlElement(name = "mch_id")
    private String mchId;

    /**
     * 随机字符串.
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;

    /**
     * 签名.
     */
    @XmlElement(name = "sign")
    private String sign;

    /**
     * 签名类型.
     */
    @XmlElement(name = "sign_type")
    private String signType;

    @XmlElement(name = "transaction_id")
    private String transactionId;

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号.
     */
    @XmlElement(name = "out_refund_no")
    private String outRefundNo;

    /**
     * 订单金额.
     */
    @XmlElement(name = "total_fee")
    private String totalFee;

    /**
     * 退款金额.
     */
    @XmlElement(name = "refund_fee")
    private String refundFee;

    @XmlElement(name = "refund_fee_type")
    private String refundFeeType;

    @XmlElement(name = "refund_desc")
    private String refundDesc;

    @XmlElement(name = "refund_account")
    private String refundAccount;
}
