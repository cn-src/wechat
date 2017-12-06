/*
 *    Copyright 2017 the original author or authors.
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

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangpeng
 */
public class WeChatUtils {
    private WeChatUtils() {}
    
    public static String uuid() {
        StringBuilder sb = new StringBuilder(UUID.randomUUID().toString());
        sb.deleteCharAt(8);
        sb.deleteCharAt(12);
        sb.deleteCharAt(16);
        sb.deleteCharAt(20);
        return sb.toString();
    }
    
    public static String joinPath(String... pathItems) {
        if (null == pathItems || pathItems.length <= 0) {
            throw new IllegalArgumentException("'pathItems' must not be empty");
        }
    
        return Stream.of(pathItems).map(pathItem -> {
            if (pathItem.endsWith("/")) {
                return pathItem.substring(0, pathItem.length() - 1);
            } else if (pathItem.startsWith("/")) {
                return pathItem.substring(1);
            } else {
                return pathItem;
            }
        }).collect(Collectors.joining("/"));
    }
    
}
