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
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 微信支付-基本响应信息.
 *
 * @author zhangpeng
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class WeChatPayResponse {
    public static final String SUCCESS = "SUCCESS";

    @XmlElement(name = "return_code")
    private String returnCode;

    @XmlElement(name = "return_msg")
    private String returnMsg;

    @XmlElement(name = "appid")
    private String appid;

    @XmlElement(name = "mch_id")
    private String mchId;

    @XmlElement(name = "sub_appid")
    private String subAppid;

    @XmlElement(name = "sub_mch_id")
    private String subMchId;

    @XmlElement(name = "nonce_str")
    private String nonceStr;

    @XmlElement(name = "generateSign")
    private String sign;

    @XmlElement(name = "result_code")
    private String resultCode;

    @XmlElement(name = "err_code")
    private String errCode;

    @XmlElement(name = "err_code_des")
    private String errCodeDes;

    public void check() {
        WeChatPayUtils.checkSign(this, WeChatPayConfigurator.INSTANCE.getMchKey());
        WeChatPayUtils.checkSuccess(this);
    }

    public boolean isSuccessful() {
        return WeChatPayUtils.isSuccessful(this, WeChatPayConfigurator.INSTANCE.getMchKey());
    }
}
