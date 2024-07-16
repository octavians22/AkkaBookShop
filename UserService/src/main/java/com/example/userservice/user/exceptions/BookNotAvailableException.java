package com.example.userservice.user.exceptions;

public class BookNotAvailableException extends RuntimeException{

		public BookNotAvailableException() {
				super();
		}

		public BookNotAvailableException(String message) {
				super(message);
		}

		public BookNotAvailableException(String message, Throwable cause) {
				super(message, cause);
		}

		public BookNotAvailableException(Throwable cause) {
				super(cause);
		}
}
