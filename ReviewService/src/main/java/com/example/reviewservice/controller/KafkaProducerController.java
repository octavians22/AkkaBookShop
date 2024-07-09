package com.example.reviewservice.controller;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.kafka.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/producer")
public class KafkaProducerController {

    private final KafkaProducerService producerService;

    @Autowired
    public KafkaProducerController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public void sendMessage(@RequestBody ReviewDTO reviewDTO) {
        producerService.sendMessage(reviewDTO);
        System.out.println("Received the following object:" +
                reviewDTO.getReviewText() + ", " + reviewDTO.getId() + ", " + reviewDTO.getRating());
    }
}
