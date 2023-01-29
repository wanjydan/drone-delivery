package com.daniel.wanjema.drone.delivery.dto;

import com.daniel.wanjema.drone.delivery.model.Enums;
import com.daniel.wanjema.drone.delivery.model.Medication;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Document("drone")
public class DroneDTO extends BaseEntity{
    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    @Field("serial_number")
    private String serialNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Field("model")
    private Enums.DroneModel model;

    @Min(0)
    @Max(500)
    @Field("weight_limit")
    private Double weightLimit;

    @Min(0)
    @Max(100)
    @Field("battery_capacity")
    private int batteryCapacity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Field("state")
    private Enums.DroneState state;

    @DBRef
    private List<Medication> medications;
}
