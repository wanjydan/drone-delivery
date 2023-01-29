package com.daniel.wanjema.drone.delivery.repository;

import com.daniel.wanjema.drone.delivery.dto.MedicationDTO;
import com.daniel.wanjema.drone.delivery.model.Drone;
import com.daniel.wanjema.drone.delivery.model.Medication;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MedicationRepository extends ReactiveMongoRepository<MedicationDTO, String> {
    Flux<Medication> findByDroneId(String id);
}
