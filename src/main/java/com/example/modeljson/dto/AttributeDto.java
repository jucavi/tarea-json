package com.example.modeljson.dto;

import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeDto extends AbstractEntityDtoConfig {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long attributeTypeId;
}
