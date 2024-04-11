package com.example.modeljson.service;


import com.example.modeljson.dto.AttributeTypeDto;
import com.example.modeljson.model.AttributeType;

import java.util.List;

public interface AttributeTypeServiceInterface {

    List<AttributeTypeDto> findAll();
    AttributeTypeDto findById(Long id);
    AttributeTypeDto create(AttributeType attributeType);
    AttributeTypeDto update(AttributeType attributeType);
    void deleteById(Long id);
}
