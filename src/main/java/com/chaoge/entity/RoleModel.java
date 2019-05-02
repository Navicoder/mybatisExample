package com.chaoge.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class RoleModel {
    private Integer id;
    private String name;
    private Boolean isActive;
    private String description;
    private Date lastUpdateTime;
    //private Timestamp lastUpdateTime;
    private List<MenuModel> menus;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Timestamp getLastUpdateTime() {
//        return lastUpdateTime;
//    }
//
//    public void setLastUpdateTime(Timestamp lastUpdateTime) {
//        this.lastUpdateTime = lastUpdateTime;
//    }


    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<MenuModel> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuModel> menus) {
        this.menus = menus;
    }
}
