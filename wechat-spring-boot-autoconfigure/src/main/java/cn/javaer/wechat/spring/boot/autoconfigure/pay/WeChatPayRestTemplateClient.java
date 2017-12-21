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
import cn.javaer.wechat.sdk.pay.model.WeChatPayRefundQueryRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayRefundQueryResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayRefundRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayRefundResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;
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
    public WeChatPayUnifiedOrderResponse unifiedOrder(
        final WeChatPayUnifiedOrderRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.UNIFIED_ORDER_PATH,
            request,
            WeChatPayUnifiedOrderResponse.class)
            .getBody();
    }

    @Override
    public WeChatPayOrderQueryResponse orderQuery(
        final WeChatPayOrderQueryRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.ORDER_QUERY_PATH,
            request,
            WeChatPayOrderQueryResponse.class)
            .getBody();
    }

    @Override
    public WeChatPayCloseOrderResponse closeOrder(
        final WeChatPayCloseOrderRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.CLOSE_ORDER_PATH,
            request,
            WeChatPayCloseOrderResponse.class)
            .getBody();
    }

    @Override
    public WeChatPayRefundResponse refund(
        final WeChatPayRefundRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.REFUND_PATH,
            request,
            WeChatPayRefundResponse.class)
            .getBody();
    }

    @Override
    public WeChatPayRefundQueryResponse refundQuery(
        final WeChatPayRefundQueryRequest request) throws WeChatPayException {
        Objects.requireNonNull(request);

        return postForEntity(
            WeChatPayClient.REFUND_QUERY_PATH,
            request,
            WeChatPayRefundQueryResponse.class)
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
