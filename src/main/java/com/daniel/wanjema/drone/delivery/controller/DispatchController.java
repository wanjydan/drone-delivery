package com.daniel.wanjema.drone.delivery.controller;

import com.daniel.wanjema.drone.delivery.model.Drone;
import com.daniel.wanjema.drone.delivery.model.Medication;
import com.daniel.wanjema.drone.delivery.model.dto.ApiResponse;
import com.daniel.wanjema.drone.delivery.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DispatchController {
    private final DroneService droneService;

    @Autowired
    public DispatchController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("drones/register")
    public Mono<ApiResponse> registerDrone(@RequestHeader Map<String, String> headers, @RequestBody Drone drone) {
        return droneService.register(headers, drone);
    }

    @GetMapping("drones/{id}/battery")
    public Mono<ApiResponse> checkDroneBatteryLevel(@RequestHeader Map<String, String> headers, @PathVariable String id) {
        return droneService.getBatteryLevel(headers, id);
    }

    @GetMapping("drones/available")
    public Mono<ApiResponse> checkAvailableDrones(@RequestHeader Map<String, String> headers, @RequestParam(value = "weight", required = false) Double weight) {
        return droneService.getAvailable(headers, weight != null ? java.util.Optional.of(weight) : java.util.Optional.empty());
    }

    @PostMapping("drones/{id}/load")
    public Mono<ApiResponse> loadDrone(@RequestHeader Map<String, String> headers, @PathVariable String id, @RequestBody List<Medication> medications) {
        return droneService.loadMedication(headers, id, medications);
    }

    @GetMapping("drones/{id}/load")
    public Mono<ApiResponse> loadDrone(@RequestHeader Map<String, String> headers, @PathVariable String id) {
        return droneService.getLoad(headers, id);
    }
}
