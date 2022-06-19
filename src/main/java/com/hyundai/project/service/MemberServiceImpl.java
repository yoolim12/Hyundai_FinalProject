package com.hyundai.project.service;

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
		// TODO Auto-generated method stub
		return mapper.getMemberInfo(email);
	}
	
	// 회원 수정
	@Override
	public void modifyMember(String memail, String mname, String birth, String telnum, String maddress) throws Exception {
		// TODO Auto-generated method stub
		mapper.modifyMember(memail, mname, birth, telnum, maddress);
	}
	
	// 회원 탈퇴
	@Override
	public void delMember(String email) throws Exception {
		// TODO Auto-generated method stub
		mapper.delMember(email);
	}

}
