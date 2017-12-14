/*
 * Copyright (c) 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.javaer.wechat.sdk.util;

import org.w3c.dom.Element;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 微信工具类.
 *
 * @author zhangpeng
 */
public class WeChatUtils {
    private WeChatUtils() {}

    /**
     * 生成长度为 32 的 uuid.
     *
     * @return uuid String
     */
    public static String uuid() {
        final StringBuilder sb = new StringBuilder(UUID.randomUUID().toString());
        sb.deleteCharAt(8);
        sb.deleteCharAt(12);
        sb.deleteCharAt(16);
        sb.deleteCharAt(20);
        return sb.toString();
    }

    /**
     * 拼接多个 path.
     *
     * @param pathItems Path items
     *
     * @return 拼接后的 path.
     */
    public static String joinPath(final String... pathItems) {
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

    public static Map<String, String> elementsToMap(final List<Element> elements) {
        if (null == elements || elements.isEmpty()) {
            return Collections.emptyMap();
        }

        final Map<String, String> map = new HashMap<>();
        for (final Element element : elements) {
            map.put(element.getNodeName(), element.getTextContent());
        }
        return map;
    }
}
