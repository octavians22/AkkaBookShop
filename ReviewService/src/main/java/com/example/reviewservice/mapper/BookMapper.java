package com.example.reviewservice.mapper;

import com.example.reviewservice.dto.BookDTO;
import com.example.reviewservice.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setStock(bookDTO.getStock());
        book.setPrice(bookDTO.getPrice());
        book.setPublisher(bookDTO.getPublisher());
        book.setAuthor(bookDTO.getAuthor());
        return book;
    }
}
