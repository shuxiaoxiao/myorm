package com.shuframework.myorm.session.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.shuframework.myorm.core.DBManager;
import com.shuframework.myorm.enums.SqlMethodEnum;
import com.shuframework.myorm.exception.MyException;
import com.shuframework.myorm.session.MySqlSession;
import com.shuframework.myorm.util.JdbcUtil;
import com.shuframework.test.model.SysUser;


public class DefaultSqlSession implements MySqlSession{
	
	private Connection connection = DBManager.getConn();
//	{
////		String driver;
//		String url = "";
//		String username = "";
//		String password = "";
//		try {
//			connection = JdbcUtil.getConn(url, username, password);
//		} catch (SQLException e) {
//			throw new MyException("获得connection对象出现异常");
//		}
//	}
	

	@Override
	public int executeUpdate(String sql, Object... params) {
//		int resultNum = 0;
//		try {
//			resultNum = JdbcUtil.update(connection, sql, params);
//		} catch (SQLException e) {
//			initException(sql, Arrays.toString(params));
//		}
//		return resultNum;
		return JdbcUtil.update(connection, sql, params);
	}

	@Override
	public int executeUpdate(String sql, List<?> params) {
//		int resultNum = 0;
//		try {
//			resultNum = JdbcUtil.update(connection, sql, params);
//		} catch (SQLException e) {
//			String msg = initExceptionMsg(sql, params.toString());
//			throw new MyException(msg);
//		}
//		return resultNum;
		return JdbcUtil.update(connection, sql, params);
	}


	@Override
	public <T> int insert(T bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int updateById(T bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> T selectById(Serializable id) {
//		//获得对象
//		Class<SysUser> clazz = SysUser.class;
//		String sql = String.format(SqlMethodEnum.SELECT_BY_ID.getSql(), "*", "sys_user");
//		List<Object> params = new ArrayList<>();
//		params.add(id);
//		T t = null;
//		try {
//			t = JdbcUtil.query2Bean(connection, sql, clazz, params);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return t;
		return null;
	}

	@Override
	public <T> List<T> selectList(String sql, Class<T> clazz, List<?> params) {
		// TODO Auto-generated method stub
		return null;
	}


	protected String initExceptionMsg(String sql, String params) {
		StringBuilder sb = new StringBuilder();
		sb.append("执行语句出现异常。");
		sb.append("sql：");
		sb.append(sql);
		sb.append("参数：");
		sb.append(params);
		return sb.toString();
	}
	
	protected void initException(String sql, String params) {
		StringBuilder sb = new StringBuilder();
		sb.append("执行语句出现异常。");
		sb.append("sql：");
		sb.append(sql);
		sb.append("参数：");
		sb.append(params);
		throw new MyException(sb.toString());
	}
	
}
