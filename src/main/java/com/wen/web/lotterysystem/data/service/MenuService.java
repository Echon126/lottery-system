package com.wen.web.lotterysystem.data.service;

import com.wen.web.lotterysystem.data.entity.MenuDO;
import com.wen.web.lotterysystem.utils.Tree;


import java.util.List;

/**
 * @author admin
 * @date 2018-11-15 18:06
 */
public interface MenuService {

    List<Tree<MenuDO>>listMenuByUserId(Long id);
}
