package com.example.orderservice.kafka.consumer;

import com.example.orderservice.dto.UserOrderDTO;
import com.example.orderservice.kafka.producer.KafkaProducerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService {


		@KafkaListener(topics = "ordered_books", groupId = "book-ordered-group", containerFactory = "kafkaListenerContainerFactoryOrderedBook")
		public void listenReviews(ConsumerRecord<String, List<UserOrderDTO>> record)  {

				List<UserOrderDTO> booksToBeOrdered = record.value();
				//procesare plata -> trimitere mesaj approve/reject la serviciul de books pentru confirmare stock/ rollback stock
				//trimitere mesaj service notifications


		}


}
