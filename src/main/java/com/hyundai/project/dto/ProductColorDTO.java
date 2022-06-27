package com.hyundai.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductColorDTO {

	private String pid;
	private String ccolorcode;
	private String ccolorimage;
	private String cimage1;
	private String cimage2;
	private String cimage3;
	private String cmatchpid;
	private List<ProductSizeDTO> psize;

}