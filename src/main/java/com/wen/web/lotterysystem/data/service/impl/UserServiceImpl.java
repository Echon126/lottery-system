package com.wen.web.lotterysystem.data.service.impl;

import com.wen.web.lotterysystem.data.entity.UserDO;
import com.wen.web.lotterysystem.data.mapper.UserMappperDao;
import com.wen.web.lotterysystem.data.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @date 2018-11-15 17:11
 */
@Service
public class UserServiceImpl extends AbstractService {

    @Autowired
    private UserMappperDao userDao;

    @Override
    public UserDO get(String id) {
        return this.userDao.get(id);
    }
}
