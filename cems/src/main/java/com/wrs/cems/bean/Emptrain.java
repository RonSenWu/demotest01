package com.wrs.cems.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Emptrain {
    private Integer TId;
    private String TWorkId;
    private Integer TDeptId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trainDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trainEnd;
    private String trainContent;
    private String remark;

    private Employee emp;
    private Department dept;

    public Emptrain() {
    }

    public Emptrain(Integer TId, String TWorkId,Integer TDeptId,
                    Date trainDate,Date trainEnd, String trainContent,
                    String remark,Employee emp,Department dept) {
        this.TId = TId;
        this.TWorkId = TWorkId;
        this.TDeptId=TDeptId;
        this.trainDate = trainDate;
        this.trainEnd = trainEnd;
        this.trainContent = trainContent;
        this.remark = remark;
        this.emp =emp;
        this.dept =dept;
    }

    public Integer getTId() {
        return TId;
    }

    public void setTId(Integer TId) {
        this.TId = TId;
    }

    public String getTWorkId() {
        return TWorkId;
    }

    public void setTWorkId(String TWorkId) {
        this.TWorkId = TWorkId;
    }

    public Integer getTDeptId() {
        return TDeptId;
    }

    public void setTDeptId(Integer TDeptId) {
        this.TDeptId = TDeptId;
    }

    public Date getTrainDate() {
        return trainDate;
    }

    public void setTrainDate(Date trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainContent() {
        return trainContent;
    }

    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
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

    public Date getTrainEnd() {
        return trainEnd;
    }

    public void setTrainEnd(Date trainEnd) {
        this.trainEnd = trainEnd;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }
}
