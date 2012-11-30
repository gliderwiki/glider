/**
 * @FileName  : TestService.java
 * @Project   : NightHawk
 * @Date      : 2012. 2. 20.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.common.etc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author bluepoet
 *
 */
@Component
public class TestService {
	@Autowired
	TestDao testDao;



}
