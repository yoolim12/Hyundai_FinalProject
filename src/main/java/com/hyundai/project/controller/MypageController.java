package com.hyundai.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/mypage")
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class MypageController {	
	
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping("")
	public String mypage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) throws Exception {
		if (oauthMemberDTO == null) {
      		model.addAttribute("member", authMemberDTO);
			model.addAttribute("membername", authMemberDTO.getMname());
			model.addAttribute("memberpoint", memberDAO.getPoint(authMemberDTO.getMemail()));
			model.addAttribute("membergno", memberDAO.findByEmail(authMemberDTO.getMemail(), 0).getGno());
		}
		else {
      		model.addAttribute("member", oauthMemberDTO);
			model.addAttribute("membername", oauthMemberDTO.getName());
			model.addAttribute("memberpoint", memberDAO.getPoint(oauthMemberDTO.getEmail()));
			model.addAttribute("membergno", memberDAO.findByEmail(oauthMemberDTO.getEmail(), 1).getGno());
		}
		return "/mypage/mypage";
	}
	
	@RequestMapping("/personInformationChange")
	public void mypageModifyPage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		if (oauthMemberDTO == null) {
			model.addAttribute("memail", authMemberDTO.getMemail());
		}
		else {
			model.addAttribute("memail", oauthMemberDTO.getEmail());
		}
	}
	
//	@RequestMapping(value="/passwordCheck", method=RequestMethod.POST)
//	@ResponseBody
//	public void passwordCheck(@RequestBody HashMap<String, String> map, 
//			@AuthenticationPrincipal AuthMemberDTO authMemberDTO, HttpServletResponse response) throws IOException {
//		String mpassword = map.get("mpassword");
//
//		if(passwordEncoder.matches(mpassword, memberDAO.findByEmail(authMemberDTO.getEmail(), 0).getMpassword())) {
//			Gson gson = new Gson();
//    		response.setContentType("application/json; charset=utf-8");
//    		response.getWriter().print(gson.toJson(map));
//			System.out.println("success!!");
//		}
//		else {
//			response.getWriter().print(false);
//			System.out.println("fail!!");
//		}
//	}
	
	@RequestMapping("/modifyPage")
	public void modifyPage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, Model model) {
		model.addAttribute("memail", authMemberDTO.getMemail());
		model.addAttribute("mname",authMemberDTO.getMname());
		model.addAttribute("birth", authMemberDTO.getBirth());
	}
}
