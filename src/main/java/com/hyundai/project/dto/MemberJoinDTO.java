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
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDTO {
	private String memail;
	private Date regdate, moddate;
	private int from_social;
	private String mname;
    private String mpassword;
    private String role_set;
}
