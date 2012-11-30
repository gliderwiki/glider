/**
 * @FileName  : FileUtil.java
 * @Project   : NightHawk
 * @Date      : 2012. 1. 14. 
 * @작성자      : @author jaeger

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * File Util
 *
 * @author jaeger
 */
public class FileUtil {

	public FileUtil() {
		// 생성자
	}

	public static List<String> fileToList(String fileName) {
        List<String> lines = new LinkedList<String>();
        String line = "";

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));

            while ((line = in.readLine()) != null) {
                lines.add(line + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       
        return lines;
    }

	public static String fileToText(String fileName) {
		StringBuilder lines = new StringBuilder();
		String line = "";

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));

			while ((line = in.readLine()) != null) {
				lines.append(line + "\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lines.toString();
	}

}
