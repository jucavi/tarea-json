package com.example.modeljson.model;

import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeTypeValue extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    private Long id;

    @NonNull
    @NotBlank(message = "Value field can't be empty")
    @Column(name = "VALUE", unique = true)
    private String value;


    @Column(name = "DESCRIPTION")
    private String description;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_TYPE_ID",
            nullable = false,
            updatable = false)
    private AttributeType attribute_type;
}
