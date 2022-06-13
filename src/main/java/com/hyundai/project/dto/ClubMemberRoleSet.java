package com.hyundai.project.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
/* 
* 작성자 : 김승환
* 
* ClubMemberRoleSet
* 참조 코드 : 교안 (한국 오라클 교육센터 학습 가이드)
*/
@Data
@Setter
@Getter
public class ClubMemberRoleSet implements Serializable{
	private String now;
	private String club_member_email;
	private String role_set;
}
