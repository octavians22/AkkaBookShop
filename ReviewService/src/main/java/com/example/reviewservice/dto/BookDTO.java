package com.example.reviewservice.dto;

import com.example.reviewservice.model.BookOperationType;

import java.util.List;

public class BookDTO {
    private long id;
    private String publisher;
    private String title;
    private String author;
    private Double price;
    private Integer stock;
    private String description;
    private BookOperationType bookOperationType;

    public BookDTO() {

    }

    public BookDTO(long id, String publisher, String title, String author, Double price, Integer stock,
                   String description, BookOperationType bookOperationType) {
        this.id = id;
        this.publisher = publisher;
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.bookOperationType = bookOperationType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookOperationType getBookOperationType() {
        return bookOperationType;
    }

    public void setBookOperationType(BookOperationType bookOperationType) {
        this.bookOperationType = bookOperationType;
    }
}
