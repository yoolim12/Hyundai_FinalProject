package com.hyundai.project.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/* 
 * 작성자 : 문혁
 * 
 * 게시글 번호와 첨부파일 정보를 저장하는 DTO 객체
 */
@Getter
@Setter
@ToString
public class BoardAttachDTO implements Serializable {
	private String uuid;
	private String folderPath;
	private String fileName;
	// 게시글 번호에 해당하는 첨부파일 조회에 필요한 bno 속성
	private long bno;
}//end class
