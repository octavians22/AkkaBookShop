package com.example.userservice.user.mapper;

import com.example.userservice.user.dto.BookAvailableDTO;
import com.example.userservice.user.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

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

				book.setAuthor(bookAvailableDTO.getAuthor());
				book.setPublisher(bookAvailableDTO.getPublisher());
				book.setTitle(bookAvailableDTO.getTitle());
				book.setPrice(bookAvailableDTO.getPrice());

				return book;
		}

		public List<BookAvailableDTO> toDtoList(List<Book> books) {
				return books.stream()
								.map(this::toDto)
								.collect(Collectors.toList());
		}

		public List<Book> toEntityList(List<BookAvailableDTO> bookAvailableDTOS) {
				return bookAvailableDTOS.stream()
								.map(this::toEntity)
								.collect(Collectors.toList());
		}
}
