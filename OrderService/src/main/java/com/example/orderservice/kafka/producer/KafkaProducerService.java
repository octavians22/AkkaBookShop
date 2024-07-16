package com.example.orderservice.kafka.producer;

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
