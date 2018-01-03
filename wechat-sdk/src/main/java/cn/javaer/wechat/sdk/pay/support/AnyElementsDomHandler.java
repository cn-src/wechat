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

package cn.javaer.wechat.sdk.pay.support;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import java.util.Map;
import java.util.TreeMap;

/**
 * 处理接收微信动态字段以及 api 变更产生的未知字段.
 *
 * @author zhangpeng
 * @see javax.xml.bind.annotation.W3CDomHandler
 */
public class AnyElementsDomHandler implements DomHandler<Map<String, String>, DOMResult> {

    private final Map<String, String> otherElements = new TreeMap<>();

    @Override
    public DOMResult createUnmarshaller(final ValidationEventHandler errorHandler) {
        return new DOMResult();
    }

    @Override
    public Map<String, String> getElement(final DOMResult rt) {
        final Node n = rt.getNode();
        if (n instanceof Document) {
            final Element element = ((Document) n).getDocumentElement();
            this.otherElements.put(element.getNodeName(), element.getTextContent());
        }
        if (n instanceof Element) {
            final Element element = (Element) n;
            this.otherElements.put(element.getNodeName(), element.getTextContent());
        }
        if (n instanceof DocumentFragment) {
            final Element element = (Element) n.getChildNodes().item(0);
            this.otherElements.put(element.getNodeName(), element.getTextContent());
        }

        return this.otherElements;
    }

    @Override
    public Source marshal(final Map<String, String> n, final ValidationEventHandler errorHandler) {
        throw new UnsupportedOperationException();
    }
}
