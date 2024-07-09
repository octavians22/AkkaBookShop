package com.example.reviewservice.dto;

public class ReviewDTO {

    private Long id;
    private String reviewText;
    private int rating;
    private BookIdentifierDTO bookIdentifierDTO;

    public ReviewDTO() {

    }

    public ReviewDTO(Long id, String reviewText, int rating, BookIdentifierDTO bookIdentifierDTO) {
        this.id = id;
        this.reviewText = reviewText;
        this.rating = rating;
        this.bookIdentifierDTO = bookIdentifierDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public BookIdentifierDTO getBookIdentifierDTO() {
        return bookIdentifierDTO;
    }

    public void setBookIdentifierDTO(BookIdentifierDTO bookIdentifierDTO) {
        this.bookIdentifierDTO = bookIdentifierDTO;
    }
}
