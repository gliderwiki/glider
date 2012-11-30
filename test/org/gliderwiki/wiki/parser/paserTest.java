/**
 * @FileName  : paserTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 4. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.util.GliderTagParser;


/**
 * @author ganji
 *
 */
public class paserTest {

	/**
	 * @param args
	 * 
	 * (1) exam1 결과
	 * 1. del 태그 변경 
	 * 2. note 태그를 잘못 썼음. 용은이가 태그규약에 맞게 변경해야함 ok
	 * 
	 * (2) exam2 결과
	 * 1. 순번 에러발생 ok
	 * 2. img태그 error	ok
	 * 3. link 태그 수정이 필요한듯. ok
	 * 
	 * (3) emam3 결과
	 * 여기에서는 테이블tag 개발후 확인 
	 * 주요내용은 테이블tag pasering
	 * 
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GliderTagParserTest gtp = new GliderTagParserTest();
		GliderTagParser gtpReal = new GliderTagParser();
		
		/*String str = "h1. 대선출마는 언제???\r\n"+
				"\r\n"+
				
				"\r\n"+
				"[sp]위첨자테스트[sp]\r\n"+
				"\r\n"+
				"[sb]아래첨자테스트[sb]\r\n"+
				"\r\n"+
				"__*[color|76923c]글자색깔은 잘 나오남???[color]*__\r\n"+
				"\r\n"+
				"[font|궁서][size|18]글자 크기와 사이즈 테스트합니다. [size] [font]\r\n"+
				"\r\n"+
				"[align:c]h1. 다음 제목 출제[align]\r\n"+
				"[align:l]h2. [box|b8cce4]부제목은??. 박스배경색도 테스트함[box][align]\r\n"+
				"\r\n"+
				"[hr]\r\n"+
				"\r\n"+
				"[@:http://www.okjsp.pe.kr/seq/200072|스프링원가게 되었습니다.]\r\n"+
				"\r\n"+
				"[!|/resource/temp/8/20120919/thumb/thumb_2012091928772396031552.jpg|코알라 이미지]\r\n"+
				"\r\n"+
				"[%]\r\n"+
				"['20',30], \r\n"+
				"['40',70], \r\n"+
				"pie[EmhUTaQq] \r\n"+
				"[%]\r\n"+
				"\r\n"+
				"\r\n"+
				"[alert] 주의사항[alert] [info]알림사항[info]\r\n"+
				"\r\n"+
				"||제목||내용||조회수|| \r\n"+
				"|1|2|3| \r\n"+
				"|4|5|6| \r\n"+
				"\r\n"+
				"\r\n"+
				"#구분선\r\n"+
				"-나니까?\r\n"+
				"\r\n"+
				"들여쓰기는 되남???\r\n"+
				"\r\n"+
				"\r\n" +
				"[field|타이틀]필드d 테스트[field]\r\n";*/
		
		String str = "[syntax] \r\n"+
					"asdfasdfasdfasdf\r\n"+
					"[syntax]";
		
		/*String str = "중국 분위기를 풍기는 것들이 대부분이다.\r\n"+
					"\r\n"+
					"[note|가오화펑#가오위평의 설명입니다.]\r\n"+
					"[note|선농펑#aa]\r\n";		
		*/
		/*String str = "[@:http://www.okjsp.pe.kr/seq/200072|스프링원가게 되었습니다.]\r\n"+
				"\r\n"+
				"[!|/resource/temp/8/20120919/thumb/thumb_2012091928772396031552.jpg|코알라 이미지]\r\n";
		*/
		//System.out.println(str);
		//str = str.replaceAll("\\n", "<br />");
		
		Map<String, Object> paseringMap = new HashMap<String, Object>();
		
