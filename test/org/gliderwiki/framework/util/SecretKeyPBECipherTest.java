/**
 * @FileName  : SecretKeyPBECipherTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 7. 
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.util;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.gliderwiki.framework.util.Base64Coder;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.web.domain.WeSpaceAuthority;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.login.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author bluepoet
 * 
 */
public class SecretKeyPBECipherTest {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private WeUser weUser;

	@Before
	public void setup() {
		weUser = new WeUser("bluepoet1112@gmail.com", "kimyong12");
	}

	@Test
	public void getEncryptPasswordTest() throws Throwable {
		System.out.println("암호화된 패스워드 확인 : " + getEncryptPassword(weUser, "kimyong12"));
	}

	private String getEncryptPassword(WeUser weUser, String passKey) throws Throwable {
		SecretKeyPBECipher.initiate(passKey);

		// 넘어온 비밀번호를 DB에서 읽어온 Key로 암호화
		byte[] data;
		data = SecretKeyPBECipher.encrypt(weUser.getWe_user_pwd());
		String password = Base64Coder.encodeString(data);
		return password;
	}
}