package com.hyundai.project.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.hyundai.project.dto.MemberJoinDTO;
import com.hyundai.project.service.MemberService;

@Component
public class MailService {
	@Autowired
	private MemberService service;
	
	@Async
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
			
			msg.setSubject("인증 부탁드립니다.", "UTF-8");
			
			
			String message = "<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "<div class=\"\">\r\n"
					+ "<div class=\"aHl\"></div>\r\n"
					+ "<div id=\":o5\" tabindex=\"-1\"></div>\r\n"
					+ "<div id=\":nu\" class=\"ii gt\" jslog=\"20277; u014N:xr6bB; 4:W251bGwsbnVsbCxbXV0.\">\r\n"
					+ "<div id=\":nt\" class=\"a3s aiL \"><u></u>\r\n"
					+ "<div>\r\n"
					+ "<table style=\"width:760px;padding:60px 20px 30px;margin:0 auto\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "    <tbody>\r\n"
					+ "        <tr>\r\n"
					+ "            <td>\r\n"
					+ "                <table style=\"width:760px;padding:0 0 5px;margin:0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                    <tbody>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td style=\"text-align:left;border:0;padding:0\">\r\n"
					+ "                                <a href=\"http://www.thehandsome.com\" style=\"border:0;padding:0;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com&amp;source=gmail&amp;ust=1656730271866000&amp;usg=AOvVaw1C0FI3UaD6ojXvMGyUv6F-\">\r\n"
					+ "                                <img src=\"http://www.thehandsome.com/_ui/desktop/common/images/email/logo.gif\" alt=\"THEHANDSOME.COM\" border=\"0\" class=\"CToWUd\">\r\n"
					+ "                                </a>\r\n"
					+ "                            </td>\r\n"
					+ "                            <td style=\"text-align:right;border:0;padding:0\">\r\n"
					+ "                                <a href=\"http://www.thehandsome.com/ko/member/login\" style=\"color:#363636;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-decoration:none;border:0;padding:0;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com/ko/member/login&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw3kS3K7uiaCqr6SOg6r8UIJ\">로그인</a>\r\n"
					+ "                                <span style=\"color:#e7e4d7;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                                <a href=\"http://www.thehandsome.com/ko/svcenter/submain\" style=\"color:#363636;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-decoration:none;border:0;padding:0;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com/ko/svcenter/submain&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw10deBODZP438R1Estn9Tor\">고객센터</a>\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                    </tbody>\r\n"
					+ "                </table>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#363636;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-align:right;border:0;border-top:1px solid #000;padding:10px 0 0\"> 2022-07-01 </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:26px;line-height:40px;font-family:Malgun Gothic,Dotum,sans-serif;font-weight:bold;text-align:center;border:0;padding:50px 0 0;margin:0\">이메일 인증 안내 메일입니다.</td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:16px;line-height:20px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;border:0;padding:15px 0 0\">\r\n"
					+ "            더한섬닷컴은 안전하고 편리한 서비스를 제공하기 위해 최선을 다합니다.\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"height:50px;border:0;padding:0;margin:0\"></td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"width:760px;height:50px;border:0;border-top:1px solid #e3e3e3;padding:0\">\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:12px;line-height:20px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;border:0;padding:30px 0 0\">\r\n"
					+ "            회원가입을 하기 위해 아래 이메일 인증하기 버튼을 클릭해주세요.\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"height:10px;border:0;padding:0;margin:0\"></td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"text-align:center;padding:20px 0 0\">\r\n"
					+ "                <a href=\"http://www.handsomeday.shop/member/simpleRegister/"+memail+" \" style=\"display:inline-block;color:#ffffff;font-size:14px;line-height:14px;font-family:Dotum,sans-serif;font-weight:bold;text-decoration:none;background-color:#474747;border:0;padding:14px 25px;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com/ko/member/authenticationMail?joincode%3DjD9GvPtEmsbgwNS8jxa1,yoolo0212@gmail.com&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw1eMl-WsmRTb0yx1T1c5Bne\">이메일 인증하기</a>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:12px;line-height:20px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;border:0;padding:10px 0 0\">\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"height:120px;border:0;padding:0;margin:0\"></td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td>\r\n"
					+ "                <table style=\"width:760px;background:#f5f5f5;padding:15px 30px 15px 20px;margin:0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                    <colgroup>\r\n"
					+ "                        <col style=\"width:10px\">\r\n"
					+ "                        <col style=\"width:700px\">\r\n"
					+ "                    </colgroup>\r\n"
					+ "                    <tbody>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td style=\"color:#999;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:top;border:0;padding:0\">ㆍ</td>\r\n"
					+ "                            <td style=\"color:#999;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;text-align:left;border:0;padding:0\">\r\n"
					+ "                            본 메일은 정보통신망 이용 촉진 및 정보 보호 등에 관한 법률에 근거하여 수신동의 여부와 관계없이 법적 의무 사항으로 발송 된 메일입니다.\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td style=\"color:#999;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:top;border:0;padding:0\">ㆍ</td>\r\n"
					+ "                            <td style=\"color:#999;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;border:0;padding:0\">\r\n"
					+ "                            본 메일은 발신 전용 메일이므로 회신 되지 않습니다. 문의사항은 고객센터를 이용해 주시길 바랍니다.\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td colspan=\"2\" style=\"text-align:right;border:0;padding:0\">\r\n"
					+ "                                <a href=\"http://www.thehandsome.com/ko/svcenter/submain\" style=\"color:#999;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;text-decoration:none;background-color:#fff;border:0;border:1px solid #e3e3e3;padding:5px 10px;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com/ko/svcenter/submain&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw10deBODZP438R1Estn9Tor\">고객센터</a>\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                    </tbody>\r\n"
					+ "                </table>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td>\r\n"
					+ "                <table style=\"width:760px;border-top:1px solid #e3e3e3;padding:0;margin:0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                    <colgroup>\r\n"
					+ "                        <col style=\"width:555px\">\r\n"
					+ "                        <col style=\"width:205px\">\r\n"
					+ "                    </colgroup>\r\n"
					+ "                    <tbody>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td colspan=\"2\" style=\"border:0;padding:30px 0 0 20px\">\r\n"
					+ "                                <img src=\"http://www.thehandsome.com/_ui/desktop/common/images/email/footer_logo.gif\" alt=\"THEHANDSOME.COM\" border=\"0\" class=\"CToWUd\">\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td style=\"color:#424242;font-size:10px;line-height:18px;font-family:Malgun Gothic,Dotum,sans-serif;font-weight:normal;border:0;padding:10px 0 0 20px\">\r\n"
					+ "                            (주) 한섬\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            대표이사 김민덕\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            서울시 강남구 도산대로 523 한섬빌딩\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            TEL 02.3416.2000\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            FAX 02.516.7028\r\n"
					+ "                            <br>\r\n"
					+ "                            사업자등록번호 120-81-26337\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            통신판매업신고번호 강남 제 00826호\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            개인정보보호책임자 윤인수\r\n"
					+ "                            </td>\r\n"
					+ "                            <td style=\"border:0;padding:10px 0 0 0;margin:0\">\r\n"
					+ "                                <a href=\"http://www.ftc.go.kr/info/bizinfo/communicationViewPopup.jsp?wrkr_no=1208126337\" style=\"color:#999;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;text-decoration:none;background-color:#fff;border:0;border:1px solid #e3e3e3;padding:5px 10px;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.ftc.go.kr/info/bizinfo/communicationViewPopup.jsp?wrkr_no%3D1208126337&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw337K3YIKGkOCIBeQESXBrR\">사업자정보확인</a>\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td colspan=\"2\" style=\"color:#363636;font-size:10px;line-height:10px;font-family:Malgun Gothic,sans-serif;font-weight:normal;text-align:left;letter-spacing:1px;border:0;padding:10px 0 0 20px;margin:0\">COPYRIGHT @ 2015 HANDSOME. ALL RIGHT RESERVED.</td>\r\n"
					+ "                        </tr>\r\n"
					+ "                    </tbody>\r\n"
					+ "                </table>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "    </tbody>\r\n"
					+ "</table>\r\n"
					+ "<div class=\"yj6qo\"></div>\r\n"
					+ "<div class=\"adL\"></div>\r\n"
					+ "</div>\r\n"
					+ "<div class=\"adL\"></div>\r\n"
					+ "</div>\r\n"
					+ "</div>\r\n"
					+ "<div id=\":o9\" class=\"ii gt\" style=\"display:none\">\r\n"
					+ "<div id=\":oa\" class=\"a3s aiL \"></div>\r\n"
					+ "</div>\r\n"
					+ "<div class=\"hi\"></div>\r\n"
					+ "</div>	\r\n"
					+ "</body>\r\n"
					+ "</html>";
			
