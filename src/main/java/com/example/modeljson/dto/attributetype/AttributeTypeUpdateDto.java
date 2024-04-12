package com.example.modeljson.dto.attributetype;

import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AttributeTypeUpdateDto extends AbstractEntityDtoConfig {

    private Long id;

    private String enumDescription;

    private boolean deleted;
}
