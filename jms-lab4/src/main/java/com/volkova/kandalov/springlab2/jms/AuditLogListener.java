package com.volkova.kandalov.springlab2.jms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkova.kandalov.springlab2.entity.AuditLog;
import com.volkova.kandalov.springlab2.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLogListener {

    private final ObjectMapper objectMapper;
    private final AuditLogRepository auditLogRepository;
    private final String auditQueue;

    public AuditLogListener(ObjectMapper objectMapper,
                            AuditLogRepository auditLogRepository,
                            @Value("${app.jms.audit-queue}") String auditQueue) {
        this.objectMapper = objectMapper;
        this.auditLogRepository = auditLogRepository;
        this.auditQueue = auditQueue;
    }

    @JmsListener(destination = "${app.jms.audit-queue}", containerFactory = "queueListenerFactory")
    public void onMessage(String messageJson) throws Exception {

        JsonNode root = objectMapper.readTree(messageJson);

        // минимальная проверка структуры
        if (!root.hasNonNull("action") || !root.hasNonNull("entity")) {
            return; // это не наше сообщение
        }

        String action = root.get("action").asText();
        String entity = root.get("entity").asText();
        Long entityId = root.hasNonNull("entityId") ? root.get("entityId").asLong() : null;

        // payload
        String payload = root.has("payload") && !root.get("payload").isNull()
                ? root.get("payload").asText()
                : null;

        AuditLog log = AuditLog.builder()
                .action(action)
                .entity(entity)
                .entityId(entityId)
                .payload(payload)
                .build();

        auditLogRepository.save(log);
    }
}