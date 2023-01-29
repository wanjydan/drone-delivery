package com.daniel.wanjema.drone.delivery.service.impl;

import com.daniel.wanjema.drone.delivery.dto.DroneDTO;
import com.daniel.wanjema.drone.delivery.dto.MedicationDTO;
import com.daniel.wanjema.drone.delivery.model.*;
import com.daniel.wanjema.drone.delivery.model.dto.ApiResponseHeader;
import com.daniel.wanjema.drone.delivery.model.dto.ApiResponse;
import com.daniel.wanjema.drone.delivery.repository.DroneRepository;
import com.daniel.wanjema.drone.delivery.repository.MedicationRepository;
import com.daniel.wanjema.drone.delivery.service.DroneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.daniel.wanjema.drone.delivery.config.GlobalVariables.*;
import static com.daniel.wanjema.drone.delivery.utils.LogManager.logError;
import static com.daniel.wanjema.drone.delivery.utils.ResponseFormatter.setApiResponse;

@Service
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DroneServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }

    public Mono<ApiResponse> register(Map<String, String> headers, Drone drone) {
        return droneRepository.findBySerialNumber(drone.getSerialNumber())
                .doOnError(error->logError(headers, error.getMessage()))
                .collectList()
                .flatMap(existingDrone -> {
                    if (!existingDrone.isEmpty())
                        return Mono.just(setApiResponse(headers, CODE_REQUEST_ERROR, "Serial number already exists", null));
                    else {
                        drone.setId(null);
                        return droneRepository.save(modelMapper.map(drone, DroneDTO.class))
                                .doOnError(error->logError(headers, error.getMessage()))
                                .map(savedDrone -> setApiResponse(headers, CODE_CREATED, MESSAGE_SUCCESS, modelMapper.map(savedDrone, Drone.class)))
                                .defaultIfEmpty(setApiResponse(headers, CODE_SERVER_ERROR, "Error saving drone", null));
                    }
                });
    }

    public Mono<ApiResponse> getBatteryLevel(Map<String, String> headers,String id) {
        return droneRepository.findById(id)
                .doOnError(error->logError(headers, error.getMessage()))
                .map(drone -> setApiResponse(headers, CODE_SUCCESS, MESSAGE_SUCCESS, new Battery(drone.getBatteryCapacity() + "%")))
                .defaultIfEmpty(setApiResponse(headers, CODE_NOT_FOUND_ERROR, "Drone not found", null));
    }

    public Mono<ApiResponse> getAvailable(Map<String, String> headers, Optional<Double> weight) {
        return droneRepository.findAll()
                .doOnError(error->logError(headers, error.getMessage()))
                .filter(drone -> drone.getState() == Enums.DroneState.IDLE && drone.getBatteryCapacity() >= 25)
                .filter(drone -> !weight.isPresent() || weight.get() <= drone.getWeightLimit())
                .collectList()
                .map(drones -> setApiResponse(headers, CODE_SUCCESS, MESSAGE_SUCCESS, drones.stream().map(drone -> modelMapper.map(drone, Drone.class)).collect(Collectors.toList())))
                .defaultIfEmpty(setApiResponse(headers, CODE_REQUEST_ERROR, "No drones available for loading", new ArrayList<>()));
    }

    public Mono<ApiResponse> loadMedication(Map<String, String> headers,String id, List<Medication> medications) {
        return droneRepository.findById(id)
                .doOnError(error->logError(headers, error.getMessage()))
                .flatMap(drone -> {
                    if (drone.getState() != Enums.DroneState.IDLE)
                        return Mono.just(setApiResponse(headers, CODE_REQUEST_ERROR, "Drone is not idle", null));
                    else if (drone.getBatteryCapacity() < 25)
                        return Mono.just(setApiResponse(headers, CODE_REQUEST_ERROR, "Drone battery level is low", null));
                    else if (drone.getWeightLimit() < medications.stream().mapToInt(Medication::getWeight).sum())
                        return Mono.just(setApiResponse(headers, CODE_REQUEST_ERROR, "Total medication weight exceeds drone limit", null));
                    else {
                        List<MedicationDTO> meds = medications.stream()
                                .map(medication -> {
                                    var med = modelMapper.map(medication, MedicationDTO.class);
                                    med.setDrone(drone);
                                    return med;
                                }).toList();

                        return medicationRepository.saveAll(meds)
                                .collectList()
                                .doOnError(error->logError(headers, error.getMessage()))
                                .flatMap(savedMedication -> {
                                    drone.setState(Enums.DroneState.LOADED);
                                    return droneRepository.save(drone)
                                            .doOnError(error->logError(headers, error.getMessage()))
                                            .map(savedDrone -> setApiResponse(headers, CODE_CREATED, MESSAGE_SUCCESS, savedMedication.stream().map(med -> modelMapper.map(med, Medication.class)).collect(Collectors.toList())))
                                            .defaultIfEmpty(setApiResponse(headers, CODE_SERVER_ERROR, "Error saving drone", null));
                                })
                                .defaultIfEmpty(setApiResponse(headers, CODE_SERVER_ERROR, "Error loading medication", null));
                    }
                })
                .defaultIfEmpty(setApiResponse(headers, CODE_NOT_FOUND_ERROR, "Drone not found", null));
    }

    public Mono<ApiResponse> getLoad(Map<String, String> headers,String id) {
        return droneRepository.findById(id)
                .doOnError(error->logError(headers, error.getMessage()))
                .flatMap(drone -> {
                    if (drone.getState() != Enums.DroneState.LOADED)
                        return Mono.just(setApiResponse(headers, CODE_SUCCESS, "Drone is not loaded", new ArrayList<>()));
                    else {
                        return medicationRepository.findByDroneId(drone.getId())
                                .doOnError(error->logError(headers, error.getMessage()))
                                .collectList()
                                .flatMap(medications -> Mono.just(setApiResponse(headers, CODE_SUCCESS, MESSAGE_SUCCESS, medications)));
                    }
                })
                .switchIfEmpty(Mono.just(setApiResponse(headers, CODE_NOT_FOUND_ERROR, "Drone not found", null)));
    }
}
