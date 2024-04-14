package com.example.modeljson.repository;

import com.example.modeljson.model.AttributeTypeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAttributeTypeValueRepository extends JpaRepository<AttributeTypeValue, Long> {
    Optional<AttributeTypeValue> findByValue(String asText);
}
