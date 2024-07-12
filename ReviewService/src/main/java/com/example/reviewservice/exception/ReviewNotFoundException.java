package com.example.reviewservice.exception;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String msg) {
        super(msg);
    }
}
