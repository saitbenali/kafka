package com.example.kafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    public static final String TOPIC = "demo-topic";

    @Value("${kafka.topic.partitions:3}")
    private int partitions;

    @Value("${kafka.topic.replication-factor:1}")
    private short replicationFactor;

    @Bean
    public NewTopic demoTopic() {
        return new NewTopic(TOPIC, partitions, replicationFactor);
    }

    @Bean
    public NewTopic demoTopicDLT() {
        // crée le topic demo-topic.DLT avec même nombre de partitions
        return new NewTopic(TOPIC + ".DLT", partitions, replicationFactor);
    }
}