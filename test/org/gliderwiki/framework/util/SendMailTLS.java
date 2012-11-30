/**
 * @FileName  : SendMailTLS.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 21. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 

/**
 * @author yion
 *
 */
public class SendMailTLS {
	public static void main(String[] args) {
		 
		final String username = "glider.wiki@gmail.com";
		final String password = "2012wiki";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("glider.wiki@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("cafeciel@hanmail.net"));
			message.setSubject("TLS 전송  테스트 합니다.");
			message.setText("내용입니다. \n\n  한글이 보이는지 테스트 합니다.!");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
