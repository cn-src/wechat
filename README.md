[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
# WeChat 微信开发
目前支持的功能
> * 微信公众号网页授权登录
> * 微信支付-扫码支付模式二
> * 微信支付-公众号支付

# 说明
对微信 api 的调用由 WeChatXXXClient 相关接口发起请求
> * WeChatMpClient 微信公众号客户端
> * WeChatPayClient 微信支付客户端

对微信回调接口已提供相关 Controller，并采用 spring 事件机制封装
> * WeChatMpAuthenticationSuccessEvent 微信公众号授权成功事件
> * WeChatPayUnifiedOrderEvent 支付统一下单事件
> * WeChatPayNotifyResultEvent 支付结果通知事件
