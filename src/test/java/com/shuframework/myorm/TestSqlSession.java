package com.shuframework.myorm;

import com.shuframework.myorm.core.DBManager;
import com.shuframework.myorm.enums.SqlMethodEnum;
import com.shuframework.myorm.session.MySqlSession;
import com.shuframework.myorm.session.impl.DefaultSqlSession;
import com.shuframework.myorm.util.JdbcUtil;
import com.shuframework.test.model.SysUser;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sqlSession的测试例子
 * Created by shu on 2018/10/10.
 */
public class TestSqlSession {

    @Test
    public void query2Bean_test2(){
        Integer id = 1;

        String sql = String.format(SqlMethodEnum.SELECT_BY_ID.getSql(), "*", "sys_user");
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
    public void query2Map_test2(){
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

//    @Test
//    public void query_test1(){
//        MySqlSession sqlSession = new DefaultSqlSession();
//
//        String sql = "select * from sys_user where id = 1";
//        List<?> params = null;
//        ResultSet resultSet = sqlSession.executeQuery(sql, params);
//        System.out.println(resultSet);
//    }
}
