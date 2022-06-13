package com.hyundai.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;

import com.hyundai.project.dto.BoardDTO;

import lombok.extern.log4j.Log4j2;
/*
 * 작성자 : 문혁
 * 
 * 게시판 컨트롤러 JUnit 테스트
 */
@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class BoardControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// 글쓰기 페이지 테스트
	@Test
	public void getInsertTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/board/detail"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test

	@Test
	public void postInsertTest() throws Exception {
		BoardDTO dto = new BoardDTO();
		dto.setEmail("0test@naver.com");
		dto.setTitle("test title0");
		dto.setContent("test content0");
		dto.setPassword("1234");
		
		ModelAndView mnv = mockMvc.perform(post("/board/insert")
				.param("boardDTO", "dto"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test	
	
	@Test
	public void getListTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/board/list")
				.param("page", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test

	@Test
	public void getDetailTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/board/detail")
				.param("no", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test

	@Test
	public void getDeleteTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/board/delete")
				.param("no", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test

	@Test
	public void postDeleteTest() throws Exception {
		BoardDTO dto = new BoardDTO();
		dto.setNo(1);
		dto.setEmail("0test@naver.com");
		dto.setTitle("test title0");
		dto.setContent("test content0");
		dto.setPassword("1234");
		
		ModelAndView mnv = mockMvc.perform(post("/board/delete")
				.param("boardDTO", "dto"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test	
	
	@Test
	public void getUpdateTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/board/update")
				.param("no", "1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력
	}// end test
	
	@Test
	public void postUpdateTest() throws Exception {
		BoardDTO dto = new BoardDTO();
		dto.setNo(1);
		dto.setEmail("0test@naver.com");
		dto.setTitle("test title0");
		dto.setContent("test content0");
		dto.setPassword("1234");
		
		ModelAndView mnv = mockMvc.perform(post("/board/update")
				.param("boardDTO", "dto"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test

}// end class
