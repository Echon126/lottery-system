package com.wen.web.lotterysystem.data.entity;

/**
 * @author admin
 * @date 2018-10-29 15:07
 */
public class SecurityRole {
    private Integer id;
    private UserInfo userInfo;
    private String name;


    public SecurityRole(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public SecurityRole(UserInfo userInfo, String name) {
        this.userInfo = userInfo;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
