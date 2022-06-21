package com.hyundai.project.dto;

import lombok.Data;

@Data
public class CartUpdateDTO {

	private String pid;
	private String ccolorcode;
	private String ssize;
	private String qty;
	private String newcolorcode;
	private String newsize;
	private String newqty;

}