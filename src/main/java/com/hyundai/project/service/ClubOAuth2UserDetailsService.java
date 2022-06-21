package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.dto.ClubMemberRole;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.MemberJoinDTO;
import com.hyundai.project.dto.MemberRoleDTO;
import com.hyundai.project.memberDAO.MemberDAO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ClubOAuth2UserDetailsService
extends DefaultOAuth2UserService {
    // db저장을 위해
    @Autowired
    private  MemberDAO clubMemberDAO;
    // 패스워드 암호화
    @Autowired
    private  PasswordEncoder passwordEncoder;
   
    private MemberJoinDTO saveSocialMember(String email)
            throws SQLException {
        log.info("saveSocialMember  시작");
        // 기본에 동일한 이메일로 가입한 회원인지 확인
        MemberJoinDTO result = clubMemberDAO.findByEmail(email, 1);
         
        // 기본 회원이면 정보 반환
        if (!(result == null)) {
            log.info("기존 회원");
            return  result;
        } // end if
 
        // 가입한적이 없다면 추가 패스워드 1111 이름은 이메일주소
        MemberDTO clubMember = new MemberDTO();
        clubMember.setMemail(email);
        clubMember.setMname(email);
        clubMember.setMpassword(passwordEncoder.encode("1111"));
        
        String now = "2009-03-20"; // 형식을 지켜야 함
        java.sql.Date d = java.sql.Date.valueOf(now);
        
        clubMember.setBirth(d);
        clubMember.setTelnum("1234");
        clubMember.setMaddress("1234");
        clubMember.setMemail_info(email);
        clubMember.setModdate(d);
        clubMember.setRegdate(d);
        clubMember.setFrom_social(1);
        clubMember.setGno(1);
        // 디비에 ClubMember 행저장
        clubMemberDAO.simpleRegister(clubMember);
 
        MemberRoleDTO clubMemberRoleSet = new MemberRoleDTO();
        clubMemberRoleSet.setMemail(email);
        clubMemberRoleSet.setRole_set(ClubMemberRole.USER.toString());
        // 디비에 ClubRoleSet 행저장
        clubMemberDAO.registerRole(clubMemberRoleSet);
 
        result = clubMemberDAO.findByEmail(email, 1);
       
        // 추가된 정보 반환
        return result;
    }// end saveSocialMember..
 
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {
        log.info("-------loaduser-------------");
        log.info("userRequest" + userRequest);
 
        String clientName = userRequest.getClientRegistration().getClientName();
        // 인증 제공자 출력
        log.info("clientName" + clientName);
        log.info(userRequest.getAdditionalParameters());
 
        // 사용자 정보 가져오기 구글에서 허용한 API 범위
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("======oAuth2User===============");
        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + " : " + v);
        });// end foreach
 
        // 신규회원 테이블에 저장 시작
        String email = null;
        if (clientName.equals("Google")) {// 구글 인증 확인
            email = oAuth2User.getAttribute("email");
        } // end if
        log.info("구글 인증 확인");
        log.info("email : " + email);
     
        try {
        	MemberJoinDTO clubMember2 = saveSocialMember(email);
            log.info("---saveSocialMember--");
            log.info(clubMember2);
            //ClubAuthMemberDTO 생성시 필요한 authorities
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(
                    new SimpleGrantedAuthority("ROLE_" + clubMember2.getRole_set()));      
   
           log.info(clubMember2.getMpassword());
            //OAuth2User 를 clubAuthMemberDTO 로 변환
            ClubAuthMemberDTO clubAuthMemberDTO =
                       new ClubAuthMemberDTO(
                       clubMember2.getMemail() ,
                       clubMember2.getMpassword() ,
                       1,
                       authorities,
                       oAuth2User.getAttributes()
               );
               clubAuthMemberDTO.setName(clubMember2.getMname());
               clubAuthMemberDTO.setPassword(clubMember2.getMpassword());
               //clubAuthMemberDTO --> UserDetails 반환
               log.info("OAuth2User 를 clubAuthMemberDTO");
               log.info(clubAuthMemberDTO);
               return clubAuthMemberDTO;
 
 
        } catch (SQLException e) {
            log.info("saveSocialMember error");
            log.info("에러 ");
            log.info(e.toString());
            return null;
        }//end try  
    }// end load..
}// end class


