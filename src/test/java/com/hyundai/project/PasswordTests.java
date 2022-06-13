package com.hyundai.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * 작성자 : 김승환
 * 
 * password JUnit 테스트
 * 참조 코드 : 교안 (한국 오라클 교육센터 학습 가이드)
 */

@SpringBootTest
public class PasswordTests {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 패스워드 인코드 테스트
	@Test
	public void testEncide(){
		String password ="1111";
	    String enPw = passwordEncoder.encode(password);
	    System.out.println("enpw" + enPw);
	    boolean matchResult = passwordEncoder.matches(password,enPw);
	    System.out.println("matchResult: " + matchResult);

	}//end testEncide
}//end class
