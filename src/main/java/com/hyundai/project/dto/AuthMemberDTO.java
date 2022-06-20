package com.hyundai.project.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {
	private static final long serialVersionUID = 1L;
    private String email;
    private String name;
    private int fromSocial;
 
    // 구성자 설정
    public AuthMemberDTO(String username,
            String password, int fromSocial
            , List<GrantedAuthority> authorities) {
        // password는 부모클래스 사용
        super(username, password, authorities);
        this.email = username;
        this.fromSocial = fromSocial;
    }// end ClubAuthMemberDTO
}
