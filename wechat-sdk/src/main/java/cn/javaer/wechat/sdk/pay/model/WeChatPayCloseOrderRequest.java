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
public class WeChatPayCloseOrderRequest {

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

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

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

}
