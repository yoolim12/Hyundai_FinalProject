package com.hyundai.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.dto.ClubMember;
import com.hyundai.project.dto.ClubMember2;
import com.hyundai.project.dto.ClubMemberRole;
import com.hyundai.project.dto.ClubMemberRoleSet;
import com.hyundai.project.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member/")
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MemberController {
	
	
}// end class
