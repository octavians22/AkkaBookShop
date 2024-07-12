package com.example.reviewservice.service;

import com.example.reviewservice.dto.BookIdentifierDTO;
import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.exception.BookNotFoundException;
import com.example.reviewservice.exception.ReviewNotFoundException;
import com.example.reviewservice.kafka.producer.KafkaProducerService;
import com.example.reviewservice.mapper.ReviewMapper;
import com.example.reviewservice.model.Book;
import com.example.reviewservice.model.Review;
import com.example.reviewservice.repository.BookRepository;
import com.example.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final KafkaProducerService producerService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,
                         BookRepository bookRepository, KafkaProducerService producerService, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.producerService = producerService;
        this.reviewMapper = reviewMapper;
    }

    /**
     * @param review = represents a new Review object to add inside the database
     * @return the review that we added inside the database.
     */
    public Review addReview(Review review) {

        Book bookByTitle = bookRepository.findBookByTitle(review.getBookTitle());
        if (bookByTitle != null) {
            review.setBook(bookByTitle);
            Review saved = reviewRepository.save(review);
            sendDTOToKafka(saved, bookByTitle);
            return saved;
        } else {
            throw new BookNotFoundException("The book " + review.getBookTitle() + " is not available at the moment in our store");
        }
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
        return reviewOptional.orElseThrow(() -> new ReviewNotFoundException("The review with the id = " + id + " was not found"));
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
            if (existedReview.getBookTitle().equals(newerReview.getBookTitle())) {
                existedReview.setReviewText(newerReview.getReviewText());
                existedReview.setRating(newerReview.getRating());
            } else if (!reviewOptional.get().getBookTitle().equals(newerReview.getBookTitle())) {
                Book bookByTitle = bookRepository.findBookByTitle(newerReview.getBookTitle());
                if (bookByTitle != null) {
                    existedReview.setReviewText(newerReview.getReviewText());
                    existedReview.setRating(newerReview.getRating());
                    existedReview.setBookTitle(newerReview.getBookTitle());
                    existedReview.setBook(bookByTitle);
                } else {
                    throw new BookNotFoundException("The book " + newerReview.getBookTitle() + " is not available at the moment in our store");
                }
            }
            return reviewRepository.save(existedReview);
        } else {
            throw new ReviewNotFoundException("The review with the id = " + id + " was not found");
        }
    }

    /**
     * @param id = id of the review we want to delete
     */
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    /**
     * @param book = represents the Book object from which we extract the necessary fields to creat a BookIdentifier object
     * @return a new BookIdentifierDTO
     */
    public BookIdentifierDTO createBookIdentifier(Book book) {
        return new BookIdentifierDTO(book.getPublisher(), book.getTitle(), book.getAuthor());
    }

    /**
     * @param review = represents the Review entity from which we are going to create a ReviewDTO
     *               that is going to be sent to Kafka.
     * @param book   = represents the Book entity that we are going to use to create a BookIdentifierDTO that we are going then to
     *               set it in the ReviewDTO.
     */
    public void sendDTOToKafka(Review review, Book book) {
        ReviewDTO reviewDTO = reviewMapper.toDTO(review);
        BookIdentifierDTO bookIdentifier = createBookIdentifier(book);
        reviewDTO.setBookIdentifierDTO(bookIdentifier);
        producerService.sendMessage("reviews", reviewDTO);
    }
}
