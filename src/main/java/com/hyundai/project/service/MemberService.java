package com.hyundai.project.service;

import java.sql.Date;
import java.util.List;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.MemberJoinDTO;

public interface MemberService {

    public List<MemberJoinDTO> mailMember() throws Exception;

    // 이메일로 회원 정보 가져오기
    public List<MemberJoinDTO> getMemberInfo(String email) throws Exception;

    // 정보 수신용 이메일 주소 가져오기
    public String findEmailInfo(String memail_info);

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

    // 총 회원수
    public long getTotalMember() throws Exception;

    // 상품 총 판매량
    public long getTotalAmount() throws Exception;

    // 이번 주의 주문 건수
    public long getOrderAmountOfWeek() throws Exception;

    // 총 매출
    public long getTotalRevenue() throws Exception;

    // 회원 휴면 해제
	public int modifySleep(String memail, int msleep);
}
