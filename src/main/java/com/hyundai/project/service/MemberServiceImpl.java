package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AuthMemberDTO;
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
	public List<MemberDTO> showAllMember() throws Exception {
		return mapper.showAllMember();
	}

	@Override
	public void admodifyMember(String memail, String mname, String birth, String telnum, String maddress, int gno) {
		// TODO Auto-generated method stub
		mapper.admodifyMember(memail, mname, birth, telnum, maddress, gno);
	}

}
