package com.example.bookservice.mapper;

import com.example.bookservice.dto.ReviewDTO;
import com.example.bookservice.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

		public ReviewDTO toDto(Review review) {

				ReviewDTO reviewDTO = new ReviewDTO();

				reviewDTO.setReviewText(review.getReviewText());
				reviewDTO.setRating(review.getRating());

				return reviewDTO;
		}

		public Review toEntity(ReviewDTO reviewDTO) {
				Review review = new Review();

				review.setReviewText(reviewDTO.getReviewText());
				review.setRating(reviewDTO.getRating());

				return review;
		}
}
