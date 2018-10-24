package com.wen.web.lotterysystem.data.mapper;

import com.wen.web.lotterysystem.data.entity.UserInfo;
import com.wen.web.lotterysystem.data.provider.BaseUserProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author admin
 * @date 2018-10-29 14:57
 */
@Mapper
public interface SecuritySysUserMapper {

    @SelectProvider(type=BaseUserProvider.class,method = "findUserByUserName")
    public UserInfo getSysUserByUserName(String userName);

}
