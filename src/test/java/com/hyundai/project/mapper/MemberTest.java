package com.hyundai.project.mapper;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubMember;
import com.hyundai.project.dto.ClubMember2;
import com.hyundai.project.dto.ClubMemberRole;
import com.hyundai.project.dto.ClubMemberRoleSet;

/* 
* 작성자 : 김승환
* 
* 회원관리 관련 DAO Junit 테스트
*/

@SpringBootTest
public class MemberTest {
	@Autowired
	private ClubMemberDAO clubMemberDAO;
	// 암호화된 데이터 입력 위해
	@Autowired
	private PasswordEncoder passwordEncoder;

	// insert test
	@Test
	public void insertDummies() throws SQLException {
		// 패스워드는 1111 로 고정
		for (int i = 1; i <= 101; i++) {
			ClubMember clubMember = new ClubMember();
			clubMember.setEmail("user" + i + "@test.com");
			clubMember.setName("사용자" + i);
			clubMember.setFrom_social(0);
			clubMember.setPassword(passwordEncoder.encode("1111"));
			clubMemberDAO.insertClubMember(clubMember);
		}
		;// end int

		System.out.println("입력 성공");
	}// end insert..

	// insert_role_set test
	@Test
	public void insertDummies2() throws SQLException {
		// 1~80 USER 81~90 USER,MANAGER
		// 91~100 USER,MANAGER,ADMIN
		for (int i = 1; i <= 101; i++) {
			ClubMemberRoleSet clubMemberRoleSet = new ClubMemberRoleSet();
			clubMemberRoleSet.setClub_member_email("user" + i + "@test.com");
			String role = null;
			// 기본 권한 user
			role = ClubMemberRole.USER.toString();
			// 81번 부터 manager 권한 추가
			if (i > 80) {
				role = ClubMemberRole.MANAGER.toString();
			} // end if
				// 91번 부터 admin권한 추가
			if (i > 90) {
				role = ClubMemberRole.ADMIN.toString();
			} // end if
			clubMemberRoleSet.setRole_set(role);
			// db에 저장
			clubMemberDAO.insertClubRoleSet(clubMemberRoleSet);
		} // end for
	}// end insert..

	// find_email_test
	@Test
	public void testRead() throws SQLException {
		ClubMember2 reult = clubMemberDAO.findByEmail("user30@test.com", 0);
		ClubMember2 clubMember2 = reult;
		System.out.println(clubMember2);
	}// end testRead

	// findAll Test
	@Test
	public void testFindAll() throws SQLException {
		List<ClubMember> result = clubMemberDAO.findAll();
		List<ClubMember> clubMember2 = result;
		System.out.println(clubMember2);
	}// end testFindAll

	// update test
	@Test
	public void updateTest() throws SQLException {
		ClubMember clubMember = new ClubMember();
		clubMember.setNow("user29@test.com");
		clubMember.setEmail("user299@test.com");
		clubMember.setName("사용자");
		clubMember.setFrom_social(0);
		clubMember.setPassword(passwordEncoder.encode("1111"));
		clubMemberDAO.updateInfo(clubMember);

		System.out.println("업데이트 성공");
	}// end updateTest

	// update test
	@Test
	public void updateRoleTest() throws SQLException {
		ClubMemberRoleSet clubRole = new ClubMemberRoleSet();
		clubRole.setNow("user29@test.com");
		clubRole.setClub_member_email("user299@test.com");
		clubMemberDAO.updateRoleEmail(clubRole);

		System.out.println("Role 업데이트 성공");
	}// end updateRoleTest

	// update test
	@Test
	public void deleteRoleTest() throws SQLException {
		String email = "user25@test.com";
		clubMemberDAO.deleteRoleSet(email);

		System.out.println("회원 Role 삭제 성공");
	}// end deleteRoleTest

	// update test
	@Test
	public void deleteTest() throws SQLException {
		String email = "user25@test.com";
		clubMemberDAO.deleteInfo(email);

		System.out.println("회원 삭제 성공");
	}// end deleteRoleTest

}
