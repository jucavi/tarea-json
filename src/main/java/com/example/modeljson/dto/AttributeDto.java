package com.example.modeljson.dto;

import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;

import javax.validation.constraints.NotNull;

public class AttributeDto extends AbstractEntityDtoConfig {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long attributeTypeId;
}
