package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubMember;
import com.hyundai.project.dto.ClubMember2;
import com.hyundai.project.dto.ClubMemberRoleSet;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

	@Autowired
	private ClubMemberDAO memberDAO;

	@Override
	public void insertClubMember(ClubMember clubMember) throws Exception {
		try {
			memberDAO.insertClubMember(clubMember);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	} // end insertClubMember

	@Override
	public void insertClubRoleSet(ClubMemberRoleSet clubMemberRoleSet) throws Exception {
		try {
			memberDAO.insertClubRoleSet(clubMemberRoleSet);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}

	@Override
	public ClubMember2 findByEmail(String email, int social) throws Exception {
		try {
			return memberDAO.findByEmail(email, social);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	} // end findByEmail

	@Override
	public List<ClubMember> findAll() throws Exception {
		try {
			return memberDAO.findAll();
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	} // end findAll

	@Override
	public void updateInfo(ClubMember clubMember) throws Exception {
		try {
			memberDAO.updateInfo(clubMember);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	} // end updateInfo

	@Override
	public void updateRoleEmail(ClubMemberRoleSet clubMemberRoleSet) throws Exception {
		try {
			memberDAO.updateRoleEmail(clubMemberRoleSet);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	} // end updateRoleEmail

	@Override
	public void deleteInfo(String email) throws Exception {
		try {
			memberDAO.deleteInfo(email);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try

	}// end deleteInfo

	@Override
	public void deleteRoleSet(String email) throws Exception {
		try {
			memberDAO.deleteRoleSet(email);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	} // end deleteRoleSet

}
