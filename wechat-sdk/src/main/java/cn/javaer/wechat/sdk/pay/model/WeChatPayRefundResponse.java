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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信支付-申请退款-响应.
 *
 * @author zhangpeng
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayRefundResponse extends WeChatPayResponse {

    @XmlElement(name = "transaction_id")
    private String transactionId;

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    @XmlElement(name = "out_refund_no")
    private String outRefundNo;

    @XmlElement(name = "refund_id")
    private String refundId;

    @XmlElement(name = "refund_fee")
    private String refundFee;

    @XmlElement(name = "settlement_refund_fee")
    private String settlementRefundFee;

    @XmlElement(name = "total_fee")
    private String totalFee;

    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;

    @XmlElement(name = "fee_type")
    private String feeType;

    @XmlElement(name = "cash_fee")
    private String cashFee;

    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;

    @XmlElement(name = "cash_refund_fee")
    private String cashRefundFee;

    @XmlElement(name = "coupon_refund_fee")
    private String couponRefundFee;

    @XmlElement(name = "coupon_refund_count")
    private String couponRefundCount;

    // ---- 代金券
    // ----  0

    @XmlElement(name = "coupon_type_0")
    private String couponType0;

    @XmlElement(name = "coupon_refund_id_0")
    private String couponId0;

    @XmlElement(name = "coupon_refund_fee_0")
    private String couponFee0;

    // ----  1

    @XmlElement(name = "coupon_type_1")
    private String couponType1;

    @XmlElement(name = "coupon_refund_id_1")
    private String couponId1;

    @XmlElement(name = "coupon_refund_fee_1")
    private String couponFee1;

    // ----  2

    @XmlElement(name = "coupon_type_2")
    private String couponType2;

    @XmlElement(name = "coupon_refund_id_2")
    private String couponId2;

    @XmlElement(name = "coupon_refund_fee_2")
    private String couponFee2;

    // ----  3

    @XmlElement(name = "coupon_type_3")
    private String couponType3;

    @XmlElement(name = "coupon_refund_id_3")
    private String couponId3;

    @XmlElement(name = "coupon_refund_fee_3")
    private String couponFee3;

    // ----  4

    @XmlElement(name = "coupon_type_4")
    private String couponType4;

    @XmlElement(name = "coupon_refund_id_4")
    private String couponId4;

    @XmlElement(name = "coupon_refund_fee_4")
    private String couponFee4;
}
