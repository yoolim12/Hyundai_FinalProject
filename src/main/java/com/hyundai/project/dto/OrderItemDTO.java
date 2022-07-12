package com.hyundai.project.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private int oid;
    private String pid;
    private String ccolorcode;
    private String ssize;
    private int qty;

}