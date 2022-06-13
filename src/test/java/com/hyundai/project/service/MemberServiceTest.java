package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubMember;
import com.hyundai.project.dto.ClubMember2;
import com.hyundai.project.dto.ClubMemberRoleSet;

/*
 * 작성자 : 김승환
 * 
 * 회원관리 서비스 JUnit 테스트
 * 회원가입, 회원정보 수정, 탈퇴, 조회
 */

@SpringBootTest
public class MemberServiceTest {
	@Autowired
	private ClubMemberDAO dao;

	// insert member test
	@Test
	public void insertClubMemberTest() throws SQLException {
		ClubMember m = new ClubMember();
		m.setEmail("testtest@test.com");
		m.setFrom_social(0);
		m.setName("test");
		m.setPassword("1111");
		dao.insertClubMember(m);
		System.out.println("insert 완료");
	} // end insertClubMemberTest

	// insert member role test
	@Test
	public void insertClubRoleSetTest() throws SQLException {
		ClubMemberRoleSet m = new ClubMemberRoleSet();
		m.setClub_member_email("testtest@test.com");
		m.setRole_set("USER");
		dao.insertClubRoleSet(m);
		System.out.println("insert Role 완료");
	} // end insertClubRoleSetTest
	
	// find email test
	@Test
	public void findByEmailTest() throws SQLException {
		ClubMember2 m = new ClubMember2();
		String email = "testtest@test.com";
		int social = 0;
		m = dao.findByEmail(email, social);
		System.out.println(m);
		System.out.println("이메일로 찾기 완료");
	} // end findByEmailTest
	
	// find all test
	@Test
	public void findAllTest() throws SQLException {
		List<ClubMember> list = new ArrayList<>();
		list = dao.findAll();
		System.out.println(list);
	}
	
	// update info test
	@Test
	public void updateInfoTest() throws SQLException {
		ClubMember m = new ClubMember();
		m.setNow("user90@test.com");
		m.setEmail("testtest@test.com");
		m.setFrom_social(0);
		m.setName("test");
		m.setPassword("1111");
		dao.updateInfo(m);
		System.out.println("업데이트 완료");
	} // end updateInfoTest
	
	// update role test
	@Test
	public void updateRoleEmailTest() throws SQLException {
		ClubMemberRoleSet m = new ClubMemberRoleSet();
		m.setClub_member_email("user90@test.com");
		dao.updateRoleEmail(m);
		System.out.println("업데이트 Role 완료");
	} // end updateRoleEmailTest
	
	// delete role test
	@Test
	public void deleteRoleSetTest() throws SQLException {
		String email = "user90@test.com";
		dao.deleteRoleSet(email);
		System.out.println("Role 삭제 완료");
	} // end deleteRoleSetTest
	
	// delete Info test
	@Test
	public void deleteInfoTest() throws SQLException {
		String email = "user90@test.com";
		dao.deleteRoleSet(email);
		System.out.println("회원정보 삭제 완료");
	} // end deleteInfoTest

}
