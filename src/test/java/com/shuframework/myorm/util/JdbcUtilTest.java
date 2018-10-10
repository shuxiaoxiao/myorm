package com.shuframework.myorm.util;

import com.shuframework.myorm.core.DBManager;
import com.shuframework.myorm.enums.SqlMethodEnum;
import com.shuframework.test.model.SysUser;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shu on 2018/10/10.
 */
public class JdbcUtilTest {

    @Test
    public void query2Bean_test(){
        Long id = 1L;

        String sql = String.format(SqlMethodEnum.SELECT_BY_ID.getSql(), "*", "sys_user", "id");
        List<Object> params = new ArrayList<>();
        params.add(id);
        SysUser user = null;
        try {
            Connection connection = DBManager.getConn();
            user = JdbcUtil.query2Bean(connection, sql, SysUser.class, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

    @Test
    public void query2Map_test(){
        Integer id = 1;

        String sql = String.format(SqlMethodEnum.SELECT_BY_ID.getSql(), "*", "sys_user");
        List<Object> params = new ArrayList<>();
        params.add(id);
        Map<String, Object> map = null;
        try {
            Connection connection = DBManager.getConn();
            map = JdbcUtil.query2Map(connection, sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }
}
