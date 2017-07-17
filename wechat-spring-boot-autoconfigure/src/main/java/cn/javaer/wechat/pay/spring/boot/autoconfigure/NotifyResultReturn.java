package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhangpeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class NotifyResultReturn
{
    public static final NotifyResultReturn SUCCESS = new NotifyResultReturn("SUCCESS", "OK");
    
    @XmlElement(name = "return_code")
    private String returnCode;
    
    @XmlElement(name = "return_msg")
    private String returnMsg;
}
