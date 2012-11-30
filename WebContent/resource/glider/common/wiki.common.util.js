/*
	javascript utility
*/


$(document).ready(function() {
		
});



/**
 * ajaxForm 사용시 더미데이터 처리 
 * @return : String
 */
 String.prototype.removePre = function(){
	
 	var temp = this;
 	var startPre = "<";
 	var endPre = ">";
 	
 	var startPreInt = temp.indexOf(startPre);
 	var endPreInt = temp.indexOf(endPre)+1;

 	var startNextInt = temp.lastIndexOf(startPre);
 	var endNextInt = temp.lastIndexOf(endPre)+1;
 	
 	var preTemp = temp.substring(startPreInt, endPreInt); 
 	var nextTemp = temp.substring(startNextInt, endNextInt);
 	
 	temp = temp.replace( preTemp, "" ); 
 	temp = temp.replace( nextTemp, "" );
 	
 	return temp; 
 };

/**
 * 문자열 길이 특정 길이로 제한하고 잘리는 부분은 ...처리(Byte처리) 
 */
 function setStringByte(str, size){
 	
 	var str_length = 0;			//입력받은 문자열의 길이
 	var limit_length = 0;		//제한 바이트
 	var byte_length = 0;		//입력받은 문자열의 길이 바이트로 변환
 	var limit_index = 0;		//제한 바이트 걸리는 시점의 문자열 인덱스
 	var limit_flag = false;		//제한여부
 	
 	//처리할 문자열과 제한 문자열의 크기 취득
 	str_length = str.length;
 	limit_length = size;
 	
 	//문자열 길이 바이트 길이로 변환
 	for(var i=0; i<str_length; i++){
 		
 		var tmp = escape(str.charAt(i));
 		
 		if(tmp.length == 1){
 		      
 			byte_length++;
       
 	    }
 	    else if (tmp.indexOf("%u") != -1){
        
 	    	byte_length += 2;
   	
 	    }
 	    else if (tmp.indexOf("%") != -1){
       
 	    	byte_length += tmp.length / 3;
 	    	
 	    }
 		
 		//길이제한이 걸리는 시점의 글자수 저장
 		if(byte_length > limit_length && limit_flag == false){
 			
 			limit_index = i;
 			
 			limit_flag = true;
 			
 		}
 		
 	}
 	
 	//처리할 문자열과 문자열 제한 사이즈 비교
 	//처리할 문자열의 크기가 문자열 제한 사이즈 보다 작을 경우 문자열 그대로 반환
 	if(limit_flag == false){
 		
 		return str;
 		
 	}
 	//처리할 문자열의 크기가 문자열 제한 사이즈 보다 클경우 문자열 제한사이즈만큼의 길이만 취득후 나머지 부분은 ...로 처리
 	else{
 		
 		var result_str = null;
 		
 		result_str = str.substring(0, limit_index) + "..";

 		return result_str;
 		
 	}
 	
 }

 function encodeURL(value){
     var s0, i, s, u; 
     s0 = "";                				// encoded str 
     for (i = 0; i < value.length; i++){   // scan the source
         s = value.charAt(i);
         u = value.charCodeAt(i);          // get unicode of the char
         if (s == " "){s0 += "+";}       // SP should be converted to "+"
         else {
             if ( u == 0x2a || u == 0x2d || u == 0x2e || u == 0x5f || ((u >= 0x30) && (u <= 0x39)) || ((u >= 0x41) && (u <= 0x5a)) || ((u >= 0x61) && (u <= 0x7a)))
             {       // check for escape
                 s0 = s0 + s;            // don't escape 
             }
             else {                  // escape
                 if ((u >= 0x0) && (u <= 0x7f)){     // single byte format
                     s = "0"+u.toString(16);
                     s0 += "%"+ s.substr(s.length-2);
                 }
                 else if (u > 0x1fffff){     // quaternary byte format (extended)
                     s0 += "%" + (oxf0 + ((u & 0x1c0000) >> 18)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0x3f000) >> 12)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0xfc0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
                 else if (u > 0x7ff){        // triple byte format
                     s0 += "%" + (0xe0 + ((u & 0xf000) >> 12)).toString(16);
                     s0 += "%" + (0x80 + ((u & 0xfc0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
                 else {                      // double byte format
                     s0 += "%" + (0xc0 + ((u & 0x7c0) >> 6)).toString(16);
                     s0 += "%" + (0x80 + (u & 0x3f)).toString(16);
                 }
             }
         }
     }
     
     return s0;
 }


 
 
/**
 * 특정 문자 모두 변경
 * @return : String
 */
String.prototype.replaceAll = function(o, r) {
	return this.split(o).join(r);
};

/**
 * 문자의 모든 공백제거
 * @return : String
 */
String.prototype.trimAll = function() {
    return this.replace(/\s*/g, "");
};

/**
 * 문자의 좌, 우 공백 제거
 * @return : String
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};


/**
 * 문자의 좌 공백 제거
 * @return : String
 */
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/, "");
};

/**
 * 문자의 우 공백 제거
 * @return : String
 */
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/, "");   
};

