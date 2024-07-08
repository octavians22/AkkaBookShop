package com.example.bookservice.exceptions;

public class ExactBookAlreadyExistsException extends RuntimeException{

		public ExactBookAlreadyExistsException() {
				super();
		}

		public ExactBookAlreadyExistsException(String message) {
				super(message);
		}

		public ExactBookAlreadyExistsException(String message, Throwable cause) {
				super(message, cause);
		}

		public ExactBookAlreadyExistsException(Throwable cause) {
				super(cause);
		}
}
