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

import cn.javaer.wechat.sdk.pay.WeChatPayUtils;
import cn.javaer.wechat.sdk.pay.support.SignIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 微信支付-查询退款-响应.
 *
 * @author zhangpeng
 */
@Getter
@Setter
@ToString(callSuper = true, exclude = {"refundMap"})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class RefundQueryResponse extends WeChatPayResponse {

    @XmlElement(name = "total_refund_count")
    private Integer totalRefundCount;

    @XmlElement(name = "transaction_id")
    private String transactionId;

    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    @XmlElement(name = "total_fee")
    private Integer totalFee;

    @XmlElement(name = "settlement_total_fee")
    private Integer settlementTotalFee;

    @XmlElement(name = "fee_type")
    private String feeType;

    @XmlElement(name = "cash_fee")
    private Integer cashFee;

    @XmlElement(name = "refund_count")
    private Integer refundCount;

    @SignIgnore
    private Map<String, Refund> refundMap;

    @Override
    public void beforeSign() {
        if (null == this.refundMap && null != this.otherElements) {
            final Map<String, BiConsumer<String, Refund>> mappingMap = new HashMap<>(11);
            mappingMap.put("out_refund_no_", (val, coupon) -> coupon.setOutRefundNo(val));
            mappingMap.put("refund_id_", (val, coupon) -> coupon.setRefundId(val));
            mappingMap.put("refund_channel_", (val, coupon) -> coupon.setRefundChannel(val));
            mappingMap.put("refund_fee_", (val, coupon) -> coupon.setRefundFee(Integer.valueOf(val)));
            mappingMap.put("settlement_refund_fee_",
                (val, coupon) -> coupon.setSettlementRefundFee(Integer.valueOf(val)));
            mappingMap.put("coupon_refund_fee_", (val, coupon) -> coupon.setCouponRefundFee(Integer.valueOf(val)));
            mappingMap.put("coupon_refund_count_", (val, coupon) -> coupon.setCouponRefundCount(Integer.valueOf(val)));
            mappingMap.put("refund_status_", (val, coupon) -> coupon.setRefundStatus(val));
            mappingMap.put("refund_account_", (val, coupon) -> coupon.setRefundAccount(val));
            mappingMap.put("refund_recv_accout_", (val, coupon) -> coupon.setRefundRecvAccout(val));
            mappingMap.put("refund_success_time_", (val, coupon) -> coupon.setRefundSuccessTime(val));

            this.refundMap = WeChatPayUtils.dynamicMapping(
                this.otherElements, Collections.unmodifiableMap(mappingMap), Refund::new);

            for (final Map.Entry<String, Refund> entry : this.refundMap.entrySet()) {
                final String key = entry.getKey();

                final Map<String, BiConsumer<String, Coupon>> mappingMap2 = new HashMap<>(3);
                mappingMap2.put("coupon_type_" + key + "_",
                    (val, coupon) -> coupon.setType(Coupon.Type.valueOf(val)));
                mappingMap2.put("coupon_refund_id_" + key + "_", (val, coupon) -> coupon.setId(val));
                mappingMap2.put("coupon_refund_fee_" + key + "_", (val, coupon) -> coupon.setFee(Integer.valueOf(val)));

                final Map<String, Coupon> couponMap = WeChatPayUtils.dynamicMapping(
                    this.otherElements, Collections.unmodifiableMap(mappingMap2), Coupon::new);

                entry.getValue().setRefundCoupons(couponMap);
            }
        }

    }

}
