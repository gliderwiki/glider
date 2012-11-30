/**
 * @FileName  : MailSender.java
 * @Project   : GLider Wiki
 * @Date      : 2012-04-02
 * @작성자      : @author Yion

 * @변경이력    : 
 * @프로그램 설명 : 로그인 회원에 대한 메일 인증을 처리한다. 
 */

package org.gliderwiki.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
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
import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SendMailSMTP {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	/**
	 * 지정된 메일 주소로 메일을 전송한다.  
	 * @param recipients
	 * @param subject
	 * @param message
	 * @param request
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public int sendSimpleMail(String toAddr, String title, String message, HttpServletRequest request) throws MessagingException, IOException {
		
		String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
		Properties property = PropertyUtil.getMailPropertyInfo(svcPath);
		
		String mailUserId 		= property.getProperty("mail.user.id");
		String mailUserPassword = property.getProperty("mail.user.password");
		String mailUserKey 		= property.getProperty("mail.user.key");
		String smtpHostName 	= property.getProperty("smtp.host.name");
		String smtpServerPort   = property.getProperty("smtp.server.port");
		String mailCharset 		= property.getProperty("mail.charset");
		String siteDomain	 	= property.getProperty("mail.site.domain");
		String siteOwner	 	= property.getProperty("mail.site.owner");
		
		logger.debug("################################");
		logger.debug("###mailUserPassword : " + mailUserPassword);
		logger.debug(" mailUserId : " + mailUserId );
		logger.debug(" mailUserPassword : " + mailUserPassword );
		logger.debug(" mailUserKey : " + mailUserKey );
		logger.debug(" smtpHostName : " + smtpHostName );
		logger.debug(" smtpServerPort : " + smtpServerPort );
		logger.debug(" mailCharset : " + mailCharset );
		logger.debug(" siteDomain : " + siteDomain );
		logger.debug(" siteOwner : " + siteOwner );
		String port = "587";
		int result = 0;
		String password = SecretKeyPBECipher.getUserPassword(mailUserPassword, mailUserKey);
		
		logger.debug("### 변환 password : " + password);
		
		
		result = this.sendMailTLS(smtpHostName, port, mailUserId, password, toAddr, title, message, mailCharset);
		logger.debug("### TLS 결과  : " + result);
		
		if(result == -2) {
			// 실패 할 경우 한번 더 SSL 방식으로 전송해본다. 
			result = this.sendMailSSL(smtpHostName, smtpServerPort, mailUserId, password, toAddr, title, message, mailCharset);
			logger.debug("### TLS 결과  : " + result);
		}
		
		return result;
	}

	
	public int sendMailSSL(String host, String port, final String hostMailId, final String hostPassword, 
												String targetId, String title, String content, String charset) {
		logger.debug("### START SSL ###");
		logger.debug("### host ### : " + host);
		logger.debug("### port ### : " + port);
		logger.debug("### hostMailId ### : " + hostMailId);
		logger.debug("### hostPassword ### : " + hostPassword);
		logger.debug("### targetId ### : " + targetId);
		logger.debug("### title ### : " + title);
		logger.debug("### content ### : " + content);
		logger.debug("### charset ### : " + charset);
		
		
		int result = 0;
		final String username = hostMailId;
		final String password =  hostPassword;
		
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.port", port);
 
		logger.debug("### START SSL session ###");
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			}
		);

		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetId));
			message.setSubject(title);
			message.setText(content);
			message.setContent(content, "text/html; charset="+charset);
			message.setHeader("Content-Transfer-Encoding", "quoted-printable");
			logger.debug("### SSL transport Message start!!! ###");
			Transport.send(message);
			logger.debug("### SSL transport Message end!!! ###");
			
 			result = 1;
 
		} catch (MessagingException e) {
			logger.debug("### SSL ERROR ###");
			result = -1;
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
	
	
	public int sendMailTLS (String host, String port, String hostMailId, String hostPassword, 
												String targetId, String title, String content, String charset)  {
		
		logger.debug("### START TLS ###");
		logger.debug("### host ### : " + host);
		logger.debug("### port ### : " + port);
		logger.debug("### hostMailId ### : " + hostMailId);
		logger.debug("### hostPassword ### : " + hostPassword);
		logger.debug("### targetId ### : " + targetId);
		logger.debug("### title ### : " + title);
		logger.debug("### content ### : " + content);
		logger.debug("### charset ### : " + charset);
		
		int result = 0;
		final String username = hostMailId;
		final String password =  hostPassword;
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.port", port);
 
		logger.debug("### START TLS session ###");
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			}
		);
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetId));
			message.setSubject(title);
			message.setText(content);
			message.setContent(content, "text/html; charset="+charset);
			message.setHeader("Content-Transfer-Encoding", "quoted-printable");
			
			logger.debug("### TLS transport Message start!!! ###");
			Transport.send(message);
			logger.debug("### TLS transport Message end!!! ###");
			result = 2;
			 
		} catch (MessagingException e) {
			logger.debug("### TLS ERROR ###");
			result = -2;
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
	

	/**
	 * url ping test 
	 * @param url 
	 * @param timeout (mill sec)
	 * @return
	 * @throws Exception
	 */
	public boolean pingCheck(String url, int timeout) throws Exception {
		
		InetAddress target = InetAddress.getByName(url);
		return target.isReachable(timeout);
	}

}