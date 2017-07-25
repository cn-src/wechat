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

package cn.javaer.wechat.sdk.util

import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author zhangpeng
 */
class WeChatUtilsTest extends Specification {
    def "ToHtmlImgBase64"() {
        def imageBase64 = 'data:image/jpg;base64,iVBORw0KGgoAAAANSUhEUgAAASwAAAEsAQAAAABRBrPYAAABGklEQVR42u3aQc6DIBCG4XHlMTyqHtUjdMkKfoUBibX+XXQgbV4XJOjDagIfECW88zwEBoPBYLCvYk70mbbOMrkhLPpigLVjqaciNfUHWCO27IXROo1xwF47WBdWurCOzOlyBevC8sJVMuR+fYMZsBLiJT7usx5mwI4nl+2ffS/MgG3Fmtc9umN+x4UrNSOsHQtReJl1gE+jwvo0ZWB2bP942FQ2uQhxmCVLqa3FkimnyXxRU5gd89vL8dRczCyYKTuFuK82VbBWrNrQ6kQ5rjxgzdhQrV7V4W6FtWX5uqlkyO3VK8yUObWaIQLrwfKuVvSUAWvKqku/WKJ4wjtfdMBMWX3pF7dS8Zwtr7Me9nnG7xAwGAwG+xn2B7MFEZLdn68SAAAAAElFTkSuQmCC'
        expect:
        imageBase64 == WeChatUtils.toHtmlImgBase64('demo')
    }

    def "Uuid"() {
        expect:
        32 == WeChatUtils.uuid().length()
    }

    @Unroll
    def "JoinPath"() {
        expect:
        joinPath == WeChatUtils.joinPath(path1, path2)
        where:
        joinPath                    || path1              | path2
        'http://demo.com/path/demo' || 'http://demo.com'  | 'path/demo'
        'http://demo.com/path/demo' || 'http://demo.com/' | 'path/demo'
        'http://demo.com/path/demo' || 'http://demo.com'  | 'path/demo/'
        'http://demo.com/path/demo' || 'http://demo.com/' | 'path/demo/'
    }
}
