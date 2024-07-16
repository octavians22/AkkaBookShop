package com.example.userservice.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

		@ExceptionHandler(BookNotAvailableException.class)
		public ResponseEntity<String> handleBookNotFoundException(BookNotAvailableException ex) {
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}

}
