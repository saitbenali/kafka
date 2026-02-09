package com.example.kafkademo.dto;

public class MessageDTO {
    private String id;
    private String payload;

    public MessageDTO() {}

    public MessageDTO(String id, String payload) {
        this.id = id;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "MessageDTO{id='" + id + "', payload='" + payload + "'}";
    }
}