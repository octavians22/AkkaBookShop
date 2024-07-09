package com.example.reviewservice.kafka.consumer;

import com.example.reviewservice.dto.ReviewDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "reviews", groupId = "review-I")
    public void consume(ConsumerRecord<String, ReviewDTO> message) {
        message.value();
    }
}
