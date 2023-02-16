package com.wrs.cems.bean;

public class User {
    private Integer id;
    private String username;
    private String account;
    private String password;
    private Integer enabled;
    private String role;

    public User() {
    }

    public User(Integer id,String username,String account, String password,Integer enabled,String role) {
        this.id = id;
        this.username = username;
        this.account=account;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }
    public User(String username,String account, String password,Integer enabled,String role) {
        this.username = username;
        this.account=account;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    public User(String password) {
        this.password = password;
    }
    public User(String account, String password,String role) {
        this.account=account;
        this.password = password;
        this.role =role;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", enabled='").append(enabled).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
