package com.dfksoft.hrm_manage.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "title")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "description")
    private String description;

    @Column(name = "time_allow")
    private int timeAllow;

    public Title() {
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

    public int getTimeAllow() {
        return timeAllow;
    }

    public void setTimeAllow(int timeAllow) {
        this.timeAllow = timeAllow;
    }
}
