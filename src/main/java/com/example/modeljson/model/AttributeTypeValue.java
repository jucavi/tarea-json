package com.example.modeljson.model;

import com.example.modeljson.config.api.utils.AbstractEntityConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttributeTypeValue extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Value field can't be empty")
    @Column(unique = true)
    private String value;

    @Column()
    private String description;

    @JsonIgnore
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,
            updatable = false)
    private AttributeType attributeType;

    @Override
    public String toString() {
        return "AttributeTypeValue{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
