package com.wen.web.lotterysystem.data.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author admin
 * @date 2018-11-16 16:16
 */
public class MenuProvider extends BaseProvider {

    public String listMenuByUserId(final Long id) {
        return new SQL() {{
            SELECT("distinct m.menu_id,parent_id, name, url,perms,`type`,icon,order_num,gmt_create, gmt_modified")
                    .FROM("sys_menu m")
                    .LEFT_OUTER_JOIN("sys_role_menu rm on m.menu_id = rm.menu_id")
                    .LEFT_OUTER_JOIN("sys_user_role ur on rm.role_id =ur.role_id")
                    .WHERE("ur.user_id = #{id}")
                    .WHERE("m.type in(0,1)")
                    .ORDER_BY("m.order_num");
        }}.toString();
    }
}
