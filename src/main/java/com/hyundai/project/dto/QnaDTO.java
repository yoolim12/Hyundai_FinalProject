package com.hyundai.project.dto;

import lombok.Data;

@Data
public class QnaDTO {

    private String qid;
    private String memail;
    private String qtitle;
    private String qkind;
    private String qcontent;
    private String qdate;
    private String qimage;
    private String qreplydate;
    private String qreplycontent;
    private String qemail;
    private int qstatus;

}