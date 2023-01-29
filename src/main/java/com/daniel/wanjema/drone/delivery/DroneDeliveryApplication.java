package com.daniel.wanjema.drone.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.Scheduled;

@EnableMongoAuditing
@EnableReactiveMongoRepositories
@SpringBootApplication
public class DroneDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneDeliveryApplication.class, args);
    }

}
