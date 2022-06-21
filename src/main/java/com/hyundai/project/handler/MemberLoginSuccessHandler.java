package com.hyundai.project.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.hyundai.project.dto.ClubAuthMemberDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MemberLoginSuccessHandler implements AuthenticationSuccessHandler {
	// 구성자 추가 SecurityConfig 에서 사용
    public MemberLoginSuccessHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
 
    // RedirectStrategy 인터페이스 생성 sendRedirect() 메서드 이용
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    // 사용자 암호 확인 용
    private PasswordEncoder passwordEncoder;

   
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("------------------------");
		log.info("onAuthenticationSuccess");
		
		// 인증 객체에서 사용자 정보 저장
        ClubAuthMemberDTO clubAuthMemberDTO = (ClubAuthMemberDTO) authentication.getPrincipal();
        log.info(clubAuthMemberDTO);
        // 소셜 사용자인지 확인
        int fromSocial = clubAuthMemberDTO.getFromSocial();
        System.out.println(fromSocial);
 
        // 소셜 사용자
        if ((fromSocial == 1)) {
            redirectStrategy.sendRedirect(request, response, "/main");
        } // end if

	}// end onAu…
	
	

}// end class
