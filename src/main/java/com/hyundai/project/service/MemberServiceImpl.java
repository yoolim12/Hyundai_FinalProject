package com.hyundai.project.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;
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
	public void modifyMember(String memail, String mpassword, String mname, Date birth, String telnum, String maddress, int gno) throws Exception {
		mapper.modifyMember(memail, mpassword, mname, birth, telnum, maddress, gno);
	}
	
	// 회원 탈퇴
	@Override
	public void delMember(String email) throws Exception {
		mapper.delMember(email);
	}
	
	// 회원 전체 조회
	@Override
	public List<MemberDTO> showAllMember() throws Exception {
		return mapper.showAllMember();
	}

}
