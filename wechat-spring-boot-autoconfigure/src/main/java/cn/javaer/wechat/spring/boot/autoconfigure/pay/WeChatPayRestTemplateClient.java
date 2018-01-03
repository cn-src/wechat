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
import cn.javaer.wechat.sdk.pay.model.CloseOrderRequest;
import cn.javaer.wechat.sdk.pay.model.CloseOrderResponse;
import cn.javaer.wechat.sdk.pay.model.OrderQueryRequest;
import cn.javaer.wechat.sdk.pay.model.OrderQueryResponse;
import cn.javaer.wechat.sdk.pay.model.RefundQueryRequest;
import cn.javaer.wechat.sdk.pay.model.RefundQueryResponse;
import cn.javaer.wechat.sdk.pay.model.RefundRequest;
import cn.javaer.wechat.sdk.pay.model.RefundResponse;
import cn.javaer.wechat.sdk.pay.model.UnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.UnifiedOrderResponse;
import cn.javaer.wechat.sdk.util.WeChatUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * 微信支付客户端-RestTemplate实现.
 *
 * @author zhangpeng
 */
public class WeChatPayRestTemplateClient implements WeChatPayClient {

    private final RestTemplate restTemplate;

    private final WeChatPayProperties weChatPayProperties;

    /**
     * Instantiates a new WeChatPayRestTemplateClient.
     *
     * @param restTemplate RestTemplate
     * @param weChatPayProperties WeChatPayProperties
     */
    public WeChatPayRestTemplateClient(
        final RestTemplate restTemplate, final WeChatPayProperties weChatPayProperties) {
        Objects.requireNonNull(restTemplate);
        Objects.requireNonNull(weChatPayProperties);

        this.restTemplate = restTemplate;
        this.weChatPayProperties = weChatPayProperties;
    }

    @Override
    public UnifiedOrderResponse unifiedOrder(
        final UnifiedOrderRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.UNIFIED_ORDER_PATH,
            request,
            UnifiedOrderResponse.class)
            .getBody();
    }

    @Override
    public OrderQueryResponse orderQuery(
        final OrderQueryRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.ORDER_QUERY_PATH,
            request,
            OrderQueryResponse.class)
            .getBody();
    }

    @Override
    public CloseOrderResponse closeOrder(
        final CloseOrderRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.CLOSE_ORDER_PATH,
            request,
            CloseOrderResponse.class)
            .getBody();
    }

    @Override
    public RefundResponse refund(
        final RefundRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.REFUND_PATH,
            request,
            RefundResponse.class)
            .getBody();
    }

    @Override
    public RefundQueryResponse refundQuery(
        final RefundQueryRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.REFUND_QUERY_PATH,
            request,
            RefundQueryResponse.class)
            .getBody();
    }

    private <Q, S> ResponseEntity<S> postForEntity(
        final String apiPath, final Q request, final Class<S> responseClass) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        final HttpEntity<Q> httpEntity = new HttpEntity<>(request, headers);
        return this.restTemplate.postForEntity(
            WeChatUtils.joinPath(this.weChatPayProperties.getApiBasePath(), apiPath),
            httpEntity,
            responseClass);
    }
}
