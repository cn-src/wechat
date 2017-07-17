package cn.javaer.wechat.sdk.util;

import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.codec.binary.Base64;

/**
 * @author zhangpeng
 */
public class QrCodeUtil
{
    public static String toHtmlImgBase64(final String str)
    {
        return "data:image/jpg;base64,"+Base64.encodeBase64String(QRCode.from(str).withSize(300,300).stream().toByteArray());
    }
}
