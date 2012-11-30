/**
 * @FileName  : ResourceNotFoundException.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Objects;

/**
 * 가져올 자원이 없을 시 해당 체크예외를 사용한다.
 *
 * @author bluepoet
 *
 */
public class ResourceNotFoundException extends GliderwikiException {

	private static final long serialVersionUID = 1L;

	private Class<?> resourceClass;
	private Serializable resourceId;

	public ResourceNotFoundException(Class<?> resourceClass, Serializable resourceId) {
		this(resourceClass, resourceId, generateDefaultMessage(resourceClass, resourceId));
	}

	public ResourceNotFoundException(Class<?> resourceClass, Serializable resourceId, String message) {
		this(resourceClass, resourceId, message, null);
	}

	public ResourceNotFoundException(Class<?> resourceClass, Serializable resourceId, Throwable cause) {
		this(resourceClass, resourceId, generateDefaultMessage(resourceClass, resourceId), cause);
	}

	public ResourceNotFoundException(Class<?> resourceClass, Serializable resourceId, String message, Throwable cause) {
		super(message, cause);
		this.resourceClass = resourceClass;
		this.resourceId = resourceId;
		setMessageCode("exception.resource_not_found.message");
		Object resourceValue = Objects.firstNonNull(resourceId, "지정되지 않은 값");
		setMessageArgs(String.valueOf(resourceValue));
		setLogLevel(LogLevel.DEBUG);
	}

	public Class<?> getResourceClass() {
		return resourceClass;
	}

	public Serializable getResourceId() {
		return resourceId;
	}

	private static String generateDefaultMessage(Class<?> resourceClass, Serializable resourceId) {
		return String.format("%s 클래스 - %s ID 데이터는 존재하지 않음.", resourceClass.getCanonicalName(), resourceId);
	}

	@Override
	public int getHttpStatusCode() {
		return HttpServletResponse.SC_NOT_FOUND;
	}
}