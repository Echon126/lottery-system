package com.wen.web.lotterysystem.data.mapper;

import com.wen.web.lotterysystem.data.entity.MenuDO;
import com.wen.web.lotterysystem.data.provider.MenuProvider;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author admin
 * @date 2018-11-16 16:16
 */
@Mapper
public interface MenuMapperDao {

    @SelectProvider(type = MenuProvider.class, method = "listMenuByUserId")
    List<MenuDO>listMenuByUserId(Long id);
}
