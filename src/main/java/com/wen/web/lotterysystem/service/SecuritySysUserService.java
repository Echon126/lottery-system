package com.wen.web.lotterysystem.service;

import com.wen.web.lotterysystem.data.entity.UserInfo;
import com.wen.web.lotterysystem.data.mapper.SecuritySysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @date 2018-10-29 14:55
 */
@Service
public class SecuritySysUserService {

    @Autowired
    private SecuritySysUserMapper securitySysUserMapper;

    public UserInfo getSysUserByUserName(String userName) throws Exception {
        return this.securitySysUserMapper.getSysUserByUserName(userName);
    }
}
