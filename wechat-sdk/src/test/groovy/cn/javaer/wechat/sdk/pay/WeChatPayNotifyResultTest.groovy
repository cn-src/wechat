package cn.javaer.wechat.sdk.pay

import spock.lang.Specification

import javax.xml.bind.JAXBContext

/**
 * @author zhangpeng
 */
class WeChatPayNotifyResultTest extends Specification {
    def "Name"() {
        when:
        def a = ""
        JAXBContext context = JAXBContext.newInstance(WeChatPayNotifyResult)
        def unmarshaller = context.createUnmarshaller()
        StringReader reader = new StringReader("<xml><coupon_fee>100</coupon_fee></xml>")

        def unmarshal = (WeChatPayNotifyResult) unmarshaller.unmarshal(reader)
        println(unmarshal)


        then:
        a == a
    }
}
