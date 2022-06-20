package com.hyundai.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRoleDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void simpleRegister(MemberDTO memberdto) {
		memberDAO.simpleRegister(memberdto);
	}

	@Override
	public void registerRole(MemberRoleDTO member_role) {
		memberDAO.registerRole(member_role);
	}
}
