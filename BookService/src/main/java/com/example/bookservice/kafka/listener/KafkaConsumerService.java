package com.example.bookservice.kafka.listener;


import com.example.bookservice.dto.ReviewDTO;
import com.example.bookservice.exceptions.BookNotFoundException;
import com.example.bookservice.kafka.producer.KafkaProducerService;
import com.example.bookservice.mapper.ReviewMapper;
import com.example.bookservice.model.Book;
import com.example.bookservice.model.Review;
import com.example.bookservice.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import java.util.List;

import static org.apache.kafka.common.requests.DeleteAclsResponse.log;

@Service
public class KafkaConsumerService {

		private final ObjectMapper objectMapper = new ObjectMapper();
		private final BookRepository bookRepository;
		private final ReviewMapper reviewMapper;
		private final KafkaProducerService kafkaProducerService;
		private List<Review> reviewList;

		public KafkaConsumerService(BookRepository bookRepository, ReviewMapper reviewMapper, KafkaProducerService kafkaProducerService) {
				this.bookRepository = bookRepository;
				this.reviewMapper = reviewMapper;
				this.kafkaProducerService = kafkaProducerService;
		}

		@RetryableTopic(attempts = "4",
						backoff = @Backoff(delay = 1500, multiplier = 2),
						kafkaTemplate = "kafkaTemplate",
						dltStrategy = DltStrategy.ALWAYS_RETRY_ON_ERROR)
		@KafkaListener(topics = "book-reviews", groupId = "book-group")
		public void listenReviews(ConsumerRecord<String, String> record) {

				try {
//						ReviewDTO reviewDTO = objectMapper.readValue(record.value(), ReviewDTO.class);
//
//
//						Book book = bookRepository.findByTitleAndAuthorAndPublisher(reviewDTO.getBookIdentifierDTO().getTitle(),
//										reviewDTO.getBookIdentifierDTO().getAuthor(),
//										reviewDTO.getBookIdentifierDTO().getPublisher())
//										.orElseThrow(() -> new BookNotFoundException("This book no longer exists to add a review"));
//
//						reviewList = book.getReviews();
//
//						if(!reviewAlreadySent(reviewDTO)) {
//								return;
//						}
//
//						Review review = reviewMapper.toEntity(reviewDTO);
//
//								book.getReviews().add(review);
//								bookRepository.save(book);
						throw new Exception("exception");

				}  catch (Exception e) {
						log.info(e.getMessage());
						handleDltPayment(record, "book-reviews-dlt");
				}
		}

		@DltHandler
		public void handleDltPayment(ConsumerRecord<String, String> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

				try {
						ReviewDTO	reviewDTO = objectMapper.readValue(record.value(), ReviewDTO.class);
						log.info("Event on dlt topic={}, payload={}", topic, reviewDTO);
						kafkaProducerService.sendMessage("book-reviews", reviewDTO);
				} catch (Exception e) {
						log.error("Error processing DLT message: ", e);
				}
		}

		private boolean reviewAlreadySent(ReviewDTO reviewDTO) {
				List<Review> collect = reviewList.stream()
								.filter(review -> {
										if (review.getReviewText() == null) {
												review.setReviewText("");
										}
										return review.getReviewText().equals(reviewDTO.getReviewText()) &&
														review.getRating() == reviewDTO.getRating();
								}) //same user to be added for idempotency check
								.toList();
				return collect.isEmpty();
		}
}
