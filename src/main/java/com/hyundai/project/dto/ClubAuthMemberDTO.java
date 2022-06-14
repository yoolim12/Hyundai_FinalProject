package com.hyundai.project.dto;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User implements OAuth2User {

	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private int fromSocial;
	private String password;
	private Map<String, Object> OA2_attr;
	private String role;

	// ClubOAuth2UserDetailsService 용 구성자
	public ClubAuthMemberDTO(String username, String password, int fromSocial, List<GrantedAuthority> authorities,
			Map<String, Object> OA2_attr) {
		this(username, password, fromSocial, authorities);
		this.OA2_attr = OA2_attr;
	}// end ClubAuthMemberDTO

	// 구성자 설정
	public ClubAuthMemberDTO(String username, String password, int fromSocial, List<GrantedAuthority> authorities) {
		super(username, password, authorities); // password는 부모클래스 사용
		this.email = username;
		this.fromSocial = fromSocial;
	}// end ClubAuthMemberDTO

	// OAuth2User 정보 저장
	@Override
	public Map<String, Object> getAttributes() {
		return OA2_attr;
	}

}// end class
