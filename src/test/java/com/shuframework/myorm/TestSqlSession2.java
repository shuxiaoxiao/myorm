package com.shuframework.myorm;

import com.shuframework.myorm.session.MySqlSession;
import com.shuframework.myorm.session.SessionFactory;
import com.shuframework.myorm.session.impl.DefaultSessionFactory;
import com.shuframework.myorm.session.impl.DefaultSqlSession;
import com.shuframework.test.model.SysUser;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * sqlSession的测试例子
 * Created by shu on 2018/10/10.
 */
public class TestSqlSession2 {

    MySqlSession sqlSession = null;

    @Before
    public void init(){
//        sqlSession = new DefaultSqlSession();
        //通过工厂来创建
        SessionFactory factory = new DefaultSessionFactory();
        sqlSession = factory.openSession();
    }

    @Test
    public void insert_test1(){
        SysUser user = new SysUser();
//        user.setId(1L);
        String name = "test2";
        user.setName(name);
        user.setLoginName(name);
        user.setCreateTime(new Date());

        int resultNum = sqlSession.insert(user);
        System.out.println(resultNum);
    }
    @Test
    public void insertAll_test1(){
        SysUser user = new SysUser();
//        user.setId(1L);
        String name = "test2";
        user.setName(name);
        user.setLoginName(name);
        user.setCreateTime(new Date());

        int resultNum = sqlSession.insertAllColumn(user);
        System.out.println(resultNum);
    }

    @Test
    public void update_test1(){
        SysUser user = new SysUser();
        user.setId(2L);
        String name = "test3";
        user.setName(name);
        user.setLoginName(name);
        user.setEnable("1");
        user.setUpdateTime(new Date());

        int resultNum = sqlSession.updateById(user);
        System.out.println(resultNum);
    }

    @Test
    public void selectById_test1(){
        int id = 3;
        SysUser user = sqlSession.selectById(SysUser.class, id);
        System.out.println(user);
    }

}
