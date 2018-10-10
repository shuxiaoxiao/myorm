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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * sqlSession的测试例子
 * Created by shu on 2018/10/10.
 */
public class TestSqlSession {

    @Test
    public void insert_test1(){
        MySqlSession sqlSession = new DefaultSqlSession();

        SysUser user = new SysUser();
        user.setId(14L);
        user.setName("test14");
        user.setLoginName("test14");
        user.setCreateTime(new Date());

        int resultNum = sqlSession.insert(user);
        System.out.println(resultNum);
    }

    @Test
    public void update_test1(){
        MySqlSession sqlSession = new DefaultSqlSession();

        SysUser user = new SysUser();
        user.setId(3L);
        user.setName("test3");
        user.setLoginName("test3");

        int resultNum = sqlSession.updateById(user);
        System.out.println(resultNum);
    }

}
