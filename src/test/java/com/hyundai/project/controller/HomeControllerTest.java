package com.hyundai.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.extern.log4j.Log4j2;
/*
 * 작성자 : 문혁
 * 
 * 홈 컨트롤러 JUnit 테스트
 */
@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	// 메인 페이지 테스트
	@Test
	public void getHomeTest() throws Exception {
	      log.info(
	              mockMvc.perform(MockMvcRequestBuilders.get("/home"))
	              .andExpect(MockMvcResultMatchers.status().isOk())
	              .andReturn().getModelAndView().getViewName()
	              
	     );

	}// end test
	
	// 로그인 페이지 테스트
	@Test
	public void getLoginTest() throws Exception {
	      log.info(
	              mockMvc.perform(MockMvcRequestBuilders.get("/loginForm"))
	              .andExpect(MockMvcResultMatchers.status().isOk())
	              .andReturn().getModelAndView().getViewName()
	              
	     );

	}// end test
}// end class
