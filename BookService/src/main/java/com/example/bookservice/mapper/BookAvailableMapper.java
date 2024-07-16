package com.example.bookservice.mapper;

import com.example.bookservice.dto.BookAvailableDTO;
import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookAvailableMapper {

		public BookAvailableDTO toDto(Book book) {

				BookAvailableDTO bookAvailableDTO = new BookAvailableDTO();

				bookAvailableDTO.setAuthor(book.getAuthor());
				bookAvailableDTO.setPublisher(book.getPublisher());
				bookAvailableDTO.setTitle(book.getTitle());
				bookAvailableDTO.setPrice(book.getPrice());

				return bookAvailableDTO;
		}

		public Book toEntity(BookAvailableDTO bookAvailableDTO) {

				Book book = new Book();

				book.setPublisher(bookAvailableDTO.getPublisher());
				book.setTitle(bookAvailableDTO.getTitle());
				book.setAuthor(bookAvailableDTO.getAuthor());
				book.setPrice(bookAvailableDTO.getPrice());

				return book;
		}
}
