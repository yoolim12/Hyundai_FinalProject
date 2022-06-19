package com.hyundai.project.dto;

import lombok.Data;

@Data
public class CartDTO {

	private String pid;
	private String bname;
	private String pname;
	private String pprice;
	private String ccolorcode;
	private String cimage1;
	private String ssize;
	private int qty;

}