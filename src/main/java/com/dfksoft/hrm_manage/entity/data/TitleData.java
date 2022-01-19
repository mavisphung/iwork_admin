package com.dfksoft.hrm_manage.entity.data;

import java.io.Serializable;
import java.time.LocalTime;

public class TitleData implements Serializable {
    private int id;

    private String titleName;

    private String description;

    private LocalTime timeAllow;

    public TitleData(int id, String titleName, String description, LocalTime timeAllow) {
        this.id = id;
        this.titleName = titleName;
        this.description = description;
        this.timeAllow = timeAllow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getTimeAllow() {
        return timeAllow;
    }

    public void setTimeAllow(LocalTime timeAllow) {
        this.timeAllow = timeAllow;
    }
}
