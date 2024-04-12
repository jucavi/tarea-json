package com.example.modeljson.dto;

import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeTypeValueDto extends AbstractEntityDtoConfig {

    private Long id;

    @NotNull
    @NotBlank(message = "Value field can't be empty")
    private String value;

    private String description;

    @NotNull
    private Long attributeTypeId;
}
