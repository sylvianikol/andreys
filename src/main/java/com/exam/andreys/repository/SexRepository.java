package com.exam.andreys.repository;

import com.exam.andreys.model.entity.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exam.andreys.model.enums.SexType;

import java.util.Optional;

@Repository
public interface SexRepository extends JpaRepository<Sex, String> {

   Optional<Sex> findBySex(SexType type);
}
