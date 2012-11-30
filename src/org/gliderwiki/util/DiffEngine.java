/**
 * @FileName  : DiffEngine.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 28.
 * @작성자      : @author jaeger

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import java.util.LinkedList;

import org.gliderwiki.util.DiffMatchPatch.Diff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * diff_match_patch 확장
 *
 * @author jaeger
 */
public class DiffEngine {

	private static Logger logger = LoggerFactory.getLogger(DiffEngine.class);


	/**
	 * 컨텐츠 내용 비교
	 *
	 * @param text1
	 * @param text2
	 * @return
	 */
	public LinkedList<Diff> diffMain(String text1, String text2) {
		DiffMatchPatch dmp = new DiffMatchPatch();

		LinkedList<Diff> diffs = dmp.diff_main(text1, text2);
		dmp.diff_cleanupSemantic(diffs);

		return diffs;
	}

	/**
	 * Convert a Diff list into a Insert HTML report.
	 *
	 * @param diffs : LinkedList of Diff objects.
	 * @return String : representation.
	 */
	public String diffInsertHtml(LinkedList<Diff> diffs) {
		StringBuffer insertList = new StringBuffer();

		for (Diff aDiff : diffs) {
			String text = aDiff.text.replace("&", "&amp;")
									.replace("<", "&lt;")
									.replace(">", "&gt;")
									.replace("\r\n", "<br />")
									.replace("\r", "<br />")
									.replace("\n", "<br />");

			switch (aDiff.operation) {
				case INSERT:
					insertList.append("<ins style=\"background:#e6ffe6;\">" + text + "</ins>");
					break;
				case EQUAL:
					insertList.append("<span>" + text + "</span>");
					break;
				default:
					break;
			}
		}

		return insertList.toString();
	}

	/**
	 * Convert a Diff list into a Insert HTML report.
	 *
	 * @param diffs : LinkedList of Diff objects.
	 * @return String : representation.
	 */
	public String diffDeleteHtml(LinkedList<Diff> diffs) {
		StringBuffer deleteList = new StringBuffer();

		for (Diff aDiff : diffs) {
			String text = aDiff.text.replace("&", "&amp;")
									.replace("<", "&lt;")
									.replace(">", "&gt;")
									.replace("\r\n", "<br />")
									.replace("\r", "<br />")
									.replace("\n", "<br />");

			switch (aDiff.operation) {
				case DELETE:
					deleteList.append("<del style=\"background:#ffe6e6;\">" + text + "</del>");
					break;
				case EQUAL:
					deleteList.append("<span>" + text + "</span>");
					break;
				default:
					break;
			}
		}

		return deleteList.toString();
	}

}