/**
 * startsWith
 * @return : boolean
 */
String.prototype.startsWith = function(firstChar) {
	return this.toString().charAt(0)==firstChar;   
};

function nvl(obj) {
	return typeof(obj)=='undefined'||obj==null ? "" : obj;
};

/**
 * 문자열의 byte 길이 반환
 * @return : int
 */
String.prototype.byte = function() {
	var cnt = 0;
	for (var i = 0; i < this.length; i++) {
		if (this.charCodeAt(i) > 127)
		    cnt += 2;
		else
		    cnt++;
	}
	return cnt;
};

/**
 * 정수형으로 변환
 * @return : String
 */
String.prototype.int = function() {
	if(!isNaN(this)) {
	    return parseInt(this,10);
	} else {
	    return null;   
	}
};

/**
 * 숫자만 가져 오기
 * @return : String
 */
String.prototype.num = function() {
	return (this.trim().replace(/[^0-9]/g, ""));
};

/**
 * 숫자에 3자리마다 , 를 찍어서 반환
 * @return : String
 */
function formatCurrency(obj) {
	var returnVal = String(obj);
	while(returnVal.match(/^(-?\d+)(\d{3})/)) {
		returnVal = returnVal.replace(/^(-?\d+)(\d{3})/,'$1,$2');
	}
	return returnVal;
}

/**
 * 숫자의 자리수(cnt)에 맞도록 반환
 * @return : String
 */
String.prototype.digits = function(cnt) {
	var digit = "";
	if (this.length < cnt) {
	    for(var i = 0; i < cnt - this.length; i++) {
	        digit += "0";
	    }
	}
	return digit + this;
};

/**
 * 문자열 마스킹 
 * ex) 'INSUTIME'.toMaskedString(4, '*') -> 'INSU****'
 * @param nakedStringLen
 * @param mask
 * @return
 */
String.prototype.toMaskedString = function(nakedStringLen, mask) {
	var maskedStr = this;
	if(this!=null && typeof(this)!='undefined') {
		if(this.length > nakedStringLen) {
			maskedStr = this.substring(0,nakedStringLen) + mask.repeat(this.length - nakedStringLen);
		}
	}
	return maskedStr;
};

/**
 * 지정된 문자 반복 
 * ex) '*'.repeat(4) -> '****'
 * @param repeatCnt
 * @return
 */
String.prototype.repeat = function(repeatCnt) {
	var sb = [];
    for (var i = 0; i < repeatCnt; i++) {
        sb.push(this);
    }
    return sb.join('');
};

/**
 * " -> &#34; ' -> &#39;로 바꾸어서 반환
 * @return : String
 */
