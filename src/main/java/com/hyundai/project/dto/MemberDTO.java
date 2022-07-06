package com.hyundai.project.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
public class MemberDTO {
	private String memail;
    private String mname;
    private Date birth;
    private String telnum;
    private String maddress;
    private String mgender;
    private String mpassword;
    private String memail_info;
    private Date moddate;
    private Date regdate;
    private int from_social;
    private int mpoint;
    private int gno;
    private int msleep;
    private int mail_check;
}
