/**
 * @FileName  : GliderTagParserMethod.java
 * @Project   : NightHawk
 * @Date      : 2012. 3. 31. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ganji
 *
 */
public class GliderTagParserMethodTest {
	
	// 링크
	public List<Object> linkTagList = new ArrayList<Object>();
	
	// h1
	public List<Object> h1TagList = new ArrayList<Object>();
	
	// h2
	public List<Object> h2TagList = new ArrayList<Object>();
	
	// h3
	public List<Object> h3TagList = new ArrayList<Object>();
	
	// 각주
	public List<Object> noteTagList = new ArrayList<Object>();
	
	// 각 list에 담을 해쉬맵
	public Map<String, Object> tagMap = new HashMap<String, Object>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List EsCharacterList(){
			
		List list = new ArrayList();
		list.add(EsCharacter.BOLD);
		list.add(EsCharacter.ITALIC);
		list.add(EsCharacter.UNDERLINING);
		list.add(EsCharacter.MIX);
		list.add(EsCharacter.SUBERSCRIPT);
		list.add(EsCharacter.SUPERSCRIPT);
		list.add(EsCharacter.STRIKE);
		list.add(EsCharacter.HIPERLINK);
		list.add(EsCharacter.IMG);
		list.add(EsCharacter.H1);
		list.add(EsCharacter.NOTE);
		list.add(EsCharacter.ALERT);
		list.add(EsCharacter.FIELD);
		list.add(EsCharacter.INFO);
		list.add(EsCharacter.SYNTAX);
		list.add(EsCharacter.LINE1);
		list.add(EsCharacter.LINE2);
		list.add(EsCharacter.TABLE);
		list.add(EsCharacter.GRAPH);
		list.add(EsCharacter.BACKGROUND);
		list.add(EsCharacter.COLOR);
		
		return list;
	}

	public String getHtml(String str, EsCharacter ch){
		String result = null;
		String temp = null;
		String patternTxt = null;
		
		Pattern pattern = null;
		Matcher match = null;
		
		switch (ch) {
		
		case BOLD:
			pattern = Pattern.compile("\\*{2}([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?\\*{2}");
			match = pattern.matcher(str);
			result = match.replaceAll("<B>$1</B>");
			break;
			
		case ITALIC:
			pattern = Pattern.compile("\\/{2}([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?\\/{2}");
			match = pattern.matcher(str);
			result = match.replaceAll("<I>$1</I>");
			break;
			
		case UNDERLINING:
			pattern = Pattern.compile("\\_{2}([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?\\_{2}");
			match = pattern.matcher(str);
			result = match.replaceAll("<U>$1</U>");
			break;
			
		case MIX:
			pattern = Pattern.compile("\\*{2}_{2}/{2}([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?\\*{2}_{2}/{2}");
			match = pattern.matcher(str);
			result = match.replaceAll("<B><U><I>$1</B></U></I>");
			break;
			
		case STRIKE:
			pattern = Pattern.compile("<sub>([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?<sub>");
			match = pattern.matcher(str);
			result = match.replaceAll("<del>$1</del>");
			break;
			
		case SUPERSCRIPT:
			pattern = Pattern.compile("<sup>([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?<sup>");
			match = pattern.matcher(str);
			result = match.replaceAll("<sup>$1</sup>");
			break;
			
		case SUBERSCRIPT:
			pattern = Pattern.compile("<del>([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?<del>");
			match = pattern.matcher(str);
			result = match.replaceAll("<sub>$1</sub>");
			break;
			
		case HIPERLINK:
			patternTxt = "\\[(@:)([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)\\|([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)?\\]";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);
			
			while (match.find()) {
				tagMap = new HashMap<String, Object>();
				
				// 위키태그를 파싱하여 html태그로 만든다.
				str = match.replaceFirst("<a href=\"$2\">$3</a>");
				
				// 위키태그중에 따로 사용할 태그를 발라서 처리한다.
				temp = replaceFirstTag(match.group(), patternTxt, "$2");
				tagMap.put("tagUrl", temp);
				temp = replaceFirstTag(match.group(), patternTxt, "$3");
				tagMap.put("tagTitle", temp);				
				linkTagList.add(tagMap);
				
				match = pattern.matcher(str);
			}
			
			patternTxt = "\\[(@:)([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)\\]";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);
			tagMap = new HashMap<String, Object>();
			
