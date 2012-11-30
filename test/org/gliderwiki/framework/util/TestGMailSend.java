package org.gliderwiki.framework.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGMailSend {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	static final String username = "glider.wiki@gmail.com"; // gmail 사용자
	static final String password = "2012wiki"; // 패스워드 7qXgar1JEeE3LjrWBBelwA==
	static final String SMTP_HOST_NAME = "smtp.gmail.com";
	static final String SMTP_PORT = "465";

	/**
	 * 받는 사람 목록(배열), 제목, 내용, 보내는 사람
	 * @param recipients
	 * @param subject
	 * @param message
	 * @param from
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public int postMail(String recipients[], String title , String message)	throws MessagingException,	IOException {

		int result = 0;
		
		// GMail 계정 및 Configuration Properties 설정
		Properties props = new Properties();
		props.put("mail.smtp.user", username); 
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		try {
			Authenticator auth = new SMTPAuthenticator();

			Session session = Session.getDefaultInstance(props, auth);
			session.setDebug(true);

			MimeMessage msg = new MimeMessage(session);
			String contents = message;			// 내용 
			msg.setSubject(title);			// 제목 
			
			Address fromAddr = new InternetAddress(username); // 보내는 사람의 메일주소
			msg.setFrom(fromAddr);
			
			Address[] toAddr = new InternetAddress[recipients.length];
			
			int count = 0;
			for (int i = 0; i < recipients.length; i++) {
				toAddr[i] = new InternetAddress(recipients[i]);				
				msg.addRecipient(Message.RecipientType.TO, toAddr[i]);
				count++;
			}
			msg.setContent(contents, "text/html; charset=EUC-KR");
			msg.setHeader("Content-Transfer-Encoding", "quoted-printable");
			System.out.println("##Message: " + msg.getContent());
			Transport.send(msg);
			
			
			logger.debug("##recipients.length : " + recipients.length);
			logger.debug("##count : " + count);
			
			if(recipients.length == count) {
				result = 1;
			} else {
				result = -1;			// 일부 전송이 되지 않았음 
			}
			
		} catch (IOException e) { 
			result = -3;
			System.out.println(e.getCause());
			e.printStackTrace();
		} catch (MessagingException e) {
			result = -4;
			System.out.println(e.getCause());
			e.printStackTrace();
		}

		return result;
	}

	private static class SMTPAuthenticator extends javax.mail.Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}

}