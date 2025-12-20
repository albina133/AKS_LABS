package com.volkova.kandalov.springlab2.service;

import com.volkova.kandalov.springlab2.entity.AuditLog;
import com.volkova.kandalov.springlab2.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {
    private final AuditLogRepository repo;

    public AuditLogService(AuditLogRepository repo) {
        this.repo = repo;
    }

    public AuditLog save(AuditLog log) {
        return repo.save(log);
    }
}
