package com.example.modeljson.model;


import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Config extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DEFAULT_VALUE")
    private String default_value;

    @Column(name = "IS_CUSTOM")
    private Boolean isCustom = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ID", nullable = false)
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY) // TODO: check orphan removes?
    @JoinColumn(name = "PARENT", referencedColumnName = "ID")
    private Config parent;

}
