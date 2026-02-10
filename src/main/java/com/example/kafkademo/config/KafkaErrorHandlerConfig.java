package com.example.kafkademo.config;

import com.example.kafkademo.dto.MessageDTO;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaErrorHandlerConfig {

    /**
     * Dead letter recoverer: publie le message en échec dans <topic>.DLT en conservant la partition.
     */
    @Bean
    @SuppressWarnings({"rawtypes", "unchecked"})
    public DeadLetterPublishingRecoverer deadLetterPublishingRecoverer(
            KafkaTemplate<String, MessageDTO> kafkaTemplate) {

        KafkaOperations<Object, Object> ops = (KafkaOperations) kafkaTemplate;

        return new DeadLetterPublishingRecoverer(
                ops,
                (record, ex) -> new TopicPartition(record.topic() + ".DLT", record.partition())
        );
    }

    /**
     * DefaultErrorHandler avec retry/backoff.
     * - FixedBackOff(1000L, 3L) => 3 tentatives de retry après la première lecture (donc 3 retries).
     * - Ajouter les exceptions non retryables (ex: IllegalArgumentException) en les envoyant directement en DLT.
     */
    @Bean
    public DefaultErrorHandler errorHandler(DeadLetterPublishingRecoverer recoverer) {
        FixedBackOff backOff = new FixedBackOff(1000L, 3L); // interval=1000ms, maxRetries=3
        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer, backOff);

        // Exemples : ne pas retry sur IllegalArgumentException (envoie direct en DLT)
        handler.addNotRetryableExceptions(IllegalArgumentException.class);

        // Optionnel : log des erreurs gérées / personnalisation...
        return handler;
    }
}