		/*paseringMap = gtp.parserMap(str);
		
		System.out.println("===================================테스트========================================");
		//System.out.println( "htmltag  :  :  \n" + paseringMap.get("htmltag") );
		System.out.println( "linkTagList  :  :  \n" + paseringMap.get("linkTagList") );
		System.out.println( "noteTagList  :  :  \n" + paseringMap.get("noteTagList") );
		System.out.println( "h1TagList  :  :  \n" + paseringMap.get("h1TagList") );
		System.out.println( "h2TagList  :  :  \n" + paseringMap.get("h2TagList") );
		System.out.println( "h3TagList  :  :  \n" + paseringMap.get("h3TagList") );
		System.out.println("===================================테스트========================================");
		*/
		paseringMap = new HashMap<String, Object>();
		paseringMap = gtpReal.parserMap(str);
		System.out.println( "htmltag  :  :  \n" + paseringMap.get("htmltag") );
		/*System.out.println("===================================리얼========================================");
		System.out.println( "htmltag  :  :  \n" + paseringMap.get("htmltag") );
		System.out.println( "linkTagList  :  :  \n" + paseringMap.get("linkTagList") );
		System.out.println( "noteTagList  :  :  \n" + paseringMap.get("noteTagList") );
		System.out.println( "h1TagList  :  :  \n" + paseringMap.get("h1TagList") );
		System.out.println( "h2TagList  :  :  \n" + paseringMap.get("h2TagList") );
		System.out.println( "h3TagList  :  :  \n" + paseringMap.get("h3TagList") );
		System.out.println("===================================리얼========================================");*/
		
	}

	
	/**
	 * 

		String str = "  @@@@@@@@@@@@@@예제 1"+
						"<br /><br />"+
						"-인생 경기를 보았다"+
						"<br /><br />"+
						"“메모를 봤어요. **경기장 오기 전에 숙소에서.** 전에 중요한 경기에서 패할 때마다 마음에 담아 두었던 걸 써 놓은 메모요. 꽤나 아팠거든요. 그걸 보면서 //__오늘은 절대 패하지 말자고 마음먹었죠.__//”"+
						"<br /><br />"+
						"[info]<br />"+
						"주장 구자철은 경기가 끝나고도 한 동안 흥분이 가라앉지 않는 듯 가쁜 숨을 몰아쉬며 말했다. 목소리는 떨렸고 눈가는 젖어있었다.<br />"+
						"[info]<br />"+
						"<br />"+
						"그럴 만 했다. 아니 충분히 그러고도 남았다. <del>지나온 기억 속에서 좌절했던 순간을 한 번에 날려버릴 승리를 이끌어냈으니 충분히 그럴 만 했다. 가야 할 길이 많고 또 길지만 지난 새벽 한일전은 축구 인생에 강렬하게 남을 명승부였다.</del> 구자철의 인생 경기라 할 만 했다."+
						"<br /><br />"+
						"어디 구자철만의 이야기인가. [[note|런던올림픽을 치르며 매 순간 뜨거운 이슈메이커였던]] 박주영이 일본 수비진을 한꺼번에 무너뜨리며 기막힌 결승골을 터뜨린 거 하며 막판 일본의 추격 골이 파울 선언되지 않았더라면 출전을 장담할 수 없었던 김기희까지 <pre>이번 런던올림픽은</pre>, 또 동메달을 놓고 싸운 한일전은 <sup>모든 선수들에게 인생 경기라 할 만 했다.</sup>"+
						"<br /><br />";


		String str = "@@@@@@@@@@@@@@@@예제 2 \n"+
				"\n"+
				"##레벨1\n"+
				"###레벨2 \n"+
				"####레벨3 \n"+
				"솔직히 전반 뛰는 걸 보면서는 후반 막판까지 계속해서 이렇게 뛸 수 있을까 하는 걱정이 들만큼 우리 선수들이 너무 많이 뛴다고 생각했다. 오버워크에 대한 우려였다. <br />이틀씩 쉬고 경기하는 올림픽 일정상 체력 부담이 적지 않은 3,4위전이었다. 그런데 지치지 않았다. 경기 종료 휘슬이 울릴 때까지 우리 선수들은 일본을 계속해서 압박 했다. 체력적 강인함보다는 승리에 대한 간절함이 가른 승부였다. 체력 부담은 한국과 일본이 다르지 않았지만 승리에 대한 보다 강렬한 갈망이 이끈 결과였다. 이처럼 몸을 던지는 경기도 없었다.\n"+
				"[[hr]]\n"+
				"[info]\n"+
				"그래서 고맙다. [note|숨이] 턱까지 올라 찼을 텐데 [note|마지막] 순간까지 모든 힘을 다 쏟아내며 싸워준 우리 [note|선수들] 모두가 고맙다.\n"+
				"\n"+
				"[info]\n"+
				"h1. Large Heading \n"+
				"[!|http://imgnews.naver.net/image/109/2012/08/11/201208110044771510_1.jpg|우사인볼트표정ㅋㅋ]\n"+
				"[info]\n"+
				"[OSEN=강필주 기자]\"볼트를 이길 수 있다.\"\n"+
				"[info]\n"+
				"\n"+
				"h2. Medium Heading\n"+
				"\n"+
				"\n"+
				"\n"+
				"하이퍼링크를 걸자 어디로? [@:http://www.daum.net|다음이~] 다음이닷!!  \n"+
				"\n"+
				"\n"+
				"[@:http://https://ucloudbiz.olleh.com/console/console.iaas.csserver.html|유클라우드 비즈입니다.]\n"+
				"\n"+
				"\n"+
				"h3. Small Heading\n"+
				"\n"+
				"하이퍼링크를 걸자 어디로? [@:http://www.google.com] 여기에도 걸어야지~~ㅋㅋ\n"+
				"\n"+
				"\n"+
				"여기서 [@:http://www.test.com|헤드라인] 입니다.\n";


		String str = "@@@@@@@@@@@@@@예제 3\n"+

						"|| 이남희|| 신재근|| 김용훈|| 박세우|| 손지성|| 최용은||\n"+ 
						" | 1 | 심슨 | 실력자 | 웹인스톨러 | 파서 | 에디터 | \n"+
						" | one | 멋쟁이 | 우신고 | 광화문 | 손간지1 | 용은이1 | \n"+
						" | captin | sm5 | 화이팅 | 강아지 | 손간지2 | 용은이2 | \n"+
						" | cheif | 갑 | 스페이스 | 크르릉 | 손간지3 | 용은이3 | \n"+
						"\n"+
						"<br />\n"+
						"\n"+
						"[[!|http://imgnews.naver.net/image/109/2012/08/11/201208110044771510_1.jpg|우사인볼트표정ㅋㅋ]]\n"+
						"[info]\n"+
						"[OSEN=강필주 기자]\"볼트를 이길 수 있다.\"\n"+
						"[info]\n"+
						"\n"+
						"미국프로풋볼(NFL) 러닝백 크리스 존슨(27, 테네시 타이탄스)이 '인간 번개' 우사인 볼트(자메이카)에 뜬금없는 도전장을 내밀었다. \n"+
						"[[hr]]\n"+
						"볼트는 2012 런던올림픽에서 육상 남자 100m에서 9초63으로 올림픽 신기록을 세우며 금메달을 따냈다. 게다가 200m에서도 우승, 올림픽 2연패를 달성한 상태다. 남은 400m 계주까지 성공할 경우 올림픽 3관왕을 2연패하게 된다. \n"+
						"\n"+
						"**이에 지난 10일(한국시간) 미국 언론들은 일제히 테네시 지역지 <테네시안>을 인용, 존슨이 볼트와 대결에서 이길 수 있다는 자신감을 내비쳤다고 보도했다. NFL서 가장 빠른 런닝백 중 한 명인 존슨은 40야드(약 37m)를 4초24에 끊을 정도로 빠른 발을 가지고 있다. 미국 올랜도 올림피아고교 시절 육상 선수로도 활약했던 그는 2004년 100m에서 10초50를 기록하며 미국 내 10위에 들었을 정도라고. **\n"+
						"\n"+
						"[alert]\n"+
						"존슨은 <테네시안>과의 인터뷰에서 \"내가 계속 트랙 훈련을 받았다면 할 수 있을 것이다. 그러나 나는 풋볼을 하고 있고 그는 트랙을 달리고 있다. 완전히 다른 것\"이라고 전제를 하면서도 \"그래도 40야드라면 이길 수 있을 것 같다. 서로 열심히 거리에 맞춰 훈련한다면 기회가 있을 것 같다\"고 자신감을 내보였다. 결국 100m의 3분 1 정도를 뛴다면 이길 수도 있다는 뜻이다.\n"+
						"[alert]\n"+
						"\n"+
						"이에 대한 반응은 탐탁치 않다. 언론들은 존슨의 자신감은 존경할 만하지만 적어도 자신의 PR 방법을 잘 알고 있는 것 같다고 깎아내렸다. 사실 존슨은 2년 전인 2010년에도 볼트와의 승부를 원했던 바 있다. 당시에도 자선경기를 위해 볼트에 도전장을 내밀기도 했다. 결국 관심끌기용 멘트지만 런던올림픽 MVP 후보로까지 거론되는 볼트의 인기를 반영하는 해프닝이기도 하다. \n"+
						"\n"+
						"한편 볼트는 이론적으로 40야드를 뛸 경우 <sub>3초97</sub>에 끊을 수 있는 것으로 알려져 있다. \n"+
						"\n"+
						"<a href=\"http://www.naver.com\">네이버컴 </a> 링크 합니다. \n"+
						"ㅋㅋㅋㅋ\n";


		String str = "@@@@@@@@@@@@@@@@@@@예제4\n"+
				"\n"+
				"##원형차트 \n"+
				"[%]\n"+
				"	['1',1111]\n"+
				"	['2',222]\n"+
				"	['3',3333]\n"+
				"	['4',4444]\n"+
				"	['5',55555]\n"+
				"bar[target_bar22]\n"+
				"pie[target_pie22]\n"+
				"[%]\n"+
				"\n"+
				"\n"+
				*/
				//"__차트__ **//만들었어요//**\n";

}
