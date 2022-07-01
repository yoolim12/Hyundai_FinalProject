package com.hyundai.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductInsertDTO {

	private String pid;
	private String bname;
	private String clarge;
	private String cmedium;
	private String csmall;
	private String pname;
	private String pprice;
	private String pdetail;
	private String ccolorcode;
	private List<ProductSizeDTO> ssize;

}