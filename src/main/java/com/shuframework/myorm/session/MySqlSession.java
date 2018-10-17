package com.shuframework.myorm.session;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface MySqlSession {

	/**
	 * 新增
	 * bean里面有值的属性才会拼进sql
	 *
	 * @param bean
	 * @param <T>
	 * @return
	 */
	<T> int insert(T bean);

	/**
	 * 新增
	 * bean里面所有的属性会拼进sql,没值是null,这样会导致数据表字段的默认值不生效
	 *
	 * @param bean
	 * @param <T>
	 * @return
	 */
	<T> int insertAllColumn(T bean);

	/**
	 * 更新
	 * bean里面有值的属性会拼进sql,如果设置值为null则不修改该属性
	 *
	 * @param bean
	 * @param <T>
	 * @return
	 */
	<T> int updateById(T bean);

//	/**
//	 * 更新
//	 * bean里面所有的属性会拼进sql,没值是null,这样会导致数据表字段的默认值不生效,而且容易将原有的值修改掉
//	 *
//	 * @param bean
//	 * @param <T>
//	 * @return
//	 */
//	<T> int updateAllById(T bean);

//	int deleteById(Serializable id);
//	int delete(T bean);

	<T> T selectById(Class clazz, Serializable id);

	<T> List<T> selectList(String sql, Class clazz, List<?> params);

//	List<Map<String, Object>> selectListMap(String sql, List<?> params);
}
