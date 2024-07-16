package com.example.userservice.user.kafka.consumer;

import com.example.userservice.user.dto.UserOrderDTO;
import com.example.userservice.user.kafka.producer.KafkaProducerService;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KafkaConsumerService {

		private final KafkaProducerService kafkaProducerService;

		@Getter
		private List<UserOrderDTO> booksInStock;

		public KafkaConsumerService(KafkaProducerService kafkaProducerService) {
				this.kafkaProducerService = kafkaProducerService;
		}

		@KafkaListener(topics = "book-available_books", groupId = "book-available-group", containerFactory = "kafkaListenerContainerFactoryAvailableBook")
		public void listenReviews(ConsumerRecord<String, List<UserOrderDTO>> record)  {

				List<UserOrderDTO> booksInStock = record.value();

				this.booksInStock = booksInStock;
				kafkaProducerService.sendMessage("ordered_books", booksInStock);

		}


}
