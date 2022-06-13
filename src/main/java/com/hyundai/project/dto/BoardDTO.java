package com.hyundai.project.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/* 
 * 작성자 : 문혁
 * 
 * 게시글 정보를 저장하는 DTO 객체
 */
@Getter
@Setter
@ToString
public class BoardDTO implements Serializable {
	private long no;
	private String email;
	private String title;
	private String content;
	private Date regdate;
	private String password;
	
	// 게시글에 포함된 첨부파일 목록
	private List<BoardAttachDTO> attachList;
	// 패스워드 암호화 메서드
	public void setPassword(String password) {
		this.password = DigestUtils.sha512Hex(password);
	}//setP...
}//end class
