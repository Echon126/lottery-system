package com.wen.web.lotterysystem.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author admin
 * @date 2018-10-23 14:51
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static Logger logger = LoggerFactory.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

    public CustomUsernamePasswordAuthenticationFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        logger.info("开始进行登录逻辑认证");
        if (!request.getMethod().equalsIgnoreCase("POST")) {
            throw new AuthenticationServiceException("");
        }
        String code = "";
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        if (StringUtils.isEmpty(username)) {
            //setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "用户名或者密码错误!");
            throw new AuthenticationServiceException("用户名不能为空");
        }

        if (StringUtils.isEmpty(password)) {
            //request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, "用户名或者密码错误!");
            throw new AuthenticationServiceException("密码不能为空");
        }

       /* if (StringUtils.isEmpty(code)) {
            throw new AuthenticationServiceException("验证码错误");
        }*/


        request.getSession().setAttribute("user", "user");
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
//TODO  UsernamePasswordAuthenticationFilter 该过滤器用来处理用户认证逻