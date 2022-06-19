package com.hyundai.project.dto;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class MemberDTO {
	private String memail;
    private String mname;
    private String birth;
    private String telnum;
    private String maddress;
    private String mgender;
    private String mpassword;
    private String memail_info;
    private Date moddate;
    private Date regdate;
    private int from_social;
}
