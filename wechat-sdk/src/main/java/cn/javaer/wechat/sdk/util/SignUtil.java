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

import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author zhangpeng
 */
public class SignUtil
{
    public static String sign(final Object data, final String key)
    {
        
        final Class<?> clazz = data.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        final Map<String, String> dataMap = new TreeMap<>();
        
        try
        {
            for (final Field field : fields)
            {
                field.setAccessible(true);
                final Object objVal;
                objVal = field.get(data);
    
                if (null != objVal && !objVal.toString().isEmpty())
                {
                    final String dataKey = Optional.ofNullable(field.getAnnotation(XmlElement.class))
                        .map(XmlElement::name)
                        .orElse(field.getName());
                    if (!"sign".equals(dataKey))
                    {
                        dataMap.put(dataKey, objVal.toString());
                    }
                }
            }
        } catch (final IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        final StringBuilder sb = new StringBuilder();
        
        for (final Map.Entry<String, String> entry : dataMap.entrySet())
        {
            sb.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
        }
        sb.append("key").append('=').append(key);
        return DigestUtils.md5Hex(sb.toString()).toUpperCase();
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
}
