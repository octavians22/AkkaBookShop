package com.example.userservice.user.kafka.producer;

import com.example.userservice.user.dto.UserOrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KafkaProducerService {

		private final KafkaTemplate<String, Object> kafkaTemplate;

		public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
				this.kafkaTemplate = kafkaTemplate;
		}

		public void sendMessage(String topic, Object msg) {
				kafkaTemplate.send(topic, msg);
		}
		public void sendList(String topic, List<UserOrderDTO> msg) {
				kafkaTemplate.send(topic, msg);
		}
}
