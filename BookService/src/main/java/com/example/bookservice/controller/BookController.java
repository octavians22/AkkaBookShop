package com.example.bookservice.controller;


import com.example.bookservice.model.Book;
import com.example.bookservice.model.Review;
import com.example.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

		private final BookService bookService;

		@Autowired
		public BookController(BookService bookService) {
				this.bookService = bookService;
		}

		@PostMapping("/add")
		public ResponseEntity<Book> addBook(@RequestBody Book book) {
				Book addedBook = bookService.addBook(book);
				return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
		}

		@PutMapping("/update/{title}")
		public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable String title) {
				Book bookToBeUpdated = bookService.updateBookByTitle(title, book);
				return new ResponseEntity<>(bookToBeUpdated, HttpStatus.OK);
		}

		@GetMapping("/{id}")
		public ResponseEntity<Book> findBookById(@PathVariable long id) {
				Book book = bookService.findBookById(id);
				return new ResponseEntity<>(book, HttpStatus.OK);
		}

		@GetMapping("/{title}")
		public ResponseEntity<Book> findBookByTitle(@PathVariable String title) {
				Book book = bookService.findBookByTitle(title);
				return new ResponseEntity<>(book, HttpStatus.OK);
		}

		@GetMapping("/title/{title}")
		public ResponseEntity<List<Review>> findBookReviewsByTitle(@PathVariable String title) {
				List<Review> reviewList = bookService.getBookReviews(title);
				return new ResponseEntity<>(reviewList, HttpStatus.OK);
		}

		@DeleteMapping("/{id}")
		public ResponseEntity<String> deleteBookById(@PathVariable long id) {
				bookService.deleteBookById(id);
				return new ResponseEntity<>("Book deleted!", HttpStatus.OK);
		}
}
