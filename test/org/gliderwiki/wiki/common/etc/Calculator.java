/**
 * @FileName  : Calculator.java
 * @Project   : NightHawk
 * @Date      : 2012. 3. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.common.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author bluepoet
 *
 */
public class Calculator {

	public int sum() {
		Operation oper = new Operation() {

			@Override
			public int exec(int result, String s) {
				result += Integer.valueOf(s);
				return result;
			}
		};

		return templateLine(oper)-1;
	}

	public int multi() {
		Operation oper = new Operation() {

			@Override
			public int exec(int result, String s) {
				result *= Integer.valueOf(s);
				return result;
			}
		};

		return templateLine(oper);
	}

	public int templateLine(Operation oper) {
		String[] arrList = new String[4];
		int result = 1;

		try {
			URL url = new URL("http://bluepoet.dothome.co.kr/test.html");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			String str = "";

			while((line = br.readLine()) != null) {
				str += line;
			}

			arrList = str.split("<br>");

			for(String s : arrList) {
				result = oper.exec(result, s);
			}

			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
