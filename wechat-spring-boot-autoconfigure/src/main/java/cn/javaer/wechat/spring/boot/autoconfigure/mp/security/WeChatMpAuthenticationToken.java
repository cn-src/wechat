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

import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author zhangpeng
 */
public class WeChatMpAuthenticationToken extends AbstractAuthenticationToken
{
    private final Object principal;
    private final int    keyHash;
    
    public WeChatMpAuthenticationToken(@NotNull final String key, @NotNull final Object principal,
        final Collection<? extends GrantedAuthority> authorities)
    {
        super(authorities);
        this.keyHash = key.hashCode();
        this.principal = principal;
        setAuthenticated(true);
    }
    
    @Override
    public Object getCredentials()
    {
        return "";
    }
    
    @Override
    public Object getPrincipal()
    {
        return this.principal;
    }
    
    public boolean equals(final Object obj)
    {
        if (!super.equals(obj))
        {
            return false;
        }
        
        if (obj instanceof WeChatMpAuthenticationToken)
        {
            final WeChatMpAuthenticationToken test = (WeChatMpAuthenticationToken) obj;
            return this.keyHash == test.keyHash;
        }
        
        return false;
    }
    
    public int getKeyHash()
    {
        return this.keyHash;
    }
}
