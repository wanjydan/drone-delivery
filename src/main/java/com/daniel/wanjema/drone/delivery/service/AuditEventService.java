package com.daniel.wanjema.drone.delivery.service;

import com.daniel.wanjema.drone.delivery.audit.AuditEvent;
import com.daniel.wanjema.drone.delivery.repository.AuditEventRepository;
import com.daniel.wanjema.drone.delivery.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AuditEventService {
    private final DroneRepository droneRepository;
    private final AuditEventRepository auditEventRepository;

    @Autowired
    public AuditEventService(DroneRepository droneRepository, AuditEventRepository auditEventRepository) {
        this.droneRepository = droneRepository;
        this.auditEventRepository = auditEventRepository;
    }

    @Scheduled(fixedRate = 60000) //runs every 60 seconds
    public void checkBatteryLevels() {
        droneRepository.findAll()
                .flatMap(drone -> {
                    int batteryLevel = drone.getBatteryCapacity();
                    var event = new AuditEvent(drone, "BATTERY_CHECK", String.valueOf(batteryLevel));
                    return auditEventRepository.save(event);
                })
                .subscribe();
    }
}
