package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MemberServiceImpl implements MemberService {

//	@Autowired
//	private MemberDAO productDAO;
//
//	@Override
//	public MemberDTO getMemberDetail(String pid, String ccolorcode) throws Exception {
//		return productDAO.getMemberDetail(pid, ccolorcode);
//	}
//	
//	@Override 
//	public List<MemberColorDTO> getMemberColorDetail(String pid, String ccolorcode) throws Exception {
//		return productDAO.getMemberColorDetail(pid, ccolorcode);
//	}
//	
//	@Override 
//	public List<MemberSizeDTO> getMemberSize(String pid, String ccolorcode) throws Exception {
//		return productDAO.getMemberSize(pid, ccolorcode);
//	}

}
