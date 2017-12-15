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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 微信支付-查询订单-响应.
 *
 * @author zhangpeng
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayOrderQueryResponse extends WeChatPayResponse {

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

    @XmlElement(name = "trade_state")
    private String tradeState;

    @XmlElement(name = "bank_type")
    private String bankType;

    @XmlElement(name = "detail")
    private String detail;

    @XmlElement(name = "total_fee")
    private String totalFee;

    @XmlElement(name = "fee_type")
    private String feeType;

    @XmlElement(name = "settlement_total_fee")
    private String settlementTotalFee;

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

    @XmlElement(name = "device_info")
    private String deviceInfo;

    @XmlElement(name = "attach")
    private String attach;

    @XmlElement(name = "time_end")
    private String timeEnd;

    @XmlElement(name = "trade_state_desc")
    private String tradeStateDesc;

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

    /**
     * 代金券.
     */
    private Map<String, Coupon> coupons;

    /**
     * 获取代金券.
     *
     * @return 代金券 Map, key 为微信支付文档描述的数字下标
     */
    public Map<String, Coupon> getCoupons() {
        if (null == this.coupons && null != this.otherMap) {
            final Map<String, BiConsumer<String, Coupon>> mappingMap = new HashMap<>();
            mappingMap.put("coupon_id_", (val, coupon) -> coupon.setId(val));
            mappingMap.put("coupon_type_", (val, coupon) -> coupon.setType(val));
            mappingMap.put("coupon_fee_", (val, coupon) -> coupon.setFee(Integer.valueOf(val)));
            WeChatPayUtils.dynamicMapping(this.otherMap, mappingMap, Coupon::new);
        }
        return this.coupons;
    }

    @Data
    public static class Coupon {
        private String id;
        private String type;
        private Integer fee;
    }
}