			while (match.find()) {
				// 위키태그를 파싱하여 html태그로 만든다.
				str = match.replaceFirst("<a href=\"$2\">$2</a>");
				
				// 위키태그중에 따로 사용할 태그를 발라서 처리한다.
				temp = replaceFirstTag(match.group(), patternTxt, "$2");
				tagMap.put("tagUrl", temp);
				temp = replaceFirstTag(match.group(), patternTxt, "$2");
				tagMap.put("tagTitle", temp);				
				linkTagList.add(tagMap);
				
				match = pattern.matcher(str);
			}			
			
			result = str;
			break;
			
		case IMG:
			pattern = Pattern.compile("\\[(!\\|)([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)\\|([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)?\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<img alt=\"$3\" src=\"$2\">");
			break;

		case H1:
			String[] splStr = str.split("\r\n");
			str = "";
			Map<String, String> hTagMap = null;
			for( int i=0; i< splStr.length; i++ ){
				
				hTagMap = new HashMap<String, String>();
				
				patternTxt = "(h1\\.(\\s{0,}))([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)";
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(splStr[i]);
				while ( match.find() ) {
					splStr[i] = match.replaceFirst("<h2><a id=\"$3\" name=\"$3\">$3</a></h2>");
					hTagMap.put("tag", "h1");
					hTagMap.put("tagVal", replaceFirstTag(match.group(), patternTxt, "$3"));
					h1TagList.add(hTagMap);
				}
				
				patternTxt = "(h2\\.(\\s{0,}))([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)";
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(splStr[i]);
				while ( match.find() ) {
					splStr[i] = match.replaceFirst("<h3><a id=\"$3\" name=\"$3\">$3</a></h3>");
					hTagMap.put("tag", "h2");
					hTagMap.put("tagVal", replaceFirstTag(match.group(), patternTxt, "$3"));
					h1TagList.add(hTagMap);
				}
				
				patternTxt = "(h3\\.(\\s{0,}))([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)";
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(splStr[i]);
				while ( match.find() ) {
					splStr[i] = match.replaceFirst("<h4><a id=\"$3\" name=\"$3\">$3</a></h4>");
					hTagMap.put("tag", "h3");
					hTagMap.put("tagVal", replaceFirstTag(match.group(), patternTxt, "$3"));
					h1TagList.add(hTagMap);
				}
				str += splStr[i]+"\r\n";
			}
			result = str;
			break;
		
