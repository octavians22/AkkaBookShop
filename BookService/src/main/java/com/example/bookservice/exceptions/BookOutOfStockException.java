package com.example.bookservice.exceptions;

public class BookOutOfStockException extends RuntimeException{

		public BookOutOfStockException() {
				super();
		}

		public BookOutOfStockException(String message) {
				super(message);
		}

		public BookOutOfStockException(String message, Throwable cause) {
				super(message, cause);
		}

		public BookOutOfStockException(Throwable cause) {
				super(cause);
		}
}
