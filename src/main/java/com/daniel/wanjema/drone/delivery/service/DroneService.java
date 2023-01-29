package com.daniel.wanjema.drone.delivery.service;

import com.daniel.wanjema.drone.delivery.model.Drone;
import com.daniel.wanjema.drone.delivery.model.Medication;
import com.daniel.wanjema.drone.delivery.model.dto.ApiResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DroneService {
    Mono<ApiResponse> register(Map<String, String> headers, Drone drone);
    Mono<ApiResponse> getBatteryLevel(Map<String, String> headers, String id);
    Mono<ApiResponse> getAvailable(Map<String, String> headers, Optional<Double> weight);
    Mono<ApiResponse> loadMedication(Map<String, String> headers, String id, List<Medication> medications);
    Mono<ApiResponse> getLoad(Map<String, String> headers, String id);
}
