package com.example.bookservice.repository;

import com.example.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

		Optional<Book> findByTitle(String title);
		Optional<Book> findByTitleAndAuthorAndPublisher(String title, String author, String publisher);
		Optional<Book> findByTitleAndAuthorAndPublisherAndStock(String title, String author, String publisher, int stock);

}
