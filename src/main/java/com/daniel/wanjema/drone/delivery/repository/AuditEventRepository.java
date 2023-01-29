package com.daniel.wanjema.drone.delivery.repository;

import com.daniel.wanjema.drone.delivery.audit.AuditEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditEventRepository extends ReactiveMongoRepository<AuditEvent, String> {
}
