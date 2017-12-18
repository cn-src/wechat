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

import cn.javaer.wechat.sdk.pay.SignIgnore;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 微信支付-申请退款-响应.
 *
 * @author zhangpeng
 */
@Getter
@Setter
@ToString(callSuper = true)
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
    private Integer refundFee;

    @XmlElement(name = "settlement_refund_fee")
    private Integer settlementRefundFee;

    @XmlElement(name = "total_fee")
    private Integer totalFee;

    @XmlElement(name = "settlement_total_fee")
    private Integer settlementTotalFee;

    @XmlElement(name = "fee_type")
    private String feeType;

    @XmlElement(name = "cash_fee")
    private Integer cashFee;

    @XmlElement(name = "cash_fee_type")
    private String cashFeeType;

    @XmlElement(name = "cash_refund_fee")
    private Integer cashRefundFee;

    @XmlElement(name = "coupon_refund_fee")
    private Integer couponRefundFee;

    @XmlElement(name = "coupon_refund_count")
    private Integer couponRefundCount;

    /**
     * 退款代金券.
     */
    @SignIgnore
    private Map<String, WeChatPayCoupon> refundCoupons;

    /**
     * 获取退款代金券.
     *
     * @return 代金券 Map, key 为微信支付文档描述的数字下标
     */
    public Map<String, WeChatPayCoupon> getRefundCoupons() {
        if (null == this.refundCoupons && null != this.otherMap) {
            final Map<String, BiConsumer<String, WeChatPayCoupon>> mappingMap = new HashMap<>();
            mappingMap.put("coupon_refund_id_", (val, coupon) -> coupon.setId(val));
            mappingMap.put("coupon_type_", (val, coupon) -> coupon.setType(WeChatPayCoupon.Type.valueOf(val)));
            mappingMap.put("coupon_refund_fee_", (val, coupon) -> coupon.setFee(Integer.valueOf(val)));
            this.refundCoupons = WeChatPayUtils.dynamicMapping(this.otherMap, mappingMap, WeChatPayCoupon::new);
        }
        return this.refundCoupons;
    }
}
