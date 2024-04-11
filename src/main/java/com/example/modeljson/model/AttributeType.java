package com.example.modeljson.model;


import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeType extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE", nullable = false, unique = true)
    private String type;

    @Column(name = "ENUM_DESCRIPTION")
    private String enumDescription;

    @Column(name = "IS_ENUM", nullable = false)
    private Boolean isEnum = false;

    @Column(name = "IS_LIST", nullable = false)
    private Boolean isList = false;
}
