package com.shuframework.myorm.session;

import java.sql.Connection;

/**
 * 由于底层 创建SqlSession 比较容易 所以这个工厂 可以不要
 * Created by shu on 2018/10/17.
 */
public interface SessionFactory {

    MySqlSession openSession();

    MySqlSession openSession(Connection connection);
}
