package cn.javaer.wechat.pay.spring.boot.autoconfigure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangpeng
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnifiedOrderRequest
{
    private String  goodsName;
    private String  orderId;
    private Integer totalFee;
    private String  productId;
}
