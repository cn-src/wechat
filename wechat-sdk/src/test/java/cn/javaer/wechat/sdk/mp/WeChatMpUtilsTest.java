package cn.javaer.wechat.sdk.mp;

import cn.javaer.wechat.sdk.mp.model.AuthorizeScope;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangpeng
 */
public class WeChatMpUtilsTest {

    @Test
    public void generateAuthorizeUrl() {
        final String authorizeUrl = WeChatMpUtils.generateAuthorizeUrl(
                "wx520c15f417810387",
                "https://chong.qq.com/php/index.php?d=&c=wxAdapter&m=mobileDeal&showwxpaytitle=1&vb2ctag=4_2030_5_1194_60",
                AuthorizeScope.BASE,
                "123");

        final String expected = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect";

        assertEquals(expected, authorizeUrl);

    }

    @Test
    public void generateAuthorizeUrlNoState() {
        final String authorizeUrl = WeChatMpUtils.generateAuthorizeUrl("wx520c15f417810387",
                "https://chong.qq.com/php/index.php?d=&c=wxAdapter&m=mobileDeal&showwxpaytitle=1&vb2ctag=4_2030_5_1194_60",
                AuthorizeScope.BASE);

        final String expected = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base#wechat_redirect";

        assertEquals(expected, authorizeUrl);

    }

}