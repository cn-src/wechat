/*
 *    Copyright 2017 the original author or authors.
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信支付原生统一下单API的参数。
 *
 * @author zhangpeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayUnifiedOrderRequest {
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
    
    @XmlElement(name = "sign_type")
    private String signType;
    
    @XmlElement(name = "body")
    private String body;
    
    @XmlElement(name = "detail")
    private String detail;
    
    @XmlElement(name = "attach")
    private String attach;
    
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;
    
    @XmlElement(name = "fee_type")
    private String feeType;
    
    @XmlElement(name = "total_fee")
    private Integer totalFee;
    
    @XmlElement(name = "spbill_create_ip")
    private String spbillCreateIp;
    
    @XmlElement(name = "time_start")
    private String timeStart;
    
    @XmlElement(name = "time_expire")
    private String timeExpire;
    
    @XmlElement(name = "goods_tag")
    private String goodsTag;
    
    @XmlElement(name = "notify_url")
    private String notifyUrl;
    
    @XmlElement(name = "trade_type")
    private String tradeType;
    
    @XmlElement(name = "product_id")
    private String productId;
    
    @XmlElement(name = "limit_pay")
    private String limitPay;
    
    @XmlElement(name = "openid")
    private String openId;
}
