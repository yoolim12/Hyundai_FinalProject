package com.hyundai.project.controller;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class ChatController {

    @GetMapping("/chat")
    public String chatGET(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model){

        log.info("@ChatController, chat GET()");
        
        if (oauthMemberDTO == null) {
        	model.addAttribute("member", authMemberDTO);
        }
        else {
        	model.addAttribute("member", oauthMemberDTO);
        }
        return "streamingchat/chat";
    }
}
