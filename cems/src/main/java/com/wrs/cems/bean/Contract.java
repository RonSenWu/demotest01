package com.wrs.cems.bean;

import java.util.Date;

public class Contract {
    private Integer CId;
    private String CWorkId;
    private Double contractTerm;
    private Date beginContract;
    private Date endContract;
    private String contractContent;

    private Employee emp;

    public Contract() {
    }

    public Contract(Integer CId, String CWorkId, Double contractTerm, Date beginContract, Date endContract, String contractContent, Employee emp) {
        this.CId = CId;
        this.CWorkId = CWorkId;
        this.contractTerm = contractTerm;
        this.beginContract = beginContract;
        this.endContract = endContract;
        this.contractContent = contractContent;
        this.emp = emp;
    }

    public Integer getCId() {
        return CId;
    }

    public void setCId(Integer CId) {
        this.CId = CId;
    }

    public String getCWorkId() {
        return CWorkId;
    }

    public void setCWorkId(String CWorkId) {
        this.CWorkId = CWorkId;
    }

    public Double getContractTerm() {
        return contractTerm;
    }

    public void setContractTerm(Double contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Date getBeginContract() {
        return beginContract;
    }

    public void setBeginContract(Date beginContract) {
        this.beginContract = beginContract;
    }

    public Date getEndContract() {
        return endContract;
    }

    public void setEndContract(Date endContract) {
        this.endContract = endContract;
    }

    public String getContractContent() {
        return contractContent;
    }

    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "  CId=" + CId +'\'' +
                ", CWorkId='" + CWorkId + '\'' +
                ", contractTerm=" + contractTerm +'\'' +
                ", beginContract=" + beginContract +'\'' +
                ", endContract=" + endContract +'\'' +
                ", contractContent='" + contractContent + '\'' +
                ", emp=" + emp +
                '}';
    }
}
