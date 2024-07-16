package com.example.bookservice.dto;

import java.io.Serializable;

public class BookAvailableDTO implements Serializable {
		private String publisher;
		private String title;
		private String author;
		private double price;

		public BookAvailableDTO() {
		}

		public BookAvailableDTO(String publisher, String title, String author, double price) {
				this.publisher = publisher;
				this.title = title;
				this.author = author;
				this.price = price;
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
}
