package com.hyundai.project.memberDAO;

import java.sql.Date;
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
	public List<MemberJoinDTO> getMemberInfo(String email) throws Exception;
		
	// 회원 수정 (비밀번호 변경)
	public void modifyMember(AuthMemberDTO authMemberDTO) throws Exception;
	
	// 회원 수정 (관리자)
	public void admodifyMember(String memail, String mname, Date birth, String telnum, String maddress, int gno, int msleep) throws Exception;
		
	// 회원 권한 수정(관리자)
	public void admodifyAuth(String memail, String role) throws Exception;
	
	// 회원 탈퇴
	public void delMember(String email) throws Exception;
		
	// 회원 전체 조회
	public List<MemberJoinDTO> showAllMember() throws Exception;
	
	// 로그인 로그 저장
	public void insertLoginLog(String memail) throws Exception;
			
	// 회원 휴면 갱신
	public int updateMemberSleep() throws Exception;
			
	// 회원 등급 갱신
	public int updateMemberGrade() throws Exception;

	// 결제 시 사용된 포인트 차감
	public void pointApply(String memail, int ousedpoint);

	// 회원 포인트 조회
	public int getPoint(String memail) throws Exception;

	// 회원 포인트 적립
	public void pointSaving(String memail, int savingpoint);
	
	// 임직원 조회
	public List<MemberJoinDTO> getEmployeeInfo(String memail);
	
	// 임직원 검색 조회
	public List<MemberJoinDTO> SfindEmployee(String memail);
	
	public long getTotalMember();
	
	public long getTotalAmount();
	
	public long getOrderAmountOfWeek();
	
	public long getTotalRevenue();
}
