package com.volkova.kandalov.springlab2.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChangeEventProducer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    private final String topic;

    public ChangeEventProducer(JmsTemplate jmsTemplate,
                               ObjectMapper objectMapper,
                               @Value("${app.jms.topic}") String topic) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    public void send(ChangeEvent event) {
        jmsTemplate.send(topic, session -> {
            String json;
            try {
                json = objectMapper.writeValueAsString(event);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize ChangeEvent to JSON", e);
            }

            var msg = session.createTextMessage(json);
            msg.setStringProperty("eventType", "ENTITY_CHANGE");
            msg.setStringProperty("action", event.getAction());
            msg.setStringProperty("entity", event.getEntity());
            if (event.getEntityId() != null) msg.setLongProperty("entityId", event.getEntityId());
            return msg;
        });
    }
}
