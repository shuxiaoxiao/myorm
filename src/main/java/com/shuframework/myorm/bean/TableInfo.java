package com.shuframework.myorm.bean;

import java.util.List;
import java.util.Map;


/**
 * 存储表结构的信息
 *
 * @author gaoqi
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 所有字段的信息
     */
    private Map<String, ColumnInfo> columns;
    /**
     * 唯一主键(目前我们只能处理表中有且只有一个主键的情况)
     */
    private ColumnInfo onlyPriKey;
    /**
     * 如果联合主键，则在这里存储
     */
    private List<ColumnInfo> priKeys;


    public TableInfo() {
    }

    public TableInfo(String tableName, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
        this.tableName = tableName;
        this.columns = columns;
        this.onlyPriKey = onlyPriKey;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }

    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }

}
