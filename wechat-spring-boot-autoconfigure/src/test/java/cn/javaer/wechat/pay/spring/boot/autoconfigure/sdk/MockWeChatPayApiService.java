// package cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk;
//
// import retrofit2.Call;
// import retrofit2.mock.BehaviorDelegate;
//
// /**
//  * @author zhangpeng
//  */
// public class MockWeChatPayApiService implements WeChatPayApiService
// {
//     private final BehaviorDelegate<WeChatPayApiService> delegate;
//
//     public MockWeChatPayApiService(final BehaviorDelegate<WeChatPayApiService> delegate)
//     {
//         this.delegate = delegate;
//     }
//
//     @Override
//     public Call<WeChatPayApiUnifiedOrderResponse> unifiedOrder(final WeChatPayApiUnifiedOrderRequest request)
//     {
//         System.out.println(request);
//         return this.delegate.returningResponse("demo").unifiedOrder(request);
//     }
// }
