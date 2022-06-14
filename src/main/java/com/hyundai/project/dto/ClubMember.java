package com.hyundai.project.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
