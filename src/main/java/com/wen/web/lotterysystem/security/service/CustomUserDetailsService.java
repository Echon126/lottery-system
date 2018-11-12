package com.wen.web.lotterysystem.security.service;

import com.wen.web.lotterysystem.data.entity.SecurityUser;
import com.wen.web.lotterysystem.data.entity.UserInfo;
import com.wen.web.lotterysystem.service.SecuritySysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author admin
 * @date 2018-10-29 14:43
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired  //数据库服务类
    private SecuritySysUserService securitySysUserService;//code7

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("开始调用 UerDetailService服务-----------");
        UserInfo user = null; //code8
        Set<GrantedAuthority> grantedAuthorities = null;
        try {
            //
            user = securitySysUserService.getSysUserByUserName(userName);

            //TODO 获取菜单信息
            //grantedAuthorities=securitySysUserService

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在!");
        }

        // SecurityUser实现UserDetails并将SUser的Email映射为username
        SecurityUser securityUser = new SecurityUser(user);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        //UserDetails userDetails = new User(user.getAccount(),user.getPassword(),grantedAuthorities);
        return securityUser; //code9

    }

    private Collection<GrantedAuthority> getAuthority() {
        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_ADMINE"));
        return authorityList;
    }

    /**
     * UserDetailsService 接口作用分析
     * TODO 1.用于返回用户相关数据，声明了loadByUsername()方法，根据username查询用户实体，可以实现该接口覆盖该方法
     * TODO 实现自定义获取用户过程。
     * TODO 2.该接口实现类被DaoAuthenticationProvider类使用，用于认证过程中载入用户信息
     * TODO 3.验证身份就是加载响应的UserDetail,判断是否输入的用户账号、密码、权限信息匹配，此步骤有实现AuthenticationProvider的DaoAuthenticationProvider 处理
     * TODO 包含GrantedAuthority的UserDetails对象在构建Authentication对象是填入的数据。
     *
     */

    /**
     * 获取当前用户的信息
     * TODO SecurityContextHolder存储当前与应用程序交互的主体的详细信息
     */
   /* public String getContext() {
        //TODO 获取当前经过身份认证的用户的信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;

    }*/
}
