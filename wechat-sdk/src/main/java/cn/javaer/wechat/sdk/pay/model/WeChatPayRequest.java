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

import cn.javaer.wechat.sdk.pay.WeChatPayConfigurator;
import cn.javaer.wechat.sdk.pay.WeChatPayUtils;
import cn.javaer.wechat.sdk.pay.support.SignIgnore;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;

/**
 * 微信支付-基本请求信息.
 *
 * @author zhangpeng
 */
@Getter
public abstract class WeChatPayRequest {
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
    @SignIgnore
    @XmlElement(name = "sign")
    private String sign;

    /**
     * 签名类型.
     */
    @XmlElement(name = "sign_type")
    private String signType;

    /**
     * 赋值配置信息并生成签名, 在其它字段赋值后调用以确保签名正确.
     */
    protected void configureAndSign() {

        final WeChatPayConfigurator configurator = WeChatPayConfigurator.DEFAULT;

        this.appid = configurator.getAppid();
        this.mchId = configurator.getMchId();

        this.nonceStr = WeChatUtils.uuid32();

        this.sign = WeChatPayUtils.generateSign(this, configurator.getMchKey());
    }
}
