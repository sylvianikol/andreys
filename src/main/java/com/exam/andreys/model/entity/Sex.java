package com.exam.andreys.model.entity;

import com.exam.andreys.model.enums.SexType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "sex")
public class Sex extends BaseEntity {

    private SexType sex;

    public Sex() {
    }

    @Enumerated(value = EnumType.STRING)
    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }
}