String.prototype.quota = function() {
	return this.replace(/"/g, "&#34;").replace(/'/g, "&#39;");
};

/**
 * 파일 확장자만 가져오기
 * @return : String
 */
String.prototype.ext = function() {
	return (this.indexOf(".") < 0) ? "" : this.substring(this.lastIndexOf(".") + 1, this.length);   
};

/**
 * URL에서 파라메터 제거한 순수한 url 얻기
 * @return : String
 */
String.prototype.uri = function() {
	var arr = this.split("?");
	arr = arr[0].split("#");
	return arr[0];   
};

/**
 * 전화번호를 자르기 - 미완성(발견한사람이 고치기)
 * @useage splitPhoneNum('023459090') -> 02 345 9090
 */
function splitPhoneNum(tel) {
	var param = {};
	param.tel = tel;
	if(param.tel.substring(0,3).isMobileFirst()) {
		param['cellTel1'] = param.tel.substring(0,3);
		param['cellTel2'] = param.tel.length == 11 ? param.tel.substring(3,7) : param.tel.substring(3,6);
		param['cellTel3'] = param.tel.length == 11 ? param.tel.substring(7) : param.tel.substring(6);
	} else {
		param['homeTel1'] = param.tel.length == 9 ? param.tel.substring(0,2) : param.tel.substring(0,3);
		param['homeTel2'] = param.tel.length == 9 ? param.tel.substring(2,5) : param.tel.substring(3,7);
		param['homeTel3'] = param.tel.length == 9 ? param.tel.substring(5) : param.tel.substring(7);
	}
	return param;
};

/*-------------------------------------------------------------------------------
	각종 체크 함수들
-------------------------------------------------------------------------------*/
/**
 * 정규식에 쓰이는 특수문자를 찾아서 이스케이프 한다.
 * @return : String
 */
String.prototype.meta = function() {
	var str = this;
	var result = "";
	for(var i = 0; i < str.length; i++) {
	    if((/([\$\(\)\*\+\.\[\]\?\\\^\{\}\|]{1})/).test(str.charAt(i))) {
	        result += str.charAt(i).replace((/([\$\(\)\*\+\.\[\]\?\\\^\{\}\|]{1})/), "\\$1");
	    } else {
	        result += str.charAt(i);
	    }
	}
	return result;
};

/**
 * 정규식에 쓰이는 특수문자를 찾아서 이스케이프 한다.
 * @return : String
*/
String.prototype.remove = function(pattern) {
	return (pattern == null) ? this : eval("this.replace(/[" + pattern.meta() + "]/g, \"\")");
};

/**
 * 최소 최대 길이인지 검증
 * str.isLength(min [,max])
 * @return : boolean
*/
String.prototype.isLength = function() {
	var min = arguments[0];
	var max = arguments[1] ? arguments[1] : null;
	var success = true;
	if(this.length < min) {
	  success = false;
	}
	if(max && this.length > max) {
	  success = false;
	}
	return success;
};

/**
 * 최소 최대 바이트인지 검증
 * str.isByteLength(min [,max])
 * @return : boolean
 */
String.prototype.isByteLength = function() {
	var min = arguments[0];
	var max = arguments[1] ? arguments[1] : null;
	var success = true;
	if(this.byte() < min) {
	  success = false;
	}
	if(max && this.byte() > max) {
	  success = false;
	}
	return success;
};


/**
 * 한글을 2글자로 계산하여 순수한 길이(Byte)를 계산
 * str.strLength()
 * @return : int
 */
String.prototype.strLength = function() {
	var temp;
	var set = 0;
	var cnt = 0;
	for (k = 0; k < this.length; k++) {
		temp = this.charAt(k);
		if (escape(temp).length > 4) cnt += 2;
		else cnt++;
	}
	return cnt;
};


/**
 * 한글 3byte, 영문 1byte로 순수한 길이(Byte)를 계산
 * str.strLength()
 * @return : int
 */
String.prototype.strCustomLength = function() {
	var temp;
	var set = 0;
	var cnt = 0;
	for (k = 0; k < this.length; k++) {
		temp = this.charAt(k);
		if (escape(temp).length > 4) cnt += 3;
		else cnt++;
	}
	return cnt;
};

/**
 * 공백이나 널인지 확인
 * @return : boolean
 */
String.prototype.isBlank = function() {
	var str = this.trim();
	for(var i = 0; i < str.length; i++) {
	  if ((str.charAt(i) != "\t") && (str.charAt(i) != "\n") && (str.charAt(i)!="\r")) {
	      return false;
	  }
	}
	return true;
};

/**
 * 숫자로 구성되어 있는지 학인
 * arguments[0] : 허용할 문자셋
 * @return : boolean
 */
String.prototype.isNum = function() {
	return (/^[0-9]+$/).test(this.remove(arguments[0])) ? true : false;
};

/**
 * 영어만 허용 - arguments[0] : 추가 허용할 문자들
 * @return : boolean
 */
String.prototype.isEng = function() {
	return (/^[a-zA-Z]+$/).test(this.remove(arguments[0])) ? true : false;
};

/**
 * 숫자와 영어만 허용 - arguments[0] : 추가 허용할 문자들
 * @return : boolean
 */
String.prototype.isEngNum = function() {
	return (/^[0-9a-zA-Z]+$/).test(this.remove(arguments[0])) ? true : false;
};

/**
 * 숫자와 영어만 허용 - arguments[0] : 추가 허용할 문자들
 * @return : boolean
 */
String.prototype.isNumEng = function() {
	return this.isEngNum(arguments[0]);
};

/**
 * 아이디 체크 영어와 숫자만 체크 첫글자는 영어로 시작 - arguments[0] : 추가 허용할 문자들
 * @return : boolean
 */
String.prototype.isUserId = function() {
	return (/^[a-zA-z]{1}[0-9a-zA-Z]+$/).test(this.remove(arguments[0])) ? true : false;
};

/**
 * FRONT 아이디 체크 영어와 숫자만 체크 - arguments[0] : 추가 허용할 문자들
 * @return : boolean
 */
String.prototype.isLoginId = function() {
	return (/^[0-9a-z]+$/).test(this.remove(arguments[0])) ? true : false;
};

/**
 * 한글 체크 - arguments[0] : 추가 허용할 문자들
 * @return : boolean
 */
String.prototype.isKor = function() {
	return (/^[가-힝]+$/).test(this.remove(arguments[0])) ? true : false;
};

/**
 * 이메일의 유효성을 체크
 * @return : boolean
 */
String.prototype.isEmail = function() {
	//return (/\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/).test(this.trim());
	return (/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(this));
};

/**
 * 전화번호 체크 - arguments[0] : 전화번호 구분자
 * @return : boolean
 */
String.prototype.isPhone = function() {
	var arg = arguments[0] ? arguments[0] : "";
	return eval("(/(02|0[3-9]{1}[0-9]{1})" + arg + "[1-9]{1}[0-9]{2,3}" + arg + "[0-9]{4}$/).test(this)");
};

/**
 * 핸드폰번호 체크 - arguments[0] : 핸드폰 구분자
 * @return : boolean
 */
String.prototype.isMobile = function() {
	var arg = arguments[0] ? arguments[0] : "";
	return eval("(/01[016789]" + arg + "[1-9]{1}[0-9]{2,3}" + arg + "[0-9]{4}$/).test(this)");
};

/**
 * 핸드폰번호여부 체크(앞자리만)
 * @return : boolean
 */
String.prototype.isMobileFirst = function() {
	return (/01[016789]$/).test(this);
};

/**
 * IP Address 체크
 * @return
 */
String.prototype.isIp = function() {
	return ((/^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$/).test(this));
};

/**
 * seperator로 join된 문자열의 유효성 체크
 * e.g. "B20|A70|A60".isValidJoinedStr("|") is true
 * e.g. "B20||A60".isValidJoinedStr("|") is false;
 * @return
 */
String.prototype.isValidJoinedStr = function() {
	var arg = arguments[0] ? arguments[0] : "|";
	var arr = this.split(arg);
	for(var i=0; i<arr.length; i++) {
		if(arr[i].isBlank()) {
			return false;
		}
	}
	return true;
};

/**
 * seperator로 join된 문자열의 유효성 체크(integer)
 * e.g. "20|70|60".isValidJoinedStr("|") is true
 * e.g. "20||60".isValidJoinedStr("|") is false;
 * e.g. "20|70|ABC".isValidJoinedStr("|") is false;
 * @return
 */
String.prototype.isValidJoinedNum = function() {
	var arg = arguments[0] ? arguments[0] : "|";
	var arr = this.split(arg);
	for(var i=0; i<arr.length; i++) {
		if(arr[i].isBlank() || !arr[i].trim().isNum()) {
			return false;
		}
	}
	return true;
};

/**
 * 주민번호 체크 - arguments[0] : 주민번호 구분자
 * XXXXXX-XXXXXXX
 * @return : boolean
 */
String.prototype.isJumin = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var jumin = eval("this.match(/[0-9]{2}[01]{1}[0-9]{1}[0123]{1}[0-9]{1}" + arg + "[1234]{1}[0-9]{6}$/)");
	if(jumin == null) {
		return false;
	} else {
		jumin = jumin.toString().num().toString();
	}
	// 생년월일 체크
	var birthYY = (parseInt(jumin.charAt(6)) == (1 ||2)) ? "19" : "20";
	birthYY += jumin.substr(0, 2);
	var birthMM = jumin.substr(2, 2) - 1;
	var birthDD = jumin.substr(4, 2);
	var birthDay = new Date(birthYY, birthMM, birthDD);
	if(birthDay.getYear() % 100 != jumin.substr(0,2) || birthDay.getMonth() != birthMM || birthDay.getDate() != birthDD) {
		return false;
	}       
	var sum = 0;
	var num = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5];
	var last = parseInt(jumin.charAt(12));
	for(var i = 0; i < 12; i++) {
		sum += parseInt(jumin.charAt(i)) * num[i];
	}
	return ((11 - sum % 11) % 10 == last) ? true : false;
};

