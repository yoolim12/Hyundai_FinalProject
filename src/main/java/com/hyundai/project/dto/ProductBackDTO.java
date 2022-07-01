package com.hyundai.project.dto;

import lombok.Data;

@Data
public class ProductBackDTO {

//	조회
	private String pid;
	private String bname;
	private String category;
	private String pname;
	private String pprice;
	private String pdetail;
	private int pstatus;
//	수정
	private int samount;
	private String size;
}