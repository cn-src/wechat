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

package cn.javaer.wechat.sdk.pay;

import cn.javaer.wechat.sdk.pay.model.WeChatPayCloseOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayCloseOrderResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayOrderQueryRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayOrderQueryResponse;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderRequest;
import cn.javaer.wechat.sdk.pay.model.WeChatPayUnifiedOrderResponse;

/**
 * 微信支付客户端接口.
 *
 * @author zhangpeng
 */
public interface WeChatPayClient {

    String BASE_PATH = "https://api.mch.weixin.qq.com";

    String UNIFIED_ORDER_PATH = "/pay/unifiedorder";
    String ORDER_QUERY_PATH = "/pay/orderquery";
    String CLOSE_ORDER_PATH = "/pay/closeorder";
    String PAY_REFUND_PATH = "/secapi/pay/refund";

    /**
     * 统一下单.
     *
     * @param request request
     *
     * @return response
     *
     * @throws WeChatPayException WeChatPayException
     */
    WeChatPayUnifiedOrderResponse unifiedOrder(WeChatPayUnifiedOrderRequest request) throws WeChatPayException;

    /**
     * 查询订单.
     *
     * @param request request
     *
     * @return response
     *
     * @throws WeChatPayException WeChatPayException
     */
    WeChatPayOrderQueryResponse orderQuery(WeChatPayOrderQueryRequest request) throws WeChatPayException;

    /**
     * 关闭订单.
     *
     * @param request request
     *
     * @return response
     *
     * @throws WeChatPayException WeChatPayException
     */
    WeChatPayCloseOrderResponse closeOrder(WeChatPayCloseOrderRequest request) throws WeChatPayException;
}