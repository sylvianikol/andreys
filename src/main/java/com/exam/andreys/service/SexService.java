package com.exam.andreys.service;

import com.exam.andreys.model.entity.Sex;
import com.exam.andreys.model.enums.SexType;

public interface SexService {
    void initSexes();

    Sex findBySex(SexType sex);
}
