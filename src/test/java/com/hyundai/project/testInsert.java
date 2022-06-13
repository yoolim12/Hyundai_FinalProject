package com.hyundai.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dao.BoardDAO;
import com.hyundai.project.dto.BoardDTO;
/* 
* 작성자 : 문혁
* 
* 게시판 더미데이터 생성용 Junit 테스트
*/
@SpringBootTest
public class testInsert {
	
	@Autowired
	private BoardDAO dao;

	@Test
	public void insertTest() throws Exception {
		for(int i=0;i<10;i++) {
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setEmail(i+"test@naver.com");		
			boardDTO.setTitle("test title"+i);
			boardDTO.setContent("test content"+i);
			boardDTO.setPassword("1234");
			dao.insertBoard(boardDTO);
		}
	}//end insert..
	
}//end class
