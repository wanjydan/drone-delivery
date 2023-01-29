package com.daniel.wanjema.drone.delivery.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {
    private String id;
    private String serialNumber;
    private Enums.DroneModel model;
    private int weightLimit;
    private int batteryCapacity;
    private Enums.DroneState state;
}
