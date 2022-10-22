package admin.mail;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Sendmail {
	public static String semail;
	
    public void Sendmaill() throws MessagingException{
    	System.out.println(semail);
        // 메일 관련 정보
        String host = "smtp.naver.com";
        final String username = "icet25"; //@naver.com 제외한 아이디만
        final String password = "tjdcjf11"; //본인 메일 비밀번호
        int port=465;
         
        // 메일 내용
        String recipient = semail;
        String subject = "영만블에서 보냅니다!!";
        String body = "경고장을 보냅니다다다다다!";
         
        Properties props = System.getProperties();
         
         
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
          
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=username;
            String pw=password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
        session.setDebug(true); //for debug
          
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("icet25@naver.com"));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(body);
        Transport.send(mimeMessage);
    }
}