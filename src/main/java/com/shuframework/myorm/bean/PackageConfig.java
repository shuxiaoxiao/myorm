package com.shuframework.myorm.bean;

import com.shuframework.myorm.util.StringUtil;
import com.shuframework.myorm.util.SystemUtil;

/**
 * Created by shu on 2018/10/8.
 */
public class PackageConfig {

    private String parent = "com.shuframework";
    private String moduleName = null;
    private String entity = "model";
    private String service = "service";
    private String serviceImpl = "service.impl";
    private String mapper = "mapper";
    private String xml = "mapper.xml";
    private String controller = "controller";

    public PackageConfig() {
    }

    public String getParent() {
        return SystemUtil.isNotEmpty(this.moduleName)? (this.parent + "." + this.moduleName):this.parent;
    }

    public PackageConfig setParent(String parent) {
        this.parent = parent;
        return this;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public PackageConfig setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public String getEntity() {
        return this.entity;
    }

    public PackageConfig setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getService() {
        return this.service;
    }

    public PackageConfig setService(String service) {
        this.service = service;
        return this;
    }

    public String getServiceImpl() {
        return this.serviceImpl;
    }

    public PackageConfig setServiceImpl(String serviceImpl) {
        this.serviceImpl = serviceImpl;
        return this;
    }

    public String getMapper() {
        return this.mapper;
    }

    public PackageConfig setMapper(String mapper) {
        this.mapper = mapper;
        return this;
    }

    public String getXml() {
        return this.xml;
    }

    public PackageConfig setXml(String xml) {
        this.xml = xml;
        return this;
    }

    public String getController() {
        return this.controller;
    }

    public PackageConfig setController(String controller) {
        this.controller = controller;
        return this;
    }
}
