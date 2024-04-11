package com.example.modeljson.model;


import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String default_value;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Attribute attribute;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // TODO: check orphan removes?
    private Config parent;

}
