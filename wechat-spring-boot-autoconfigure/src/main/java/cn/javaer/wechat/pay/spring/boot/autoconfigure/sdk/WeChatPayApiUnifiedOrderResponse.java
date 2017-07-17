package cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhangpeng
 */
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayApiUnifiedOrderResponse
{
    @XmlElement(name = "return_code")
    private String returnCode;
    
    @XmlElement(name = "return_msg")
    private String returnMsg;
    
    @XmlElement(name = "appid")
    private String appId;
    
    @XmlElement(name = "mch_id")
    private String mchId;
    
    @XmlElement(name = "device_info")
    private String deviceInfo;
    
    @XmlElement(name = "nonce_str")
    private String nonceStr;
    
    @XmlElement(name = "sign")
    private String sign;
    
    @XmlElement(name = "result_code")
    private String resultCode;
    
    @XmlElement(name = "err_code")
    private String errCode;
    
    @XmlElement(name = "err_code_des")
    private String errCodeDes;
    
    @XmlElement(name = "trade_type")
    private String tradeType;
    
    @XmlElement(name = "prepay_id")
    private String prepayId;
    
    @XmlElement(name = "code_url")
    private String codeUrl;
}