/**
 * 외국인 등록번호 체크 - arguments[0] : 등록번호 구분자
 * XXXXXX-XXXXXXX
 * @return : boolean
 */
String.prototype.isForeign = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var jumin = eval("this.match(/[0-9]{2}[01]{1}[0-9]{1}[0123]{1}[0-9]{1}" + arg + "[5678]{1}[0-9]{1}[02468]{1}[0-9]{2}[6789]{1}[0-9]{1}$/)");
	if(jumin == null) {
		return false;
	} else {
		jumin = jumin.toString().num().toString();
	}
	// 생년월일 체크
	var birthYY = (parseInt(jumin.charAt(6)) == (5 || 6)) ? "19" : "20";
	birthYY += jumin.substr(0, 2);
	var birthMM = jumin.substr(2, 2) - 1;
	var birthDD = jumin.substr(4, 2);
	var birthDay = new Date(birthYY, birthMM, birthDD);
	if(birthDay.getYear() % 100 != jumin.substr(0,2) || birthDay.getMonth() != birthMM || birthDay.getDate() != birthDD) {
		return false;
	}
	if((parseInt(jumin.charAt(7)) * 10 + parseInt(jumin.charAt(8))) % 2 != 0) {
		return false;
	}
	var sum = 0;
	var num = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5];
	var last = parseInt(jumin.charAt(12));
	for(var i = 0; i < 12; i++) {
		sum += parseInt(jumin.charAt(i)) * num[i];
	}
	return (((11 - sum % 11) % 10) + 2 == last) ? true : false;
};

