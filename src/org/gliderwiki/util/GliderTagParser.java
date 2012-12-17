/**
 * @FileName  : GliderTagParserMethod2.java
 * @Project   : NightHawk
 * @Date      : 2012. 12. 13. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.util.parser.GliderTagParserMethodBasicTag;
import org.gliderwiki.util.parser.GliderTagParserMethodBoxTag;
import org.gliderwiki.util.parser.GliderTagParserMethodSpecialTag;


/**
 * @author ganji
 *
 */
public class GliderTagParser {

	GliderTagParserMethodBasicTag basic = new GliderTagParserMethodBasicTag();
	GliderTagParserMethodBoxTag box = new GliderTagParserMethodBoxTag();
	GliderTagParserMethodSpecialTag special = new GliderTagParserMethodSpecialTag();
	
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getHtml(String str){
		
		List characherList = EsCharacterList();
		
		for( int i=0; i < characherList.size(); i++ ){
			str = getParsering(str, (EsCharacter) characherList.get(i) );
		}
		
		Map<String, Object> parsingMap = new HashMap<String, Object>();
		
		parsingMap.put("htmltag", str);
		parsingMap.put("linkTagList", basic.linkTagList );
		parsingMap.put("noteTagList", box.noteTagList);
		parsingMap.put("h1TagList", special.h1TagList);
		parsingMap.put("graphCnt", special.graphCnt);
		
		return parsingMap;
	}
	
	
	private String getParsering(String str, EsCharacter ch){

		switch (ch) {
		
		case BOLD:
			str = basic.getBOLD(str);
			break;
			
		case ITALIC:
			str = basic.getITALIC(str);
			break;
			
		case STRIKE:
			str = basic.getSTRIKE(str);
			break;
		
		case SUBERSCRIPT:
			str = basic.getSUBERSCRIPT(str);
			break;
		
		case SUPERSCRIPT:
			str = basic.getSUPERSCRIPT(str);
			break;
		
		case HR:
			str = basic.getHR(str);
			break;
			
		case HIPERLINK:
			str = basic.getHIPERLINK(str);
			break;
			
		case IMG:
			str = basic.getIMG(str);
			break;
			
		case UNDERLINING:
			str = basic.getUNDERLINING(str);
			break;
		
		case BACKGROUND:
			str = basic.getBACKGROUND(str);
			break;
			
		case COLOR:
			str = basic.getCOLOR(str);
			break;
			
		case FONT:
			str = basic.getFONT(str);
			break;
			
		case FONTALIGN:
			str = basic.getFONTALIGN(str);
			break;
			
		case FONTSIZE:
			str = basic.getFONTSIZE(str);
			break;
			
		case ALERT:
			str = box.getALERT(str);
			break;
			
		case BOX:
			str = box.getBOX(str);
			break;
			
		case FIELD:
			str = box.getFIELD(str);
			break;
			
		case INFO:
			str = box.getINFO(str);
			break;
		
		case NOTE:
			str = box.getNOTE(str);
			break;
			
		case GRAPH:
			str = special.getGRAPH(str);
			break;
			
		case H1:
			str = special.getH1(str);
			break;
			
		case LINE:
			str = special.getLINE(str);
			break;
		
		case TABLE:
			str = special.getTABLE(str);
			break;
			
		case SYNTAXAFTER:
			str = special.getSYNTAX(str, false);
			break;
			
		case SYNTAXBEFORE:
			str = special.getSYNTAX(str, true);
			break;
			
		case BR:
			str = basic.getBR(str);
			
		default:
			break; 
		}
		
		
		return str;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List EsCharacterList(){
		
		List list = new ArrayList();
		list.add(EsCharacter.SYNTAXBEFORE);
		
		list.add(EsCharacter.BOLD);
		list.add(EsCharacter.ITALIC);
		list.add(EsCharacter.UNDERLINING);
		list.add(EsCharacter.SUBERSCRIPT);
		list.add(EsCharacter.SUPERSCRIPT);
		list.add(EsCharacter.STRIKE);
		list.add(EsCharacter.HIPERLINK);
		list.add(EsCharacter.IMG);
		list.add(EsCharacter.BACKGROUND);
		list.add(EsCharacter.COLOR);
		list.add(EsCharacter.FONT);
		list.add(EsCharacter.FONTSIZE);
		list.add(EsCharacter.FONTALIGN);
		list.add(EsCharacter.HR);
		
		list.add(EsCharacter.ALERT);
		list.add(EsCharacter.BOX);
		list.add(EsCharacter.FIELD);
		list.add(EsCharacter.INFO);
		list.add(EsCharacter.NOTE);
		
		list.add(EsCharacter.GRAPH);
		list.add(EsCharacter.H1);
		list.add(EsCharacter.LINE);
		list.add(EsCharacter.TABLE);
		list.add(EsCharacter.SYNTAXAFTER);
		
		list.add(EsCharacter.BR);
		
		return list;
	}
	
	private enum EsCharacter {
		BOLD, ITALIC, UNDERLINING, STRIKE, SUPERSCRIPT, SUBERSCRIPT, HIPERLINK, 
		H1, NOTE, SYNTAXAFTER, SYNTAXBEFORE, LINE, IMG, ALERT, FIELD, INFO, TABLE,
		GRAPH, COLOR, BACKGROUND, FONT, FONTSIZE, FONTALIGN, BOX, HR, BR
		
	}
}
