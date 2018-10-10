package com.shuframework.myorm.core;

import com.shuframework.myorm.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * mysql数据类型和java数据类型的转换
 * @author gaoqi
 *
 */
public class MySqlTypeConvertor implements TypeConvertor {

	@Override
	public String databaseType2JavaType(String columnType) {
		
		Map<String, String> typeMap = new HashMap<>();
		typeMap.put("bigint", "Long");
		typeMap.put("float", "Float");
		typeMap.put("double", "Double");
		typeMap.put("decimal", "java.math.BigDecimal");
		typeMap.put("date", "java.util.Date");
		typeMap.put("datetime", "java.util.Date");
		typeMap.put("time", "java.sql.Timestamp");
		typeMap.put("clob", "java.sql.Clob");
		typeMap.put("blob", "java.sql.BLob");

		String key = columnType.toLowerCase();
		String dataType = typeMap.get(key);
		if (key.contains("char")){
			dataType = "String";
		}
		if (key.contains("int") && !key.equals("bigint")){
			dataType = "Integer";
		}
//		if (key.contains("number")){
//			if ((StringUtil.isNotEmpty(scale)) && (Integer.parseInt(scale) > 0))
//				dataType = "java.math.BigDecimal";
//			else if ((StringUtils.isNotBlank(precision)) && (Integer.parseInt(precision) > 6))
//				dataType = "Long";
//			else
//				dataType = "Integer";
//		}

		return dataType;
	}

	@Override
	public String javaType2DatabaseType(String javaDataType) {
		return null;
	}

}
