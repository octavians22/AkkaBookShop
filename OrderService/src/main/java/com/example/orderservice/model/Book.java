package com.example.orderservice.model;

import jakarta.persistence.*;

@Entity
public class Book {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String publisher;
		private String title;
		private String author;
		private double price;
		private int stock;
		private String description;
		@OneToOne(mappedBy = "book")
		private UserOrder userOrder;

		public Book() {
		}

		public Book(long id, String publisher, String title, String author, double price, int stock, String description, UserOrder userOrder) {
				this.id = id;
				this.publisher = publisher;
				this.title = title;
				this.author = author;
				this.price = price;
				this.stock = stock;
				this.description = description;
				this.userOrder = userOrder;
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

		public UserOrder getUserOrder() {
				return userOrder;
		}

		public void setUserOrder(UserOrder userOrder) {
				this.userOrder = userOrder;
		}
}
