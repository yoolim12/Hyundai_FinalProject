package com.hyundai.project.controller;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.service.CartService;
import com.hyundai.project.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@Controller
public class OrderController {

    @Autowired
    private MemberService service;

    @GetMapping("/order")
    public String orderView(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) throws Exception {

        if (oauthMemberDTO == null) {
            model.addAttribute("member", authMemberDTO);
            model.addAttribute("mpoint", service.getPoint(authMemberDTO.getMemail()));
        } else {
            model.addAttribute("member", oauthMemberDTO);
            model.addAttribute("mpoint", service.getPoint(oauthMemberDTO.getMemail()));
        }
        return "member/ordersheet";
    }

}
