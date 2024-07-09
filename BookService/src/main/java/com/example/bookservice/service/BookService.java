package com.example.bookservice.service;

import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.exceptions.BookNotFoundException;
import com.example.bookservice.exceptions.ExactBookAlreadyExistsException;
import com.example.bookservice.kafka.producer.KafkaProducerService;
import com.example.bookservice.mapper.BookMapper;
import com.example.bookservice.model.Book;
import com.example.bookservice.model.BookOperationType;
import com.example.bookservice.model.Review;
import com.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


		private final BookRepository bookRepository;
		private final KafkaProducerService kafkaProducerService;
		private final BookMapper bookMapper;

		@Autowired
		public BookService(BookRepository bookRepository, KafkaProducerService kafkaProducerService, BookMapper bookMapper) {
				this.bookRepository = bookRepository;
				this.kafkaProducerService = kafkaProducerService;
				this.bookMapper = bookMapper;
		}

		public Book addBook(Book book) {
				if(bookRepository.findByTitleAndAuthorAndPublisher(book.getTitle(), book.getAuthor(),book.getPublisher()).isEmpty()) {
						BookDTO bookDTO = bookMapper.toDto(book);
						bookDTO.setBookOperationType(BookOperationType.ADD);
						kafkaProducerService.sendMessage("review", bookDTO);
						return bookRepository.save(book);
				}
				else {
						throw new ExactBookAlreadyExistsException("Book with title: " + book.getTitle() +
										", author: " + book.getAuthor() +
										", publisher: " + book.getPublisher() + " already exists");
				}
		}

		public Book updateBookByTitle(String title, Book book) {
				Book existingBook = bookRepository.findByTitle(title)
								.orElseThrow(() -> new BookNotFoundException("No book wih title " + title));

				existingBook.setAuthor(book.getAuthor());
				existingBook.setDescription(book.getDescription());
				existingBook.setTitle(book.getTitle());
				existingBook.setPrice(book.getPrice());
				existingBook.setPublisher(book.getPublisher());
				existingBook.setReviews(book.getReviews());
				existingBook.setStock(book.getStock());
				existingBook.setReviews(book.getReviews());

				bookRepository.save(existingBook);

				BookDTO bookDTO = bookMapper.toDto(book);
				bookDTO.setBookOperationType(BookOperationType.UPDATE);
				kafkaProducerService.sendMessage("review", bookDTO);

				return existingBook;
		}

		public Book findBookById(long id) {
				return bookRepository.findById(id)
								.orElseThrow(() -> new BookNotFoundException("No book with id " + id));
		}

		public Book findBookByTitle(String title) {
				return bookRepository.findByTitle(title)
								.orElseThrow(() -> new BookNotFoundException("No book with title " + title));
		}

		public List<Review> getBookReviews(String title) {
				Book book = bookRepository.findByTitle(title)
								.orElseThrow(() -> new BookNotFoundException("No book wih title " + title));

				return book.getReviews();

		}

		public void deleteBookById(long id) {
				Book book = bookRepository.findById(id)
								.orElseThrow(() -> new BookNotFoundException("Book with id " + id + " can't be deleted because it doesn't exist"));
				bookRepository.delete(book);

				BookDTO bookDTO = bookMapper.toDto(book);
				bookDTO.setBookOperationType(BookOperationType.DELETE);
				kafkaProducerService.sendMessage("review", bookDTO);
		}
}
