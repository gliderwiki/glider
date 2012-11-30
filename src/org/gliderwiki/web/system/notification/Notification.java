/**
 * @FileName  : Notification.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 16.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.notification;

import java.io.Serializable;

/**
 * @author bluepoet
 *
 */
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	private String channel;
	private String message;

	public Notification() {

	}

	public Notification(String channel, String message) {
		this.channel = channel;
		this.message = message;
	}
}