		case NOTE:
			patternTxt = "\\[(note\\|)(.*?)#(.*?)\\]";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);
			temp = null;
			Integer cnt = 0;
			Map<String, String> noteTagMap = null;
			while (match.find()) {
				cnt = cnt+1;
				noteTagMap = new HashMap<String, String>();
				// 위키태그를 파싱하여 html태그로 만든다.
				str = match.replaceFirst("$2<a href=\"#note-"+cnt+"\"><sup id=\"note-sub-"+cnt+"\">"+cnt+"</sup></a>");
				
				// 위키태그중에 따로 사용할 태그를 발라서 처리한다.
				temp = replaceFirstTag(match.group(), patternTxt, "$2");
				noteTagMap.put("tag", temp);
				temp = replaceFirstTag(match.group(), patternTxt, "$3");
				noteTagMap.put("tagVal", temp);
				
				noteTagList.add(noteTagMap);
				
				match = pattern.matcher(str);
			}
			result = str;
			break;

		case ALERT:
			pattern = Pattern.compile("(\\[alert\\])(.*?)(\\[alert\\])");
			match = pattern.matcher(str);
			result = match.replaceAll("<div class=\"box-alert\">$2</div>");
			break;
			
		case INFO:
			pattern = Pattern.compile("(\\[info\\])(.*?)(\\[info\\])");
			match = pattern.matcher(str);
			result = match.replaceAll("<div class=\"box-check\">$2</div>");
			break;
			
		case FIELD:
			pattern = Pattern.compile("(\\[field\\])(.*?)(\\[field\\])");
			match = pattern.matcher(str);
			result = match.replaceAll("<div style=\"border:1px dashed; padding:10px;\">$2</div>");
			
		case SYNTAX:
			pattern = Pattern.compile("(\\[syntax\\])(.*?)(\\[syntax\\])");
			match = pattern.matcher(str);
			result = match.replaceAll("<script type=\"syntaxhighlighter\" class=\"brush: js\">$2</script>");
			break;
			
		case LINE1:
			result = linePaser(str, "#");
			break;

		case LINE2:
			result = linePaser(str, "-");
			break;

		case TABLE:
			String[] strAll = str.split("\r\n");
			str = "";
			boolean matchFind = false;
			int table_cnt = 0;
			String ret = "";
			
			for( int i=0; i<strAll.length; i++ ){
				matchFind = false;
				// 제목 파싱
				pattern = Pattern.compile("((\\|\\|)+)(.*)((\\|\\|)+)");
				match = pattern.matcher(strAll[i]);
				if( match.find() ){
					ret = "";
					strAll[i] = match.replaceAll("\r\n<tr>\r\n$2$3$4\r\n</tr>\r\n");
					
					pattern = Pattern.compile("(?<=\\|\\|)(?!(col:[\\w]+#)|(row:[\\w]+#))(.*?)(?=\\|\\|)");
					match = pattern.matcher(strAll[i]);

					
					if( match.find() && table_cnt == 0){
						ret += "<table>\r\n";
						table_cnt++;
					}
					ret += match.replaceAll("\r\n<th class=\"\">$3</th>");
					
					pattern = Pattern.compile("(?<=\\|\\|)(col:(\\w+)#)(.*?)(?=\\|\\|)");
					match = pattern.matcher(ret);
					ret = match.replaceAll("\r\n<td colspan=\"$2\">$3</td>");
					
					pattern = Pattern.compile("(?<=\\|\\|)(row:(\\w+)#)(.*?)(?=\\|\\|)");
					match = pattern.matcher(ret);
					ret = match.replaceAll("\r\n<td rowspan=\"$2\">$3</td>");
					
					pattern = Pattern.compile("(\\|\\|)?(.*?)(\\|\\|)?");
					match = pattern.matcher(ret);
					ret = match.replaceAll("$2");
					strAll[i] = ret;
					matchFind = true;
				}else{
					ret = strAll[i];
				}
				
				// 내용 파싱
				pattern = Pattern.compile("((\\|)+)(.*)((\\|)+)");
				match = pattern.matcher(strAll[i]);
				
				if( match.find() ){
					ret = "";
					strAll[i] = match.replaceAll("\r\n<tr>\r\n$2$3$4\r\n</tr>\r\n");
					
					pattern = Pattern.compile("(?<=\\|)(?!(col:[\\w]+#)|(row:[\\w]+#))(.*?)(?=\\|)");
					match = pattern.matcher(strAll[i]);

					if( match.find() && table_cnt == 0){
						ret += "<table>\r\n";
						table_cnt++;
					}
					ret += match.replaceAll("\r\n<td class=\"\">$3</td>");
					
					pattern = Pattern.compile("(?<=\\|)(col:(\\w+)#)(.*?)(?=\\|)");
					match = pattern.matcher(ret);
					ret = match.replaceAll("\r\n<td colspan=\"$2\">$3</td>");
					
					pattern = Pattern.compile("(?<=\\|)(row:(\\w+)#)(.*?)(?=\\|)");
					match = pattern.matcher(ret);
					ret = match.replaceAll("\r\n<td rowspan=\"$2\">$3</td>");
					
					pattern = Pattern.compile("(\\|)?(.*?)(\\|)?");
					match = pattern.matcher(ret);
					ret = match.replaceAll("$2");
					matchFind = true;
				}
				
				if( strAll.length == (i+1) && table_cnt != 0 ){
					ret += "</table>\r\n";
					table_cnt = 0;
				}else if( matchFind == false && table_cnt != 0 ){
					ret = "</table>\r\n"+ret;
					table_cnt = 0;
				}
				str += ret+"\r\n";
				
			}
			result = str;
			break;
			
		case GRAPH:
			String graph_pattern = "\\[%\\](.*[\r\n])*\\[%\\]";
			pattern = Pattern.compile(graph_pattern);
			match = pattern.matcher(str);
			
			String pieTag = "";
			String pieDiv = "";
			String barTag = "";
			String barDiv = "";
			
			int graph_cnt = 0;
			while ( match.find() ) {
				
				graph_cnt++;
				// pieTag 검색 및 치환
				String graphTemp = match.group();
				pattern = Pattern.compile("((?<=pie\\[)(.*)(?=\\]))");
				match = pattern.matcher(graphTemp);
				if( match.find() ){
					pieTag = match.group();
					pieDiv = "<div id='"+pieTag+"'></div>";
					pieTag = "&#36;(\"#"+pieTag+"\").pieChart(data"+graph_cnt+",300,\"pie\");";
					pattern = Pattern.compile("(pie\\[)(.*)(\\])");
					match = pattern.matcher(graphTemp);	
					graphTemp = match.replaceFirst("");
				}
				// barTag 검색 및 치환
				pattern = Pattern.compile("((?<=bar\\[)(.*)(?=\\]))");
				match = pattern.matcher(graphTemp);
				if( match.find() ){
					barTag = match.group();
					barDiv = "<div id='"+barTag+"'></div>";
					barTag = "&#36;(\"#"+barTag+"\").barChart(data"+graph_cnt+",300,\"bar\");";
					pattern = Pattern.compile("(bar\\[)(.*)(\\])");
					match = pattern.matcher(graphTemp);	
					graphTemp = match.replaceFirst("");
				}
				
				// [%] 태그 제거
				pattern = Pattern.compile("\\[%\\]");
				match = pattern.matcher(graphTemp);
				graphTemp = match.replaceFirst(pieDiv+"\r\n" +
										  barDiv+"\r\n" +
										  "<script type=\"text/javascript\">\r\n" +
										  "var data"+graph_cnt+" = [ \r\n");
				match = pattern.matcher(graphTemp);
				graphTemp = match.replaceFirst(" ];\r\n" +
										 	pieTag+"\r\n" +
											barTag+"\r\n" +
											"</script>\r\n");
				
				pattern = Pattern.compile(graph_pattern);
				match = pattern.matcher(str);
				
				if( match.find() ){
					str = match.replaceFirst(graphTemp);
				}
				pattern = Pattern.compile(graph_pattern);
				match = pattern.matcher(str);
				
			}
			result = str;
			break;
			
		case COLOR:
			pattern = Pattern.compile("\\[col!(.*?)\\](.*)?\\[col\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<font color='$1'>$2</font>");
			break;
			
		case BACKGROUND:
			pattern = Pattern.compile("\\[bg!(.*?)\\](.*)?\\[bg\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<span style='background:$1'>$2</span>");
			break;
		
		case FONT:
			pattern = Pattern.compile("\\[fn\\|(.*?)\\](.*)?\\[fn\\]");
			match = pattern.matcher(str);
			
			String font = null;
			while ( match.find() ) {
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(match.group());	
				
				font = match.replaceFirst("$1");
				if( "돋음".equals(font) ){
					font = "dotum";
				}else if( "굴림".equals(font) ){
					font = "gulim";
				}else if( "궁서".equals(font) ){
					font = "gungsuh";
				}else if( "바탕".equals(font) ){
					font = "batang";
				}
				
				str = match.replaceFirst("<span style=\"font-family:'$1','"+font+"'\">$2</span>");
				match = pattern.matcher(str);
			}
			break;
			
		case HR:
			pattern = Pattern.compile("\\[hr\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<hr>");
			break;
			
		default:
			result = str;
			break; 
		}
		
		return result;
	}
	
	// 태그를 잘라내서 처리할때 사용하는 메소드
	public String replaceFirstTag(String str, String patternTxt, String replacement){
		
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		str = match.replaceFirst(replacement);
		
		return str;
	}
	
	// 라인 파싱 메소드
	public String linePaser(String str, String patternMainTxt){
		Pattern pattern = null;
		Matcher match = null;
		String[] splStr = str.split("\r\n");
		str = "";
		String patternChk = "";
		String tagChk = "";

		String pt2 = patternMainTxt+patternMainTxt;
		String pt3 = patternMainTxt+patternMainTxt+patternMainTxt;
		String pt4 = patternMainTxt+patternMainTxt+patternMainTxt+patternMainTxt;
		
		for( int i=0; i< splStr.length; i++ ){
			patternChk = "";
			
			pattern = Pattern.compile("("+pt4+")(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = pt4;
				splStr[i] = match.replaceFirst("<li class=\"lv3\">$2</li>");
				
			}
			
			pattern = Pattern.compile("("+pt3+")(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = pt3;
				splStr[i] = match.replaceFirst("<li class=\"lv2\">$2</li>");
			}
			
			pattern = Pattern.compile("("+pt2+")(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = pt2;
				splStr[i] = match.replaceFirst("<li>$2</li>");
			}
			
			String[] reTag = tag(splStr[i], patternChk, tagChk, patternMainTxt);
			
			if( reTag != null ){
				splStr[i] = reTag[0];
				patternChk = reTag[1];
				tagChk = reTag[2];
			}
			str += splStr[i]+"\r\n";
		}
		return str;
	}
	
	// 라인태그에 사용할 공통 메서드
	public String[] tag(String str, String patternChk, String tagChk, String patternMainTxt ){
		
		String[] reTag = new String[3];
		
		if( "".equals(patternChk) && "".equals(tagChk) ){
			return null;
		}
		//최초 라인 패턴을 찾았을시 적용한다.
		if( !"".equals(patternChk) && "".equals(tagChk) ){
			if( "#".equals(patternMainTxt) ){
				str = "<ul>"+str;
			}else if( "-".equals(patternMainTxt) ){
				str = "<ol>"+str;
			}
			
			tagChk = patternChk;
		
		// 패턴이 끝났을 경우 적용
		}else if( !tagChk.equals(patternChk) && "".equals(patternChk) ){
			if( "#".equals(patternMainTxt) ){
				str = "</ul>"+str;
			}else if( "-".equals(patternMainTxt) ){
				str = "</ol>"+str;
			}
			
			tagChk = patternChk;
		
		// 초기화
		}else if( "".equals(patternChk) ){
			tagChk = "";
			patternChk = "";
		}
		
		reTag[0] = str;
		reTag[1] = patternChk;
		reTag[2] = tagChk;
		
		return reTag;
	}	
	
	public enum EsCharacter {
		BOLD, ITALIC, UNDERLINING, MIX, STRIKE, SUPERSCRIPT, SUBERSCRIPT, HIPERLINK, 
		H1, H2, H3, NOTE, SYNTAX, LINE1, LINE2, IMG, ALERT, FIELD, INFO, TABLE,
		GRAPH, COLOR, BACKGROUND, FONT, HR
		
	}
	
}
