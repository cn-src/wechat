package cn.javaer.wechat.sdk.pay.model;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.util.Objects;

/**
 * Abstract base class for {@link WeChatPayResponse} specific assertions - Generated by CustomAssertionGenerator.
 */
@javax.annotation.Generated(value="assertj-assertions-generator")
public abstract class AbstractWeChatPayResponseAssert<S extends AbstractWeChatPayResponseAssert<S, A>, A extends WeChatPayResponse> extends AbstractObjectAssert<S, A> {

  /**
   * Creates a new <code>{@link AbstractWeChatPayResponseAssert}</code> to make assertions on actual WeChatPayResponse.
   * @param actual the WeChatPayResponse we want to make assertions on.
   */
  protected AbstractWeChatPayResponseAssert(A actual, Class<S> selfType) {
    super(actual, selfType);
  }

  /**
   * Verifies that the actual WeChatPayResponse's appid is equal to the given one.
   * @param appid the given appid to compare the actual WeChatPayResponse's appid to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's appid is not equal to the given one.
   */
  public S hasAppid(String appid) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting appid of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualAppid = actual.getAppid();
    if (!Objects.areEqual(actualAppid, appid)) {
      failWithMessage(assertjErrorMessage, actual, appid, actualAppid);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's errCode is equal to the given one.
   * @param errCode the given errCode to compare the actual WeChatPayResponse's errCode to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's errCode is not equal to the given one.
   */
  public S hasErrCode(String errCode) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting errCode of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualErrCode = actual.getErrCode();
    if (!Objects.areEqual(actualErrCode, errCode)) {
      failWithMessage(assertjErrorMessage, actual, errCode, actualErrCode);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's errCodeDes is equal to the given one.
   * @param errCodeDes the given errCodeDes to compare the actual WeChatPayResponse's errCodeDes to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's errCodeDes is not equal to the given one.
   */
  public S hasErrCodeDes(String errCodeDes) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting errCodeDes of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualErrCodeDes = actual.getErrCodeDes();
    if (!Objects.areEqual(actualErrCodeDes, errCodeDes)) {
      failWithMessage(assertjErrorMessage, actual, errCodeDes, actualErrCodeDes);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's mchId is equal to the given one.
   * @param mchId the given mchId to compare the actual WeChatPayResponse's mchId to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's mchId is not equal to the given one.
   */
  public S hasMchId(String mchId) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting mchId of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualMchId = actual.getMchId();
    if (!Objects.areEqual(actualMchId, mchId)) {
      failWithMessage(assertjErrorMessage, actual, mchId, actualMchId);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's nonceStr is equal to the given one.
   * @param nonceStr the given nonceStr to compare the actual WeChatPayResponse's nonceStr to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's nonceStr is not equal to the given one.
   */
  public S hasNonceStr(String nonceStr) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting nonceStr of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualNonceStr = actual.getNonceStr();
    if (!Objects.areEqual(actualNonceStr, nonceStr)) {
      failWithMessage(assertjErrorMessage, actual, nonceStr, actualNonceStr);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's resultCode is equal to the given one.
   * @param resultCode the given resultCode to compare the actual WeChatPayResponse's resultCode to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's resultCode is not equal to the given one.
   */
  public S hasResultCode(String resultCode) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting resultCode of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualResultCode = actual.getResultCode();
    if (!Objects.areEqual(actualResultCode, resultCode)) {
      failWithMessage(assertjErrorMessage, actual, resultCode, actualResultCode);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's returnCode is equal to the given one.
   * @param returnCode the given returnCode to compare the actual WeChatPayResponse's returnCode to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's returnCode is not equal to the given one.
   */
  public S hasReturnCode(String returnCode) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting returnCode of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualReturnCode = actual.getReturnCode();
    if (!Objects.areEqual(actualReturnCode, returnCode)) {
      failWithMessage(assertjErrorMessage, actual, returnCode, actualReturnCode);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's returnMsg is equal to the given one.
   * @param returnMsg the given returnMsg to compare the actual WeChatPayResponse's returnMsg to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's returnMsg is not equal to the given one.
   */
  public S hasReturnMsg(String returnMsg) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting returnMsg of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualReturnMsg = actual.getReturnMsg();
    if (!Objects.areEqual(actualReturnMsg, returnMsg)) {
      failWithMessage(assertjErrorMessage, actual, returnMsg, actualReturnMsg);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's sign is equal to the given one.
   * @param sign the given sign to compare the actual WeChatPayResponse's sign to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's sign is not equal to the given one.
   */
  public S hasSign(String sign) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting sign of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualSign = actual.getSign();
    if (!Objects.areEqual(actualSign, sign)) {
      failWithMessage(assertjErrorMessage, actual, sign, actualSign);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's subAppid is equal to the given one.
   * @param subAppid the given subAppid to compare the actual WeChatPayResponse's subAppid to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's subAppid is not equal to the given one.
   */
  public S hasSubAppid(String subAppid) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting subAppid of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualSubAppid = actual.getSubAppid();
    if (!Objects.areEqual(actualSubAppid, subAppid)) {
      failWithMessage(assertjErrorMessage, actual, subAppid, actualSubAppid);
    }

    // return the current assertion for method chaining
    return myself;
  }

  /**
   * Verifies that the actual WeChatPayResponse's subMchId is equal to the given one.
   * @param subMchId the given subMchId to compare the actual WeChatPayResponse's subMchId to.
   * @return this assertion object.
   * @throws AssertionError - if the actual WeChatPayResponse's subMchId is not equal to the given one.
   */
  public S hasSubMchId(String subMchId) {
    // check that actual WeChatPayResponse we want to make assertions on is not null.
    isNotNull();

    // overrides the default error message with a more explicit one
    String assertjErrorMessage = "\nExpecting subMchId of:\n  <%s>\nto be:\n  <%s>\nbut was:\n  <%s>";

    // null safe check
    String actualSubMchId = actual.getSubMchId();
    if (!Objects.areEqual(actualSubMchId, subMchId)) {
      failWithMessage(assertjErrorMessage, actual, subMchId, actualSubMchId);
    }

    // return the current assertion for method chaining
    return myself;
  }

}
