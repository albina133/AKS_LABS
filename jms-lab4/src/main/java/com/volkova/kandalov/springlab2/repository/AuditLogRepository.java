package com.volkova.kandalov.springlab2.repository;

import com.volkova.kandalov.springlab2.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
