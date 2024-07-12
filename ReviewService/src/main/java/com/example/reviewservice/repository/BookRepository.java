package com.example.reviewservice.repository;

import com.example.reviewservice.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findBookByTitle(String title);
}
