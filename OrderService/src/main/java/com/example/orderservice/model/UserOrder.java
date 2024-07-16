package com.example.orderservice.model;

import jakarta.persistence.*;


@Entity
public class UserOrder {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		@OneToOne
		@JoinColumn(name = "book_id")
		private Book book;
		private int quantity;
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "user_id")
		private User user;

		public UserOrder() {
		}

		public UserOrder(long id, Book book, int quantity, User user) {
				this.id = id;
				this.book = book;
				this.quantity = quantity;
				this.user = user;
		}

		public long getId() {
				return id;
		}

		public void setId(long id) {
				this.id = id;
		}

		public Book getBook() {
				return book;
		}

		public void setBook(Book book) {
				this.book = book;
		}

		public int getQuantity() {
				return quantity;
		}

		public void setQuantity(int quantity) {
				this.quantity = quantity;
		}

		public User getUser() {
				return user;
		}

		public void setUser(User user) {
				this.user = user;
		}
}
