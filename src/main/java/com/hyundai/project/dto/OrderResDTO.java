package com.hyundai.project.dto;

import lombok.Data;

@Data
public class OrderResDTO {

	private int oid;
	private String memail;
	private int ozipcode;
	private String oaddress1;
	private String oaddress2;
	private String odate;
	private String oreceiver;
	private String otel;
	private int ousedpoint;
	private int ousedcoupon;
	private int opayment;
	private int oprice;
	private int ostatus;
	private int odiscounted;
	private String pid;
	private String pname;
	private String bname;
	private String ccolorcode;
	private String ssize;
	private int qty;
	private String cimage1;
	
}