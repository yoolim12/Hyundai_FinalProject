package com.hyundai.project.dto;

import lombok.Data;

@Data
public class ProductListDTO {
    private String pid; // 상품 id - color
    private String cimage1; // 상품 이미지1 - color
    private String cimage2; // 상품 이미지2 - color
    private String ccolorimage; // 이미지 칩 색상 url - color
    private String pname; // 상품 이름 - product
    private String pprice; // 상품 가격 - product
    private String bname; // 상품 브랜드 - product
    private String ccolorcode; // 상품 칼라 코드 - color
    private String clarge; // 카테고리 - 대
    private String cmedium; // 카테고리 - 중
    private String csmall; // 카테고리 -소
} // end class
