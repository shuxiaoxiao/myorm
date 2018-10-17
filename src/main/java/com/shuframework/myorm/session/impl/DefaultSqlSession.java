package com.shuframework.myorm.session.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shuframework.myorm.annotations.Table;
import com.shuframework.myorm.annotations.TableField;
import com.shuframework.myorm.annotations.TableId;
import com.shuframework.myorm.core.DBManager;
import com.shuframework.myorm.enums.SqlMethodEnum;
import com.shuframework.myorm.session.MySqlSession;
import com.shuframework.myorm.util.JdbcUtil;


public class DefaultSqlSession implements MySqlSession{
	
	private Connection connection;
//	private Connection connection = DBManager.getConn();

	public DefaultSqlSession() {
		this.connection = DBManager.getConn();
	}

	public DefaultSqlSession(Connection connection) {
		this.connection = connection;
	}

	@Override
	public <T> int insert(T bean) {
		if (bean == null){
			throw new IllegalArgumentException("插入的元素为空");
		}
		Class clazz = bean.getClass();
		String tableName = getTableName(clazz);
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0){
			throw new RuntimeException(bean + "没有属性");
		}

		//参数个数与字段个数不对应，改为用集合
		List<Object> params = new ArrayList<>();
		String sql = getInsertSqlAndParams(tableName, fields, bean, params);
		System.out.println("insertSql = " + sql);
		System.out.println(params);
		return JdbcUtil.update(connection, sql, params);
	}

	@Override
	public <T> int insertAllColumn(T bean) {
		if (bean == null){
			throw new IllegalArgumentException("插入的元素为空");
		}
		Class clazz = bean.getClass();
		String tableName = getTableName(clazz);
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0){
			throw new RuntimeException(bean + "没有属性");
		}

		Object[] params = new Object[fields.length];
		String sql = getInsertAllColumnSqlAndParams(tableName, fields, bean, params);
		System.out.println("insertSql = " + sql);
		System.out.println(Arrays.toString(params));
		return JdbcUtil.update(connection, sql, params);
	}

	@Override
	public <T> int updateById(T bean) {
		if (bean == null){
			throw new IllegalArgumentException("修改的元素为空");
		}
		Class clazz = bean.getClass();
		String tableName = getTableName(clazz);
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0){
			throw new RuntimeException(bean + "没有属性");
		}

		Object[] params = new Object[fields.length];
		String sql = getUpdateSqlAndParams(tableName, fields, bean, params);
		System.out.println("updateSql = " + sql);
		System.out.println(Arrays.toString(params));
		return JdbcUtil.update(connection, sql, params);
	}

	@Override
	public <T> T selectById(Class clazz, Serializable id) {
		String tableName = getTableName(clazz);
		String idName = "";
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0, max = fields.length; i < max; i ++) {
			fields[i].setAccessible(true);
			// 找到id对应的列名和值
			if (fields[i].isAnnotationPresent(TableId.class)) {
				idName = fields[0].getAnnotation(TableId.class).value();
			}
		}
		String sql = String.format(SqlMethodEnum.SELECT_BY_ID.getSql(), "*", tableName, idName);
		List<Object> params = new ArrayList<>();
		params.add(id);
		System.out.println("selectSql = " + sql);
		System.out.println(params);
