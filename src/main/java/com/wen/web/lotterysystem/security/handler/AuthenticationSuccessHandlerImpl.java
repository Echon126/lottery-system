package com.wen.web.lotterysystem.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author admin
 * @date 2018-10-24 9:51
 */

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private static final String targetUrl="/user";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("认证成功后的处理器 ----------------" + request.getMethod());
        redirectStrategy.sendRedirect(request,response,targetUrl);
    }
}
/**
 * TODO 自定义验证成功后的处理逻辑，例如：保存登录时间、登录ip到数据库、缓存用户信心到Redis等一些列操作
 */
