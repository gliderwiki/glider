/**
 * @FileName  : GliderTagTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 28. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ganji
 *
 */
public class tagTest {
	Logger logger = LoggerFactory.getLogger(tagTest.class);
	GliderTagParserTest gtp = new GliderTagParserTest();
	
	@Test
	public void 굵은글씨_처리(){
		String str = "____**중복 마크업은 적용되남???**____   ";
		
		String patternTxt = "\\*{2}([\\D\\d]+?)\\*{2}";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		str = match.replaceAll("<B>$1</B>");
		System.out.println(str);

		/**
	*  pattern.matcher(" "+str+" ") 이렇게 값을 매칭하는 이유는 패턴의 앞과뒤에 [^\\*] 이 패턴때문이며
	*  이렇게 사용한 이유는 **문장** 이렇게만 써져야 하고 ***문장**,**문장*** 이렇게 사용시 패턴에서 걸러내지 못하게 하기 위함이다.
	*/
	}
	
	@Test
	public void 기울임글씨_처리(){
		String str = "기울인 //글씨를<br> //처리// 하자<br>";
		
		String patternTxt = "\\/{2}([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?\\/{2}";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		str = match.replaceAll("<I>$1</I>");
		System.out.println(str);
		
	}
	
	@Test
	public void 밑줄글씨_처리(){
		String str = "밑줄 __글씨를 <br>__처리__ 하자<br>";

		//String resultStr = gtp.parser(str);
		//logger.debug("##결과 문자열 :::{} ",resultStr);
		
	}
	
	@Test
	public void 조합글씨_처리(){
		String str = "조합 글씨를<br> **__//처리**__// 하자<br>";
		
		String patternTxt = "\\*{2}_{2}/{2}([\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+)?\\*{2}_{2}/{2}";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		str = match.replaceAll("<I>$1</I>");
		System.out.println(str);

	}
	
	@Test
	public void 아랫첨자글씨_처리(){
		String str = "아랫첨자 <sb>글씨를<br> <sb>처리<sb> <sb>하자<br><sb>";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile("<sb>(.*?)<sb>");
		match = pattern.matcher(str);
		
		str = match.replaceAll("<sub>$1</sub>");
		System.out.println(str);	

		
	}
	
	@Test
	public void 윗첨자글씨_처리(){
		String str = "윗첨자 <\\sp>글씨를<br> <sp>처리<sp> 하자";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile("<sp>(.*?)<sp>");
		match = pattern.matcher(str);
		
		str = match.replaceAll("<sup>$1</sup>");
		System.out.println(str);	
		
	}
	
	@Test
	public void 취소선글씨_처리(){
		String str = "취소선 <d>글씨를<br><sup>asdfasdf</sup> <d>처리<d> 하자";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile("<d>(.*?)<d>");
		match = pattern.matcher(str);
		
		str = match.replaceAll("<del>$1</del>");
		System.out.println(str);	
		//String resultStr = gtp.parser(str);
		//logger.debug("##결과 문자열 :::{} ",resultStr);
		
		
	}
	
	@Test
	public void 하이퍼링크_처리(){
		String str = "하이퍼링크를 걸자 어디로? [@:http://www.okjsp.pe.kr/seq/200072|스프링원가게 되었습니다.] \r\n " +
				"여기에도 걸어야지~~ㅋㅋ [@:http://www.gliderwiki.org:8080]";
		
		String patternTxt = "\\[(@:)(.*)?\\|(.*)?\\]";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		
		String temp = null;
		/*while ( match.find() ) {
			str = match.replaceFirst("<a href=\"$2\">$3</a>");
			
			temp = match.group(); 
			match = pattern.matcher(temp);
			temp = match.replaceFirst("$2");
			System.out.println("temp : "+temp);	
			
			match = pattern.matcher(str);
			System.out.println(str);	
		}*/
		
		str = match.replaceAll("<a href=\"$2\">$3</a>");
		System.out.println(str);
	}
	
