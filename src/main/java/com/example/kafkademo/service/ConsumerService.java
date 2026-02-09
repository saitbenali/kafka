package com.example.kafkademo.service;

import com.example.kafkademo.dto.MessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "demo-topic", groupId = "demo-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(MessageDTO message) {
        System.out.println("Consumed message: " + message);
    }
}