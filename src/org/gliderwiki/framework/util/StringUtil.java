package org.gliderwiki.framework.util;

/**
 * String 처리와 관련된 공통 유틸리티
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {

    // 여부 상수
    public final static String YES              = "Y";
    public final static String NO               = "N";

    /** The Constant EMPTY. */
    public static final String   EMPTY       = "";

    /** The Constant EMPTY_ARRAY. */
    public static final String[] EMPTY_ARRAY = new String[] {};

    /**
     * Default charset to use for encoding/decoding strings.
     */
    public static final String ENCODING_CHARSET = "UTF-8";


	/**
	 * 해당 확장자가 섬네일 이미지를 생성하는 대상인지 판별 (JPG, PNG, GIF)
	 *
	 * @param extendname
	 * @return
	 */
	public static String getThumbnailYn(String extendname) {
		if (extendname.equalsIgnoreCase("jpg")
				|| extendname.equalsIgnoreCase("png")
				|| extendname.equalsIgnoreCase("gif")) {
			return "Y";
		} else {
			return "N";
		}
	}

	 /**
     * null 또는 "" 공백 검사.
     *
     * @param String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if ( str == null || str.length() < 1 )
            return true;

        return false;
    }

    /**
     * null 값을 "" 공백 문자로 바꿔준다.
     *
     * @param String
     * @return String
     */
	public static String strNull(String str) {
		String strVal = str;
        if ( strVal == null || strVal.length() < 1 ) {
            return "";
        }
        return strVal;
	}

	 /**
     * 값이 null이거나 값이 없을 경우 "" 지정된 Default 문자로 바꿔준다.
     *
     * @param String
     * @return String
     */
    public static String strNullToSpace(String str, String strDefault) {
        String strVal = str;
        if ( strVal == null || strVal.length() < 1 ) {
            return strDefault;
        }
        return strVal;
    }

    /**
     * Integer 값이 null이면 0, 그렇지않으면 그대로 돌려준다
     * @param strInt
     * @param intDefault
     * @return
     */
    public static Integer intNull(Integer strInt) {
        if ( strInt == null ) {
            return 0;
        }
        return strInt;
    }


	public static String convertHtmlBr(String comment) {
		int length = comment.length();

        StringBuffer buffer = new StringBuffer();
        if ( comment.indexOf("<table") == -1 ) {
            for ( int i = 0 ; i < length ; ++i ) {
                String comp = comment.substring(i, i + 1);

                if ( "\r".compareTo(comp) == 0 ) {
                    comp = comment.substring(++i, i + 1);
                    if ( "\n".compareTo(comp) == 0 ) {
                        buffer.append("<BR>\r");
                    }
                    else {
                        buffer.append("\r");
                    }
                }

                buffer.append(comp);
            }
        }
        else {
            return comment;
        }

        return buffer.toString();
	}

	 /**
     * 파일경로에서 확장자만 추출해서 반환한다.
     *
     * @param String
     * @return String
     */
    public static String getFileExt(String filePath) {
        int index = filePath.lastIndexOf(".");

        if ( index == -1 ) {
            return "";
        }

        return filePath.substring(index + 1, filePath.length());
    }

	/**
     * null 값을 오늘 일자로 바꿔준다. (형식: yyyy-MM-dd)
     *
     * @param String
     * @return String
     */
    public static String nullToDate(String str) {
        if ( str == null || str.length() < 1 ) {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.KOREA);
            return dateFormat.format(new java.util.Date());
        }
        return str;
    }



    /**
     * &lt; &gt; &nbsp; 를 html 태그로 변환한다.
     * @param contents
     * @return
     */
	public static String text2html(String contents) {
		contents = contents.replaceAll("&lt;", "<");
		contents = contents.replaceAll("&gt;", ">");
		contents = contents.replaceAll("&nbsp;", " ");
		return contents;
	}

	public static String strCut(String str, Integer size) {
		// Vector returnsStr = new Vector();

		// String ctmp = str.replaceAll("'", "''");

		int cntlen = str.getBytes().length;
		int bylen = 0, strlen = str.length();

		char c;
		String ct = "";
		if (cntlen > size) {
			for (int i = 0; i < strlen; i++) {
				c = str.charAt(i);
				bylen++;
				if (c > 127) {
					bylen++; // 한글이다..
				}
				if (size < bylen) {
					ct = str.substring(0, i);
					break;
				}
			}
			ct = ct + "...";
		} else {
			ct = str;
		}

		return ct;
	}

	public static String cut(String str, Integer maxLength) {
		if (isEmpty(str)) {
			return str;
		}

		if (maxLength < 0) {
			return str;
		}

		if (str.length() <= maxLength) {
			return str;
		}

		String cutStr = str.substring(0, maxLength) + "...";
		return cutStr;
	}

	/**
	 * HTML 태그를 지운다.
	 *
	 * @param s
	 * @return
	 */
	public static String removeTag(String s) {
		if (s == null || s.equals("")) {
			s = "";
		} else {
			// s =
			// s.replaceAll("(?:<!.*?(?:--.*?--\\s*)*.*?>)|(?:<(?:[^>'\"]*|\".*?\"|'.*?')+>)","");

			Pattern p = Pattern.compile("\\<(\\/?)(\\w+)*([^<>]*)>");
			Matcher m = p.matcher(s);
			s = m.replaceAll("");

			// s = s.replaceAll("&nbsp;", "");
		}
		return s;

	}

	/**
	 * script style 등을 지운다.
	 *
	 * @param s
	 * @return
	 */
	public static String removeScript(String s) {
		if (s == null || s.equals("")) {
			s = "";
		} else {

			Pattern p = Pattern
					.compile("\\<(\\/?)(script|style|SCRIPT|STYLE)([^<>]*)>");
			Matcher m = p.matcher(s);
			s = m.replaceAll("");
		}
		return s;

	}


	/**
	 * 현재 URL 을 반환한다.
	 * @param request
	 * @return
	 * @Author yion
	 * @History
	 * @Method 설명
	 * @Method Name  : getURL
	 */
	public static String getURL(HttpServletRequest request) {
		Enumeration<?> param = request.getParameterNames();

		StringBuffer strParam = new StringBuffer();
		StringBuffer strURL = new StringBuffer();

		if (param.hasMoreElements()) {
			strParam.append("?");
		}

		while (param.hasMoreElements()) {
			String name = (String) param.nextElement();
			String value = request.getParameter(name);

			strParam.append(name + "=" + value);

			if (param.hasMoreElements()) {
				strParam.append("&");
			}
		}

		strURL.append(request.getRequestURI());
		strURL.append(strParam);

		return strURL.toString();
	}

	/**
	 * 넘어온 delimeter 가 원본 str 에 몇개나 포함되어있는지 갯수를 판단하여 리턴한다.
	 *
	 * @param str : 원본 문자열
	 * @param delim : 구분자
	 * @return 구분자가 포함되어 있는 갯수
	 * @Author yion
	 * @History
	 * @Method 설명
	 * @Method Name  : patternMatchCount
	 */
	public static int patternMatchCount(String str, String delim) {


		Pattern p= Pattern.compile(delim);
		Matcher m = p.matcher(str);

		int count = 0;
		for (int i=0; m.find(i); i=m.end()) {
			count++;
		}

		return count;

	}

	//암호화 passkey generator
	public static String stringBuffersChars(int len) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		String chars[] = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z"
				.split(",");

		for (int i = 0; i < len; i++) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}

		System.out.println("buffer.toString() :" + buffer.toString());
		return buffer.toString();
	}

    /**
     * 문자열에 포함되어 있는 < 와 > 를 없애 버린다
     *
     * @param contents
     * @return
     */
    public static String removeTags(String contents) {

        StringBuffer source = new StringBuffer(contents);
        StringBuffer target = new StringBuffer();
        boolean deleteChar = false;

        for ( int i = 0 ; i < source.length() ; i++ ) {
            if ( source.charAt(i) == '<' ) {
                deleteChar = true;
            }
            else if ( source.charAt(i) == '>' ) {
                deleteChar = false;
            }
            else if ( !deleteChar ) {
                target.append(source.charAt(i));
            }
        }

        return target.toString();
    }

    /**
     * IMG 태그의 src 필드의 내용에 URLEncoder를 적용시킨다.<br/>
     * IMG 태그의 사용은 반드시 대소문자를 구별하여 IMG, src, 큰 따옴표(")를 사용하여야 한다. <br/>
     * (예: <IMG src="예제.jpg"> )<br/>
     * SpaceEditor를 사용하는 경우 위와 같은 형식으로 세팅된다.<br/>
     *
     * @param contents
     *            변경할 내용
     * @param enc
     *            encoding type (예: EUC-KR)
     * @return 변경된 스트링
     * @throws UnsupportedEncodingException
     */
    public static String convertImgURLEncode(String contents, String enc)
            throws UnsupportedEncodingException {
        String returnContents = contents;
        if ( contents == null || enc == null )
            return null;

        int endIdx = 0;
        for ( int i = 0 ; i < contents.length() ; i = endIdx + 1 ) {
            int imgIdx = contents.indexOf("<IMG", i);
            if ( imgIdx == -1 )
                break;

            int srcIdx = contents.indexOf("src", imgIdx);
            if ( srcIdx == -1 )
                break;

            int beginIdx = contents.indexOf("\"", srcIdx);
            if ( beginIdx == -1 )
                break;

            endIdx = contents.indexOf("\"", beginIdx + 1);
            if ( endIdx == -1 )
                break;

            String replaceStr = contents.substring(beginIdx + 1, endIdx);
            returnContents = contents.replace(replaceStr, URLEncoder.encode(replaceStr, enc));
        }

        return returnContents;
    }

    /**
     * 대상 String 에서 특정 String을 찾아서 다른 String으로 대체하여 return
     *
     * @param str
     *            대상 String
     * @param from
     *            찾는 String
     * @param to
     *            취환할 String
     */
    public static String replaceEx(String str, String from, String to) {
        String sResult = "";
        try {
            if ( str == null || str.length() == 0 || from == null || from.length() == 0
                    || to == null || to.length() == 0 )
                return str;

            StringBuffer sb = null;

            sb = new StringBuffer(str.length() * 2);
            String posString = str.toLowerCase();
            String cmpString = from.toLowerCase();
            int i = 0;
            boolean done = false;
            while ( i < str.length() && !done ) {
                int start = posString.indexOf(cmpString, i);
                if ( start == -1 ) {
                    done = true;
                }
                else {
                    sb.append(str.substring(i, start) + to);
                    i = start + from.length();
                }
            }
            if ( i < str.length() ) {
                sb.append(str.substring(i));
            }

            sResult = sb.toString();
        }
        catch ( Exception e ) {
            sResult = str;
        }
        finally {

        }

        return sResult;
    }

    /**
     * 대상 String 에서 < 와 > & 를 &lt; 와 &gt; 와 &amp;로 바꿔서 return
     *
     * @param sHTML
     *            대상 String
     */
    public static String replaceHtmlTag(String sHTML) {
        if ( sHTML == null || sHTML.trim().equals("") )
            return "";

        String sResult = "";
        StringBuffer sbResult = null;

        try {
            String s = "";
            sbResult = new StringBuffer();

            for ( int i = 0 ; i < sHTML.length() ; i++ ) {
                s = sHTML.substring(i, i + 1);

                if ( s.equals("<") ) {
                    sbResult.append("&lt;");
                }
                else if ( s.equals(">") ) {
                    sbResult.append("&gt;");
                }
                else if ( s.equals("\"") ) {
                    sbResult.append("&quot;");
                }
                else if ( s.equals("'") ) {
                    sbResult.append("&#39;");
                }
                else if ( s.equals("&") ) {
                    sbResult.append("&amp;");
                }
                else {
                    sbResult.append(s);
                }
            }

            sResult = sbResult.toString();
        }
        finally {
            sbResult = null;
        }
        sResult = replaceEx(sResult, "/*", "");
        sResult = replaceEx(sResult, "*/", "");
        sResult = replaceEx(sResult, "%", "");
        sResult = replaceEx(sResult, "%00", "");
        return sResult;
    }

    // XSS 처리
    public static String cleanXSS(String str) {
        String strVal = str;
        strVal = strVal.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        strVal = strVal.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        strVal = strVal.replaceAll("'", "&#39;");
        strVal = strVal.replaceAll("eval\\((.*)\\)", "");
        strVal = strVal.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");
        strVal = strVal.replaceAll("[\\\"\\'][\\s]*JAVASCRIPT:(.*)[\\\"\\']", "\"\"");
        strVal = strVal.replaceAll("&lt;/script&gt;", "");
        strVal = strVal.replaceAll("&lt;SCRIPT&gt;", "");
        strVal = strVal.replaceAll("&lt;/script&gt;", "");
        strVal = strVal.replaceAll("&lt;SCRIPT&gt;", "");
        return strVal;
    }

    // XSS 처리사항 원복
    public static String remakeXSS(String str) {
        String strVal = str;
        strVal = strVal.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
        strVal = strVal.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
        strVal = strVal.replaceAll("&#39;", "'");
        return strVal;
    }

    /**
     * 대상 String 에서 < 와 > & 를 &lt; 와 &gt; 와 &amp;로 바꿔서 return
     *
     * @param sHTML
     *            대상 String
     */
    public static boolean checkScriptTag(String data) throws Exception {

        boolean bExistTag = false;
        if ( data == null || data.length() <= 0 ) {
            return false;
        }

        try {
            String sHTML = data; // new String(data);
            sHTML = sHTML.toLowerCase().replace(" ", "");
            sHTML = sHTML.replace("\n", "");

            if ( sHTML.length() <= 0 )
                return false;

            if ( sHTML.indexOf("<script") != -1 || sHTML.indexOf("<iframe") != -1
                    || sHTML.indexOf("style") != -1 || sHTML.indexOf("src") != -1 ) {
                bExistTag = true;
            }

        }
        catch ( Exception e ) {
            throw new Exception(e);
        }
        finally {

        }
        return bExistTag;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     *
     * @param source
     *            원본 문자열 배열
     * @param length
     *            지정길이
     * @param suffix
     *            더할문자열
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int length, String suffix) {
        if ( source == null )
            return "";
        if ( source.length() <= length )
            return source;

        suffix = strNull(suffix);
        return source.substring(0, length - suffix.length()) + suffix;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     *
     * @param source
     *            원본 문자열 배열
     * @param length
     *            지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int length) {
        return cutString(source, length, "");
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     *
     * @param source
     *            원본 문자열 배열
     * @param length
     *            지정 바이트 길이
     * @param suffix
     *            더할문자열
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutByteString(String source, int length, String suffix) {
        if ( source == null )
            return "";

        Charset charset = Charset.forName(StringUtil.ENCODING_CHARSET);
        byte[] bytes = source.getBytes(charset);

        if ( bytes.length <= length )
            return source;

        suffix = strNull(suffix);
        int suffixLength = suffix.length();
        if ( length <= suffixLength )
            return suffix;

        length -= suffixLength;
        int counter = 0;
        for ( int i = length - 1 ; i >= 0 ; i-- ) {
            if ( ( bytes[i] & 0x80 ) == 0 )
                break;
            counter++;
        }

        return new String(bytes, 0, length - ( counter % 3 ), charset) + suffix;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     *
     * @param source
     *            원본 문자열 배열
     * @param length
     *            지정 바이트 길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutByteString(String source, int length) {
        return cutByteString(source, length, "");
    }

    /**
     * SMS 메세지를 가져온다. 40자 단위로 메세지를 잘라온다.
     *
     * @param msg
     * @return
     */
    public static String[] getSMSMessage(String msg) {
        Charset charset = Charset.forName(StringUtil.ENCODING_CHARSET);
        byte[] bytes = msg.getBytes(charset);

        // 문자 분할
        List<String> messages = new ArrayList<String>();
        if ( bytes.length <= 40 ) {
            messages.add(msg);
        }
        else {
            int begin = 0;
            int finish = 40;
            boolean doParse = true;
            while ( doParse ) {
                int counter = 0;
                for ( int i = finish - 1 ; i >= begin ; i-- ) {
                    if ( ( bytes[i] & 0x80 ) == 0 )
                        break;
                    counter++;
                }
                int length = 40 - ( counter % 3 );
                if ( finish - begin < 40 ) {
                    length = finish - begin;
                }
                messages.add(new String(bytes, begin, length, charset));

                if ( finish >= bytes.length ) {
                    doParse = false;
                }
                begin += length;
                finish = begin + 40;
                if ( finish > bytes.length ) {
                    finish = bytes.length;
                }
                // System.out.println(begin);
                // System.out.println(finish);
            }
        }

        String[] arr = new String[messages.size()];
        return messages.toArray(arr);
    }

    /**
     * 입력한 문자열이 길이가 0보다 큰 문자열인지 확인한다.
     *
     * @param str
     *            확인할 문자열
     * @return 입력한 문자열이 null이 아니고 길이가 0보다 크면 <code>true</code>
     */
    public static boolean hasLength(String str) {
        return ( str != null && str.length() > 0 );
    }

    /**
     * 입력한 문자열이 공백 문자 이외의 문자열을 포함하고 있는지 확인한다.
     *
     * @param text
     *            확인할 문자열
     * @return 입력한 문자열이 공백 문자 이외의 문자열을 포함하고 있다면 <code>true</code>
     */
    public static boolean hasText(String text) {
        if ( !hasLength(text) ) {
            return false;
        }

        for ( int i = 0, l = text.length() ; i < l ; i++ ) {
            if ( !Character.isWhitespace(text.charAt(i)) ) {
                return true;
            }
        }
        return false;
    }

    public static boolean wildCardMatch(String text, String pattern) {
        String[] blocks = pattern.split("\\*");

        for ( String block : blocks ) {
            int idx = text.indexOf(block);

            if ( idx == -1 ) {
                return false;
            }

            text = text.substring(idx + block.length());
        }

        return true;
    }

    public static String cutAndRemoveTag(String str, Integer len) {
    	return strCut(removeTag(str), len);
    }
}