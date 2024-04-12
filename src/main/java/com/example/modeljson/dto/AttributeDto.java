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
public class AttributeDto extends AbstractEntityDtoConfig {

    private Long id;

    @NotNull
    @NotBlank(message = "Name field can't be empty")
    private String name;

    @NotNull
    private Long attributeTypeId;
}
