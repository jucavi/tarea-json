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
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"name", "attribute_type"})
//})
public class Attribute extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Name field can't be empty")
    @Column()
    private String name;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_type", nullable = false,
            updatable = false)
    private AttributeType attributeType;
}
