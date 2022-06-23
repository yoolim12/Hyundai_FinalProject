package com.hyundai.project.dto;

import lombok.Data;

@Data
public class KakaoAmountDTO {
	private Integer total, tax_free, vat, point, discount;
}
