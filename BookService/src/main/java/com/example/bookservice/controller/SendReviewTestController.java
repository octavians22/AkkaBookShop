package com.example.bookservice.controller;


import com.example.bookservice.dto.ReviewDTO;
import com.example.bookservice.kafka.producer.KafkaProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SendReviewTestController {

		private final KafkaProducerService kafkaProducerService;

		public SendReviewTestController(KafkaProducerService kafkaProducerService) {
				this.kafkaProducerService = kafkaProducerService;
		}

		@PostMapping
		public ResponseEntity<String> sendReviewToTopic(@RequestBody ReviewDTO reviewDTO) {

				kafkaProducerService.sendMessage("book-reviews", reviewDTO);
				return new ResponseEntity<>("Review sent to topic", HttpStatus.CREATED);
		}
}
