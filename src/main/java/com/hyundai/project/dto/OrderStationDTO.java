package com.hyundai.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderStationDTO {

	private List<OrderStationItemDTO> item;
	private int totalPrice;

}