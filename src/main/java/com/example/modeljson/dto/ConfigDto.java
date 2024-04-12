package com.example.modeljson.dto;


import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigDto extends AbstractEntityDtoConfig {


    private Long id;

    private String description;

    private String default_value;

    private Boolean isCustom = Boolean.FALSE;

    @NonNull()
    private Long attributeId;

    private Long parentId;
}
