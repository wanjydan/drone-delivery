package com.daniel.wanjema.drone.delivery.audit;

import com.daniel.wanjema.drone.delivery.dto.BaseEntity;
import com.daniel.wanjema.drone.delivery.dto.DroneDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("audit_event")
public class AuditEvent extends BaseEntity {
    private DroneDTO drone;
    private String eventType;
    private String eventData;
}
