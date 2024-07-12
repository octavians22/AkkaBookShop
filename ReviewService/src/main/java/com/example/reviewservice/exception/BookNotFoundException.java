package com.example.reviewservice.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("The book with the id = " + id + " was not found in the database");
    }

    public BookNotFoundException(String msg) {
        super(msg);
    }
}
