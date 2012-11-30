package org.gliderwiki.web.system.argumentresolver;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 사용자 웹 객체를 컨트롤러의 인자로 받게 해준다.
 *
 * {@link LoginUserWebArgumentResolver}가 사용한다.
 *
 * @author bluepoet
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}