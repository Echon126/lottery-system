package com.wen.web.lotterysystem.data.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author admin
 * @date 2018-11-16 11:55
 */
public class UserProvider extends BaseProvider {

    public String findUserByUserName(final String userName) {

        return new SQL() {{
            SELECT("*").FROM("sys_user").WHERE("username=#{userName}");
        }}.toString();

    }
}
