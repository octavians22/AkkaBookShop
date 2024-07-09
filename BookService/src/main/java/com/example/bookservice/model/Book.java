package com.example.bookservice.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
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
		 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		 @JoinColumn(name = "book_id")
		 private List<Review> reviews;

		public Book() {
		}

		public Book(long id, String publisher, String title, String author, double price, int stock, String description, List<Review> reviews) {
				this.id = id;
				this.publisher = publisher;
				this.title = title;
				this.author = author;
				this.price = price;
				this.stock = stock;
				this.description = description;
				this.reviews = reviews;
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

		public List<Review> getReviews() {
				return reviews;
		}

		public void setReviews(List<Review> reviews) {
				this.reviews = reviews;
		}

		@Override
		public String toString() {
				return "Book{" +
								"id=" + id +
								", publisher='" + publisher + '\'' +
								", title='" + title + '\'' +
								", author='" + author + '\'' +
								", price=" + price +
								", stock=" + stock +
								", description='" + description + '\'' +
								", reviews=" + reviews +
								'}';
		}
}
