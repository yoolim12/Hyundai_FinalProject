package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.MemberDTO;

public interface MemberService {
	
	// 이메일로 회원 정보 가져오기
	public MemberDTO getMemberInfo(String email) throws Exception;
	
	// 회원 수정
	public void modifyMember(AuthMemberDTO authMemberDTO) throws Exception;
	
	// 회원 탈퇴
	public void delMember(String email) throws Exception;
	
	// 회원 전체 조회
	public List<MemberDTO> showAllMember() throws Exception;
}
