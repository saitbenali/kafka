package com.example.kafkademo.service;

import com.example.kafkademo.dto.MessageDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String, MessageDTO> kafkaTemplate;
    public static final String TOPIC = "demo-topic";

    public ProducerService(KafkaTemplate<String, MessageDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MessageDTO message) {
        kafkaTemplate.send(TOPIC, message);
    }
}