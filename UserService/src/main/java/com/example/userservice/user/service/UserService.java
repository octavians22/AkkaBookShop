package com.example.userservice.user.service;



import com.example.userservice.user.dto.UserOrderDTO;
import com.example.userservice.user.exceptions.BookNotAvailableException;
import com.example.userservice.user.kafka.consumer.KafkaConsumerService;
import com.example.userservice.user.kafka.producer.KafkaProducerService;
import com.example.userservice.user.mapper.UserOrderMapper;
import com.example.userservice.user.model.UserOrder;
import com.example.userservice.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

		private final KafkaProducerService kafkaProducerService;
		private final KafkaConsumerService kafkaConsumerService;
		private final UserRepository userRepository;
		private final UserOrderMapper userOrderMapper;


		public UserService(KafkaProducerService kafkaProducerService, KafkaConsumerService kafkaConsumerService, UserRepository userRepository, UserOrderMapper userOrderMapper) {
				this.kafkaProducerService = kafkaProducerService;
				this.kafkaConsumerService = kafkaConsumerService;
				this.userRepository = userRepository;
				this.userOrderMapper = userOrderMapper;
		}


		public List<UserOrderDTO> orderBook(List<UserOrderDTO> list) {

//				List<UserOrder> userOrderList = userOrderMapper.toEntityList(list);
				sendBookToCheckIfAvailable(list);

				return kafkaConsumerService.getBooksInStock();

		}

		private void sendBookToCheckIfAvailable(List<UserOrderDTO> list) {

//				List<UserOrderDTO> userOrderDTOList = userOrderMapper.toDtoList(list);
				kafkaProducerService.sendMessage("desired_books", list);
		}
}
