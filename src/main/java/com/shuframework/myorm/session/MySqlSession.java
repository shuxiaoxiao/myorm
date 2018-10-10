package com.shuframework.myorm.session;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface MySqlSession<T> {

//	int executeUpdate(String sql, Object... params);
//	int executeUpdate(String sql, List<?> params);
	
	int insert(T bean);

	int insertAllColumn(T bean);

	int updateById(T bean);

//	int deleteById(Serializable id);
//	int delete(T bean);

	T selectById(Serializable id);

	List<T> selectList(String sql, Class<T> clazz, List<?> params);

//	List<Map<String, Object>> selectListMap(String sql, List<?> params);
}
