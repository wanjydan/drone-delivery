package com.daniel.wanjema.drone.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    private String id;
    private String name;
    private Integer weight;
    private String code;
    private String image;
}
