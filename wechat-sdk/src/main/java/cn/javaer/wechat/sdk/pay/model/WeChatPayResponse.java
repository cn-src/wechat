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
import cn.javaer.wechat.sdk.util.WeChatUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import java.util.Map;

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

    @XmlAnyElement
    @Setter(AccessLevel.PACKAGE)
    @Getter(AccessLevel.PACKAGE)
    private List<Element> other;

    @Setter(AccessLevel.PACKAGE)
    protected Map<String, String> otherMap;

    /**
     * 校验 this 的签名是否正确, 以及 returnCode, resultCode 是否为 'SUCCESS'.
     */
    public void check() {
        this.otherMap = WeChatUtils.elementsToMap(this.other);

        WeChatPayUtils.checkSign(this, WeChatPayConfigurator.INSTANCE.getMchKey());
        WeChatPayUtils.checkSuccess(this);
    }

    /**
     * 判断 this 的签名是否正确, 以及 returnCode, resultCode 是否为 'SUCCESS'.
     *
     * @return 签名正确以及状态都为 'SUCCESS' 时返回 true.
     */
    public boolean isSuccessful() {
        return WeChatPayUtils.isSuccessful(this, WeChatPayConfigurator.INSTANCE.getMchKey());
    }
}