/**
 * 사업자번호 체크 - arguments[0] : 등록번호 구분자
 * XX-XXX-XXXXX
 * @return : boolean
 */
String.prototype.isBiznum = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var biznum = eval("this.match(/[0-9]{3}" + arg + "[0-9]{2}" + arg + "[0-9]{5}$/)");
	if(biznum == null) {
		return false;
	} else {
		biznum = biznum.toString().num().toString();
	}
	var sum = parseInt(biznum.charAt(0));
	var num = [0, 3, 7, 1, 3, 7, 1, 3];
	for(var i = 1; i < 8; i++) sum += (parseInt(biznum.charAt(i)) * num[i]) % 10;
	sum += Math.floor(parseInt(parseInt(biznum.charAt(8))) * 5 / 10);
	sum += (parseInt(biznum.charAt(8)) * 5) % 10 + parseInt(biznum.charAt(9));
	return (sum % 10 == 0) ? true : false;
};

/**
 * 법인 등록번호 체크 - arguments[0] : 등록번호 구분자
 * XXXXXX-XXXXXXX
 * @return : boolean
 */
String.prototype.isCorpnum = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var corpnum = eval("this.match(/[0-9]{6}" + arg + "[0-9]{7}$/)");
	if(corpnum == null) {
		return false;
	} else {
		corpnum = corpnum.toString().num().toString();
	}
	var sum = 0;
	var num = [1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2];
	var last = parseInt(corpnum.charAt(12));
	for(var i = 0; i < 12; i++) {
		sum += parseInt(corpnum.charAt(i)) * num[i];
	}
	return ((10 - sum % 10) % 10 == last) ? true : false;
};

