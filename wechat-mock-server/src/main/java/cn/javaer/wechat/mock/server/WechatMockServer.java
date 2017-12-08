package cn.javaer.wechat.mock.server;

import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;

/**
 * @author zhangpeng
 */
public class WechatMockServer {
    public static void main(String[] args) {

        final String[] newArgs;

        if (args != null) {
            newArgs = new String[args.length + 2];
            System.arraycopy(args, 0, newArgs, 0, args.length);
        } else {
            newArgs = new String[2];
        }
        newArgs[newArgs.length - 2] = "--extensions";
        newArgs[newArgs.length - 1] = "org.wiremock.webhooks.Webhooks";
        WireMockServerRunner.main(newArgs);
    }
}
