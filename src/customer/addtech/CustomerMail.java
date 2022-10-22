package customer.addtech;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CustomerMail {
	
	public static String mysubject;
	public static String mycontents;
	
	
	
	public void sendCustomerEmail() throws AddressException, MessagingException  {
		System.out.println("메일발송시작");
		
		String host = "smtp.naver.com";
		final String username = "icet25";
		final String password = "tjdcjf11";
		int port = 465;
		
		
		String recipient = "icet25@naver.com";
		String subject = mysubject;
		String body = mycontents;
		
		Properties props = System.getProperties();
		
		
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true);
		
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("icet25@naver.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		Transport.send(mimeMessage);
		
	}
	
	
	
	
	
	
	
	
}
