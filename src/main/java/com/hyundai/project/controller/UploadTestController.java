package com.hyundai.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/* 
* 작성자 : 문혁
* 
* 파일 업로드 기능을 테스트하기 위한 컨트롤러
*/
@Controller
public class UploadTestController {
	// 테스트용 html 매핑
	@GetMapping("/uploadTest")
	public void uploadEx() {
	}
}
