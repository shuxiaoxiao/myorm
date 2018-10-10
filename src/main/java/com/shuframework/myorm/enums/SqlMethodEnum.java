package com.shuframework.myorm.enums;

/**
 * 支持的 SQL方法
 * 
 * @author shu
 *
 */
public enum SqlMethodEnum {
	/**
	 * 插入
	 */
	INSERT_ONE("insert", "插入一条数据（选择字段插入）", "INSERT INTO %s (%s) VALUES (%s)"),
	INSERT_ONE_ALL_COLUMN("insertAllColumn", "插入一条数据（全部字段插入）", "INSERT INTO %s VALUES (%s)"),
	
    /**
     * 修改
     */
    UPDATE_BY_ID("updateById", "根据ID 选择修改数据", "UPDATE %s set %s WHERE %s=?"),
//    UPDATE_ALL_COLUMN_BY_ID("updateAllColumnById", "根据ID 修改全部数据", "UPDATE %s %s WHERE %s=#{%s} %s"),
//    UPDATE("update", "根据 whereEntity 条件，更新记录", "UPDATE %s %s %s"),
	
    /**
     * 逻辑删除
     */
    LOGIC_DELETE_BY_ID("deleteById", "根据ID 逻辑删除一条数据", "UPDATE %s set %s WHERE %s=?"),
//    LOGIC_DELETE_BY_MAP("deleteByMap", "根据columnMap 条件逻辑删除记录", "UPDATE %s %s %s"),
//    LOGIC_DELETE("delete", "根据 entity 条件逻辑删除记录", "UPDATE %s %s %s"),
//    LOGIC_DELETE_BATCH_BY_IDS("deleteBatchIds", "根据ID集合，批量逻辑删除数据", "UPDATE %s %s WHERE %s IN (%s)"),

    
	/**
     * 查询
     */
    SELECT_BY_ID("selectById", "根据ID 查询一条数据", "SELECT %s FROM %s WHERE %s=?"),
//    SELECT_BY_MAP("selectByMap", "根据columnMap 查询一条数据", "SELECT %s FROM %s %s"),
//    SELECT_BATCH_BY_IDS("selectBatchIds", "根据ID集合，批量查询数据", "SELECT %s FROM %s WHERE %s IN (%s)"),
//    SELECT_ONE("selectOne", "查询满足条件一条数据", "SELECT %s FROM %s %s"),
    SELECT_COUNT("selectCount", "查询满足条件总记录数", "SELECT COUNT(1) FROM %s %s"),
    SELECT_LIST("selectList", "查询满足条件所有数据", "SELECT %s FROM %s %s"),
//    SELECT_PAGE("selectPage", "查询满足条件所有数据（并翻页）", "SELECT %s FROM %s %s"),
//    SELECT_MAPS("selectMaps", "查询满足条件所有数据", "SELECT %s FROM %s %s"),
//    SELECT_MAPS_PAGE("selectMapsPage", "查询满足条件所有数据（并翻页）", "SELECT %s FROM %s %s"),
//    SELECT_OBJS("selectObjs", "查询满足条件所有数据", "SELECT %s FROM %s %s")
 	;

	
	private final String method;
	private final String desc;
	private final String sql;

	SqlMethodEnum(final String method, final String desc, final String sql) {
		this.method = method;
		this.desc = desc;
		this.sql = sql;
	}

	public String getMethod() {
		return this.method;
	}

	public String getDesc() {
		return this.desc;
	}

	public String getSql() {
		return this.sql;
	}
}