//		System.out.println(Arrays.toString(params));
		T t = null;
		try {
			t = JdbcUtil.query2Bean(connection, sql, clazz, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public <T> List<T> selectList(String sql, Class clazz, List<?> params) {
		// TODO Auto-generated method stub
		return null;
	}


	private String getTableName(Class clazz) {
		boolean existTableAnno = clazz.isAnnotationPresent(Table.class);
		if (!existTableAnno){
			throw new RuntimeException(clazz + " 没有Table注解.");
		}
		Table tableAnno = (Table) clazz.getAnnotation(Table.class);
		return tableAnno.value();
	}

	/**
	 * 有值才会拼接, 参数个数与字段个数不对应，改为用集合
	 * @param tableName
	 * @param fields
	 * @param element
	 * @param params
	 * @param <T>
	 * @return
	 */
	private <T> String getInsertSqlAndParams(String tableName, Field[] fields, T element, List<Object> params) {
		int length = fields.length;
		// 添加参数占位符?
		StringBuilder filedSql = new StringBuilder();
		StringBuilder paramSql = new StringBuilder(length*2);
		try {
			for (int i = 0; i < length; i ++){
				fields[i].setAccessible(true);
				Object objVal = fields[i].get(element);
				if (objVal != null){
					params.add(objVal);
					paramSql.append("?,");

					if (fields[i].isAnnotationPresent(TableId.class)) {
						TableId id = fields[i].getAnnotation(TableId.class);
						String idName = id.value();
						filedSql.append(idName).append(",");
					}
					if (fields[i].isAnnotationPresent(TableField.class)) {
						TableField column = fields[i].getAnnotation(TableField.class);
						String columnName = column.value();
						filedSql.append(columnName).append(",");
					}
				}
			}
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			System.out.println("获取" + element + "的属性值失败！");
		}
		filedSql.deleteCharAt(filedSql.length()-1);
		paramSql.deleteCharAt(paramSql.length()-1);

		String sql = String.format(SqlMethodEnum.INSERT_ONE.getSql(), tableName, filedSql.toString(), paramSql.toString());
		return sql;
	}

	/**
	 * 拼接所有字段，不管是否有值
	 * @param tableName
	 * @param fields
	 * @param element
	 * @param params
	 * @param <T>
	 * @return
	 */
	private <T> String getInsertAllColumnSqlAndParams(String tableName, Field[] fields, T element, Object[] params) {
		int length = fields.length;
		// 添加参数占位符?
		StringBuilder param = new StringBuilder(length*2);
		try {
			for (int i = 0; i < length; i ++){
				param.append("?,");
				fields[i].setAccessible(true);
				params[i] = fields[i].get(element);
			}
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			System.out.println("获取" + element + "的属性值失败！");
		}
		param.deleteCharAt(param.length()-1);

		String sql = String.format(SqlMethodEnum.INSERT_ONE_ALL_COLUMN.getSql(), tableName, param.toString());
		return sql;
	}

	private <T> String getUpdateSqlAndParams(String tableName, Field[] fields, T element, Object[] params) {
		//拼接set后面的部分，如 name = v1, age = 18
		StringBuilder param = new StringBuilder();
		String idName = "";
		int index = 0; // 记录参数的位置
		try {
			for (int i = 0, max = fields.length; i < max; i ++){
				fields[i].setAccessible(true);
				// 找到id对应的列名和值
				if (fields[i].isAnnotationPresent(TableId.class)){
					idName = fields[i].getAnnotation(TableId.class).value();
					// id作为update sql 的最后一个参数
					params[max-1] = fields[i].get(element);
					if (params[max-1] == null){
						throw new RuntimeException(element + "没有Id属性!");
					}
				}
				// 找到字段
				if (fields[i].isAnnotationPresent(TableField.class)) {
					TableField column = fields[i].getAnnotation(TableField.class);
					String columnName = column.value();
					param.append(columnName).append( " = ? ,");
					// update sql 的参数
					params[index++] = fields[i].get(element);
				}else{
					if (fields[i].isAnnotationPresent(TableId.class)) {
						continue;
					}
					//没加注解的表示都是字段
					String columnName2 = fields[i].getName();
					param.append(columnName2).append( " = ? ,");
					// update sql 的参数
					params[index++] = fields[i].get(element);
				}
			}
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			System.out.println("获取" + element + "的属性值失败！");
		}
		param.deleteCharAt(param.length()-1);
		String sql = String.format(SqlMethodEnum.UPDATE_BY_ID.getSql(), tableName, param.toString(), idName);
		return sql;
	}


}
