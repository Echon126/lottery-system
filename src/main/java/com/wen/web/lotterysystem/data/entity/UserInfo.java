package com.wen.web.lotterysystem.data.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author admin
 * @date 2018-10-29 14:15
 */
public class UserInfo {
    private Integer id;
    private String account;
    private String name;
    private String password;
    private String note;
    private int issys;
    private Date createTime;
    private Date updateTime;
    private int status;
    private Set<SecurityRole> securityRoles = new HashSet<SecurityRole>(0);// Code12

    public UserInfo() {
    }

    public UserInfo(String account, String name, String password) {
        this.account = account;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIssys() {
        return issys;
    }

    public void setIssys(int issys) {
        this.issys = issys;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<SecurityRole> getSecurityRoles() {
        return securityRoles;
    }

    public void setSecurityRoles(Set<SecurityRole> securityRoles) {
        this.securityRoles = securityRoles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", note='" + note + '\'' +
                ", issys=" + issys +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
