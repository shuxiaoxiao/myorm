package com.shuframework.myorm.bean;

/**
 * 封装表中一个字段的信息
 *
 * @author shuheng
 */
public class ColumnInfo {
    /**
     * 字段名称
     */
    private String columnName;
    /**
     * 字段的备注
     */
    private String columnComment;
    /**
     * 字段的数据类型
     */
    private String columnType;
    /**
     * 字段的键类型(0：普通键，1：主键 2：外键)
     */
    private int keyType;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 对应类的属性名称
     */
    private String propertyName;


    public ColumnInfo() {
    }

    public ColumnInfo(String columnName, String columnType, int keyType, String dataType) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.keyType = keyType;
        this.dataType = dataType;
    }

    public ColumnInfo(String columnName, String columnComment, String columnType, int keyType, String dataType, String propertyName) {
        this.columnName = columnName;
        this.columnComment = columnComment;
        this.columnType = columnType;
        this.keyType = keyType;
        this.dataType = dataType;
        this.propertyName = propertyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

}
