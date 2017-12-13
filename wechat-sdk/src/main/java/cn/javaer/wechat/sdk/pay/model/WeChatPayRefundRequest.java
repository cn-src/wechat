/*
 * Copyright (c) 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.javaer.wechat.sdk.pay.model;

import lombok.Builder;
import lombok.Value;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信支付-关闭订单-请求.
 *
 * @author zhangpeng
 */
@Value
@Builder
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

    /**
     * 退款原因.
     */
    @XmlElement(name = "refund_desc")
    private String refundDesc;

    /**
     * 退款资金来源.
     */
    @XmlElement(name = "refund_account")
    private String refundAccount;
}
