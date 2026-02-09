package com.example.kafkademo.service;

import com.example.kafkademo.dto.MessageDTO;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService {

    private final KafkaTemplate<String, MessageDTO> kafkaTemplate;
    public static final String TOPIC = "demo-topic";

    public ProducerService(KafkaTemplate<String, MessageDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MessageDTO message) {
        CompletableFuture<SendResult<String, MessageDTO>> future = kafkaTemplate.send(TOPIC, message.getId(), message);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.printf("Failed to send message key=%s: %s%n", message.getId(), ex.getMessage());
            } else if (result != null) {
                RecordMetadata meta = result.getRecordMetadata();
                System.out.printf("Produced message key=%s to topic=%s partition=%d offset=%d%n",
                        message.getId(), meta.topic(), meta.partition(), meta.offset());
            }
        });
    }
}