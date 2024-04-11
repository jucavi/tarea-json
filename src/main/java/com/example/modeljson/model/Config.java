package com.example.modeljson.model;


import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_ID", referencedColumnName = "ID", nullable = false)
    private Attribute attribute;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // TODO: check orphan removes?
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "ID")
    private Config parent;

}
