/*
 *    Copyright 2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import lombok.Data;

/**
 * @author zhangpeng
 */
@Data
public class NotifyResult {
    private String openId;
    
    private String isSubscribe;
    
    private String tradeType;
    
    private String bankType;
    
    private String settlementTotalFee;
    
    private String feeType;
    
    private String totalFee;
    
    private String cashFee;
    
    private String cashFeeType;
    
    private String couponFee;
    
    private String couponCount;
    
    private String transactionId;
    
    private String outTradeNo;
    
    private String attach;
    
    private String timeEnd;
}
