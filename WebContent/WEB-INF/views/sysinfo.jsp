<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<%@ page import="java.util.*" %>

<body>
<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">시스템 정보 목록</h2>
		
				
		<div class="body-cont">
		=========================================================================<br/>
		String jvm = System.getProperty("java.version");						// 자바 버전 <br/>
		String vendor = System.getProperty("java.vendor");						// 자바 공급자 <br/>
		String url = System.getProperty("java.vendor.url");						// 공급자 주소<br/>
		String home  = System.getProperty("java.home");							// 자바 설치 디렉토리 <br/>
		String classVersion  = System.getProperty("java.class.version");		// 자바 클래스 버전<br/>
		
		String osName = System.getProperty("os.name");							// os 이름 <br/>
		String osArch = System.getProperty("os.arch");							// os 아키텍쳐 <br/>
		String osVersion = System.getProperty("os.version");					// os 버전 정보 <br/>
		
		String fileSeparator = System.getProperty("file.separator");			// 파일구분자 <br/>
		String pathSeparator = System.getProperty("path.separator");			// 경로 구분자 <br/>
		String lineSeparator = System.getProperty("line.separator");			// 행 구분자 <br/>
		String userDir = System.getProperty("user.dir");						// 현재 작업 디렉토리 <br/>
		String userHome = System.getProperty("user.home");						// 사용자홈 디렉토리  <br/>
		String userName = System.getProperty("user.name");						// 사용자계정  <br/>
		String fileEncoding = System.getProperty("file.encoding");				// 파일인코딩 <br/>
		String tmpDir = System.getProperty("java.io.tmpdir");					// 임시경로 <br/>
		Locale loc = Locale.getDefault();										// 로케일 <br/>
		=========================================================================<br/>
		<br/>
		- 자바 버전             : ${jvm}		<br />
		- 자바 공급자           : ${vendor}		<br />
		- 공급자 주소           : ${url}		<br />
		- 자바 설치 디렉토리    : ${home}	<br />
		- 자바 클래스 버전    : ${classVersion}	<br />
		- os 이름               : ${osName}		<br />
		- os 아키텍쳐           : ${osArch}	<br />
		- os 버전 정보          : ${osVersion}	<br />
		- 파일구분자            : ${fileSeparator}	<br />
		- 경로 구분자           : ${pathSeparator}	<br />
		- 행 구분자             : ${lineSeparator}		<br />
		- 현재 작업 디렉토리    : ${userDir}	<br />
		- 사용자홈 디렉토리     : ${userHome}	<br />
		- 사용자계정            : ${userName}	<br />
		- 파일인코딩            : ${fileEncoding}	<br />
		- 기본 인코딩            : ${enc}	<br />
		- 임시경로            : ${tmpDir}	<br />
		- 로케일                : ${loc}	<br /><br /><br />
		
		*시스템 환경변수 <br>
		<%
		for (Map.Entry entry: System.getenv().entrySet()) {
		    out.println("##### " + entry.getKey() + "=" + entry.getValue() +"<BR/>" );
		}

		%>
		</div>
		<div class="foot-cont">
			<a href="#" role="button" class="btn">버튼이 있다면</a>
			<a href="#" role="button" class="btn">여기에</a>
		</div>
		
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		
	});
	
	
//]]>
</script>
</js:scripts>
</body>