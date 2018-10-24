/*
package com.wen.web.lotterysystem.security;

import com.wen.web.lotterysystem.data.entity.SecurityUser;
import com.wen.web.lotterysystem.data.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

*/
/**
 * @author admin
 * @date 2018-10-30 12:02
 *//*

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    */
/**
     * 自定义验证方式
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     *//*

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        SecurityUser securityUser = (SecurityUser) customUserDetailsService.loadUserByUsername(username);
        if (securityUser == null) {
            throw new BadCredentialsException("UserName not found");
        }

        //加密过程在这里体现
        if (!password.equals(securityUser.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities =  securityUser.getAuthorities();
        return new UsernamePasswordAuthenticationToken(securityUser, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}










*/
