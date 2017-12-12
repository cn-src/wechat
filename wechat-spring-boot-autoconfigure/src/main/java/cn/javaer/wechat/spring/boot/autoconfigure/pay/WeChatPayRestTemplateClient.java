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

package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import cn.javaer.wechat.sdk.pay.WeChatPayClient;
import cn.javaer.wechat.sdk.pay.WeChatPayException;
import cn.javaer.wechat.sdk.pay.model.WeChatPayCloseOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayCloseOrderResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayOrderQueryRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayOrderQueryResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 微信支付客户端-RestTemplate实现.
 *
 * @author zhangpeng
 */
public class WeChatPayRestTemplateClient implements WeChatPayClient {

    private final RestTemplate restTemplate;

    private final WeChatPayProperties weChatPayProperties;

    public WeChatPayRestTemplateClient(final RestTemplate restTemplate, final WeChatPayProperties weChatPayProperties) {
        this.restTemplate = restTemplate;
        this.weChatPayProperties = weChatPayProperties;
    }

    @Override
    public WeChatPayUnifiedOrderResponse unifiedOrder(final WeChatPayUnifiedOrderRequest request)
            throws WeChatPayException {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        final HttpEntity<WeChatPayUnifiedOrderRequest> httpEntity = new HttpEntity<>(request, headers);
        final String url = WeChatUtils.joinPath(
                weChatPayProperties.getApiBasePath(), WeChatPayClient.UNIFIED_ORDER_PATH);
        final ResponseEntity<WeChatPayUnifiedOrderResponse> responseEntity = restTemplate.postForEntity(
                url,
                httpEntity,
                WeChatPayUnifiedOrderResponse.class);

        return responseEntity.getBody();
    }

    @Override
    public WeChatPayOrderQueryResponse orderQuery(final WeChatPayOrderQueryRequest request) throws WeChatPayException {
        return null;
    }

    @Override
    public WeChatPayCloseOrderResponse closeOrder(final WeChatPayCloseOrderRequest request) throws WeChatPayException {
        // TODO
        return null;
    }
}