	@Test
	public void 외부이미지_처리(){
		String str = "외부 이미지를 걸자 어디로? [!|http://imgnews.naver.net/image/109/2012/08/11/201208110044771510_1.jpg|우사인볼트표정ㅋㅋ|left||200]\r\n" +
				"[!|http://i2.media.daumcdn.net/photo-media/201209/16/ilyo/20120916102108966.jpg|박지성|right||]\r\n" +
				"[!|/resource/temp/20/20120923/thumb/thumb_201209238742454633195.jpg||left|] \r\n";
		
		String patternTxt = "\\[(!\\|)(.*)?\\|(.*)?\\|(left|right|center)\\|?(\\d+)?\\|?(\\d+)?\\]";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		
		while (match.find()) {
			String align = "";
			align = replaceFirstTag(match.group(), patternTxt, "$4");
			
			if( "left".equals(align) ){
				str = match.replaceFirst("<img alt=\"$3\" src=\"$2\" width=\"$5\" height=\"$6\" style=\"FLOAT: left;\" >");
			}else if( "center".equals(align) ){
				str = match.replaceFirst("<img alt=\"$3\" src=\"$2\" width=\"$5\" height=\"$6\" style=\"FLOAT: center;\" >");
			}else if( "right".equals(align) ){
				str = match.replaceFirst("<img alt=\"$3\" src=\"$2\" width=\"$5\" height=\"$6\" style=\"FLOAT: right;\" >");
			} 
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);
			
		}
		
