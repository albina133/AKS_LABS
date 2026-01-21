package com.volkova.kandalov.springlab2.jms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkova.kandalov.springlab2.entity.AuditLog;
import com.volkova.kandalov.springlab2.repository.AuditLogRepository;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AuditLogListener {

    private final ObjectMapper objectMapper;
    private final AuditLogRepository auditLogRepository;

    public AuditLogListener(ObjectMapper objectMapper,
                            AuditLogRepository auditLogRepository) {
        this.objectMapper = objectMapper;
        this.auditLogRepository = auditLogRepository;
    }

    @JmsListener(destination = "${app.jms.topic}", containerFactory = "topicListenerFactory")
    public void onMessage(String messageJson) throws Exception {
        System.out.println("[AUDIT] received: " + messageJson);

        JsonNode root = objectMapper.readTree(messageJson);

        // минимальная проверка структуры
        if (!root.hasNonNull("action") || !root.hasNonNull("entity")) {
            return; // это не наше сообщение
        }

        String action = root.get("action").asText();
        String entity = root.get("entity").asText();
        Long entityId = root.hasNonNull("entityId") ? root.get("entityId").asLong() : null;

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
