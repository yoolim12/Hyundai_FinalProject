package com.hyundai.project.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hyundai.project.dto.ClubMember;
import com.hyundai.project.dto.ClubMember2;
import com.hyundai.project.dto.ClubMemberRoleSet;

@Mapper
public interface ClubMemberDAO {
	public void insertClubMember(ClubMember clubMember) throws SQLException;
	public void insertClubRoleSet(ClubMemberRoleSet clubMemberRoleSet) throws SQLException;
	public ClubMember2 findByEmail(@Param("email") String email, 
			@Param("social") int social) throws SQLException;
	public List<ClubMember> findAll() throws SQLException;
	public void updateInfo(ClubMember clubMember) throws SQLException;
	public void updateRoleEmail(ClubMemberRoleSet clubMemberRoleSet) throws SQLException;
	public void deleteInfo(String email) throws SQLException;
	public void deleteRoleSet(String email) throws SQLException;
}
