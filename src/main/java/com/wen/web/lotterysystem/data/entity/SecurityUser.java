package com.wen.web.lotterysystem.data.entity;

import com.wen.web.lotterysystem.utils.Tree;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class SecurityUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private UserDO userDO;

    private List<Tree<MenuDO>> menus;

    public SecurityUser(UserDO userDO, List<Tree<MenuDO>> menus) {
        this.userDO = userDO;
        this.menus = menus;
    }

    public UserDO getUserDO() {
        return userDO;
    }

    public void setUserDO(UserDO userDO) {
        this.userDO = userDO;
    }

    public List<Tree<MenuDO>> getMenus() {
        return menus;
    }

    public void setMenus(List<Tree<MenuDO>> menus) {
        this.menus = menus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {

        return this.userDO.getPassword();

    }


    @Override
    public String getUsername() {

        return this.userDO.getUsername();

    }


    @Override
    public boolean isAccountNonExpired() {

        return true;

    }


    @Override
    public boolean isAccountNonLocked() {

        return true;

    }


    @Override
    public boolean isCredentialsNonExpired() {

        return true;

    }


    @Override
    public boolean isEnabled() {

        return true;

    }

}
