package com.example.userservice.user.controller;

import com.example.userservice.user.dto.UserOrderDTO;
import com.example.userservice.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderBook")
public class UserServiceController {

		private final UserService userService;

		public UserServiceController(UserService userService) {
				this.userService = userService;
		}

		@PostMapping
		public ResponseEntity<List<UserOrderDTO>> orderBooks(@RequestBody List<UserOrderDTO> desiredBooks) {
				List<UserOrderDTO> availableBooks = userService.orderBook(desiredBooks);
				return new ResponseEntity<>(availableBooks, HttpStatus.CREATED);
		}
}
