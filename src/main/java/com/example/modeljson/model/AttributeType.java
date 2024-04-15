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
public class AttributeType extends AbstractEntityConfig<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "Type field can't be empty")
    @Column(nullable = false)
    private String type;

    @Column()
    private String enumDescription;

    @Column(nullable = false,
            updatable = false)
    private Boolean isEnum = Boolean.FALSE;

    @Column(nullable = false,
            updatable = false)
    private Boolean isList = Boolean.FALSE;

    @Override
    public String toString() {
        return "AttributeType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", enumDescription='" + enumDescription + '\'' +
                ", isEnum=" + isEnum +
                ", isList=" + isList +
                '}';
    }
}
