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

package cn.javaer.wechat.spring.boot.autoconfigure.mp.security;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangpeng
 */
public class WeChatMpAuthenticationFilter extends GenericFilterBean implements ApplicationEventPublisherAware
{
    private ApplicationEventPublisher eventPublisher;
    
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
        final FilterChain chain) throws IOException, ServletException
    {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (SecurityContextHolder.getContext().getAuthentication() == null)
        {
            // SecurityContextHolder.getContext().setAuthentication(new WeChatMpAuthenticationToken(""));
        } else
        {
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void setApplicationEventPublisher(final ApplicationEventPublisher eventPublisher)
    {
        this.eventPublisher = eventPublisher;
    }
}
