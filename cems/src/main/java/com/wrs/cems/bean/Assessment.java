package com.wrs.cems.bean;

import java.util.Date;

public class Assessment {
    private Integer id;
    private String eid;
    private Date appDate;
    private String appResult;
    private String appContent;
    private String remark;

    private Employee emp;

    public Assessment() {
    }

    public Assessment(Integer id, String eid, Date appDate, String appResult, String appContent, String remark) {
        this.id = id;
        this.eid = eid;
        this.appDate = appDate;
        this.appResult = appResult;
        this.appContent = appContent;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getAppResult() {
        return appResult;
    }

    public void setAppResult(String appResult) {
        this.appResult = appResult;
    }

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }
}
