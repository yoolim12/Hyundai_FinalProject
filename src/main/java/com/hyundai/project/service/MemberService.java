package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.MemberDTO;

public interface MemberService {
//	public void insertClubMember(ClubMember clubMember) throws Exception;
//
//	public void insertClubRoleSet(ClubMemberRoleSet clubMemberRoleSet) throws Exception;
//
//	public ClubMember2 findByEmail(@Param("email") String email, @Param("social") int social) throws Exception;
//
//	public List<ClubMember> findAll() throws Exception;
//
//	public void updateInfo(ClubMember clubMember) throws Exception;
//
//	public void updateRoleEmail(ClubMemberRoleSet clubMemberRoleSet) throws Exception;
//
//	public void deleteInfo(String email) throws Exception;
//
//	public void deleteRoleSet(String email) throws Exception;
	
	// 이메일로 회원 정보 가져오기
	public MemberDTO getMemberInfo(String email) throws Exception;
	
	// 회원 수정
	public void modifyMember(String memail, String mname, String birth, String telnum, String maddress) throws Exception;
	
	// 회원 탈퇴
	public void delMember(String email) throws Exception;
	
	// 회원 전체 조회
	public List<MemberDTO> showAllMember() throws Exception;
}
