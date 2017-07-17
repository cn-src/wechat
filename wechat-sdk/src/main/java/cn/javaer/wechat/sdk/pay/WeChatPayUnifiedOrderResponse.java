package cn.javaer.wechat.sdk.pay;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhangpeng
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayUnifiedOrderResponse extends AbstractWeChatPayResponse
{
    @XmlElement(name = "appid")
    private String appId;
    
    @XmlElement(name = "mch_id")
    private String mchId;
    
    @XmlElement(name = "device_info")
    private String deviceInfo;
    
    @XmlElement(name = "trade_type")
    private String tradeType;
    
    @XmlElement(name = "prepay_id")
    private String prepayId;
    
    @XmlElement(name = "code_url")
    private String codeUrl;
}
