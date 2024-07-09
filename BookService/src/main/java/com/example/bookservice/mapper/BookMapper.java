package com.example.bookservice.mapper;

import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

		public BookDTO toDto(Book book) {

				BookDTO bookDTO = new BookDTO();

				bookDTO.setDescription(book.getDescription());
				bookDTO.setAuthor(book.getAuthor());
				bookDTO.setId(book.getId());
				bookDTO.setPublisher(book.getPublisher());
				bookDTO.setPrice(book.getPrice());
				bookDTO.setStock(book.getStock());
				bookDTO.setTitle(book.getTitle());

				return bookDTO;
		}

		public Book toEntity(BookDTO bookDTO) {

				Book book = new Book();

				book.setStock(bookDTO.getStock());
				book.setPublisher(bookDTO.getPublisher());
				book.setPrice(bookDTO.getPrice());
				book.setTitle(bookDTO.getTitle());
				book.setAuthor(bookDTO.getAuthor());
				book.setId(bookDTO.getId());
				book.setDescription(bookDTO.getDescription());

				return book;
		}
}
