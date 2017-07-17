package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import lombok.Data;

/**
 * @author zhangpeng
 */
@Data
public class UnifiedOrderResponse
{
    private String nonceStr;
    private String prepayId;
    private String codeUrl;
}
