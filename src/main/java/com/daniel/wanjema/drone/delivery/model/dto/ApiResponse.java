package com.daniel.wanjema.drone.delivery.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private ApiResponseHeader header;
    private Object body;
}
