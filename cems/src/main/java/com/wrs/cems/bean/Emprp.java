package com.wrs.cems.bean;

import java.util.Date;

public class Emprp {
    private Integer id;
    private String rpWorkId;
    private Date rpDate;
    private String rpType;
    private String rpReason;
    private Integer rpPoint;
    private String  remark;

    private Employee emp;

    public Emprp() {
    }

    public Emprp(Integer id, String rpWorkId, Date rpDate, String rpType, String rpReason, Integer rpPoint, String remark, Employee emp) {
        this.id = id;
        this.rpWorkId = rpWorkId;
        this.rpDate = rpDate;
        this.rpType = rpType;
        this.rpReason = rpReason;
        this.rpPoint = rpPoint;
        this.remark = remark;
        this.emp = emp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRpWorkId() {
        return rpWorkId;
    }

    public void setRpWorkId(String rpWorkId) {
        this.rpWorkId = rpWorkId;
    }

    public Date getRpDate() {
        return rpDate;
    }

    public void setRpDate(Date rpDate) {
        this.rpDate = rpDate;
    }

    public String getRpReason() {
        return rpReason;
    }

    public void setRpReason(String rpReason) {
        this.rpReason = rpReason;
    }

    public Integer getRpPoint() {
        return rpPoint;
    }

    public void setRpPoint(Integer rpPoint) {
        this.rpPoint = rpPoint;
    }

    public String getRpType() {
        return rpType;
    }

    public void setRpType(String rpType) {
        this.rpType = rpType;
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
