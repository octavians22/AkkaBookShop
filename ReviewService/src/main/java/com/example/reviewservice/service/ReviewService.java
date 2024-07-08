package com.example.reviewservice.service;

import com.example.reviewservice.model.Review;
import com.example.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * @param review = represents a new Review object to add inside the database
     * @return the review that we added inside the database.
     */
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * @return a list of all the Reviews that exist in the database.
     */
    public List<Review> findAll() {
        Iterable<Review> all = reviewRepository.findAll();
        List<Review> reviewList = new ArrayList<>();
        all.forEach(reviewList::add);
        return reviewList;
    }

    /**
     * @param id = represents the id of the Review that we search
     * @return either the Review if it was found. If the review was not found, then return an empty review
     */
    public Review findById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        return reviewOptional.orElse(new Review());
    }

    /**
     * @param newerReview = the updated data we want to insert
     * @param id          = the id of the review we want to update
     * @return either the updated review if everything worked, or else return an empty Review if
     * no Review with that id was found
     */
    public Review updateReview(Review newerReview, Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            Review existedReview = reviewOptional.get();
            existedReview.setReviewText(newerReview.getReviewText());
            existedReview.setRating(newerReview.getRating());
            return reviewRepository.save(existedReview);
        } else {
            return new Review();
        }
    }

    /**
     * @param id = id of the review we want to delete
     */
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
