package com.hyundai.project.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dao.BoardAttachDAO;
import com.hyundai.project.dto.BoardAttachDTO;
/* 
* 작성자 : 문혁
* 
* 게시판 첨부파일 관련 DAO Junit 테스트
*/
@SpringBootTest
public class BoardAttachTest {

	@Autowired
	private BoardAttachDAO dao;
	
	// 파일 업로드 테스트
	@Test
	public void insertTest() throws SQLException {
		for(int i=0;i<5;i++) {
			BoardAttachDTO dto = new BoardAttachDTO();
			dto.setUuid("67a77cd9-8492-44c8-a4d0-8618144511b3");		
			dto.setFolderPath("2022\\05\\17");
			dto.setFileName("KakaoTalk_20191110_221920467.jpg");
			dto.setBno(i);
			dao.insert(dto);
			System.out.println("파일 업로드가 완료되었습니다.");
		}
	}
	
	// 게시글 번호에 해당하는 첨부파일 삭제 테스트
	@Test
	public void deleteTest() throws SQLException {
		dao.delete(1);
		System.out.println("1번 게시글의 첨부파일이 삭제되었습니다.");
	}
	
	// 게시글 번호를 통한 첨부파일 조회 테스트
	@Test
	public void findByBnoTest() throws SQLException {
		List<BoardAttachDTO> list = new ArrayList<>();
		list = dao.findByBno(1);
		System.out.println(list);
	}
	
}//end class
