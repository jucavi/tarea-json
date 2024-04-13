package com.example.modeljson.repository;

import com.example.modeljson.model.AttributeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttributeTypeRepository extends JpaRepository<AttributeType, Long> {
}
