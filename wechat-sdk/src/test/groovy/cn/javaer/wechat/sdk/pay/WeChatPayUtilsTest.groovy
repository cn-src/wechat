/*
 *    Copyright 2017 zhangpeng
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

package cn.javaer.wechat.sdk.pay

import spock.lang.Specification

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

/**
 * @author zhangpeng
 */
class WeChatPayUtilsTest extends Specification {
    def "Sign"() {
        when:
        final WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder()
                .body("body")
                .nonceStr("nonceStr")
                .outTradeNo("outTradeNo")
                .productId("productId")
                .totalFee(100)
                .appId("appId")
                .mchId("mchId")
                .notifyUrl("notifyUrl")
                .spbillCreateIp("spbillCreateIp")
                .tradeType("NATIVE")
                .sign("") // 不参与签名算法
                .signType("MD5") // 参与签名算法
                .build()
        request.setSign(WeChatPayUtils.sign(request, "key"))
        println genXml(request)

        then:
        // 官方校验工具 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=20_1
        "EFAF8A5CD089B3749AF9EAEE6C6B4156" == WeChatPayUtils.sign(request, "key")
    }

    private String genXml(object) {
        JAXBContext context = JAXBContext.newInstance(object.getClass())
        Marshaller marshaller = context.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8")
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true)
        final StringWriter sw = new StringWriter();
        marshaller.marshal(object, sw)
        return sw.toString()
    }
}
