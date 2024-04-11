package com.example.modeljson.model;

import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeTypeValue extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "VALUE", unique = true)
    private String value;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_TYPE", nullable = false)
    private AttributeType attribute_type;
}
