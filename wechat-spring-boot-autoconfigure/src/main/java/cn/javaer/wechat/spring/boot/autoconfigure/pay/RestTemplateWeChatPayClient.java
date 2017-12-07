package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayException;
import cn.javaer.wechat.sdk.pay.WeChatPayOrderQueryRequest;
import cn.javaer.wechat.sdk.pay.WeChatPayOrderQueryResponse;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.WeChatPayUnifiedOrderResponse;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangpeng
 */
public class RestTemplateWeChatPayClient implements WeChatPayClient {

    private final RestTemplate restTemplate;

    private final WeChatPayProperties weChatPayProperties;

    public RestTemplateWeChatPayClient(RestTemplate restTemplate, WeChatPayProperties weChatPayProperties) {
        this.restTemplate = restTemplate;
        this.weChatPayProperties = weChatPayProperties;
    }

    @Override
    public WeChatPayUnifiedOrderResponse unifiedOrder(WeChatPayUnifiedOrderRequest request) throws WeChatPayException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<WeChatPayUnifiedOrderRequest> httpEntity = new HttpEntity<>(request, headers);
        String url = WeChatUtils.joinPath(weChatPayProperties.getApiBasePath(), WeChatPayClient.UNIFIED_ORDER_URL);
        ResponseEntity<WeChatPayUnifiedOrderResponse> responseEntity = restTemplate.postForEntity(
                url,
                httpEntity,
                WeChatPayUnifiedOrderResponse.class);

        return responseEntity.getBody();
    }

    @Override
    public WeChatPayOrderQueryResponse orderQuery(WeChatPayOrderQueryRequest request) throws WeChatPayException {
        return null;
    }
}
