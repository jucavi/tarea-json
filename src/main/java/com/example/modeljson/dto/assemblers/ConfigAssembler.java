package com.example.modeljson.dto.assemblers;

import com.example.modeljson.dto.ConfigDto;
import com.example.modeljson.model.Attribute;
import com.example.modeljson.model.Config;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;

public class ConfigAssembler {

    /**
     * Returns a Config entity from dto
     * @param dto config data transfer object
     * @return config entity
     */
    public static Config mapFromDto(@NonNull ConfigDto dto) {

        var config = new Config();

        BeanUtils.copyProperties(dto, config, "attribute, parent");
        config.setParent(
                Config.builder()
                        .id(dto.getId())
                        .build());

        config.setAttribute(
                Attribute.builder()
                        .id(dto.getId())
                        .build());

        return config;
    }

    /**
     * Returns a Config data transfer object from dto
     * @param entity config entity
     * @return config data transfer object
     */
    public static ConfigDto mapToDto(@NonNull Config entity) {

        var configDto = new ConfigDto();

        BeanUtils.copyProperties(configDto, entity, "attribute, parent");
        configDto.setParentId(entity.getParent().getId());
        configDto.setAttributeId(entity.getAttribute().getId());

        return configDto;
    }
}
