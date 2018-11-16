package com.wen.web.lotterysystem.controller;

import com.wen.web.lotterysystem.data.entity.UserDO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author admin
 * @date 2018-10-29 16:29
 */
public abstract class BaseController {

    //TODO 获取当前经过身份认证的用户的信息
    protected String getContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
