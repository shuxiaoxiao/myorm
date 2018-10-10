package com.shuframework.test.model;


import java.util.Date;

/**
 * <p>
 * 系统管理_用户
 * </p>
 *
 * @author shuheng
 */
public class SysUser {


    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 性别  1男 2女
     */
    private String sex;
    /**
     * 图标
     */
    private String icon;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 备用号
     */
    private String phoneNum2;
    /**
     * 地址
     */
    private String address;
    /**
     * 用户状态
     */
    private String enable;
    /**
     * 部门id
     */
    private String deptId;
    /**
     * 入职时间
     */
    private Date createTime;
    /**
     * 离职时间
     */
    private Date leaveTime;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 排序号
     */
    private Integer sortid;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum2() {
        return phoneNum2;
    }

    public void setPhoneNum2(String phoneNum2) {
        this.phoneNum2 = phoneNum2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "id=" + id +
        ", name=" + name +
        ", loginName=" + loginName +
        ", pwd=" + pwd +
        ", sex=" + sex +
        ", icon=" + icon +
        ", phoneNum=" + phoneNum +
        ", phoneNum2=" + phoneNum2 +
        ", address=" + address +
        ", enable=" + enable +
        ", deptId=" + deptId +
        ", createTime=" + createTime +
        ", leaveTime=" + leaveTime +
        ", userType=" + userType +
        ", sortid=" + sortid +
        ", updateTime=" + updateTime +
        "}";
    }
}
