package com.example.bookservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

		@ExceptionHandler({ExactBookAlreadyExistsException.class, BookOutOfStockException.class, BookNotFoundException.class})
		public ResponseEntity<String> handleBookNotFoundException(ExactBookAlreadyExistsException ex) {
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.ALREADY_REPORTED);
		}
}
