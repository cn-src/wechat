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

import cn.javaer.wechat.sdk.util.WeChatUtils;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

/**
 * 微信支付-查询退款-响应.
 *
 * @author zhangpeng
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class WeChatPayRefundQueryResponse extends WeChatPayResponse {

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

    @XmlAnyElement
    @Setter(AccessLevel.PACKAGE)
    @Getter(AccessLevel.PACKAGE)
    private List<Element> other;

    private Map<String, String> otherValues;

    public Map<String, String> getOtherValues() {
        return WeChatUtils.elementsToMap(this.other);
    }
}
