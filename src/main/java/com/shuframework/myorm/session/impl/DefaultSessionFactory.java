package com.shuframework.myorm.session.impl;

import com.shuframework.myorm.session.MySqlSession;
import com.shuframework.myorm.session.SessionFactory;

import java.sql.Connection;

/**
 *
 * Created by shu on 2018/10/17.
 */
public class DefaultSessionFactory implements SessionFactory {

    @Override
    public MySqlSession openSession() {
        return new DefaultSqlSession();
    }

    @Override
    public MySqlSession openSession(Connection connection) {
        return new DefaultSqlSession(connection);
    }
}
