package com.example.bookservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String reviewText;
		private int rating;

		public Review() {
		}

		public Review(long id, String reviewText, int rating) {
				this.id = id;
				this.reviewText = reviewText;
				this.rating = rating;
		}

		public long getId() {
				return id;
		}

		public void setId(long id) {
				this.id = id;
		}

		public String getReviewText() {
				return reviewText;
		}

		public void setReviewText(String reviewText) {
				this.reviewText = reviewText;
		}

		public int getRating() {
				return rating;
		}

		public void setRating(int rating) {
				this.rating = rating;
		}

		@Override
		public String toString() {
				return "Review{" +
								"id=" + id +
								", reviewText='" + reviewText + '\'' +
								", rating=" + rating +
								'}';
		}
}
