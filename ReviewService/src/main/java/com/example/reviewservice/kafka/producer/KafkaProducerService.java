package com.example.reviewservice.kafka.producer;

import com.example.reviewservice.dto.BookDTO;
import com.example.reviewservice.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, ReviewDTO reviewDTO) {
        kafkaTemplate.send(topicName, reviewDTO);
    }

    public void sendMessage(String topicName, BookDTO bookDTO) {
        kafkaTemplate.send(topicName, bookDTO);
    }
}