		//str = match.replaceAll("aa$5  bb$6");
		System.out.println(str);
		
	}
	
	// 태그를 잘라내서 처리할때 사용하는 메소드
	public String replaceFirstTag(String str, String patternTxt, String replacement){
		
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		str = match.replaceFirst(replacement);
		
		return str;
	}
	
	Map<String, String> chkList = new HashMap<String, String>();
	
	@Test
	public void 순서2번_처리(){
		
		String all = "[\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+";
		
/*		String str = "  안녕하세요.<br />" +
					 "  ## 밥2번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  ### 밥3번 <br />" +
					 "  ### 밥3번 <br />" +
					 "  #### 밥4-1번 <br />" +
					 "  #### 밥4-2번 <br />" +
					 "  ### 밥3번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  이제부터 다시 시작 <br />" +
					 "  ## 밥2번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  #### 밥4-3번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  이거 끝났네요~ ^^; ";*/

		String str = "  안녕하세요.<br />" +
				 "  ## 밥2번 <br />" +
				 "  ### 밥3번 <br />" +
				 "  #### 밥4번 <br />" +
				 "  이거 끝났네요~ ^^; ";
		
		String[] splStr = str.split("<br />");
		str = "";
		
		Pattern pattern = null;
		Matcher match = null;
		String patternChk = "";
		String tagChk = "";

		for( int i=0; i< splStr.length; i++ ){
			patternChk = "";
			
			pattern = Pattern.compile("(####)(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = "####";
				splStr[i] = match.replaceFirst("<li>$2</li>");
				
			}
			
			pattern = Pattern.compile("(###)(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = "###";
				splStr[i] = match.replaceFirst("<li>$2</li>");
			}
			
			pattern = Pattern.compile("(##)(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = "##";
				splStr[i] = match.replaceFirst("<li>$2</li>");
			}
			chkList.put(patternChk, patternChk);
			
			String[] reTag = tag(splStr[i], patternChk, tagChk);
			
			if( reTag != null ){
				splStr[i] = reTag[0];
				patternChk = reTag[1];
				tagChk = reTag[2];
			}
			str += splStr[i]+"<br />";
		}
		str = str.replaceAll("<br />", "<br />\n");
		System.out.println(str);
	}
	
	public String[] tag(String str, String patternChk, String tagChk ){
		
		String[] reTag = new String[3];
		
		if( "".equals(patternChk) && "".equals(tagChk) ){
			return null;
		}
		//최초 라인 패턴을 찾았을시 적용한다.
		if( !"".equals(patternChk) && "".equals(tagChk) ){
			str = "<ul>"+str;
			tagChk = patternChk;
		
		}else if( !tagChk.equals(patternChk) && "".equals(patternChk) ){
			
			str = "</ul>"+str;
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
	
	@Test
	public void 순서1번_처리(){
		
		String all = "[\\w\\d\\s\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.|]+";
		
		String str = "  안녕하세요.<br />" +
					 "  ## 밥2번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  ### 밥3번 <br />" +
					 "  ### 밥3번 <br />" +
					 "  #### 밥4-1번 <br />" +
					 "  #### 밥4-2번 <br />" +
					 "  ### 밥3번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  이제부터 다시 시작 <br />" +
					 "  ## 밥2번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  #### 밥4-3번 <br />" +
					 "  ## 밥2번 <br />" +
					 "  이거 끝났네요~ ^^; ";

		/*String str = "  안녕하세요.<br />" +
				 "  ## 밥2번 <br />" +
				 "  ### 밥3번 <br />" +
				 "  #### 밥4번 <br />" +
				 "  이거 끝났네요~ ^^; ";
		*/
		String[] splStr = str.split("<br />");
		str = "";
		
		Pattern pattern = null;
		Matcher match = null;
		String patternChk = "";
		String tagChk = "";

		for( int i=0; i< splStr.length; i++ ){
			patternChk = "";
			
			pattern = Pattern.compile("(####)(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = "####";
				splStr[i] = match.replaceFirst("<li class=\"lv3\">$2</li>");
				
			}
			
			pattern = Pattern.compile("(###)(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = "###";
				splStr[i] = match.replaceFirst("<li class=\"lv2\">$2</li>");
			}
			
			pattern = Pattern.compile("(##)(.*)");
			match = pattern.matcher(splStr[i]);
			if( match.find() ){
				patternChk = "##";
				splStr[i] = match.replaceFirst("<li>$2</li>");
			}
			//chkList.put(patternChk, patternChk);
			
			String[] reTag = tag(splStr[i], patternChk, tagChk);
			
			if( reTag != null ){
				splStr[i] = reTag[0];
				patternChk = reTag[1];
				tagChk = reTag[2];
			}
			str += splStr[i]+"<br />";
		}
		str = str.replaceAll("<br />", "<br />\n");
		System.out.println(str);
	}
	
	
	@Test
	public void 헤드라인_처리(){
		
		String str = "  안녕하세요.\r\n" +
					 "  h1. 문재인 대선후보 확정, 안철수 “진심으로 축하”  \r\n" +
					 "  -- 밥2번 \r\n" +
					 "  h2. Medium Heading  \r\n" +
					 "  이제부터 다시 시작 \r\n" +
					 "  -- 밥4번 \r\n" +
					 "  -- 밥5번 \r\n" +
					 "  h3. 중간 헤드라인에 속한 작은거 1 \r\n" +
					 "  이거 끝났네요~ ^^; ";
		
		String[] splStr = str.split("\r\n");
		str = "";
		String patternTxt = "";
		Pattern pattern = null;
		Matcher match = null;
		str = "";
		Map<String, String> hTagMap = null;
		for( int i=0; i< splStr.length; i++ ){
			
			hTagMap = new HashMap<String, String>();
			
			patternTxt = "(h1\\.(\\s{0,}))(.*)?";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(splStr[i]);
			while ( match.find() ) {
				splStr[i] = match.replaceFirst("<h2><a id=\"$3\" name=\"$3\">$3</a></h2>");
			}
			
			patternTxt = "(h2\\.(\\s{0,}))([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(splStr[i]);
			while ( match.find() ) {
				splStr[i] = match.replaceFirst("<h3><a id=\"$3\" name=\"$3\">$3</a></h3>");
			}
			
			patternTxt = "(h3\\.(\\s{0,}))([\\w\\d\\s\\n\\t가-힣ㄱ-ㅎㅏ-ㅣ:/~\\.]+)";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(splStr[i]);
			while ( match.find() ) {
				splStr[i] = match.replaceFirst("<h4><a id=\"$3\" name=\"$3\">$3</a></h4>");
			}
			str += splStr[i]+"\r\n";
		}
		
		System.out.println(str);	
	}
	
	@Test
	public void 알림_처리(){
		
		String str = "  안녕하세요.<br />" +
					 "  [alert] <br />" +
					 "  - name(필수) : 필드이름 <br />" +
					 "  name(필수) : 필드이름 <br />" +
					 "  name(필수) : 필드이름 <br />" +
					 "  - type(필수) : 데이터타입 <br />" +
					 "  - index(필수) : 인덱스 <br />" +
					 "  [alert] <br />" +
					 "  이거 끝났네요~ ^^; ";
		
		String patternTxt = "(\\[alert\\])(.*?)(\\[alert\\])";  
		//String patternTxt = "<br />([\\s]{0,})(-)(.*?)<br />";  
		//System.out.println(patternTxt);
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		
		while(match.find()){
			
			str = match.replaceFirst("<alert><ul>$2</ul></alert>");
			
			patternTxt = "(?<=(<br />|</li>))([\\s]{0,})(-)(.*?)(<br />)";
			pattern = Pattern.compile(patternTxt);
			match = pattern.matcher(str);
			
			while(match.find()){
				str = match.replaceFirst("<li>$4</li>");
				match = pattern.matcher(str);
			}
			match = pattern.matcher(str);
		}
		System.out.println(str);	
	}

	@Test
	public void 필드셋_처리(){
		
		String str = "  안녕하세요.\r\n" +
					 "  [field|제목]\r\n" +
					 "  내용은 적절하게\r\n" +
					 "	asdfasdf " +
					 "	  <field name=\"categoryId\" type=\"long\" indexed=\"false\" stored=\"true\" /> " +
					 "	  <field name=\"title\" type=\"string\" indexed=\"true\" stored=\"true\" /> " +
					 "	  <field name=\"contents\" type=\"string\" indexed=\"true\" stored=\"true\" /> " +
					 "  [field]\r\n" +
					 "  이거 끝났네요~ ^^; \r\n";
		
		String patternTxt = "(\\[field\\]|\\[field\\|(.*?)\\])([\\D\\d]+?)\\[field\\]";  
		
		System.out.println(patternTxt);
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		str = match.replaceAll("<fieldset class=\"fieldset\">\n<legend class=\"legend\">$2</legend>\n$3\n</fieldset>");

/*		<fieldset class="fieldset">
		<legend class="legend">으앙아으앙</legend>
		내용은 적절하게
		<table>
			<tr>
				<td>안에 테이블도 들어간다!</td>
				<td>테이블 헠헠 </td>
			</tr>
			<tr>
				<td colspan="2">호옹이!</td>
			</tr>
		</table>
	</fieldset>*/
		
	/*	while(match.find()){
			System.out.println("##일치하는 문자열 :::{} "+match.group());
			str = str.replace(match.group().trim(), "aa");
			
			// nT4hD5vK6jy3
		}
		*/
		System.out.println(str);	
	}
	
	@Test
	public void 정보_처리(){
		
		String str = "  안녕하세요.\r\n" +
					 "  [alert] 헤드라인1 입니다. </h1> \r\n" +
					 "  -- 밥2번 \r\n" +
					 "  <h2> 헤드라인2 입니다. [alert] \r\n" +
					 "  이제부터 다시 시작 \r\n" +
					 "  -- 밥4번 \r\n" +
					 "  -- 밥5번 \r\n" +
					 "  <h3> 헤드라인3 입니다. {alert} \r\n" +
					 "  이거 끝났네요~ ^^; \r\n";
		
		String patternTxt = "(\\[alert\\])((.*?[\r\n]?)+?)(\\[alert\\])";  
		
		System.out.println(patternTxt);
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		//str = match.replaceAll("<note>$2</note>");

		while(match.find()){
			System.out.println("##일치하는 문자열 :::{} "+match.group());
			str = str.replace(match.group().trim(), "aa");
			
			// nT4hD5vK6jy3
		}
		
		System.out.println(str);	
	}
	
	@Test
	public void 각주_처리(){
		
		String str = "  [note|각주일#Copyright © 2012 GLiDERWiki™ OpenSource Team 을 포함한 하단 메뉴 전체를 지칭합니다.] 안녕하세요~~<br />" +
					 "  이거 끝났네요~ ^^; ";
		
		String patternTxt = "\\[(note\\|)(.*?)#(.*?)\\]";
		Pattern pattern = Pattern.compile(patternTxt);
		Matcher match = pattern.matcher(str);
		Integer cnt = 0;
		
		String note = "";
		note = match.replaceAll("-->$3<--");
		/*
		while (match.find()) {
			cnt = cnt+1;
			// 위키태그를 파싱하여 html태그로 만든다.
			str = match.replaceFirst("$2<a href=\"#note-"+cnt+"\"><sup id=\"note-sub-"+cnt+"\">"+cnt+"</sup></a>");
			
			// 위키태그중에 따로 사용할 태그를 발라서 처리한다.
			note = match.replaceAll("aa$3aa");
			
			match = pattern.matcher(str);
		}
		*/
		System.out.println(str);
		System.out.println("note :: "+note);
	}
	
	@Test
	public void 테이블_처리(){
		
		String str = "  안녕하세요.<br />" +
					 "  [field] <br />" +
					 "  <fields>  <br />" +
					 "	  <field name=\"docId\" type=\"long\" indexed=\"true\" stored=\"true\" required=\"true\" /> " +
					 "	  <field name=\"categoryId\" type=\"long\" indexed=\"false\" stored=\"true\" /> " +
					 "	  <field name=\"title\" type=\"string\" indexed=\"true\" stored=\"true\" /> " +
					 "	  <field name=\"contents\" type=\"string\" indexed=\"true\" stored=\"true\" /> " +
					 "	</fields> " +
					 "  [field]" +
					 "  이거 끝났네요~ ^^; ";
		
		String patternTxt = "(\\[field\\])(.*?)(\\[field\\])";  
		
		System.out.println(patternTxt);
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		str = match.replaceAll("<div style=\"border:1px dashed; padding:10px;\">$2</div>");

	/*	while(match.find()){
			System.out.println("##일치하는 문자열 :::{} "+match.group());
			str = str.replace(match.group().trim(), "aa");
			
			// nT4hD5vK6jy3
		}
		*/
		System.out.println(str);	
	}
	
	
	@Test
	public void 일반테스트(){
		
		String str = "##start\r\n 삐리뽐\r\n end## 빼리뽐 \r\n adfa ##start  asdf  end##";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile("##start[\r\n]*.*");
		match = pattern.matcher(str);
		//str = match.replaceAll("<sub>$2</sub>");
		
		while ( match.find() ) {
			System.out.println(match.group());	
		}

		//System.out.println(str);	
		
	}
	
	@Test
	public void 그래프테스트(){
		//var aa = [ {'date':'1','value':'1'},{'date':'2','value':'2'},{'date':'3','value':'3'} ];
		String str = "차트를 보여줍니다.\r\n"+
				"[%]\r\n"+
				"['1',2],\r\n"+
				"['2',2],\r\n"+
				"['3',3],\r\n"+
				"['4',4],\r\n"+
				"pie[test]\r\n"+
				"bar[asdf]\r\n"+
				"size[200][%]\r\n"+
				"" ;
			/*	"차트를22222 보여줍니다.\r\n"+
				"[%]\r\n"+
				"['11',25]\r\n"+
				"['22',25]\r\n"+
				"['33',35]\r\n"+
				"['44',45]\r\n"+
				"pie[testaaa]\r\n"+
				"bar[asdfaaa]\r\n"+
				"size[200]\r\n"+
				"[%]\r\n";*/
		
		String graph_pattern = "\\[%\\]([\\D\\d]+?)\\[%\\]"; //\\[%\\](.*[\r\n])*\\[%\\]
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(graph_pattern);
		match = pattern.matcher(str);
		
		String pieTag = "";
		String pieDiv = "";
		String barTag = "";
		String barDiv = "";
		String size = "100";
		
		int graph_cnt = 0;
		while ( match.find() ) {
			
			graph_cnt++;
			String graphTemp = match.group().replaceAll("\r\n", "\n");
			
			//size
			pattern = Pattern.compile("(?<=size\\[)(\\w+)(?=\\])");
			match = pattern.matcher(graphTemp);
			if ( match.find() ) {
				size = match.group();
				pattern = Pattern.compile("size\\[(\\w)+\\]");
				match = pattern.matcher(graphTemp);	
				graphTemp = match.replaceFirst("");
			}
			
			// pieTag 검색 및 치환
			pattern = Pattern.compile("((?<=pie\\[)(.*)(?=\\]))");
			match = pattern.matcher(graphTemp);
			if( match.find() ){
				pieTag = match.group();
				pieDiv = "<div id='"+pieTag+"'></div>";
				pieTag = "&#36;(\"#"+pieTag+"\").pieChart(data"+graph_cnt+","+size+",\"pie\");";
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
				barTag = "&#36;(\"#"+barTag+"\").pieChart(data"+graph_cnt+","+size+",\"bar\");";
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
		System.out.println( str );
	}
	
	@Test
	public void 컬러_태그(){
		String str = "색깔을 입히자 ~ ? [col!#11234]색입히기[col]";

		String patternTxt = "\\[col!(.*?)\\](.*)?\\[col\\]";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		
		while ( match.find() ) {
			str = match.replaceFirst("<font color='$1'>$2</font>");
			match = pattern.matcher(str);
			System.out.println(str);	
		}
		
	}
	
	@Test
	public void 폰트_사이즈(){
		String str = "색깔을 입히자 ~ ? [font|궁서][size|18]글자 크기와 사이즈 \r\n테스트합니다. [size] [font]";

		String patternTxt = "\\[size\\|(.*?)\\]([\\D\\d]+?)\\[size\\]";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		
		while ( match.find() ) {
			str = match.replaceFirst("<font size=\"$1\">$2</font>");
			match = pattern.matcher(str);
			System.out.println(str);	
		}
		
	}
	
	@Test
	public void 배경색(){
		String str = "배경색을 입히자 ~ ? [bg!#11234]글자배경색[bg]";

		String patternTxt = "\\[bg!(.*?)\\](.*)?\\[bg\\]";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		
		while ( match.find() ) {
			str = match.replaceFirst("<span style='background:$1'>$2</span>");
			match = pattern.matcher(str);
			System.out.println(str);	
		}
		
	}
	
	@Test
	public void 글자(){
		String str = "색깔을 입히자 ~ ? [fn|굴림]글자색~~[fn]";

		String patternTxt = "\\[fn\\|(.*?)\\](.*)?\\[fn\\]";
		
		Pattern pattern = null;
		Matcher match = null;
		pattern = Pattern.compile(patternTxt);
		match = pattern.matcher(str);
		
		String font = null;
		while ( match.find() ) {
			pattern = Pattern.compile("\\[fn\\|(.*?)\\](.*)?\\[fn\\]");
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
			
			str = match.replaceFirst("<span style=”font-family:'$1','"+font+"'”>$2</span>");
			match = pattern.matcher(str);
			System.out.println(str);	
		}
		
	}
	
	@Test
	public void 테이블(){
		/*String str = "-- asdf cvbsfg <b>aaa<br>  ||ㅁㄴㅇ1||ㅁㄴㅇ2||ㅁㄴㅇ3||ㅁㄴㅇ4||ㅁㄴㅇ5|| bbb \r\n " +
					 "||ㅁㄴㅇ6||ㅁㄴㅇ7||ㅁㄴㅇ8||ㅁㄴㅇ9||ㅁㄴㅇ10|| \r\n" +
					 "테이블 테스트 끝 1. \r\n" +
					 "||테이블1||테이블2||테이블3|| \r\n" +
					 "||테이블4||테이블4||테이블5||";*/
		/*
		String str = "테이블 테스트 할껍메다.~ \r\n"+
					"||row:1#컬럼1||컬럼2||col:15#컬럼3||\r\n"+
					"|컬럼5|col:2#컬럼6|컬럼7|\r\n"+
					"테이블 1번 끝\r\n"+
					"테이블 2번테스트 할껍메다.~ \r\n"+
					"||컬럼1||컬럼2||컬럼3||컬럼4||\r\n"+
					"|row:4#컬럼9|col:4#컬럼10|컬럼11|\r\n";
		*/
		String str = "|| 제목1 || 제목2 || 제목3 ||\r\n" +
				"| col:2#내용1 | row:2#내용2 |\r\n" +
				"| 내용1-1 | 내용 1-2 |\r\n";
		
		Pattern pattern = null;
		Matcher match = null;
		boolean matchFind = false;
		int table_cnt = 0;
		String ret = "";
		String[] strAll = str.split("\r\n");
		
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
				
				pattern = Pattern.compile("(?<=\\|)(((\\s)+?)?(col:(\\w+)#)(.*?)((\\s)+?)?)(?=\\|)");
				match = pattern.matcher(ret);
				ret = match.replaceAll("\r\n<td colspan=\"$2\">$3</td>");
				
				pattern = Pattern.compile("(?<=\\|)(((\\s)+?)?(row:(\\w+)#)(.*?)((\\s)+?)?)(?=\\|)");
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
			strAll[i] = ret+"\r\n";
			
		}
		
		str = "";
		for( int i=0; i<strAll.length; i++ ){
			str += strAll[i];
		}
		System.out.println(str);
	}
	
	@Test
	public void 테스트(){
		String str = "asdfasdf \r\n" +
					"-- 순서 있는 목록 1\r\n"+
					"-- 순서 있는 목록 2\r\n"+
					"--- 순서 있는 목록 1-1\r\n"+
					"--- 순서 있는 목록 1-2\r\n"+
					"--- 순서 있는 목록 1-3\r\n"+
					"---- 순서 있는 목록 1-3-1\r\n"+
					"---- 순서 있는 목록 1-3-2\r\n"+
					"---- 순서 있는 목록 1-3-3\r\n"+
					"--- 순서 있는 목록 1-3\r\n"+
					"---- 순서 있는 목록 2-3-1\r\n"+
					"---- 순서 있는 목록 2-3-2\r\n"+
					"---- 순서 있는 목록 2-3-3\r\n"+
					"---- 순서 있는 목록 2-3-4\r\n"+
					"-- 순서 있는 목록 3\r\n" +
					"asdfasdf";				
		
		//String patternTxt = "(^----)((.*?[\r\n]))";
		
		String[] strAll = str.split("\r\n");
		str = "";
		Pattern pattern = null;
		Matcher match = null;
		
		int cnt = 0;
		for( int i=0; i<strAll.length; i++ ){
			pattern = Pattern.compile("(^----)(\\s)(.*)", Pattern.MULTILINE);
			match = pattern.matcher(strAll[i]);
			if( match.find() ){
				cnt++;
				if( cnt == 1 ){
					strAll[i] = match.replaceFirst("<ol>\n<li class=\"lv3\">$3</li>\n");
				}else{
					strAll[i] = match.replaceFirst("<li class=\"lv3\">$3</li>\n");
				}
			}else{
				if( cnt > 0 ){
					strAll[i-1] = strAll[i-1]+"</ol>\n";
					strAll[i] = strAll[i]+"\r\n";
					cnt = 0;	
				}else{
					strAll[i] = strAll[i]+"\r\n";
				}
			}
		}

		for( int i=0; i<strAll.length; i++ ){
			str += strAll[i];
		}
		strAll = str.split("\r\n");
		str = "";
		
		cnt = 0;
		for( int i=0; i<strAll.length; i++ ){
			pattern = Pattern.compile("(^---)(\\s)(.*)", Pattern.MULTILINE);
			match = pattern.matcher(strAll[i]);
			if( match.find() ){
				cnt++;
				if( cnt == 1 ){
					strAll[i] = match.replaceFirst("<ol>\n<li class=\"lv2\">$3</li>\n");
				}else{
					strAll[i] = match.replaceFirst("<li class=\"lv2\">$3</li>\n");
				}
			}else{
				if( cnt > 0 ){
					strAll[i-1] = strAll[i-1]+"</ol>\n";
					strAll[i] = strAll[i]+"\r\n";
					cnt = 0;	
				}else{
					strAll[i] = strAll[i]+"\r\n";
				}
			}
		}
		
		for( int i=0; i<strAll.length; i++ ){
			str += strAll[i];
		}
		strAll = str.split("\r\n");
		str = "";
		
		cnt = 0;
		for( int i=0; i<strAll.length; i++ ){
			pattern = Pattern.compile("(^--)(\\s)(.*)", Pattern.MULTILINE);
			match = pattern.matcher(strAll[i]);
			if( match.find() ){
				cnt++;
				if( cnt == 1 ){
					strAll[i] = match.replaceFirst("<ol>\n<li >$3</li>\n");
				}else{
					strAll[i] = match.replaceFirst("<li >$3</li>\n");
				}
			}else{
				if( cnt > 0 ){
					strAll[i-1] = strAll[i-1]+"</ol>\n";
					strAll[i] = strAll[i]+"\r\n";
					cnt = 0;	
				}else{
					strAll[i] = strAll[i]+"\r\n";
				}
			}
		}
		
		for( int i=0; i<strAll.length; i++ ){
			str += strAll[i];
		}
		
		System.out.println(str);
		
		//System.out.println( match.replaceAll("<ul>\r\n$0") );
		
		
		/*while ( match.find() ) {
			
		}*/

	}
	
	
	@Test
	public void 테이블2(){
		
		String str = "|| col:2#제목1 || 제목2 || 제목3 ||\r\n" +
					 "| col:2#내용1|row:2#내용2|\r\n" +
					 "| 내용1-1 | 내용 1-2 |\r\n";
		
		Pattern pattern = null;
		Matcher match = null;
		boolean matchFind = false;
		int table_cnt = 0;
		String ret = "<tr>\r\n"+
					"|| 제목1||제목2||제목3||\r\n"+
					"</tr>\r\n";
		pattern = Pattern.compile("(?<=\\|\\|)((?!(\\s)+?)?(col:[\\w]+#)|((\\s)+?)?(row:[\\w]+#))(.*?)(?=\\|\\|)");
		match = pattern.matcher(str);
		
		str = match.replaceAll("\n<th class=\"\">$7</th>");
		System.out.println(str);
		System.out.println("==========");
		
		
		pattern = Pattern.compile("(?<=\\|\\|)(((\\s)+?)?(col:(\\w+)#)(.*?)((\\s)+?)?)(?=\\|\\|)");
		match = pattern.matcher(str);

		str = match.replaceAll("\n<th colspan=\"$5\">$2$6$7</td>");
		
		System.out.println(str);
		
		
	}
}









