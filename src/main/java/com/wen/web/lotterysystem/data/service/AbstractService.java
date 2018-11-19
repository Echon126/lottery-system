package com.wen.web.lotterysystem.data.service;


import com.wen.web.lotterysystem.data.entity.MenuDO;
import com.wen.web.lotterysystem.data.entity.UserDO;
import com.wen.web.lotterysystem.utils.Tree;


import java.util.List;

/**
 * @author admin
 * @date 2018-11-15 17:10
 */
public abstract class AbstractService implements UserService, MenuService {

    @Override
    public UserDO get(String id) {
        return null;
    }

    @Override
    public List<Tree<MenuDO>>listMenuByUserId(Long id) {
        return null;
    }
}
