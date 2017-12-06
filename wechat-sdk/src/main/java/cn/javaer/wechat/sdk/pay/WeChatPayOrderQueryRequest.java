package cn.javaer.wechat.sdk.pay;

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
 * @author zhangpeng
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayOrderQueryRequest {

    /**
     * 公众账号ID
     */
    @XmlElement(name = "appid")
    private String appid;

    /**
     * 商户号
     */
    @XmlElement(name = "mch_id")
    private String mchId;

    /**
     * 子商户公众账号ID
     */
    @XmlElement(name = "sub_appid")
    private String subAppId;

    /**
     * 子商户号
     */
    @XmlElement(name = "sub_mch_id")
    private String subMchId;

    @XmlElement(name = "transaction_id")
    private String transactionId;

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 随机字符串
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    @XmlElement(name = "sign")
    private String sign;

}
