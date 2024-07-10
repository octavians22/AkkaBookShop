package com.example.reviewservice.service;

import com.example.reviewservice.exception.BookNotFoundException;
import com.example.reviewservice.model.Book;
import com.example.reviewservice.repository.BookRepository;
import com.example.reviewservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        Iterable<Book> all = bookRepository.findAll();
        List<Book> reviewList = new ArrayList<>();
        all.forEach(reviewList::add);
        return reviewList;
    }

    public Book findById(Long id) {
        Optional<Book> reviewOptional = bookRepository.findById(id);
        return reviewOptional.orElse(new Book());
    }

    public Book updateBook(Book updatedBook, Long id) {

        Optional<Book> byId = bookRepository.findById(id);
        if (byId.isPresent()) {
            Book existedBook = byId.get();
            existedBook.setAuthor(updatedBook.getAuthor());
            existedBook.setPrice(updatedBook.getPrice());
            existedBook.setPublisher(updatedBook.getPublisher());
            existedBook.setStock(updatedBook.getStock());
            existedBook.setTitle(updatedBook.getTitle());
            existedBook.setReviews(updatedBook.getReviews());
            return bookRepository.save(existedBook);
        } else {
            throw new BookNotFoundException("The book with the id = " + id + " was not found in the database");
        }
    }

    public void deleteBook(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException("The book with the id = " + id + " was not found in the database");
        }

    }

}
