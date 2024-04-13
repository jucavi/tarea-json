package com.example.modeljson.dto.assemblers;

import com.example.modeljson.dto.Mappeable;
import com.example.modeljson.dto.attributetype.AttributeTypeDto;
import com.example.modeljson.model.AttributeType;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;

/**
 * Assembler for AttributeType mappings
 */
public class AttributeTypeAssembler {

    /**
     * Returns an AttributeType entity from dto
     * @param dto attribute type data transfer object
     * @return attribute type entity
     */
    public static AttributeType mapFromDto(@NonNull Mappeable dto) {

        var entity = new AttributeType();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    /**
     * Returns a dto from AttributeType
     * @param entity AttributeType entity
     * @return attribute type data transfer object
     */
    public static AttributeTypeDto mapToDto(@NonNull AttributeType entity) {

        var dto = new AttributeTypeDto();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

}
