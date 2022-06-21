package com.hyundai.project.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/* 
* 작성자 : 김승환
* 
* ClubMember2 (DTO)
* 참조 코드 : 교안 (한국 오라클 교육센터 학습 가이드)
*/
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubMember2 {
	private String now;
	private String email;
	private String password;
	private String name;
	private int from_social;
	private Date regdate, moddate;
	private String role_set;
}
