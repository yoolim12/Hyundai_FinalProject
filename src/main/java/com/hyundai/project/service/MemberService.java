package com.hyundai.project.service;

import java.util.List;

import org.springframework.ui.Model;

import com.hyundai.project.dto.MemberDTO;

public interface MemberService {
	
	// 이메일로 회원 정보 가져오기
	public MemberDTO getMemberInfo(String email) throws Exception;
	
	// 회원 수정
	public void modifyMember(String memail, String mname, String birth, String telnum, String maddress, int gno) throws Exception;
	
	// 회원 탈퇴
	public void delMember(String email) throws Exception;
	
	// 회원 전체 조회
	public List<MemberDTO> showAllMember() throws Exception;
}
