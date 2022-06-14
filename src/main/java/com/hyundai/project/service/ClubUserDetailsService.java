package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyundai.project.dao.ClubMemberDAO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.dto.ClubMember2;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ClubUserDetailsService implements UserDetailsService {
 
    @Autowired
    private ClubMemberDAO clubMemberDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // 입력한 이메일로 ClubMember 찾음
        ClubMember2 result = null;
        try {
            log.info(username);
            result = clubMemberDAO.findByEmail(username, 0);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("Check Email or Social!!");
        } // end try
        
        if(result == null) {
            try {
                result = clubMemberDAO.findByEmail(username, 1);
            } catch (SQLException e1) {
                throw new UsernameNotFoundException("Check Email or Social!!");
            }//end try  
           
        }//end if
        // clubMember 생성

 
        // clubMember 생성
        ClubMember2 clubMember2 = result;
        log.info("-------------------");
        log.info(clubMember2);
        log.info(clubMember2.getRole_set().toString());
 
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + clubMember2.getRole_set()));
 
        // clubMember --> ClubAuthMemberDTO 변환
        ClubAuthMemberDTO clubAuthMemberDTO = new
                ClubAuthMemberDTO(clubMember2.getEmail(), clubMember2.getPassword(),
                clubMember2.getFrom_social(), authorities);
        // ClubAuthMemberDTO 값 세팅
        clubAuthMemberDTO.setName(clubMember2.getName());
        clubAuthMemberDTO.setFromSocial(clubMember2.getFrom_social());
        clubAuthMemberDTO.setPassword(clubMember2.getPassword());
 
        log.info(clubAuthMemberDTO);
        log.info(clubAuthMemberDTO.getAuthorities().toString());
 
        // ClubAuthMemberDTO는 UserDetails 타입으로 처리됨
        return clubAuthMemberDTO;
    }// end load..
 
}// end Cla...
