package com.example.modeljson.repository;

import com.example.modeljson.model.AttributeTypeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeTypeValueRepository extends JpaRepository<AttributeTypeValue, Long> {
}
