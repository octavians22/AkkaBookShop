package com.example.bookservice.dto;

public class BookIdentifierDTO {

		private String publisher;
		private String title;
		private String author;

		public BookIdentifierDTO() {
		}

		public BookIdentifierDTO(String publisher, String title, String author) {
				this.publisher = publisher;
				this.title = title;
				this.author = author;
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
}
