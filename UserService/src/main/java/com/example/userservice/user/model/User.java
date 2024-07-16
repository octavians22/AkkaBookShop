package com.example.userservice.user.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String username;
		private String password;
		@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
		private List<UserOrder> userOrderList;

		public User() {
		}

		public User(long id, String username, String password, List<UserOrder> userOrderList) {
				this.id = id;
				this.username = username;
				this.password = password;
				this.userOrderList = userOrderList;
		}

		public long getId() {
				return id;
		}

		public void setId(long id) {
				this.id = id;
		}

		public String getUsername() {
				return username;
		}

		public void setUsername(String username) {
				this.username = username;
		}

		public String getPassword() {
				return password;
		}

		public void setPassword(String password) {
				this.password = password;
		}

		public List<UserOrder> getUserOrderList() {
				return userOrderList;
		}

		public void setUserOrderList(List<UserOrder> userOrderList) {
				this.userOrderList = userOrderList;
		}
}
