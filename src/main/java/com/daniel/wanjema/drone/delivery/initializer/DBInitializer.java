package com.daniel.wanjema.drone.delivery.initializer;

import com.daniel.wanjema.drone.delivery.dto.DroneDTO;
import com.daniel.wanjema.drone.delivery.model.Drone;
import com.daniel.wanjema.drone.delivery.model.Enums;
import com.daniel.wanjema.drone.delivery.repository.DroneRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DBInitializer implements CommandLineRunner {

//    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final ReactiveMongoOperations mongoOperations;
    private final DroneRepository droneRepository;
    private final ModelMapper modelMapper;

    public DBInitializer(ReactiveMongoOperations mongoOperations, DroneRepository droneRepository, ModelMapper modelMapper) {
        this.mongoOperations = mongoOperations;
        this.droneRepository = droneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) {

//        logger.info("Creating cities");

       mongoOperations.executeCommand("{dropDatabase:1}").block();
//        log.info("Dropped database");

        var drones = IntStream.range(0, 10)
                .mapToObj(i -> {
                    var rand = new Random();
                    var models = Enums.DroneModel.values();

                    return new Drone(
                            null,
                            RandomStringUtils.randomAlphanumeric(10).toUpperCase(),
                            models[rand.nextInt(models.length)],
                            rand.nextInt(500),
                            rand.nextInt(100),
                            Enums.DroneState.IDLE);
                }).toList();

        droneRepository.saveAll(drones.stream()
                .map(drone -> modelMapper.map(drone, DroneDTO.class))
                .collect(Collectors.toList())).collectList().block();
//        log.info("Inserted sample drones data");
    }
}