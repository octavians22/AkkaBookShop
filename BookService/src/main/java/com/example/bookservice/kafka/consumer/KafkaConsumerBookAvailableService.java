package com.example.bookservice.kafka.consumer;

import com.example.bookservice.dto.BookAvailableDTO;
import com.example.bookservice.dto.UserOrderDTO;
import com.example.bookservice.exceptions.BookOutOfStockException;
import com.example.bookservice.kafka.producer.KafkaProducerService;
import com.example.bookservice.mapper.BookAvailableMapper;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KafkaConsumerBookAvailableService {

		private final BookRepository bookRepository;
		private final KafkaProducerService kafkaProducerService;
		private final BookAvailableMapper bookAvailableMapper;

		public KafkaConsumerBookAvailableService(BookRepository bookRepository, KafkaProducerService kafkaProducerService, BookAvailableMapper bookAvailableMapper) {
				this.bookRepository = bookRepository;
				this.kafkaProducerService = kafkaProducerService;
				this.bookAvailableMapper = bookAvailableMapper;
		}

		@KafkaListener(topics = "desired_books", groupId = "book-desired-group", containerFactory = "kafkaListenerContainerFactoryAvailableBook")
		public void listenForBookOrder(ConsumerRecord<String, List<UserOrderDTO>> data) {
				List<UserOrderDTO> availableBooks = new ArrayList<>();
				List<UserOrderDTO> userOrderDTOList = data.value();

						for(UserOrderDTO userOrderDTO: userOrderDTOList) {
								Optional<Book> book = bookRepository.findByTitleAndAuthorAndPublisher(userOrderDTO.getBook().getTitle(),
												userOrderDTO.getBook().getAuthor(),
												userOrderDTO.getBook().getPublisher());

								if(book.isPresent()) {
										try{
												if(userOrderDTO.getQuantity() <= book.get().getStock()) {
														BookAvailableDTO bookAvailableDTO = bookAvailableMapper.toDto(book.get());
														availableBooks.add(new UserOrderDTO(bookAvailableDTO, userOrderDTO.getQuantity()));
														book.get().setStock(book.get().getStock() - userOrderDTO.getQuantity());
														bookRepository.save(book.get());
												}
												else {
														throw new BookOutOfStockException("Your desired quantity was " + userOrderDTO.getQuantity() + ", but the stock for this book is " +
																		book.get().getStock());
												}
										} catch (Exception e) {
												e.printStackTrace();
										}
								}
						}

						kafkaProducerService.sendMessage("book-available_books", availableBooks);



		}
}
