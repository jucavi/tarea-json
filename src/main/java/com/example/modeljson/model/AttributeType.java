package com.example.modeljson.model;


import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeType extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @NotBlank(message = "Type field can't be empty")
    @Column(name = "TYPE", nullable = false, unique = true)
    private String type;

    @Column(name = "ENUM_DESCRIPTION")
    private String enumDescription;

    @Column(name = "IS_ENUM",
            nullable = false,
            updatable = false)
    private Boolean isEnum = false;

    @Column(name = "IS_LIST",
            nullable = false,
            updatable = false)
    private Boolean isList = false;
}
