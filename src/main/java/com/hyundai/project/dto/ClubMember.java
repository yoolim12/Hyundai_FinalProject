package com.hyundai.project.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/* 
* 작성자 : 김승환
* 
* ClubMember (DTO)
* 참조 코드 : 교안 (한국 오라클 교육센터 학습 가이드)
*/

@Data
@Setter
@Getter
public class ClubMember implements Serializable{
	private String now;
	private String email;
	private String password;
	private String name;
	private int from_social;
	private Date regdate, moddate;
}//end class
