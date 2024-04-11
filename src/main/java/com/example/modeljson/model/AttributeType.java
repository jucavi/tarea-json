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
    private Long id;

    @Column(nullable = false, unique = true)
    private String type;

    private String enumDescription;

    @Column(nullable = false)
    private Integer is_enum = 0;

    @Column(nullable = false)
    private Integer is_list = 0;
}
