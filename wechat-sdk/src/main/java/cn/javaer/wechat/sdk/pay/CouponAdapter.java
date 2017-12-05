package cn.javaer.wechat.sdk.pay;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Map;

/**
 * @author zhangpeng
 */
public class CouponAdapter extends XmlAdapter<WeChatPayNotifyResult, Map<String, Coupon>> {

    @Override
    public Map<String, Coupon> unmarshal(WeChatPayNotifyResult v) throws Exception {
        System.out.println("=========" + v);
        return null;
    }

    @Override
    public WeChatPayNotifyResult marshal(Map<String, Coupon> v) throws Exception {
        return null;
    }

}
