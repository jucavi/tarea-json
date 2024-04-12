package com.example.modeljson.dto;

import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeTypeDto extends AbstractEntityDtoConfig {

    private Long id;

    @NotNull
    private String type;

    private Boolean enumDescription;

    private Boolean isEnum = Boolean.FALSE;

    private Boolean isList = Boolean.FALSE;
}
