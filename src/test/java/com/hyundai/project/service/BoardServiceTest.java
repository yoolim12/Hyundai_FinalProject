package com.hyundai.project.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dto.BoardAttachDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.PageDTO;
/*
 * 작성자 : 문혁
 * 
 * 게시판 서비스 JUnit 테스트
 * 글쓰기, 상세 정보, 수정, 삭제
 */
@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	private BoardService  service;
	
	@Test
	public void insertTest() throws Exception {
		System.out.println(service);
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNo(1);
		boardDTO.setEmail("0test@naver.com");		
		boardDTO.setTitle("test title0");
		boardDTO.setContent("test content0");
		boardDTO.setPassword("1234");
		service.insertBoard(boardDTO);
		System.out.println(boardDTO);
	}//end test
	
	@Test
	public void getListTest() throws Exception {
		System.out.println(service);
		PageDTO dto = new PageDTO(1,1,10,50); 
		List<BoardDTO> list = service.getBoardList(dto);
		System.out.println(list);
	}//end test

	@Test
	public void getDetailTest() throws Exception {
		BoardDTO boardDTO = service.getDetail(1);
		System.out.println(boardDTO);
	}//end test

	
	@Test
	public void deleteBoardTest() throws Exception {
		System.out.println(service);
		BoardDTO boardDTO = new BoardDTO();
		// 실행 전 DB에 해당 데이터 있는지 확인
		boardDTO.setNo(1);
		boardDTO.setEmail("0test@naver.com");		
		boardDTO.setTitle("test title0");
		boardDTO.setContent("test content0");
		boardDTO.setPassword("1234");
		service.deleteBoard(boardDTO);
		System.out.println(boardDTO.getNo()+"번 게시글이 삭제되었습니다.");
	}//end test
	
	@Test
	public void getBoardTest() throws Exception {
		BoardDTO boardDTO = service.getBoard(1);
		System.out.println(boardDTO);
	}//end test

	@Test
	public void updateBoardTest() throws Exception {
		System.out.println(service);
		BoardDTO boardDTO = new BoardDTO();
		// 실행 전 DB에 해당 데이터 있는지 확인
		boardDTO.setNo(1);
		boardDTO.setEmail("1test@naver.com");		
		boardDTO.setTitle("test title1");
		boardDTO.setContent("test content1");
		boardDTO.setPassword("1234");
		service.updateBoard(boardDTO);
		System.out.println(boardDTO.getNo()+"번 게시글이 수정되었습니다.");
	}//end test

	@Test
	public void getTotalTest() throws Exception {
		long total = service.getTotal();
		System.out.println(total);
	}//end test

	@Test
	public void getAttachListTest() throws Exception {
		System.out.println(service);
		List<BoardAttachDTO> list = service.getAttachList(1L);
		System.out.println(list);
	}//end test


}//end class
