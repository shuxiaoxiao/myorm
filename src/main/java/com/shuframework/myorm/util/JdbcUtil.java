package com.shuframework.myorm.util;

import com.shuframework.myorm.exception.MyException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库连接工具
 * 
 * @author shu
 *
 */
public class JdbcUtil {

	/**默认驱动*/
	private static final String DRIVER_DEFAULT = "com.mysql.jdbc.Driver";
	
	private JdbcUtil() {
	}
	
	/**
	 * 获得数据库的连接对象(Connection), 默认数据源是mysql
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public static Connection getConn(String url, String username, String password) {
		return getConn(DRIVER_DEFAULT, url, username, password);
	}
	
	/**
	 * 获得数据库的连接对象(Connection)
	 * @param driver
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public static Connection getConn(String driver, String url, String username, String password) {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 释放连接，如果参数没有就设置为null
	 * @param connection
	 * @param pstmt
	 * @param resultSet
	 * @throws SQLException 
	 */
	public static void releaseConn(Connection connection, PreparedStatement pstmt, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new MyException("jdbc释放资源异常");
		}
	}
	
	/**
	 * 执行新增、修改、删除操作
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int update(Connection connection, String sql, Object... params) {
		int resultNum = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = initStatement(connection, sql, params);
			resultNum = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			String msg = initExceptionMsg(sql, Arrays.toString(params));
			throw new MyException(msg);
		}finally {
			releaseConn(connection, pstmt, null);
		}
		return resultNum;
	}

	/**
	 * 执行新增、修改、删除操作
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int update(Connection connection, String sql, List<?> params) {
		int resultNum = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = initStatement(connection, sql, params);
			resultNum = pstmt.executeUpdate();

		} catch (SQLException e) {
			String msg = initExceptionMsg(sql, params.toString());
			throw new MyException(msg);
		}finally {
			releaseConn(connection, pstmt, null);
		}
		return resultNum;
	}

	/**
	 * 关闭ResultSet后循环读取会有问题
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 */
	@Deprecated
	private static ResultSet query(Connection connection, String sql, List<?> params){
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			pstmt = initStatement(connection, sql, params);
			resultSet = pstmt.executeQuery();

		} catch (SQLException e) {
			String msg = initExceptionMsg(sql, params.toString());
			throw new MyException(msg);
		}finally {
			releaseConn(connection, pstmt, resultSet);
		}
		return resultSet;
	}

	
	/**
	 * 执行查询操作
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> query2Map(Connection connection, String sql, List<?> params) throws SQLException {
		List<Map<String, Object>> list = query2ListMap(connection, sql, params);
		return list.stream().findAny().orElse(null);
	}
	
	/**
	 * 执行查询操作
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> query2ListMap(Connection connection, String sql, List<?> params) throws SQLException {
		//返回查询结果（关闭ResultSet后循环读取会有问题）
//		ResultSet resultSet = query(connection, sql, params);
		PreparedStatement pstmt = initStatement(connection, sql, params);
		ResultSet resultSet = pstmt.executeQuery();

		List<Map<String, Object>> list = new ArrayList<>();
		// 获取此 ResultSet 对象的列的编号、类型和属性。
		ResultSetMetaData metaData = resultSet.getMetaData();
		int colLen = metaData.getColumnCount();// 获取列的长度
		Map<String, Object> map = null;
		while (resultSet.next()) {
			map = new HashMap<>();
			for (int i = 0; i < colLen; i++) {
				String colName = metaData.getColumnName(i + 1);
				Object colValue = resultSet.getObject(colName);
//				// 列的值没有时，设置列值为""
//				if (colValue == null){
//					colValue = "";
//				}
				//字段转换为属性
				String propName = StringUtil.toPropertyName(colName);
				map.put(propName, colValue);
			}
			list.add(map);
		}
		//释放资源
		releaseConn(connection, pstmt, resultSet);

		return list;
	}
	
	/**
	 * 执行查询操作
	 * 
	 * @param connection
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static <T> T query2Bean(Connection connection, String sql, Class<T> clazz, List<?> params) throws Exception {
		List<T> list = query2ListBean(connection, sql, clazz, params);
		return list.stream().findAny().orElse(null);
	}
	
	/**
	 * 执行查询操作
	 * 
	 * @param connection
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> query2ListBean(Connection connection, String sql, Class<T> clazz, List<?> params) throws Exception {
		//返回查询结果（关闭ResultSet后循环读取会有问题）
//		ResultSet resultSet = query(connection, sql, params);
		PreparedStatement pstmt = initStatement(connection, sql, params);
		ResultSet resultSet = pstmt.executeQuery();

		List<T> list = new ArrayList<>();
		// 获取此 ResultSet 对象的列的编号、类型和属性。
		ResultSetMetaData metaData = resultSet.getMetaData();
		int colLen = metaData.getColumnCount();// 获取列的长度
		T bean = null;
		while (resultSet.next()) {
			bean = clazz.newInstance();
			for (int i = 0; i < colLen; i++) {
				String colName = metaData.getColumnName(i + 1);
				Object colValue = resultSet.getObject(colName);
//				// 列的值没有时，设置列值为""
//				if (colValue == null){
//					colValue = "";
//				}
				//字段转换为属性
				String propName = StringUtil.toPropertyName(colName);
				MyBeanUtil.setProperty(bean, propName, colValue);
			}
			list.add(bean);
		}

		//释放资源
		releaseConn(connection, pstmt, resultSet);

		return list;
	}

	
	/**
	 * 创建PreparedStatement 并给参数赋值
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	protected static PreparedStatement initStatement(Connection connection, String sql, Object... params)
			throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(sql);
		int index = 1;
		// 填充sql语句中的占位符
		if (params != null) {
			int size = params.length;
			for (int i = 0; i < size; i++) {
				pstmt.setObject(index++, params[i]);
			}
		}
		return pstmt;
	}
	
	/**
	 * 创建PreparedStatement 并给参数赋值
	 * 
	 * @param connection
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	protected static PreparedStatement initStatement(Connection connection, String sql, List<?> params)
			throws SQLException {
		PreparedStatement pstmt = connection.prepareStatement(sql);
		int index = 1;
		// 填充sql语句中的占位符
		if (params != null) {
			int size = params.size();
			for (int i = 0; i < size; i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		return pstmt;
	}

	protected static String initExceptionMsg(String sql, String params) {
		StringBuilder sb = new StringBuilder();
		sb.append("执行语句出现异常。");
		sb.append("sql：");
		sb.append(sql);
		sb.append("参数：");
		sb.append(params);
		return sb.toString();
	}

	protected static void initException(String sql, String params) {
		StringBuilder sb = new StringBuilder();
		sb.append("执行语句出现异常。");
		sb.append("sql：");
		sb.append(sql);
		sb.append("参数：");
		sb.append(params);
		throw new MyException(sb.toString());
	}

}