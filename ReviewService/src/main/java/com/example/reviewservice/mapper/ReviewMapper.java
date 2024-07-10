package com.example.reviewservice.mapper;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDTO toDTO(Review review) {
        return new ReviewDTO(review.getId(), review.getReviewText(), review.getRating());
    }

    public Review toEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setReviewText(reviewDTO.getReviewText());
        review.setBookTitle(reviewDTO.getBookIdentifierDTO().getTitle());
        review.setRating(reviewDTO.getRating());
        return review;
    }
}
