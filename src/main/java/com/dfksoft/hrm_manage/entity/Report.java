package com.dfksoft.hrm_manage.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "report")
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "date_work")
    @JsonProperty(value = "day_work")
    private LocalDate dateWork;

    @Column(name = "time_work")
    private int timeWork;

    @Column(name = "start_time_work")
    private int startTimeWork;

    @Column(name = "time_retirement")
    private int timeRetirement;

    @Column(name = "mac_address")
    private String macAddress;

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public void setDateWork(LocalDate dateWork) {
        this.dateWork = dateWork;
    }

    public int getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(int timeWork) {
        this.timeWork = timeWork;
    }

    public int getStartTimeWork() {
        return startTimeWork;
    }

    public void setStartTimeWork(int startTimeWork) {
        this.startTimeWork = startTimeWork;
    }

    public int getTimeRetirement() {
        return timeRetirement;
    }

    public void setTimeRetirement(int timeRetirement) {
        this.timeRetirement = timeRetirement;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
