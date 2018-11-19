package com.wen.web.lotterysystem.configuration;

import com.wen.web.lotterysystem.security.filter.CustomUsernamePasswordAuthenticationFilter;
import com.wen.web.lotterysystem.security.handler.AuthenticationFailureHandlerImpl;
import com.wen.web.lotterysystem.security.handler.AuthenticationSuccessHandlerImpl;
import com.wen.web.lotterysystem.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author admin
 * @date 2018-10-29 11:41
 */
//TODO 帮助创建SpringSecurity工作中要使用的Filter
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    AuthenticationFailureHandlerImpl authenticationFailureHandler;


    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/","/login").permitAll()
                 .anyRequest().authenticated()
                .and()
                //添加自定义过滤器
                .addFilterAt(CustomAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                //.defaultSuccessUrl("/main")
                //.failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        //TODO 控制单个用户只能创建一个session，也就只能在服务器登录一次
        //http.sessionManagement().maximumSessions(1).expiredUrl("/login");
        //always – 如果session不存在总是需要创建；
        //ifRequired – 仅当需要时，创建session(默认配置)；
        //never – 框架从不创建session，但如果已经存在，会使用该session ；
        //stateless – Spring Security不会创建session，或使用session；
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    /**
     * 配置全局
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.eraseCredentials(false);//允许记住密码
    }

    /**
     * TODO 配置 DaoAuthenticationProvider，可以设置异常的抛出，和相应的属性字段值
     *
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    //TODO 直接使用以实现AuthenticationSuccessHandler接口的类
  /* @Bean
    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/main");
        return successHandler;
    }*/


    //TODO 添加自定义过滤器
    @Bean
    public CustomUsernamePasswordAuthenticationFilter CustomAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter authenticationFilterChild = new CustomUsernamePasswordAuthenticationFilter();
        //authenticationFilterChild.setAuthenticationManager(authenticationManagerBean());
        authenticationFilterChild.setAuthenticationManager(authenticationManager());
        //TODO 使用自己实现authenticationSuccessHandler接口的类
        authenticationFilterChild.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        //TODO 使用自定义的authenticationFailureHandler
        authenticationFilterChild.setAuthenticationFailureHandler(authenticationFailureHandler);
        return authenticationFilterChild;
    }

    // 自定义会话超时登出 Bean
//    @Bean
//    public AjaxAwareAuthenticationEntryPoint ajaxAwareAuthenticationEntryPoint() {
//        return new AjaxAwareAuthenticationEntryPoint("/login");
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/js/**", "/css/**","/fonts/**","/img/**");

    }
}
