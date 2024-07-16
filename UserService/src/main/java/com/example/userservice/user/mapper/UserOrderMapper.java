package com.example.userservice.user.mapper;

import com.example.userservice.user.dto.UserOrderDTO;
import com.example.userservice.user.model.UserOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserOrderMapper {

		private final BookMapper bookMapper;

		public UserOrderMapper(BookMapper bookMapper) {
				this.bookMapper = bookMapper;
		}


		public UserOrderDTO toDto(UserOrder userOrder) {

				UserOrderDTO userOrderDTO = new UserOrderDTO();

				userOrderDTO.setBook(bookMapper.toDto(userOrder.getBook()));
				userOrderDTO.setQuantity(userOrder.getQuantity());

				return userOrderDTO;
		}

		public UserOrder toEntity(UserOrderDTO userOrderDTO) {

				UserOrder userOrder = new UserOrder();

				userOrder.setBook(bookMapper.toEntity(userOrderDTO.getBook()));
				userOrder.setQuantity(userOrderDTO.getQuantity());

				return userOrder;
		}

		public List<UserOrderDTO> toDtoList(List<UserOrder> userOrders) {
				return userOrders.stream()
								.map(this::toDto)
								.collect(Collectors.toList());
		}

		public List<UserOrder> toEntityList(List<UserOrderDTO> userOrderDTOs) {
				return userOrderDTOs.stream()
								.map(this::toEntity)
								.collect(Collectors.toList());
		}
}