/**
 * 주민번호 앞6자리 유효성 체크
 *
 */
String.prototype.isValidDate = function() {
	if(this.length != 6 || !this.isNumber()){
		return false;
	}else{
		inputMonth = this.substring(2,4);
		inputDate = this.substring(4,6);
		if((inputMonth < 1 || inputMonth > 12) || (inputDate <1 || inputDate > 31)) return false;
		else return true;
	}
};

/**
 * 해당 월의 마지막 날짜.
 */
function endDays(y, m, d) {
	var MonthTable = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); // 양력 각달의 일수를 저장한 배열
	var endday = "";
	if (((Number(y) % 4 == 0) && (Number(y) % 100 != 0)) || (Number(y) % 400 == 0)) MonthTable[1] = 29;
	else MonthTable[1] = 28;
	endday = endday + y ;
	if ( m.length == 1 ) endday = endday + "0" + parseInt(m) ;
	else endday = endday + "" + m ;
	endday = endday + "" + MonthTable[Number(m)-1] ;

	return endday ;
}

/**
 * 날짜 체크
 * yyyy/mm/dd or yyyy-mm-dd or yyyy.mm.dd or yyyymmdd
 * @return
 */
String.prototype.isDate = function() {
	//var arg = arguments[0] ? arguments[0] : "/";
	var objRegExp = /^\d{4}(\/|\-|\.|)\d{2}(\/|\-|\.|)\d{2}$/;
	var isValid = false;
	if(eval(objRegExp.test(this))) {
		var arrayDate = [];
		if(this.length==8) {
			arrayDate[0] = this.substring(0,4);
			arrayDate[1] = this.substring(4,6);
			arrayDate[2] = this.substring(6,8);
		} else {
			var strSeparator = this.substring(4,5); 
			arrayDate = this.split(strSeparator); 
		}
		var arrayLookup = { '01':31, '03':31,
							'04':30, '05':31,
							'04':30, '05':31,
							'06':30, '07':31,
							'08':31, '09':30,
	                        '10':31, '11':30,
	                        '12':31};
		var intDay = parseInt(arrayDate[2],10); 

		if(arrayLookup[arrayDate[1]] != null) {
			if(intDay <= arrayLookup[arrayDate[1]] && intDay != 0)
				isValid = true;
		}
		else {
			var intMonth = parseInt(arrayDate[1],10);
			if (intMonth == 2) {
				var intYear = parseInt(arrayDate[0]);
				if (intDay > 0 && intDay < 29) {
					isValid = true;
				}
				else if(intDay == 29) {
					if ((intYear % 4 == 0) && (intYear % 100 != 0) || (intYear % 400 == 0)) {
						isValid = true;
					}   
				}
			}
		}
	}

	return isValid;
};

/**
 * 시간 체크
 * HH:MM or HH:MM:SS or HH:MM:SS.mmm
 */
String.prototype.isTime = function() {
	return (/^([1-9]|1[0-2]):[0-5]\d(:[0-5]\d(\.\d{1,3})?)?$/).test(this);
};


/**
 * 생년월일 유효성 체크(yyyymmdd)
 * 
 */
String.prototype.isBirthDate = function() {
	var pt = /^\d{8}$/;
	if (!pt.test(this)) return false;
	var y = parseInt(this.substring(0,4), 10);
	var m = parseInt(this.substring(4,6), 10) - 1;
	var d = parseInt(this.substring(6,8), 10);
	var dt = new Date(y, m, d);
	if (dt.getFullYear() == y && dt.getMonth() == m && dt.getDate() == d) {	
	    return true;
	} else {
	    return false;
	}
};

/**
 * 금액 단위 한글 입력
 */
String.prototype.formatPayment = function() {
	var result = "";
	if ((this % 100000000) == 0)
		result = (this / 100000000).formatCurrency() + "억원";
	else if((this % 10000000) == 0)
		result = (this / 10000000).formatCurrency()  + "천만원";
	else if((this % 1000000) == 0 )
		result = (this / 1000000).formatCurrency()   + "백만원";
	else if((this % 100000) == 0 )
		result = (this / 100000).formatCurrency()    + "십만원";
	else if ((this % 10000) == 0)
		result = (this / 10000).formatCurrency()     + "만원";
	else
		result = this.formatCurrency()               + "원";
	return result;
};

