package com.shuframework.myorm.core;

import com.shuframework.myorm.bean.DataSourceConfig;
import com.shuframework.myorm.util.JdbcUtil;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * 根据配置信息，维持连接对象的管理(增加连接池功能)
 * @author Administrator
 *
 */
public class DBManager {

	private static DataSourceConfig dbConfig;
//	private static PackageConfig packageConfigConfig;

	//静态代码块
	static {
		Properties pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dbConfig = new DataSourceConfig();
		dbConfig.setDriver(pros.getProperty("jdbc.driverClassName"));
		dbConfig.setJdbcUrl(pros.getProperty("jdbc.url"));
		dbConfig.setUsername(pros.getProperty("jdbc.username"));
		dbConfig.setPassword(pros.getProperty("jdbc.password"));
	}


	/**
	 * 获得Connection对象
	 * @return
	 */
	public static Connection getConn(){
//		if(pool==null){
//			pool = new DBConnPool();
//		}
//		return pool.getConnection();

		return JdbcUtil.getConn(dbConfig.getDriver(), dbConfig.getJdbcUrl(),
				dbConfig.getUsername(), dbConfig.getPassword());
	}

	public static void main(String[] args) {
		DBManager.getConn();
	}
}
