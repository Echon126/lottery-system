package com.wen.web.lotterysystem.data.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class SecurityUser extends UserInfo implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(UserInfo userInfo) {
        if (userInfo != null) {
            this.setId(userInfo.getId());
            this.setName(userInfo.getName());
            this.setAccount(userInfo.getAccount());
            this.setPassword(userInfo.getPassword());
            this.setSecurityRoles(userInfo.getSecurityRoles());
        }
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {


        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        Set<SecurityRole> securityRoles = this.getSecurityRoles();


        if (securityRoles != null)

        {

            for (SecurityRole role : securityRoles) {

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());

                authorities.add(authority);

            }

        }

        return authorities;

    }


    @Override
    public String getPassword() {

        return super.getPassword();

    }


    @Override
    public String getUsername() {

        return super.getName();

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
