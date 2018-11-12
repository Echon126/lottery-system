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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
        http.authorizeRequests()
                //TODO  允许所有所用户访问"/"
                .antMatchers("/").permitAll()
                //.antMatchers("/user/**").permitAll()
                //TODO 其他的地址访问均需要验证
                //.anyRequest().authenticated()
                .and().addFilterAt(CustomAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                //TODO 配置登陆页面
                .formLogin()
                //TODO 指定登录界面的访问路劲
                .loginPage("/login")
                //TODO 登录成功默认跳转的路径
                .defaultSuccessUrl("/index")
                //TODO 登陆失败后跳转路径，为了给客户端提示
                //.failureUrl("/login?error=true")
                .and()
                .logout()//用户退出操作
                //.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST")) //用户退出所访问的路径，需要使用POST的方式
                .logoutUrl("/logout")
                //TODO 退出成功后所要访问的路径
                .logoutSuccessUrl("/login")
                .permitAll() ;

        //TODO 控制单个用户只能创建一个session，也就只能在服务器登录一次
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

    /**
     * 配置全局
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
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
    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/user");
        return successHandler;
    }



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


    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/**/*.js", "/css/**");

    }
}
