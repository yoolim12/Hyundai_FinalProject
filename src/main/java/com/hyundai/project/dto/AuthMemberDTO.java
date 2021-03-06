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
    private int mpoint;
    private int fromSocial;
    private int gno;
    private int msleep;
    private int mail_check;
 
    // 구성자 설정
    public AuthMemberDTO(String username,
                         String password, int fromSocial
            , List<GrantedAuthority> authorities, String mname, Date birth, String telnum,
            String maddress, String mgender, String memail_info, Date moddate, Date regdate, int mpoint, int gno, int msleep, int mail_check) {
        // password는 부모클래스 사용
        super(username, password, authorities);
        this.memail = username;
        this.fromSocial = fromSocial;
        this.mpassword = password;

        this.mname = mname;
        this.birth = birth;
        this.telnum = telnum;
        this.maddress = maddress;
        this.mgender = mgender;
        this.memail_info = memail_info;
        this.moddate = moddate;
        this.mpoint = mpoint;
        this.regdate = regdate;
        this.gno = gno;
        this.msleep = msleep;
        this.mail_check = mail_check;
    }// end ClubAuthMemberDTO
}