/**
 * yyy/mm/dd hh:mi:ss
 * @param fm date type
 * @return format date
 */
String.prototype.simpleFormatDate = function(fm) {
	var result = "";
	if(fm == "D")
		result = this.substring(0, 4) + "/" + this.substring(4, 6) + "/" + this.substring(6, 8);
	else if(fm == "T")
		result = this.substring(8, 10);
	else if(fm == "M")
		result = this.substring(10, 12);
	else {
		if(this.length == 14)
			result = this.substring(0, 4) + "/" + this.substring(4, 6) + "/" + this.substring(6, 8) + " " + this.substring(8, 10) + ":" + this.substring(10, 12) + ":" + this.substring(12);
		else if(this.length == 12)
			result = this.substring(0, 4) + "/" + this.substring(4, 6) + "/" + this.substring(6, 8) + " " + this.substring(8, 10) + ":" + this.substring(10, 12);
		else if(this.length == 10)
			result = this.substring(0, 4) + "/" + this.substring(4, 6) + "/" + this.substring(6, 8) + " " + this.substring(8, 10);
		else if(this.length == 8)
			result = this.substring(0, 4) + "/" + this.substring(4, 6) + "/" + this.substring(6, 8);
	}
	
	return result;
};

/**
 *  쿠키 읽기
 */
function getCookie(name){
    var arg = name + "=";
    var alen = arg.length;
    var clen = document.cookie.length;
    var i = 0;
    while(i < clen){
        var j = i + alen;
        if(document.cookie.substring(i, j) == arg)
        return getCookieVal(j);
        i = document.cookie.indexOf(" ", i) + 1;
        if(i == 0) break;
    }
    return null;
}

/**
 * 쿠키 읽기
 * @param offset
 * @return
 */
function getCookieVal(offset){
    var endstr = document.cookie.indexOf(";", offset);
    if(endstr == -1)
        endstr = document.cookie.length;
    return decodeURIComponent(document.cookie.substring(offset, endstr));
}


/**
 * json object를 string으로 변환
 * @param json object
 * @usage var jsonStr = $.json2string({id:'user', name:'jhone doe'});
 */
$.json2string = function(obj) {
	var res = [];
	for(var i=0;i<obj.length;i++) {
		for (var property in obj[i]) {  
			var value = obj[i][property];  
			if (value)  
				res.push("'"+property.toString() + "':'" + value + "'");  
		}
	}
	return '{' + res.join(',') + '}';  
};

/**
 * jQuery serializeArray()를 json형식으로 변환
 * @param serializeArray
 * @usage var param = $.params2json($('#frm').serializeArray());
 */
$.params2json = function(d) {
    if (d.constructor != Array) {
        return d;
    }
    var data={};
    for(var i=0;i<d.length;i++) {
        if (typeof data[d[i].name] != 'undefined') {
            if (data[d[i].name].constructor!= Array) {
                data[d[i].name]=[data[d[i].name],d[i].value];
            } else {
                data[d[i].name].push(d[i].value);
            }
        } else {
            data[d[i].name]=d[i].value;
        }
    }
    return data;
};


/*
 * jQuery JSON Plugin
 * version: 2.1 (2009-08-14)
 *
 * This document is licensed as free software under the terms of the
 * MIT License: http://www.opensource.org/licenses/mit-license.php
 *
 * Brantley Harris wrote this plugin. It is based somewhat on the JSON.org 
 * website's http://www.json.org/json2.js, which proclaims:
 * "NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.", a sentiment that
 * I uphold.
 *
 * It is also influenced heavily by MochiKit's serializeJSON, which is 
 * copyrighted 2005 by Bob Ippolito.
 */
 
