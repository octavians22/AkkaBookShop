package com.example.bookservice.dto;

import com.example.bookservice.model.BookOperationType;

public class BookDTO {
		private long id;
		private String publisher;
		private String title;
		private String author;
		private double price;
		private int stock;
		private String description;
		private BookOperationType bookOperationType;

		public BookDTO() {
		}

		public BookDTO(long id, String publisher, String title, String author, double price, int stock, String description, BookOperationType bookOperationType) {
				this.id = id;
				this.publisher = publisher;
				this.title = title;
				this.author = author;
				this.price = price;
				this.stock = stock;
				this.description = description;
				this.bookOperationType = bookOperationType;
		}

		public long getId() {
				return id;
		}

		public void setId(long id) {
				this.id = id;
		}

		public String getPublisher() {
				return publisher;
		}

		public void setPublisher(String publisher) {
				this.publisher = publisher;
		}

		public String getTitle() {
				return title;
		}

		public void setTitle(String title) {
				this.title = title;
		}

		public String getAuthor() {
				return author;
		}

		public void setAuthor(String author) {
				this.author = author;
		}

		public double getPrice() {
				return price;
		}

		public void setPrice(double price) {
				this.price = price;
		}

		public int getStock() {
				return stock;
		}

		public void setStock(int stock) {
				this.stock = stock;
		}

		public String getDescription() {
				return description;
		}

		public void setDescription(String description) {
				this.description = description;
		}

		public BookOperationType getBookOperationType() {
				return bookOperationType;
		}

		public void setBookOperationType(BookOperationType bookOperationType) {
				this.bookOperationType = bookOperationType;
		}
}
