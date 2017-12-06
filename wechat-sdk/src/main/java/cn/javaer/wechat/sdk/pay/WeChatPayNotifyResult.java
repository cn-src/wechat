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

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhangpeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayNotifyResult extends AbstractWeChatPayResponse {
    @XmlElement(name = "appid")
    private String appid;

    @XmlElement(name = "mch_id")
    private String mchId;

    @XmlElement(name = "sub_appid")
    private String subAppid;

    @XmlElement(name = "sub_mch_id")
    private String subMchId;

    @XmlElement(name = "device_info")
    private String deviceInfo;

    @XmlElement(name = "sign_type")
    private String signType;

    @XmlElement(name = "openid")
    private String openid;

    @XmlElement(name = "is_subscribe")
    private String isSubscribe;

    @XmlElement(name = "sub_openid")
    private String subOpenid;

    @XmlElement(name = "sub_is_subscribe")
    private String subIsSubscribe;

    @XmlElement(name = "trade_type")
    private String tradeType;

    @XmlElement(name = "bank_type")
    private String bankType;

    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;

    @XmlElement(name = "fee_type")
    private String feeType;

    @XmlElement(name = "total_fee")
    private String totalFee;

    @XmlElement(name = "cash_fee")
    private String cashFee;

    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;

    @XmlElement(name = "coupon_fee")
    private String couponFee;

    @XmlElement(name = "coupon_count")
    private String couponCount;

    @XmlElement(name = "transaction_id")
    private String transactionId;

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    @XmlElement(name = "attach")
    private String attach;

    @XmlElement(name = "time_end")
    private String timeEnd;

    // ---- 代金券
    // ----  0

    @XmlElement(name = "coupon_type_0")
    private String couponType0;

    @XmlElement(name = "coupon_id_0")
    private String couponId0;

    @XmlElement(name = "coupon_fee_0")
    private String couponFee0;

    // ----  1

    @XmlElement(name = "coupon_type_1")
    private String couponType1;

    @XmlElement(name = "coupon_id_1")
    private String couponId1;

    @XmlElement(name = "coupon_fee_1")
    private String couponFee1;

    // ----  2

    @XmlElement(name = "coupon_type_2")
    private String couponType2;

    @XmlElement(name = "coupon_id_2")
    private String couponId2;

    @XmlElement(name = "coupon_fee_2")
    private String couponFee2;

    // ----  3

    @XmlElement(name = "coupon_type_3")
    private String couponType3;

    @XmlElement(name = "coupon_id_3")
    private String couponId3;

    @XmlElement(name = "coupon_fee_3")
    private String couponFee3;

    // ----  4

    @XmlElement(name = "coupon_type_4")
    private String couponType4;

    @XmlElement(name = "coupon_id_4")
    private String couponId4;

    @XmlElement(name = "coupon_fee_4")
    private String couponFee4;
}
