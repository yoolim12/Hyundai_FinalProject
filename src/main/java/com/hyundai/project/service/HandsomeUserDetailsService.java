package com.hyundai.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.AuthMemberDTO;
import com.hyundai.project.dto.MemberJoinDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class HandsomeUserDetailsService implements UserDetailsService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 입력한 이메일로 ClubMember 찾음
		MemberJoinDTO result = null;
//		log.info(username);
//		result = memberRepository.findByEmail(username, 0);

		try {
			log.info(username);
			result = memberDAO.findByEmail(username, 0);
		} catch (Exception e) {
//			return null;
			throw new UsernameNotFoundException("Check Email or Social!");
		}
		
		MemberJoinDTO memberJoinDTO = result;
		log.info("************************** MEMBERJOINDTO ******************************");
		log.info(memberJoinDTO);
		log.info(memberJoinDTO.getRole_set().toString());
		
		List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + memberJoinDTO.getRole_set()));
        
        // Member --> AuthMemberDTO 변환
        AuthMemberDTO authMemberDTO = new AuthMemberDTO(memberJoinDTO.getMemail(), memberJoinDTO.getMpassword(),
        		memberJoinDTO.getFrom_social(), authorities);
        
        // ClubAuthMemberDTO 값 세팅
        authMemberDTO.setName(memberJoinDTO.getMname());
        authMemberDTO.setFromSocial(memberJoinDTO.getFrom_social());
        
        log.info(authMemberDTO);
        log.info(authMemberDTO.getAuthorities().toString());
		
        // AuthMemberDTO는 UserDetails 타입으로 처리됨
		return authMemberDTO;
	}
}
