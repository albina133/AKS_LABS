package com.volkova.kandalov.springlab2.jms;

public class ChangeEvent {

    private String action;
    private String entity;
    private Long entityId;
    private String payload;

    public ChangeEvent() {}

    public ChangeEvent(String action, String entity, Long entityId, String payload) {
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.payload = payload;
    }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }

    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }

    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }
}
