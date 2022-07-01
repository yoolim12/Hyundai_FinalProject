package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberJoinDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO mapper;
	
	// 이메일로 회원 정보 가져오기
	@Override
	public MemberDTO getMemberInfo(String email) throws Exception {
		return mapper.getMemberInfo(email);
	}
	
	// 회원 수정
	@Override
	public void modifyMember(AuthMemberDTO authMemberDTO) throws Exception {
		mapper.modifyMember(authMemberDTO);
	}
	
	// 회원 탈퇴
	@Override
	public void delMember(String email) throws Exception {
		mapper.delMember(email);
	}
	
	// 회원 전체 조회
	@Override
	public List<MemberJoinDTO> showAllMember() throws Exception {
		return mapper.showAllMember();
	}

	@Override
	public void admodifyMember(String memail, String mname, String birth, String telnum, String maddress, int gno) throws Exception{
		mapper.admodifyMember(memail, mname, birth, telnum, maddress, gno);
	}

	// 회원 포인트 조회
	@Override
	public int getPoint(String memail) throws Exception {
		return mapper.getPoint(memail);
	}
	
	// 로그인 로그 저장
	@Override
	public void insertLoginLog(String memail) throws Exception {
		mapper.insertLoginLog(memail);
	}
		
	// 회원 휴면 갱신
	@Override
	public int updateMemberSleep() throws Exception{
		return mapper.updateMemberSleep();
	}
		
	// 회원 등급 갱신
	@Override
	public int updateMemberGrade() throws Exception {
		return mapper.updateMemberGrade();
	}

}
