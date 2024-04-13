package com.example.modeljson.dto.attributetype;

import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;
import com.example.modeljson.dto.Mappeable;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeTypeDto extends AbstractEntityDtoConfig implements Mappeable {

    private Long id;

    @NotNull
    @NotBlank(message = "Type field can't be empty")
    private String type;

    private String enumDescription;

    private Boolean isEnum = Boolean.FALSE;

    private Boolean isList = Boolean.FALSE;
}
