package com.example.modeljson.dto.assemblers;

import com.example.modeljson.dto.ConfigDto;
import com.example.modeljson.model.Attribute;
import com.example.modeljson.model.Config;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;

/**
 * Assembler for Config mappings
 */
public class ConfigAssembler {

    /**
     * Returns a Config entity from dto
     * @param dto config data transfer object
     * @return config entity
     */
    public static Config mapFromDto(@NonNull ConfigDto dto) {

        var entity = new Config();

        BeanUtils.copyProperties(dto, entity, "attribute, parent");
        entity.setParent(
                Config.builder()
                        .id(dto.getId())
                        .build());

        entity.setAttribute(
                Attribute.builder()
                        .id(dto.getId())
                        .build());

        return entity;
    }

    /**
     * Returns a Config data transfer object from dto
     * @param entity config entity
     * @return config data transfer object
     */
    public static ConfigDto mapToDto(@NonNull Config entity) {

        var dto = new ConfigDto();

        BeanUtils.copyProperties(dto, entity, "attribute, parent");
        dto.setParentId(entity.getParent().getId());
        dto.setAttributeId(entity.getAttribute().getId());

        return dto;
    }
}
