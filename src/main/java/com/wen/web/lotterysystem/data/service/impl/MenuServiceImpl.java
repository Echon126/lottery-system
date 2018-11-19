package com.wen.web.lotterysystem.data.service.impl;

import com.wen.web.lotterysystem.data.entity.MenuDO;
import com.wen.web.lotterysystem.data.mapper.MenuMapperDao;
import com.wen.web.lotterysystem.data.service.AbstractService;
import com.wen.web.lotterysystem.utils.BuildTree;
import com.wen.web.lotterysystem.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @date 2018-11-16 16:34
 */
@Service
public class MenuServiceImpl extends AbstractService {

    @Autowired
    MenuMapperDao menuMapperDao;

    @Override
    public List<Tree<MenuDO>> listMenuByUserId(Long id) {
        List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
        List<MenuDO> menuDOs = menuMapperDao.listMenuByUserId(id);
        for (MenuDO sysMenuDO : menuDOs) {
            Tree<MenuDO> tree = new Tree<MenuDO>();
            tree.setId(sysMenuDO.getMenuId().toString());
            tree.setParentId(sysMenuDO.getParentId().toString());
            tree.setText(sysMenuDO.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenuDO.getUrl());
            attributes.put("icon", sysMenuDO.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
        return list;
    }
}
