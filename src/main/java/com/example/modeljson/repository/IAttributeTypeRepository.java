package com.example.modeljson.repository;

import com.example.modeljson.model.AttributeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAttributeTypeRepository extends JpaRepository<AttributeType, Long> {

    Optional<AttributeType> findByType(String strAttrType);
}
