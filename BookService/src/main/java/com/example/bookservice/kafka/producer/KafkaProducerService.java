package com.example.bookservice.kafka.producer;

import com.example.bookservice.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService {

		private final KafkaTemplate<String, Object> kafkaTemplate;

		public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
				this.kafkaTemplate = kafkaTemplate;
		}

		public void sendMessage(String topic, Object msg) {
				kafkaTemplate.send(topic, msg);
		}
}
