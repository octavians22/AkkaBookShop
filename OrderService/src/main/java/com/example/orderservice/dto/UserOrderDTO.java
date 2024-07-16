package com.example.orderservice.dto;


import com.example.orderservice.model.Book;

import java.io.Serializable;

public class UserOrderDTO implements Serializable {

		private BookAvailableDTO book;
		private int quantity;

		public UserOrderDTO() {
		}

		public UserOrderDTO(BookAvailableDTO book, int quantity) {
				this.book = book;
				this.quantity = quantity;
		}

		public BookAvailableDTO getBook() {
				return book;
		}

		public void setBook(BookAvailableDTO book) {
				this.book = book;
		}

		public int getQuantity() {
				return quantity;
		}

		public void setQuantity(int quantity) {
				this.quantity = quantity;
		}
}
