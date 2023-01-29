package com.daniel.wanjema.drone.delivery.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseHeader {
    private String requestRefId;
    private int responseCode;
    private String responseMessage;
    private String timestamp;
}
