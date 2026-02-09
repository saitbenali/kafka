package com.example.kafkademo.service;

import com.example.kafkademo.dto.MessageDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "demo-topic", groupId = "demo-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(MessageDTO message,
                       @Header("kafka_receivedPartitionId") int partition,
                       @Header("kafka_receivedTopic") String topic) {
        String thread = Thread.currentThread().getName();
        System.out.printf("Consumed message: %s | topic=%s | partition=%d | thread=%s%n", message, topic, partition, thread);
    }
}