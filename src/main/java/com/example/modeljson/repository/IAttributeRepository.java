package com.example.modeljson.repository;

import com.example.modeljson.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttributeRepository extends JpaRepository<Attribute, Long> {
}
