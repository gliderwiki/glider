/**
 * @FileName  : NotificationComponent.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 16.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.notification;

import javax.annotation.Resource;

import org.gliderwiki.framework.util.Base64Coder;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.framework.util.SimpleAesStringCipherManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


/**
 * redis서버로 알림메세지를 전송한다(publish)
 * @author bluepoet
 *
 */
@Component
public class NotificationComponent {
	Logger logger = LoggerFactory.getLogger(NotificationComponent.class);

	@Value("#{config['alarm.key']}")
	String alarmKey;

	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private SimpleAesStringCipherManager simpleAesStringCipherManager;

	@Async
	public void sendNotification(Integer userIdx, String message) throws Throwable {
		try {
			logger.debug("redis call!!!");
			redisTemplate.convertAndSend(simpleAesStringCipherManager.encrypt(String.format("A_%d", userIdx)), message);
			logger.debug("alarm send success!!");
		} catch (Exception e) {
			logger.debug("alarm send fail!!");
		}
	}
}
