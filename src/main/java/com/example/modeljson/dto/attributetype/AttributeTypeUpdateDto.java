package com.example.modeljson.dto.attributetype;

import com.example.modeljson.config.api.utils.AbstractEntityDtoConfig;
import com.example.modeljson.dto.Mappeable;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AttributeTypeUpdateDto extends AbstractEntityDtoConfig implements Mappeable {

    private Long id;

    private String enumDescription;
}
