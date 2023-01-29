package com.daniel.wanjema.drone.delivery.repository;

import com.daniel.wanjema.drone.delivery.dto.DroneDTO;
import com.daniel.wanjema.drone.delivery.model.Drone;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DroneRepository extends ReactiveMongoRepository<DroneDTO, String> {
    Flux<Drone> findBySerialNumber(String serialNumber);
}
