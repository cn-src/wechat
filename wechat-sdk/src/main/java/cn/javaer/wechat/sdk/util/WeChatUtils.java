/*
 *    Copyright 2017 zhangpeng
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cn.javaer.wechat.sdk.util;

import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.codec.binary.Base64;

import java.util.StringJoiner;
import java.util.UUID;

/**
 * @author zhangpeng
 */
@SuppressWarnings("WeakerAccess")
public class WeChatUtils
{
    private WeChatUtils() {}
    
    public static String toHtmlImgBase64(final String str)
    {
        return "data:image/jpg;base64," + Base64.encodeBase64String(QRCode.from(str).withSize(300, 300).stream().toByteArray());
    }
    
    public static String uuid()
    {
        final StringBuilder sb = new StringBuilder(UUID.randomUUID().toString());
        sb.deleteCharAt(8);
        sb.deleteCharAt(12);
        sb.deleteCharAt(16);
        sb.deleteCharAt(20);
        return sb.toString();
    }
    
    public static String joinPath(final String... pathItems)
    {
        if (null == pathItems || pathItems.length <= 0)
        {
            throw new IllegalArgumentException("'pathItems' must not be empty");
        }
        final StringJoiner sj = new StringJoiner("/");
        for (final String item : pathItems)
        {
            String fixItem = item;
            if (fixItem.endsWith("/"))
            {
                fixItem = fixItem.substring(0, fixItem.length() - 1);
            }
            if (fixItem.startsWith("/"))
            {
                fixItem = fixItem.substring(1);
            }
            sj.add(fixItem);
        }
        return sj.toString();
    }
    
}
