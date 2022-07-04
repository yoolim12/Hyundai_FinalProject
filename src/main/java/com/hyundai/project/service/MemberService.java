package com.hyundai.project.service;

import java.sql.Date;
import java.util.List;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.MemberJoinDTO;

public interface MemberService {
	
	// 이메일로 회원 정보 가져오기
	public List<MemberJoinDTO> getMemberInfo(String email) throws Exception;
	
	// 회원 수정
	public void modifyMember(AuthMemberDTO authMemberDTO) throws Exception;
	
	// 회원 수정 (관리자)
	public void admodifyMember(String memail, String mname, Date birth, String telnum, String maddress, int gno, int msleep) throws Exception;
	
	// 회원 권한 수정(관리자)
	public void admodifyAuth(String memail, String role) throws Exception;
	
	// 회원 탈퇴
	public void delMember(String email) throws Exception;
	
	// 회원 전체 조회
	public List<MemberJoinDTO> showAllMember() throws Exception;

	// 회원 포인트 조회
	public int getPoint(String memail) throws Exception;
	
	// 로그인 기록 저장
	public void insertLoginLog(String memail) throws Exception;

	// 휴면 갱신 (배치)
	public int updateMemberSleep() throws Exception;
		
	// 등급 갱신 (배치)
	public int updateMemberGrade() throws Exception;
	
	// 임직원 조회
	public List<MemberJoinDTO> getEmployeeInfo(String memail);

	public List<MemberJoinDTO> SfindEmployee(String memail);
}
