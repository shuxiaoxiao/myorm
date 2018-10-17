package com.shuframework.myorm.mapper;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {

	/**
     * 新增
	 * bean里面有值的属性才会拼进sql
	 *
	 * @param bean
     * @return
     */
	int insert(T bean);

	/**
     * 新增
	 * bean里面所有的属性会拼进sql,没值是null,这样会导致数据表字段的默认值不生效
	 *
	 * @param bean
     * @return
     */
	int insertAllColumn(T bean);

	/**
	 * 更新
	 * bean里面有值的属性会拼进sql,如果设置值为null则不修改该属性
	 *
	 * @param bean
	 * @return
	 */
	int updateById(T bean);

//	/**
//	 * 更新
//	 * bean里面所有的属性会拼进sql,没值是null,这样会导致数据表字段的默认值不生效,而且容易将原有的值修改掉
//	 *
//	 * @param bean
//	 * @return
//	 */
//	int updateAllById(T bean);

//	int deleteById(Serializable id);
//	int delete(T bean);

	T selectById(Class clazz, Serializable id);

	List<T> selectList(String sql, Class clazz, List<?> params);

}
