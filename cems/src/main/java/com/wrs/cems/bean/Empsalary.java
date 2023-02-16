package com.wrs.cems.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Empsalary {
    private Integer SId;
    private String SWorkId;
    private Integer basicSal;
    private Integer perk;
    private Integer bonus;
    private Integer forfeit;
    private Integer accountSal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date salDate;

    private Employee emp;

    public Empsalary() {
    }

    public Empsalary(Integer SId, String SWorkId, Integer basicSal,
                     Integer perk, Integer bonus, Integer forfeit,
                     Integer accountSal, Date salDate, Employee emp) {
        this.SId = SId;
        this.SWorkId = SWorkId;
        this.basicSal = basicSal;
        this.perk = perk;
        this.bonus = bonus;
        this.forfeit = forfeit;
        this.accountSal = accountSal;
        this.salDate = salDate;
        this.emp = emp;
    }

    public Integer getSId() {
        return SId;
    }

    public void setSId(Integer SId) {
        this.SId = SId;
    }

    public String getSWorkId() {
        return SWorkId;
    }

    public void setSWorkId(String SWorkId) {
        this.SWorkId = SWorkId;
    }

    public Integer getPerk() {
        return perk;
    }

    public void setPerk(Integer perk) {
        this.perk = perk;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Integer getForfeit() {
        return forfeit;
    }

    public void setForfeit(Integer forfeit) {
        this.forfeit = forfeit;
    }

    public Integer getBasicSal() {
        return basicSal;
    }

    public void setBasicSal(Integer basicSal) {
        this.basicSal = basicSal;
    }

    public Integer getAccountSal() {
        return accountSal;
    }

    public void setAccountSal(Integer accountSal) {
        this.accountSal = accountSal;
    }

    public Date getSalDate() {
        return salDate;
    }

    public void setSalDate(Date salDate) {
        this.salDate = salDate;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

}
