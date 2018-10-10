package com.shuframework.myorm.session;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface MySqlSession {

	int executeUpdate(String sql, Object... params);
	int executeUpdate(String sql, List<?> params);
	
	<T> int insert(T bean);

	<T> int updateById(T bean);

//	<T> int deleteById(Serializable id);
//	<T> int delete(T bean);

	<T> T selectById(Serializable id);

	<T> List<T> selectList(String sql, Class<T> clazz, List<?> params);

//	List<Map<String, Object>> selectListMap(String sql, List<?> params);
}
