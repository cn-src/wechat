package cn.javaer.wechat.spring.boot.autoconfigure.pay;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangpeng
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class RestTemplateWeChatPayClientTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private RestTemplateWeChatPayClient weChatPayClient;

    @Before
    public void setUp() throws Exception {
//        this.weChatPayClient = new RestTemplateWeChatPayClient(restTemplate, new WeChatPayProperties());
    }

    @Test
    public void unifiedOrder() {

    }

    @Test
    public void orderQuery() {
    }
}