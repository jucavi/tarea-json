package com.example.modeljson.model;


import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(
        columnNames = {"type", "isList", "isEnum"}
)})
public class AttributeType extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Type field can't be empty")
    @Column(nullable = false)
    private String type;

    private String enumDescription;

    @Column(nullable = false,
            updatable = false)
    private Boolean isEnum = Boolean.FALSE;

    @Column(nullable = false,
            updatable = false)
    private Boolean isList = Boolean.FALSE;
}
