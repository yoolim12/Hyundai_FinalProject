package com.hyundai.project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j2;

/*
 * 작성자 : 김승환
 * 
 * 회원관리 컨트롤러 JUnit 테스트
 */

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class MemberControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	
	// 회원가입 테스트
	@Test
	public void JoinTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(post("/member/join")
				.param("email", "user222@test.com")
				.param("password", "1111")
				.param("name", "test")
				.param("from_social", "0")
				.param("regdate", "2022-02-02")
				.param("moddate", "2022-02-02"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test
	
	
	// 아이디 중복 테스트
	@Test
	public void checkIdTest() throws Exception {
		
		ModelAndView mnv = mockMvc.perform(post("/member/checkid")
				.param("email", "user95@test.com"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	}// end test
	
	// 관리자 페이지 테스트
	@WithMockUser(roles = {"ADMIN"})
	@Test
	public void adminTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/member/admin"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test
	
	
	// 회원정보 수정
	@Test
	public void updateTest() throws Exception {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("now", "user30@test.com");
		map.add("email", "user30@test.com");
		map.add("password", "user30@test.com");
		map.add("name", "user30@test.com");
		mockMvc.perform(post("/member/update")
				.params(map))
				.andReturn().getModelAndView();

	}// end test
	
	// 회원탈퇴 테스트
	@Test
	public void deleteTest() throws Exception {
		ModelAndView mnv = mockMvc.perform(get("/member/update")
				.param("email", "user20@test.com"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getModelAndView();
	      log.info(mnv.getViewName());// 이동한 페이지 출력
	      log.info(mnv.getModel());// 모델에 담긴 값 출력		
	}// end test
	

}
