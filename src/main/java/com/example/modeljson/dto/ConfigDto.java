package com.example.modeljson.dto;


import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigDto extends AbstractEntityDtoConfig {


    private Long id;

    private String description;

    private String default_value;

    @NonNull()
    private String attributeId;

    private String parentId;
}