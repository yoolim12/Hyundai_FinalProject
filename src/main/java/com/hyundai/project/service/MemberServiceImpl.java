package com.hyundai.project.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AuthMemberDTO;
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
	public List<MemberJoinDTO> getMemberInfo(String email) throws Exception {
		return mapper.getMemberInfo(email);
	}
	
	// 정보 수신용 이메일 주소 가져오기
	@Override
	public String findEmailInfo(String memail_info) {
		return mapper.findEmailInfo(memail_info);
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
	public void admodifyMember(String memail, String mname, Date birth, String telnum, String maddress, int gno, int msleep) throws Exception{
		mapper.admodifyMember(memail, mname, birth, telnum, maddress, gno, msleep);
	}
	
	// 회원 권한 수정(관리자)
	@Override
	public void admodifyAuth(String memail, String role) throws Exception{
		mapper.admodifyAuth(memail, role);
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

	// 임직원 조회
	@Override
	public List<MemberJoinDTO> getEmployeeInfo(String memail) {
		return mapper.getEmployeeInfo(memail);
	}

	// 임직원 검색 조회
	@Override
	public List<MemberJoinDTO> SfindEmployee(String memail) {
		return mapper.SfindEmployee(memail);
	}
	
	// 이메일 수신 동의한 멤버 조회
	@Override
	public List<MemberJoinDTO> mailMember() throws Exception {
		return mapper.mailMember();
	}
	
	@Override
	public long getTotalMember() throws Exception {
		return mapper.getTotalMember();
	}
	
	@Override
	public long getTotalAmount() throws Exception {
		return mapper.getTotalAmount();
	}
	
	@Override
	public long getOrderAmountOfWeek() throws Exception {
		return mapper.getOrderAmountOfWeek();
	}
	@Override
	
	public long getTotalRevenue() throws Exception {
		return mapper.getTotalRevenue();
	}
	
	
}
