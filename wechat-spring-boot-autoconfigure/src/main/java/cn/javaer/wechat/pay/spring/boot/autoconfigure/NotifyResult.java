package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import lombok.Data;

/**
 * @author zhangpeng
 */
@Data
public class NotifyResult
{
    private Boolean hasSuccessful;
    private String  message;
    //
    private String  nonceStr;
    
    private String errCode;
    
    private String errCodeDes;
    
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
