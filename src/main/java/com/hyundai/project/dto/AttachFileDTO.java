package com.hyundai.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
/* 
 * 작성자 : 문혁
 * 
 * 첨부파일 정보를 저장하는 DTO 객체
 */
@Data
@AllArgsConstructor
public class AttachFileDTO {
	
	private String fileName;
	private String folderPath;
	private String uuid;

}//end class
