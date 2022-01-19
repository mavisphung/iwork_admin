package com.dfksoft.hrm_manage.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "absence_type")
public class AbsenceType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "appliedTo")
    private Character appliedTo;

    public AbsenceType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Character getAppliedTo() {
        return appliedTo;
    }

    public void setAppliedTo(Character appliedTo) {
        this.appliedTo = appliedTo;
    }
}