(function($) {
    /** jQuery.toJSON( json-serializble )
        Converts the given argument into a JSON respresentation.

        If an object has a "toJSON" function, that will be used to get the representation.
        Non-integer/string keys are skipped in the object, as are keys that point to a function.

        json-serializble:
            The *thing* to be converted.
     **/
    $.toJSON = function(o)
    {
        if (typeof(JSON) == 'object' && JSON.stringify)
            return JSON.stringify(o);
        
        var type = typeof(o);
    
        if (o === null)
            return "null";
    
        if (type == "undefined")
            return undefined;
        
        if (type == "number" || type == "boolean")
            return o + "";
    
        if (type == "string")
            return $.quoteString(o);
    
        if (type == 'object')
        {
            if (typeof o.toJSON == "function") 
                return $.toJSON( o.toJSON() );
            
            if (o.constructor === Date)
            {
                var month = o.getUTCMonth() + 1;
                if (month < 10) month = '0' + month;

                var day = o.getUTCDate();
                if (day < 10) day = '0' + day;

                var year = o.getUTCFullYear();
                
                var hours = o.getUTCHours();
                if (hours < 10) hours = '0' + hours;
                
                var minutes = o.getUTCMinutes();
                if (minutes < 10) minutes = '0' + minutes;
                
                var seconds = o.getUTCSeconds();
                if (seconds < 10) seconds = '0' + seconds;
                
                var milli = o.getUTCMilliseconds();
                if (milli < 100) milli = '0' + milli;
                if (milli < 10) milli = '0' + milli;

                return '"' + year + '-' + month + '-' + day + 'T' +
                             hours + ':' + minutes + ':' + seconds + 
                             '.' + milli + 'Z"'; 
            }

            if (o.constructor === Array) 
            {
                var ret = [];
                for (var i = 0; i < o.length; i++)
                    ret.push( $.toJSON(o[i]) || "null" );

                return "[" + ret.join(",") + "]";
            }
        
            var pairs = [];
            for (var k in o) {
                var name;
                var type = typeof k;

                if (type == "number")
                    name = '"' + k + '"';
                else if (type == "string")
                    name = $.quoteString(k);
                else
                    continue;  //skip non-string or number keys
            
                if (typeof o[k] == "function") 
                    continue;  //skip pairs where the value is a function.
            
                var val = $.toJSON(o[k]);
            
                pairs.push(name + ":" + val);
            }

            return "{" + pairs.join(", ") + "}";
        }
    };

    /** jQuery.evalJSON(src)
        Evaluates a given piece of json source.
     **/
    $.evalJSON = function(src)
    {
        if (typeof(JSON) == 'object' && JSON.parse)
            return JSON.parse(src);
        return eval("(" + src + ")");
    };
    
    /** jQuery.secureEvalJSON(src)
        Evals JSON in a way that is *more* secure.
    **/
    $.secureEvalJSON = function(src)
    {
        if (typeof(JSON) == 'object' && JSON.parse)
            return JSON.parse(src);
        
        var filtered = src;
        filtered = filtered.replace(/\\["\\\/bfnrtu]/g, '@');
        filtered = filtered.replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, ']');
        filtered = filtered.replace(/(?:^|:|,)(?:\s*\[)+/g, '');
        
        if (/^[\],:{}\s]*$/.test(filtered))
            return eval("(" + src + ")");
        else
            throw new SyntaxError("Error parsing JSON, source is not valid.");
    };

    /** jQuery.quoteString(string)
        Returns a string-repr of a string, escaping quotes intelligently.  
        Mostly a support function for toJSON.
    
        Examples:
            >>> jQuery.quoteString("apple")
            "apple"
        
            >>> jQuery.quoteString('"Where are we going?", she asked.')
            "\"Where are we going?\", she asked."
     **/
    $.quoteString = function(string)
    {
        if (string.match(_escapeable))
        {
            return '"' + string.replace(_escapeable, function (a) 
            {
                var c = _meta[a];
                if (typeof c === 'string') return c;
                c = a.charCodeAt();
                return '\\u00' + Math.floor(c / 16).toString(16) + (c % 16).toString(16);
            }) + '"';
        }
        return '"' + string + '"';
    };
    
    var _escapeable = /["\\\x00-\x1f\x7f-\x9f]/g;
    
    var _meta = {
        '\b': '\\b',
        '\t': '\\t',
        '\n': '\\n',
        '\f': '\\f',
        '\r': '\\r',
        '"' : '\\"',
        '\\': '\\\\'
    };
})(jQuery);
