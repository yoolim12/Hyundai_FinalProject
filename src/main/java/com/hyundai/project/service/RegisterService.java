package com.hyundai.project.service;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberRoleDTO;

public interface RegisterService {
	public void simpleRegister(MemberDTO memberdto);
	public void registerRole(MemberRoleDTO member_role);
}
