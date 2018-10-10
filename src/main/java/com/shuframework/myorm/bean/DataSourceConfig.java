package com.shuframework.myorm.bean;

/**
 * 数据源配置
 * Created by shu on 2018/10/8.
 */
public class DataSourceConfig {

    /**
     * 驱动类
     */
    private String driver;
    /**
     * jdbc的url
     */
    private String jdbcUrl;
    /**
     * 数据库的用户名
     */
    private String username;
    /**
     * 数据库的密码
     */
    private String password;
    /**
     * 正在使用哪个数据库
     */
    private String databaseType;

    public DataSourceConfig() {
    }

    public DataSourceConfig(String driver, String jdbcUrl, String username, String password) {
        this.driver = driver;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
}
