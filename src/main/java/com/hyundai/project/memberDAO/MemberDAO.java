package com.hyundai.project.memberDAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberJoinDTO;
import com.hyundai.project.dto.MemberRoleDTO;

@Mapper
public interface MemberDAO {

	public void simpleRegister(MemberDTO member);
	public void registerRole(MemberRoleDTO member_role);
	public MemberJoinDTO findByEmail(String memail, int from_social);
	
	// 이메일로 회원 정보 가져오기
	public MemberDTO getMemberInfo(String email) throws Exception;
		
	// 회원 수정
	public void modifyMember(AuthMemberDTO authMemberDTO) throws Exception;
		
	// 회원 탈퇴
	public void delMember(String email) throws Exception;
		
	// 회원 전체 조회
	public List<MemberDTO> showAllMember() throws Exception;
}
