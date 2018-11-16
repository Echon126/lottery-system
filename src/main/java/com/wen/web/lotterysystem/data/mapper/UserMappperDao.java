package com.wen.web.lotterysystem.data.mapper;

import com.wen.web.lotterysystem.data.entity.UserDO;


import com.wen.web.lotterysystem.data.provider.UserProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2018-10-29 14:57
 */
@Mapper
public interface UserMappperDao {

    @SelectProvider(type = UserProvider.class, method = "findUserByUserName")
    UserDO get(String userId);

    List<UserDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserDO user);

    int update(UserDO user);

    int remove(Long userId);

    int batchRemove(Long[] userIds);

    Long[] listAllDept();

}
