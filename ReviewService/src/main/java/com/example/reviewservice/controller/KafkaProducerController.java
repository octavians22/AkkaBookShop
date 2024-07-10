package com.example.reviewservice.controller;

import com.example.reviewservice.dto.BookDTO;
import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.kafka.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/producer")
public class KafkaProducerController {

    private final KafkaProducerService producerService;

    @Autowired
    public KafkaProducerController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/review")
    public void sendReviewMessage(@RequestBody ReviewDTO reviewDTO,
                                  @RequestParam(name = "topic") String topic) {
        producerService.sendMessage(topic, reviewDTO);
    }

    @PostMapping("/book")
    public ResponseEntity<String> sendBookMessage(@RequestBody BookDTO bookDTO,
                                                  @RequestParam(name = "topic") String topic) {
        producerService.sendMessage(topic, bookDTO);
        return new ResponseEntity<>("The message was sent succesfull", HttpStatus.CREATED);
    }
}
