package com.wen.web.lotterysystem.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wen.web.lotterysystem.security.bean.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * @author admin
 * @date 2018-10-24 10:27
 */
@Component
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);
    private static final String targetUrl = "/login?error=true";
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        logger.info("认证失败后的操作-------请求方法" + request.getMethod() + "请求IP地址 " + request.getRemoteAddr() + "错误信息 " + e.getMessage() + "URL " + request.getRequestURL().toString());
        this.setDefaultFailureUrl(targetUrl);

        //String msg = messageSource.getMessage("badCredentials", null, Locale.CHINA);
        //logger.info("错误信息" + msg);

        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            AuthenticationException ex = new BadCredentialsException("用户不存在或密码错误");
            super.onAuthenticationFailure(request, response, ex);
        } else {
            super.onAuthenticationFailure(request, response, e);
        }


    }


    //TODO 根据异常类型----自定义错误信息
  /*  @Override
    public void onAuthenticationFailure(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        AuthenticationException e) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        RespBean respBean = null;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            respBean = RespBean.error("账户名或者密码输入错误!");
        } else if (e instanceof LockedException) {
            respBean = RespBean.error("账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            respBean = RespBean.error("密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            respBean = RespBean.error("账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            respBean = RespBean.error("账户被禁用，请联系管理员!");
        } else {
            respBean = RespBean.error("登录失败!");
        }
        resp.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();

    }*/
}
