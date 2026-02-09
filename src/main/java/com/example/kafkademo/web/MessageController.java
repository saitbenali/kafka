package com.example.kafkademo.web;

import com.example.kafkademo.dto.MessageDTO;
import com.example.kafkademo.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final ProducerService producer;

    public MessageController(ProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody MessageDTO message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Sent: " + message);
    }
}