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

    /**
     * 公众账号ID
     */
    @XmlElement(name = "appid")
    private String appid;

    /**
     * 商户号
     */
    @XmlElement(name = "mch_id")
    private String mchId;

    /**
     * 子商户公众账号ID
     */
    @XmlElement(name = "sub_appid")
    private String subAppId;

    /**
     * 子商户号
     */
    @XmlElement(name = "sub_mch_id")
    private String subMchId;

    /**
     * 设备号
     */
    @XmlElement(name = "device_info")
    private String deviceInfo;

    /**
     * 随机字符串
     */
    @XmlElement(name = "nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    @XmlElement(name = "sign")
    private String sign;

    /**
     * 签名类型
     */
    @XmlElement(name = "sign_type")
    private String signType;

    /**
     * 商品描述
     */
    @XmlElement(name = "body")
    private String body;

    /**
     * 商品详情
     */
    @XmlElement(name = "detail")
    private String detail;

    /**
     * 附加数据
     */
    @XmlElement(name = "attach")
    private String attach;

    /**
     * 商户订单号
     */
    @XmlElement(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 货币类型
     */
    @XmlElement(name = "fee_type")
    private String feeType;

    /**
     * 总金额
     */
    @XmlElement(name = "total_fee")
    private Integer totalFee;

    /**
     * 终端IP
     */
    @XmlElement(name = "spbill_create_ip")
    private String spbillCreateIp;

    /**
     * 交易起始时间
     */
    @XmlElement(name = "time_start")
    private String timeStart;

    /**
     * 交易结束时间
     */
    @XmlElement(name = "time_expire")
    private String timeExpire;

    /**
     * 订单优惠标记
     */
    @XmlElement(name = "goods_tag")
    private String goodsTag;

    /**
     * 通知地址
     */
    @XmlElement(name = "notify_url")
    private String notifyUrl;

    /**
     * 交易类型
     */
    @XmlElement(name = "trade_type")
    private String tradeType;

    /**
     * 商品ID
     */
    @XmlElement(name = "product_id")
    private String productId;

    /**
     * 指定支付方式
     */
    @XmlElement(name = "limit_pay")
    private String limitPay;

    /**
     * 用户标识
     */
    @XmlElement(name = "openid")
    private String openid;

    /**
     * 用户子标识
     */
    @XmlElement(name = "sub_openid")
    private String subOpenid;

    /**
     * 场景信息
     */
    @XmlElement(name = "scene_info")
    private String sceneInfo;
}
