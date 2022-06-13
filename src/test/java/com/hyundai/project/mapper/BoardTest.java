package com.hyundai.project.mapper;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dao.BoardDAO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.PageDTO;
/* 
* 작성자 : 문혁
* 
* 게시판 관련 DAO Junit 테스트
*/
@SpringBootTest
public class BoardTest {

	@Autowired
	private BoardDAO dao;
	
	// 게시판 글쓰기 테스트
	@Test
	public void insertTest() throws SQLException {
		for(int i=0;i<10;i++) {
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setEmail(i+"test@naver.com");		
			boardDTO.setTitle("test title"+i);
			boardDTO.setContent("test content"+i);
			boardDTO.setPassword("1234");
			dao.insertBoard(boardDTO);
		}
	}
	// 게시판 목록 조회 테스트
	@Test
	public void getBoardTest() throws SQLException {
		PageDTO dto = new PageDTO(1,1,10,dao.getTotal());
		List<BoardDTO> list = dao.getBoardList(dto);
		System.out.println(list);
	}
	// 게시판 글 상세 페이지 테스트
	@Test
	public void getDetailTest() throws SQLException {
		BoardDTO boardDTO = dao.getDetail(1);
		System.out.println(boardDTO);
	}
	// 게시판 글 삭제 테스트
	@Test
	public void deleteTest() throws SQLException {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNo(1);
		boardDTO.setEmail("0test@naver.com");		
		boardDTO.setTitle("test title0");
		boardDTO.setContent("test content0");
		boardDTO.setPassword("1234");
		int result = dao.deleteBoard(boardDTO);
		if (result == 0) {
			System.out.println("해당 게시글이 존재하지 않거나 일치하지 않는 비밀번호입니다.");
		} else {
			System.out.println("게시글이 삭제되었습니다.");
		}
	}
	// 게시판 글 수정 테스트
	@Test
	public void updateTest() throws SQLException {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNo(1);
		boardDTO.setEmail("0test@naver.com");		
		boardDTO.setTitle("test title0");
		boardDTO.setContent("test content0");
		boardDTO.setPassword("1234");
		int result = dao.updateBoard(boardDTO);
		if (result == 0) {
			System.out.println("해당 게시글이 존재하지 않거나 일치하지 않는 비밀번호입니다.");
		} else {
			System.out.println("게시글이 수정되었습니다.");
		}
	}
	// 게시판 총 게시물 수 조회 테스트
	@Test
	public void totalTest() throws SQLException {
		long total = dao.getTotal();
		System.out.println("total : " + total);
		
		System.out.println("-------------");
	}//end test
	
	
}//end class
