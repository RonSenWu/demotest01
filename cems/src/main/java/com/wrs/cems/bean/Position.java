package com.wrs.cems.bean;

public class Position {
    private Integer id;
    private String name;
    private Integer did;

    public Position() {
    }

    public Position(Integer id, String name,Integer did) {
        this.id = id;
        this.name = name;
        this.did = did;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
}
