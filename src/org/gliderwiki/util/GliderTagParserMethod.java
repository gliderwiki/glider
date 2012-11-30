/**
 * @FileName  : GliderTagParserMethod.java
 * @Project   : NightHawk
 * @Date      : 2012. 3. 31. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

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
public class GliderTagParserMethod {
	
	// 링크
	public List<Object> linkTagList = new ArrayList<Object>();
	
	// h1
	public List<Object> h1TagList = new ArrayList<Object>();
	
	// 각주
	public List<Object> noteTagList = new ArrayList<Object>();

	public List<Object> syntaxTxt = new ArrayList<Object>();
	
	// 그래프
	public Integer graphCnt = 0;
	
	// 각 list에 담을 해쉬맵
	public Map<String, Object> tagMap = new HashMap<String, Object>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List EsCharacterList(){
			
		List list = new ArrayList();
		list.add(EsCharacter.SYNTAXAFTER);
		list.add(EsCharacter.LINE1);
		list.add(EsCharacter.LINE2);

		list.add(EsCharacter.BOLD);
		list.add(EsCharacter.ITALIC);
		list.add(EsCharacter.UNDERLINING);
		list.add(EsCharacter.SUBERSCRIPT);
		list.add(EsCharacter.SUPERSCRIPT);
		list.add(EsCharacter.STRIKE);
		list.add(EsCharacter.HIPERLINK);
		list.add(EsCharacter.IMG);
		
		list.add(EsCharacter.NOTE);
		list.add(EsCharacter.ALERT);
		list.add(EsCharacter.FIELD);
		list.add(EsCharacter.INFO);
		
		list.add(EsCharacter.GRAPH);
		list.add(EsCharacter.BACKGROUND);
		list.add(EsCharacter.COLOR);
		list.add(EsCharacter.FONT);
		list.add(EsCharacter.FONTSIZE);
		list.add(EsCharacter.FONTALIGN);
		list.add(EsCharacter.BOX);
		list.add(EsCharacter.HR);
		
		list.add(EsCharacter.H1);
		list.add(EsCharacter.TABLE);

		list.add(EsCharacter.SYNTAXBEFORE);
		
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
			pattern = Pattern.compile("\\*{2}([\\w\\W]+?)\\*{2}");
			match = pattern.matcher(str);
			result = match.replaceAll("<B>$1</B>");
			break;
			
		case ITALIC:
			pattern = Pattern.compile("([^http:])(\\/{2}([\\w\\W]+?)\\/{2})");
			match = pattern.matcher(str);
			result = match.replaceAll("$1<I>$3</I>");
			break;
			
		case UNDERLINING:
			pattern = Pattern.compile("\\_{2}([\\w\\W]+?)\\_{2}");
			match = pattern.matcher(str);
			result = match.replaceAll("<U>$1</U>");
			break;
			
		case STRIKE:
			pattern = Pattern.compile("\\[d\\]([\\w\\W]+?)\\[d\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<del>$1</del>");
			break;
			
		case SUPERSCRIPT:
			pattern = Pattern.compile("\\[sp\\]([\\w\\W]+?)\\[sp\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<sup>$1</sup>");
			break;
			
		case SUBERSCRIPT:
			pattern = Pattern.compile("\\[sb\\]([\\w\\W]+?)\\[sb\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<sub>$1</sub>");
			break;
			
		case HIPERLINK:
			patternTxt = "\\[(@:)(.*)?\\|(.*)?\\]";
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
			
			patternTxt = "\\[(@:)(.*)?\\]";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);

			while (match.find()) {
				
				tagMap = new HashMap<String, Object>();
				
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
			patternTxt = "\\[(!\\|)(.*)?\\|(.*)?\\|(left|right|center)\\|?(\\d+)?\\|?(\\d+)?\\]";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);

			while (match.find()) {
				
				String align = replaceFirstTag(match.group(), patternTxt, "$4");
				String width = replaceFirstTag(match.group(), patternTxt, "$5");
				String height = replaceFirstTag(match.group(), patternTxt, "$6");
				
				if( "".equals(width) &&  "".equals(height) ){
					str = match.replaceFirst("<img alt=\"$3\" src=\"$2\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >");
					
				}else if( !"".equals(width) &&  "".equals(height) ){
					str = match.replaceFirst("<img alt=\"$3\" src=\"$2\" width=\""+width+"\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >");
					
				}else if( "".equals(width) &&  !"".equals(height) ){
					str = match.replaceFirst("<img alt=\"$3\" src=\"$2\" height=\""+height+"\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >");
					
				}else{
					str = match.replaceFirst("<img alt=\"$3\" src=\"$2\" width=\""+width+"\" height=\""+height+"\" style=\"display:block; float:"+align+"; margin: 0 auto; clear: both;\" >");
					
				}
				
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(str);
			}
			result = str;
			break;
			
		case H1:
			String[] splStr = str.split("\r\n");
			str = "";
			boolean brFlag = false;
			Map<String, String> hTagMap = null;
			for( int i=0; i< splStr.length; i++ ){
				brFlag = false;
				hTagMap = new HashMap<String, String>();
				
				patternTxt = "(h1\\.(\\s{0,}))(.*)?";
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(splStr[i]);
				while ( match.find() ) {
					splStr[i] = match.replaceFirst("<h2><a id=\"$3\" name=\"$3\">$3</a></h2>\n");
					hTagMap.put("tag", "h1");
					hTagMap.put("tagVal", replaceFirstTag(match.group(), patternTxt, "$3"));
					h1TagList.add(hTagMap);
					brFlag = true;
				}
				
				patternTxt = "(h2\\.(\\s{0,}))(.*)?";
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(splStr[i]);
				while ( match.find() ) {
					splStr[i] = match.replaceFirst("<h3><a id=\"$3\" name=\"$3\">$3</a></h3>\n");
					hTagMap.put("tag", "h2");
					hTagMap.put("tagVal", replaceFirstTag(match.group(), patternTxt, "$3"));
					h1TagList.add(hTagMap);
					brFlag = true;
				}
				
				patternTxt = "(h3\\.(\\s{0,}))(.*)?";
				pattern = Pattern.compile(patternTxt);
				match = pattern.matcher(splStr[i]);
				while ( match.find() ) {
					splStr[i] = match.replaceFirst("<h4><a id=\"$3\" name=\"$3\">$3</a></h4>\n");
					hTagMap.put("tag", "h3");
					hTagMap.put("tagVal", replaceFirstTag(match.group(), patternTxt, "$3"));
					h1TagList.add(hTagMap);
					brFlag = true;
				}
				
				// 정상적인 엔터(\r\n)인지 확인후 str에 값을 추가한다.
				if( brFlag == true){
					str += splStr[i];
				}else{
					str += splStr[i]+"\r\n";
				}
				
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
			pattern = Pattern.compile("(\\[alert\\])([\\w\\W]+?)(\\[alert\\])");
			match = pattern.matcher(str);
			result = match.replaceAll("<div class=\"box-alert\">$2</div>");
			break;
			
		case INFO:
			pattern = Pattern.compile("(\\[info\\])([\\w\\W]+?)(\\[info\\])");
			match = pattern.matcher(str);
			result = match.replaceAll("<div class=\"box-check\">$2</div>");
			break;
			
		case FIELD:
			pattern = Pattern.compile("(\\[field\\]|\\[field\\|(.*?)\\])([\\w\\W]+?)\\[field\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<fieldset class=\"fieldset\">\n<legend class=\"legend\">$2</legend>\n$3\n</fieldset>");
			break;
		
		case SYNTAXAFTER:
			patternTxt = "(\\[syntax\\])([\\w\\W]+?)(\\[syntax\\])";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);
			
			while( match.find() ){
				syntaxTxt.add( replaceFirstTag(match.group(), patternTxt, "$2").replaceAll("\r\n", "\n") );
			}
			result = match.replaceAll("[SONJSsyntax][SONJSsyntax]");
			
			break;
			
		case SYNTAXBEFORE:
			pattern = Pattern.compile("(\\[SONJSsyntax\\])(\\[SONJSsyntax\\])");
			match = pattern.matcher(str);
			
			int syntaxCnt = 0;
			while( match.find() ){
				//str = match.replaceFirst("<script type=\"syntaxhighlighter\" class=\"brush: js\">"+syntaxTxt.get(syntaxCnt)+"</script>");
				str = match.replaceFirst("<pre class=\"brush: js\">"+syntaxTxt.get(syntaxCnt).toString().replaceAll("[$]", "\\\\\\$")+"</pre>");
				syntaxCnt++;
				match = pattern.matcher(str);
			}
			
			result = str;
			break;
			
		case LINE1:
			result = linePaser(str, "#");
			break;

		case LINE2:
			String[] strLine = str.split("\r\n");
			str = "";
			
			int lineCnt = 0;
			for( int i=0; i<strLine.length; i++ ){
				pattern = Pattern.compile("(^----)(.*)", Pattern.MULTILINE);
				match = pattern.matcher(strLine[i]);
				if( match.find() ){
					lineCnt++;
					if( lineCnt == 1 ){
						strLine[i] = match.replaceFirst("<ol>\n<li class=\"lv3\">$2</li>\n");
					}else{
						strLine[i] = match.replaceFirst("<li class=\"lv3\">$2</li>\n");
					}
				}else{
					if( lineCnt > 0 ){
						strLine[i-1] = strLine[i-1]+"</ol>\n";
						strLine[i] = strLine[i]+"\r\n";
						lineCnt = 0;	
					}else{
						strLine[i] = strLine[i]+"\r\n";
					}
				}
			}

			for( int i=0; i<strLine.length; i++ ){
				str += strLine[i];
			}
			strLine = str.split("\r\n");
			str = "";
			
			lineCnt = 0;
			for( int i=0; i<strLine.length; i++ ){
				pattern = Pattern.compile("(^---)(.*)", Pattern.MULTILINE);
				match = pattern.matcher(strLine[i]);
				if( match.find() ){
					lineCnt++;
					if( lineCnt == 1 ){
						strLine[i] = match.replaceFirst("<ol>\n<li class=\"lv2\">$2</li>\n");
					}else{
						strLine[i] = match.replaceFirst("<li class=\"lv2\">$2</li>\n");
					}
				}else{
					if( lineCnt > 0 ){
						strLine[i-1] = strLine[i-1]+"</ol>\n";
						strLine[i] = strLine[i]+"\r\n";
						lineCnt = 0;	
					}else{
						strLine[i] = strLine[i]+"\r\n";
					}
				}
			}
			
			for( int i=0; i<strLine.length; i++ ){
				str += strLine[i];
			}
			strLine = str.split("\r\n");
			str = "";
			
			lineCnt = 0;
			for( int i=0; i<strLine.length; i++ ){
				pattern = Pattern.compile("(^--)(.*)", Pattern.MULTILINE);
				match = pattern.matcher(strLine[i]);
				if( match.find() ){
					lineCnt++;
					if( lineCnt == 1 ){
						strLine[i] = match.replaceFirst("<ol>\n<li >$2</li>\n");
					}else{
						strLine[i] = match.replaceFirst("<li >$2</li>\n");
					}
				}else{
					if( lineCnt > 0 ){
						strLine[i-1] = strLine[i-1]+"</ol>\n";
						strLine[i] = strLine[i]+"\r\n";
						lineCnt = 0;	
					}else{
						strLine[i] = strLine[i]+"\r\n";
					}
				}
			}
			
			for( int i=0; i<strLine.length; i++ ){
				str += strLine[i];
			}
			result = str;
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
				pattern = Pattern.compile("((^\\|\\|)+)(.*)((\\|\\|)+)", Pattern.MULTILINE);
				match = pattern.matcher(strAll[i]);
				if( match.find() ){
					ret = "";
					strAll[i] = match.replaceAll("\n<tr>\n$2$3$4\n</tr>\n");
					
					pattern = Pattern.compile("(?<=\\|\\|)(?!((\\s)+?)?(col:[\\w]+#)|((\\s)+?)?(row:[\\w]+#))(.*?)(?=\\|\\|)", Pattern.MULTILINE);
					match = pattern.matcher(strAll[i]);

					
					if( match.find() && table_cnt == 0){
						ret += "<table>\n";
						table_cnt++;
					}
					ret += match.replaceAll("\n<th class=\"\">$7</th>");
					
					pattern = Pattern.compile("(?<=\\|\\|)(((\\s)+?)?(col:(\\w+)#)(.*?)((\\s)+?)?)(?=\\|\\|)", Pattern.MULTILINE);
					match = pattern.matcher(ret);
					ret = match.replaceAll("\n<th colspan=\"$5\">$2$6$7</td>");
					
					pattern = Pattern.compile("(?<=\\|\\|)(((\\s)+?)?(row:(\\w+)#)(.*?)((\\s)+?)?)(?=\\|\\|)", Pattern.MULTILINE);
					match = pattern.matcher(ret);
					ret = match.replaceAll("\n<th rowspan=\"$5\">$2$6$7</td>");
					
					pattern = Pattern.compile("(^\\|\\|)?(.*?)(\\|\\|)?", Pattern.MULTILINE);
					match = pattern.matcher(ret);
					ret = match.replaceAll("$2");
					strAll[i] = ret;
					matchFind = true;
				}else{
					ret = strAll[i];
				}
				
				// 내용 파싱
				pattern = Pattern.compile("((^\\|)+)(.*)((\\|)+)", Pattern.MULTILINE);
				match = pattern.matcher(strAll[i]);
				
				if( match.find() ){
					ret = "";
					strAll[i] = match.replaceAll("\n<tr>\n$2$3$4\n</tr>\n");
					
					pattern = Pattern.compile("(?<=\\|)(?!((\\s)+?)?(col:[\\w]+#)|((\\s)+?)?(row:[\\w]+#))(.*?)(?=\\|)", Pattern.MULTILINE);
					match = pattern.matcher(strAll[i]);

					if( match.find() && table_cnt == 0){
						ret += "<table>\n";
						table_cnt++;
					}
					ret += match.replaceAll("\n<td class=\"\">$7</td>");
					
					pattern = Pattern.compile("(?<=\\|)(((\\s)+?)?(col:(\\w+)#)(.*?)((\\s)+?)?)(?=\\|)", Pattern.MULTILINE);
					match = pattern.matcher(ret);
					ret = match.replaceAll("\n<td colspan=\"$5\">$2$6$7</td>");
					
					pattern = Pattern.compile("(?<=\\|)(((\\s)+?)?(row:(\\w+)#)(.*?)((\\s)+?)?)(?=\\|)", Pattern.MULTILINE);
					match = pattern.matcher(ret);
					ret = match.replaceAll("\n<td rowspan=\"$5\">$2$6$7</td>");
					
					pattern = Pattern.compile("(^\\|)?(.*?)(\\|)?", Pattern.MULTILINE);
					match = pattern.matcher(ret);
					ret = match.replaceAll("$2");
					matchFind = true;
				}else{
					// 테이블에 파싱되지 않는 txt에만 \r\n 을 붙인다.
					ret = ret+"\r\n";
				}
				
				if( strAll.length == (i+1) && table_cnt != 0 ){
					ret += "</table>\n";
					table_cnt = 0;
				}else if( matchFind == false && table_cnt != 0 ){
					ret = "</table>\n"+ret;
					table_cnt = 0;
				}
				str += ret;
				
			}
			result = str;
			break;
			
		case GRAPH:
			String graph_pattern = "\\[%\\]([\\w\\W]+?)\\[%\\]"; //\\[%\\](.*[\r\n])*\\[%\\]
			pattern = Pattern.compile(graph_pattern);
			match = pattern.matcher(str);
			
			int graph_cnt = 0;
			while ( match.find() ) {
				String pieTag = "";
				String pieDiv = "";
				String barTag = "";
				String barDiv = "";
				String pieSize = "300";
				String barSize = "400";
				
				graph_cnt++;
				String graphTemp = match.group().replaceAll("\r\n", "\n");
				
				//size
				pattern = Pattern.compile("(?<=size\\[)(\\w+)(?=\\])");
				match = pattern.matcher(graphTemp);
				if ( match.find() ) {
					pieSize = match.group();
					barSize = match.group();
					pattern = Pattern.compile("size\\[(\\w)+\\]");
					match = pattern.matcher(graphTemp);	
					graphTemp = match.replaceFirst("");
				}
				
				// pieTag 검색 및 치환
				pattern = Pattern.compile("((?<=pie\\[)(.*)(?=\\]))");
				match = pattern.matcher(graphTemp);
				if( match.find() ){
					pieTag = match.group();
					pieDiv = "<div id='"+pieTag+"'></div>\n<div style=\"clear:left;height: 50px;\"></div>";
					pieTag = "&#36;(\"#"+pieTag+"\").pieChart(data"+graph_cnt+","+pieSize+",\"pie\");";
					pattern = Pattern.compile("(pie\\[)(.*)(\\])");
					match = pattern.matcher(graphTemp);	
					graphTemp = match.replaceFirst("");
				}
				// barTag 검색 및 치환
				pattern = Pattern.compile("((?<=bar\\[)(.*)(?=\\]))");
				match = pattern.matcher(graphTemp);
				if( match.find() ){
					barTag = match.group();
					barDiv = "<div id='"+barTag+"'></div>\n<div style=\"clear:left;height: 50px;\"></div>";
					barTag = "&#36;(\"#"+barTag+"\").pieChart(data"+graph_cnt+","+barSize+",\"bar\");";
					pattern = Pattern.compile("(bar\\[)(.*)(\\])");
					match = pattern.matcher(graphTemp);	
					graphTemp = match.replaceFirst("");
				}
				
				// [%] 태그 제거
				pattern = Pattern.compile("\\[%\\]");
				match = pattern.matcher(graphTemp);
				graphTemp = match.replaceFirst(pieDiv+"\n" +
										  barDiv+"\n" +
										  "<script type=\"text/javascript\">\n" +
										  "function graph"+graph_cnt+"(){\n" +
										  "var data"+graph_cnt+" = [ \n");
				match = pattern.matcher(graphTemp);
				graphTemp = match.replaceFirst(" ];\n" +
										 	pieTag+"\n" +
											barTag+"\n" +
													"};\n" +
											"</script>\n");
				
				pattern = Pattern.compile(graph_pattern);
				match = pattern.matcher(str);
				
				if( match.find() ){
					str = match.replaceFirst(graphTemp);
					str = str.replaceAll("[&][#]36;", "\\$");
				}
				pattern = Pattern.compile(graph_pattern);
				match = pattern.matcher(str);
				
			}
			graphCnt = graph_cnt;
			result = str;
			break;
			
		case COLOR:
			pattern = Pattern.compile("\\[color\\|(.*?)\\]([\\w\\W]+?)\\[color\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<font color='#$1'>$2</font>");
			break;
			
		case BACKGROUND:
			pattern = Pattern.compile("\\[bg\\|(.*?)\\]([\\w\\W]+?)\\[bg\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<span style='background:#$1'>$2</span>");
			break;
		
		case FONT:
			String fontPattern = "\\[font\\|(.*?)\\]([\\w\\W]+?)\\[font\\]";
			pattern = Pattern.compile(fontPattern);
			match = pattern.matcher(str);
			
			String font = null;
			while ( match.find() ) {
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
				pattern = Pattern.compile(fontPattern);
				match = pattern.matcher(str);
				str = match.replaceFirst("<span style=\"font-family:'$1','"+font+"'\">$2</span>");
			}
			result = str;
			break;
			
		case FONTSIZE:
			pattern = Pattern.compile("\\[size\\|(.*?)\\]([\\w\\W]+?)\\[size\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<span style=\"font-size: $1px;\">$2</span>");
			break;
			
		case FONTALIGN:
			String fontalignPattern = "\\[align\\:(left|right|center|justify)\\]([\\w\\W]+?)\\[align\\]";
			pattern = Pattern.compile(fontalignPattern);
			match = pattern.matcher(str);
			
			String fontalign = null;
			while ( match.find() ) {
				match = pattern.matcher(match.group());	
				
				fontalign = match.replaceFirst("$1");
				if( "left".equals(fontalign) ){
					fontalign = "left";
				}else if( "center".equals(fontalign) ){
					fontalign = "center";
				}else if( "right".equals(fontalign) ){
					fontalign = "right";
				}else if( "justify".equals(fontalign)){
					fontalign = "justify";
				}
				pattern = Pattern.compile(fontalignPattern);
				match = pattern.matcher(str);
				str = match.replaceFirst("<div style='text-align:"+fontalign+"'>$2</div>");
			}
			result = str;
			break;
			
		case BOX:
			pattern = Pattern.compile("\\[box\\|(.*?)\\]([\\w\\W]+?)\\[box\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<div class=\"box-empty\" style=\"background-color:#$1\">$2</div>");
			break;
			
		case HR:
			pattern = Pattern.compile("\\[hr\\]");
			match = pattern.matcher(str);
			result = match.replaceAll("<hr>");
			break;
			
		case BR:
			pattern = Pattern.compile("^[\r\n]",Pattern.MULTILINE);
			match = pattern.matcher(str);
			result = match.replaceAll("<br />\r\n");
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
			
			pattern = Pattern.compile("(^"+pt4+")(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = pt4;
				splStr[i] = match.replaceFirst("<li class=\"lv3\">$2</li>\n");
				
			}
			
			pattern = Pattern.compile("(^"+pt3+")(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = pt3;
				splStr[i] = match.replaceFirst("<li class=\"lv2\">$2</li>\n");
			}
			
			pattern = Pattern.compile("(^"+pt2+")(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = pt2;
				splStr[i] = match.replaceFirst("<li>$2</li>\n");
			}
			
			String[] reTag = tag(splStr[i], patternChk, tagChk, patternMainTxt);
			
			if( reTag != null ){
				splStr[i] = reTag[0];
				patternChk = reTag[1];
				tagChk = reTag[2];
			}else{
				splStr[i] = splStr[i]+"\r\n";
			}
			str += splStr[i];
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
				str = "<ul>\n"+str;
			}else if( "-".equals(patternMainTxt) ){
				str = "<ol>\n"+str;
			}
			
			tagChk = patternChk;
		
		// 패턴이 끝났을 경우 적용
		}else if( !tagChk.equals(patternChk) && "".equals(patternChk) ){
			if( "#".equals(patternMainTxt) ){
				str = "</ul>\n"+str;
			}else if( "-".equals(patternMainTxt) ){
				str = "</ol>\n"+str;
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
		H1, NOTE, SYNTAXAFTER, SYNTAXBEFORE, LINE1, LINE2, IMG, ALERT, FIELD, INFO, TABLE,
		GRAPH, COLOR, BACKGROUND, FONT, FONTSIZE, FONTALIGN, BOX, HR, BR
		
	}
	
}