			msg.setText(message, "UTF-8", "html");
			
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
	
	@Async
	public void noticeMailSend(String title, String content, String url) throws Exception {
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
            
			List<MemberJoinDTO> member = new ArrayList<>();
			member = service.mailMember();
			InternetAddress[] toAddr = new InternetAddress[member.size()]; 
			for(int i=0; i<member.size(); i++) {
				toAddr[i] = new InternetAddress(member.get(i).getMemail());
			}
			
			// 수신자 설정 
			msg.setRecipients(Message.RecipientType.TO, toAddr);
			
			msg.setSubject(title, "UTF-8");
			System.out.println(title);
			System.out.println(content);
			
			String message = "<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "<div class=\"\">\r\n"
					+ "<div class=\"aHl\"></div>\r\n"
					+ "<div id=\":o5\" tabindex=\"-1\"></div>\r\n"
					+ "<div id=\":nu\" class=\"ii gt\" jslog=\"20277; u014N:xr6bB; 4:W251bGwsbnVsbCxbXV0.\">\r\n"
					+ "<div id=\":nt\" class=\"a3s aiL \"><u></u>\r\n"
					+ "<div>\r\n"
					+ "<table style=\"width:760px;padding:60px 20px 30px;margin:0 auto\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "    <tbody>\r\n"
					+ "        <tr>\r\n"
					+ "            <td>\r\n"
					+ "                <table style=\"width:760px;padding:0 0 5px;margin:0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                    <tbody>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td style=\"text-align:left;border:0;padding:0\">\r\n"
					+ "                                <a href=\"http://www.thehandsome.com\" style=\"border:0;padding:0;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com&amp;source=gmail&amp;ust=1656730271866000&amp;usg=AOvVaw1C0FI3UaD6ojXvMGyUv6F-\">\r\n"
					+ "                                <img src=\"http://www.thehandsome.com/_ui/desktop/common/images/email/logo.gif\" alt=\"THEHANDSOME.COM\" border=\"0\" class=\"CToWUd\">\r\n"
					+ "                                </a>\r\n"
					+ "                            </td>\r\n"
					+ "                            <td style=\"text-align:right;border:0;padding:0\">\r\n"
					+ "                                <a href=\"http://www.thehandsome.com/ko/member/login\" style=\"color:#363636;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-decoration:none;border:0;padding:0;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com/ko/member/login&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw3kS3K7uiaCqr6SOg6r8UIJ\">로그인</a>\r\n"
					+ "                                <span style=\"color:#e7e4d7;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                                <a href=\"http://www.thehandsome.com/ko/svcenter/submain\" style=\"color:#363636;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-decoration:none;border:0;padding:0;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com/ko/svcenter/submain&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw10deBODZP438R1Estn9Tor\">고객센터</a>\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                    </tbody>\r\n"
					+ "                </table>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#363636;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-align:right;border:0;border-top:1px solid #000;padding:10px 0 0\"> 2022-07-01 </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:26px;line-height:40px;font-family:Malgun Gothic,Dotum,sans-serif;font-weight:bold;text-align:center;border:0;padding:50px 0 0;margin:0\">스트리밍 방송 안내 메일입니다.</td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:16px;line-height:20px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;border:0;padding:15px 0 0\">\r\n"
					+ "            더한섬닷컴은 안전하고 편리한 서비스를 제공하기 위해 최선을 다합니다.\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"height:50px;border:0;padding:0;margin:0\"></td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"width:760px;height:50px;border:0;border-top:1px solid #e3e3e3;padding:0\">\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:12px;line-height:20px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;border:0;padding:30px 0 0\">\r\n"
					+ content + "\r\n"
					+ "            <img src=\" "+ url+" \" width=\"1000\" height=\"500\" alt=\"THEHANDSOME.COM\" border=\"0\" class=\"CToWUd\">\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"height:10px;border:0;padding:0;margin:0\"></td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"text-align:center;padding:20px 0 0\">\r\n"
					+ "                <a href=\"http://www.handsomeday.shop/mobile\" style=\"display:inline-block;color:#ffffff;font-size:14px;line-height:14px;font-family:Dotum,sans-serif;font-weight:bold;text-decoration:none;background-color:#474747;border:0;padding:14px 25px;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.thehandsome.com/ko/member/authenticationMail?joincode%3DjD9GvPtEmsbgwNS8jxa1,yoolo0212@gmail.com&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw1eMl-WsmRTb0yx1T1c5Bne\">방송 시청하기</a>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"color:#222;font-size:12px;line-height:20px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;border:0;padding:10px 0 0\">\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td style=\"height:120px;border:0;padding:0;margin:0\"></td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td>\r\n"
					+ "                <table style=\"width:760px;background:#f5f5f5;padding:15px 30px 15px 20px;margin:0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                    <colgroup>\r\n"
					+ "                        <col style=\"width:10px\">\r\n"
					+ "                        <col style=\"width:700px\">\r\n"
					+ "                    </colgroup>\r\n"
					+ "                </table>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "        <tr>\r\n"
					+ "            <td>\r\n"
					+ "                <table style=\"width:760px;border-top:1px solid #e3e3e3;padding:0;margin:0\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
					+ "                    <colgroup>\r\n"
					+ "                        <col style=\"width:555px\">\r\n"
					+ "                        <col style=\"width:205px\">\r\n"
					+ "                    </colgroup>\r\n"
					+ "                    <tbody>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td colspan=\"2\" style=\"border:0;padding:30px 0 0 20px\">\r\n"
					+ "                                <img src=\"http://www.thehandsome.com/_ui/desktop/common/images/email/footer_logo.gif\" alt=\"THEHANDSOME.COM\" border=\"0\" class=\"CToWUd\">\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td style=\"color:#424242;font-size:10px;line-height:18px;font-family:Malgun Gothic,Dotum,sans-serif;font-weight:normal;border:0;padding:10px 0 0 20px\">\r\n"
					+ "                            (주) 한섬\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            대표이사 김민덕\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            서울시 강남구 도산대로 523 한섬빌딩\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            TEL 02.3416.2000\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            FAX 02.516.7028\r\n"
					+ "                            <br>\r\n"
					+ "                            사업자등록번호 120-81-26337\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            통신판매업신고번호 강남 제 00826호\r\n"
					+ "                            <span style=\"color:#e7e4d7;font-size:11px;line-height:18px;font-family:Dotum,sans-serif;font-weight:normal;vertical-align:middle;border:0;padding:0;margin:0\">│</span>\r\n"
					+ "                            개인정보보호책임자 윤인수\r\n"
					+ "                            </td>\r\n"
					+ "                            <td style=\"border:0;padding:10px 0 0 0;margin:0\">\r\n"
					+ "                                <a href=\"http://www.ftc.go.kr/info/bizinfo/communicationViewPopup.jsp?wrkr_no=1208126337\" style=\"color:#999;font-size:11px;line-height:11px;font-family:Dotum,sans-serif;font-weight:normal;text-align:center;text-decoration:none;background-color:#fff;border:0;border:1px solid #e3e3e3;padding:5px 10px;margin:0\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://www.ftc.go.kr/info/bizinfo/communicationViewPopup.jsp?wrkr_no%3D1208126337&amp;source=gmail&amp;ust=1656730271867000&amp;usg=AOvVaw337K3YIKGkOCIBeQESXBrR\">사업자정보확인</a>\r\n"
					+ "                            </td>\r\n"
					+ "                        </tr>\r\n"
					+ "                        <tr>\r\n"
					+ "                            <td colspan=\"2\" style=\"color:#363636;font-size:10px;line-height:10px;font-family:Malgun Gothic,sans-serif;font-weight:normal;text-align:left;letter-spacing:1px;border:0;padding:10px 0 0 20px;margin:0\">COPYRIGHT @ 2015 HANDSOME. ALL RIGHT RESERVED.</td>\r\n"
					+ "                        </tr>\r\n"
					+ "                    </tbody>\r\n"
					+ "                </table>\r\n"
					+ "            </td>\r\n"
					+ "        </tr>\r\n"
					+ "    </tbody>\r\n"
					+ "</table>\r\n"
					+ "<div class=\"yj6qo\"></div>\r\n"
					+ "<div class=\"adL\"></div>\r\n"
					+ "</div>\r\n"
					+ "<div class=\"adL\"></div>\r\n"
					+ "</div>\r\n"
					+ "</div>\r\n"
					+ "<div id=\":o9\" class=\"ii gt\" style=\"display:none\">\r\n"
					+ "<div id=\":oa\" class=\"a3s aiL \"></div>\r\n"
					+ "</div>\r\n"
					+ "<div class=\"hi\"></div>\r\n"
					+ "</div>	\r\n"
					+ "</body>\r\n"
					+ "</html>";
			
			msg.setText(message, "UTF-8", "html");
			
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

