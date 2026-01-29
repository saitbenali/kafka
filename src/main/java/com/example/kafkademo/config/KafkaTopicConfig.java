package com.example.kafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    public static final String TOPIC = "demo-topic";

    @Bean
    public NewTopic demoTopic() {
        return new NewTopic(TOPIC, 1, (short) 1);
    }
}