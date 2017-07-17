package cn.javaer.wechat.sdk.pay;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author zhangpeng
 */
@Getter
@Setter
public abstract class AbstractWeChatPayResponse
{
    @XmlElement(name = "return_code")
    protected String returnCode;
    
    @XmlElement(name = "return_msg")
    protected String returnMsg;
    
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
}
