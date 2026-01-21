package com.volkova.kandalov.springlab2.jms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volkova.kandalov.springlab2.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NotifyListener {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public NotifyListener(ObjectMapper objectMapper, EmailService emailService) {
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${app.jms.topic}", containerFactory = "topicListenerFactory")
    public void onMessage(String messageJson) throws Exception {

        System.out.println("[NOTIFY] received: " + messageJson);

        JsonNode root = objectMapper.readTree(messageJson);

        if (!root.hasNonNull("action") || !root.hasNonNull("entity")) return;

        String action = root.get("action").asText();
        String entity = root.get("entity").asText();

        if (!"DELETE".equalsIgnoreCase(action) || !"Student".equalsIgnoreCase(entity)) {
            return;
        }

        Long entityId = root.hasNonNull("entityId") ? root.get("entityId").asLong() : null;
        String payload = root.has("payload") && !root.get("payload").isNull()
                ? root.get("payload").asText()
                : "";

        String subject = "УВЕДОМЛЕНИЕ: удалён студент (id=" + entityId + ")";
        String text = "Событие изменения в системе:\n"
                + "action=" + action + "\n"
                + "entity=" + entity + "\n"
                + "entityId=" + entityId + "\n"
                + "payload=" + payload + "\n";

        System.out.println("NOTIFY CONDITION PASSED: sending email...");

        emailService.send(subject, text);
    }
}
