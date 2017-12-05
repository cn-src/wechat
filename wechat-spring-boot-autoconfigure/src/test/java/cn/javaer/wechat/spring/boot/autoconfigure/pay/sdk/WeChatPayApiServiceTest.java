// package cn.javaer.wechat.pay.spring.boot.autoconfigure.sdk;
//
// import org.junit.Before;
// import org.junit.Test;
// import retrofit2.Retrofit;
// import retrofit2.mock.BehaviorDelegate;
// import retrofit2.mock.MockRetrofit;
// import retrofit2.mock.NetworkBehavior;
//
// /**
//  * @author zhangpeng
//  */
// public class WeChatPayApiServiceTest
// {
//     private MockWeChatPayApiService mockApiService;
//
//     @Before
//     public void setUp() throws Exception
//     {
//         // Create a very simple Retrofit adapter which points the GitHub API.
//         final Retrofit retrofit = new Retrofit.Builder()
//             .baseUrl("https://mock.com")
//             .build();
//
//         // Create a MockRetrofit object with a NetworkBehavior which manages the fake behavior of calls.
//         final NetworkBehavior behavior = NetworkBehavior.create();
//         final MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
//             .networkBehavior(behavior)
//             .build();
//
//         final BehaviorDelegate<WeChatPayApiService> mockServiceDelegate = mockRetrofit.create(WeChatPayApiService.class);
//         this.mockApiService = new MockWeChatPayApiService(mockServiceDelegate);
//     }
//
//     @Test
//     public void unifiedOrderWithNative() throws Exception
//     {
//         final WeChatPayApiUnifiedOrderRequest request = WeChatPayApiUnifiedOrderRequest.builder()
//             .appId("demo")
//             .build();
//
//         this.mockApiService.unifiedOrderWithNative(request).execute();
//     }
//
// }