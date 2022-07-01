package com.hyundai.project.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailService {		

	public void welcomeMailSend(String memail) {
		// 메일 설정 정보
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");	
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");// TLS 포트번호= 587, SSL 포트번호= 465
		
		String mail_id = "kkp6249@gmail.com";
		String mail_pw = "suaszxmastpohqrd";
		
		// 구글 계정 인증용 ID/PW 세팅
		Authenticator auth = new MailAuth(mail_id, mail_pw);
		// 세션 및 메세지 생성 (프로퍼티, 인증)
		Session session = Session.getInstance(prop, auth);
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress("hyundai@gmail.com", "한섬"));
            
			// 수신자 설정 
			InternetAddress to = new InternetAddress(memail);
			msg.setRecipient(Message.RecipientType.TO, to);
			
			msg.setSubject("환영합니다.", "UTF-8");
			msg.setText("가입을 축하한다.", "UTF-8");
			
            // 메일 발송
			Transport.send(msg);

		} catch (AddressException ae) {
			System.out.println("AddressException : " + ae.getMessage());
		} catch (MessagingException me) {
			System.out.println("MessagingException : " + me.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println("UnsupportedEncodingException : " + e.getMessage());
		}
	}
}

