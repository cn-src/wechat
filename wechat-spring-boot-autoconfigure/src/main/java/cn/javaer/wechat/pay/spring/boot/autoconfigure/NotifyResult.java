package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import lombok.Data;

/**
 * @author zhangpeng
 */
@Data
public class NotifyResult
{
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
