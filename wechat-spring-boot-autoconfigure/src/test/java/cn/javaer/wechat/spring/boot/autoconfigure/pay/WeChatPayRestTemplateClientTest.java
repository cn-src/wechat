package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponseAssert.assertThat;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * @author zhangpeng
 */
public class WeChatPayRestTemplateClientTest {

    private WeChatPayRestTemplateClient weChatPayClient;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort()); // No-args constructor defaults to port 8080

    @Before
    public void setUp() throws Exception {
        final WeChatPayProperties weChatPayProperties = new WeChatPayProperties();
        weChatPayProperties.setApiBasePath("http://localhost:" + wireMockRule.port());
        this.weChatPayClient = new WeChatPayRestTemplateClient(new RestTemplate(), weChatPayProperties);
    }

    @Test
    public void unifiedOrder() {

        stubFor(post(urlEqualTo(WeChatPayClient.UNIFIED_ORDER_URL))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_XML_VALUE)
                        .withBody("<xml><return_code>SUCCESS</return_code><result_code>SUCCESS</result_code></xml>")));

        WeChatPayUnifiedOrderRequest request = WeChatPayUnifiedOrderRequest.builder().build();
        final WeChatPayUnifiedOrderResponse unifiedOrderResponse = weChatPayClient.unifiedOrder(request);

        assertThat(unifiedOrderResponse)
                .hasReturnCode("SUCCESS")
                .hasResultCode("SUCCESS");
    }

    @Test
    public void orderQuery() {
    }
}