package org.gliderwiki.wiki.common.etc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * /**
 *
 * @FileName : CalculatorTEst.java
 * @Project : NightHawk
 * @Date : 2012. 3. 5.
 * @작성자 : @author bluepoet
 *
 * @변경이력 :
 * @프로그램 설명 :
 */

public class CalculatorTest {
	Calculator calculator;

	@Before
	public void setup() {
		calculator = new Calculator();
	}

	@Test
	public void sumTest() {
		assertThat(calculator.sum(), is(18));
	}

	@Test
	public void multiTest() {
		assertThat(calculator.multi(), is(360));
	}
}
