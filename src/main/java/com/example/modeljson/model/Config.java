package com.example.modeljson.model;


import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Config extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(updatable = false)
    private String defaultValue;

    @Column(columnDefinition = "boolean default false")
    private Boolean isCustom = Boolean.FALSE;

    //@NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_id",
            nullable = false,
            updatable = false)
    private Attribute attribute;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER) // TODO: check orphan removes?
    @JoinColumn(name = "parent")
    private Config parent;
}
