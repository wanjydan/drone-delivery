package com.daniel.wanjema.drone.delivery.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Data
@Document("medication")
public class MedicationDTO extends BaseEntity {
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9_-]+")
    private String name;

    @NotNull
    private Integer weight;

    @NotBlank
    @Pattern(regexp = "[A-Z_0-9]+")
    private String code;

    @NotBlank
    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private DroneDTO drone;
}
