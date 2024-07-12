package com.example.reviewservice.controller;

import com.example.reviewservice.kafka.producer.KafkaProducerService;
import com.example.reviewservice.model.Review;
import com.example.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final KafkaProducerService producerService;

    @Autowired
    public ReviewController(ReviewService reviewService, KafkaProducerService producerService) {
        this.reviewService = reviewService;
        this.producerService = producerService;
    }

    @PostMapping
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @GetMapping
    public List<Review> getAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable(name = "id") Long id) {
        return reviewService.findById(id);
    }

    @PutMapping("/{id}")
    public Review updateReview(@RequestBody Review review,
                               @PathVariable(name = "id") Long id) {
        return reviewService.updateReview(review, id);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable(name = "id") Long id) {
        reviewService.deleteReview(id);
    }
}
