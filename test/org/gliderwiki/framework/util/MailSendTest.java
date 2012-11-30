/**
 * @FileName  : mailTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 17. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.util;

import javax.mail.MessagingException;


/**
 * @author yion
 *
 */
public class MailSendTest {

	public static void main(String[] args) throws MessagingException {
		String[] emailList = { "cafeciel@hanmail.net" };// 메일 보낼사람 리스트  , "cafeciel@naver.com"
		String emailMsgTxt = "메일 테스트 내용 text html 12345 <BR> \n test test <BR><BR>"; // 내용
		String emailSubject = "제목임 잘가는지 테스트 중1~~~~~~~~~~";// 제목

		int result = 0;
		TestGMailSend sender = new TestGMailSend();
		try {
			result = sender.postMail(emailList, emailSubject, emailMsgTxt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("##### 메일 전송 결과 : " + result);
	}
}
