package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayConfigurator;
import cn.javaer.wechat.sdk.pay.model.UnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.UnifiedOrderResponse;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static cn.javaer.wechat.sdk.pay.model.UnifiedOrderResponseAssert.assertThat;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * @author zhangpeng
 */
public class WeChatPayRestTemplateClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort()); // No-args constructor defaults to port 8080
    private WeChatPayRestTemplateClient weChatPayClient;

    @Before
    public void setUp() throws Exception {
        final WeChatPayProperties weChatPayProperties = new WeChatPayProperties();
        weChatPayProperties.setApiBasePath("http://localhost:" + this.wireMockRule.port());
        this.weChatPayClient = new WeChatPayRestTemplateClient(new RestTemplate(), weChatPayProperties);
    }

    @Test
    public void unifiedOrder() {

        stubFor(post(urlEqualTo(WeChatPayClient.UNIFIED_ORDER_PATH))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_XML_VALUE)
                        .withBody("<xml><return_code>SUCCESS</return_code><result_code>SUCCESS</result_code></xml>")));
        WeChatPayConfigurator.DEFAULT.setMchKey("key");
        final UnifiedOrderRequest request = UnifiedOrderRequest.createWithNative("body", "outTradeNo", 100);
        final UnifiedOrderResponse unifiedOrderResponse = this.weChatPayClient.unifiedOrder(request);

        assertThat(unifiedOrderResponse)
                .hasReturnCode("SUCCESS")
                .hasResultCode("SUCCESS");
    }

    @Test
    public void orderQuery() {
    }
}