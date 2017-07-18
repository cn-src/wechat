/*
 *    Copyright 2017 zhangpeng
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
