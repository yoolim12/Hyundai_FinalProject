package com.hyundai.project.dto;

import java.sql.Date;
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
	
	private String memail;
    private String mname;
    private Date birth;
    private String telnum;
    private String maddress;
    private String mgender;
    private String mpassword;
    private String memail_info;
    private Date moddate;
    private Date regdate;
    private int from_social;
    private int gno;	  
 
    // 구성자 설정
    public AuthMemberDTO(String username,
            String password, int fromSocial
            , List<GrantedAuthority> authorities, String mname, Date birth, String telnum,
            String maddress, String mgender, String memail_info, Date moddate, Date regdate, int gno) {
        // password는 부모클래스 사용
        super(username, password, authorities);
        this.memail = username;
        this.from_social = fromSocial;
        this.mpassword = password;
        
        this.mname = mname;
        this.birth = birth;
        this.telnum = telnum;
        this.maddress = maddress;
        this.mgender = mgender;
        this.memail_info = memail_info;
        this.moddate = moddate;
        this.regdate = regdate;
        this.gno = gno;
    }// end ClubAuthMemberDTO
}
