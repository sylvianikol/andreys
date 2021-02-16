package com.exam.andreys.service.impl;

import com.exam.andreys.model.entity.Category;
import com.exam.andreys.model.entity.Sex;
import com.exam.andreys.model.enums.CategoryName;
import com.exam.andreys.model.enums.SexType;
import com.exam.andreys.repository.SexRepository;
import com.exam.andreys.service.SexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SexServiceImpl implements SexService {

    private final SexRepository sexRepository;

    @Autowired
    public SexServiceImpl(SexRepository sexRepository) {
        this.sexRepository = sexRepository;
    }

    @Override
    public void initSexes() {
        if (sexRepository.count() == 0) {

            Arrays.stream(SexType.values())
                    .forEach(value -> {
                        Sex sex = new Sex();
                        sex.setSex(value);

                        this.sexRepository.save(sex);
                    });
        }
    }

    @Override
    public Sex findBySex(SexType sex) {
        return this.sexRepository.findBySex(sex).orElse(null);
    }
}
