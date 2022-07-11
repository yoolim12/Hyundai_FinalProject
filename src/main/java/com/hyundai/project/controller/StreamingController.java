package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.service.StreamingService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class StreamingController {
	
	@Autowired
	private StreamingService service;
	
	@GetMapping("/streaming")
	public String stream(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,@AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model){

		log.info(authMemberDTO);
		log.info(oauthMemberDTO);
		if (oauthMemberDTO == null)
			model.addAttribute("member", authMemberDTO);
		else
			model.addAttribute("member", oauthMemberDTO);
		return "streaming/streaming";
	}
	
	@GetMapping("/mobile")
	public String mobileStream(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model){
		if (oauthMemberDTO == null) {
        	model.addAttribute("member", authMemberDTO);
        }
        else {
        	model.addAttribute("member", oauthMemberDTO);
        }
		return "streaming/mobile";
	}
	
	@GetMapping("/streamingReplay/{sno}")
	public String replayStream(@PathVariable("sno") int sno, Model model){
		try {
			model.addAttribute("video", service.getReplay(sno));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "streaming/streamingReplay";
	}
	
	@GetMapping("/streamingChat")
	public void streamChat(@AuthenticationPrincipal AuthMemberDTO authMemberDTO,@AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model){

		log.info("@StreamingController, GET()");
		log.info(authMemberDTO);
		log.info(oauthMemberDTO);
		if (oauthMemberDTO == null)
			model.addAttribute("member", authMemberDTO);
		else
			model.addAttribute("member", oauthMemberDTO);
	}
}
