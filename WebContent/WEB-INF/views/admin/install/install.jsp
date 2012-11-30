<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width">
<title>GLiDERWiki™ Web Installer </title>
<link rel="stylesheet" href="/resource/glider/admin/css/install.css">
<link rel="stylesheet" href="/resource/glider/front/css/common.css">
<link rel="stylesheet" href="/resource/glider/front/css/modal.css">
</head>

<body>
<section class="contents" role="main">
   
	<div class="wrap-cont" id="step_0">
		<h2 class="tit-cont">GLiDERWiki™ Web Installer 에 오신걸 환영합니다!</h2>
		<div class="body-cont">
		<div class="num">Step1. Installer 설명 및 동의 </div> <br/><br/>
		<input type="hidden" name="domain" id="domain" value="${domain }" />
		GLiDERWiki™ 는  GLiDER Opensource Team 이 기업 환경에 적합한 지식정보 공유 및 축적을 위한 협업 플랫폼으로 개발한 오픈소스 입니다.<br/>
		GLiDERWiki™ 는 손쉽게 사용자들이 Wiki를 설치 할 수 있도록 Web 기반의 Installer 를 제공합니다.<br />
		사용자의 어드민 정보, DB 연동 정보등을 토대로 서비스 운영에  필요한 항목들을 순서대로 설치하게 됩니다.	<br />
		아래의 항목을 반드시 읽어보신 후 설치 하시기 바랍니다.
		<br/>
		<ul>
			<li>
			GLiDERWiki™ 커뮤니티 (이하 GLiDERWiki™)는 기업, 단체, 개인 등이 무료로 이용할 수 있는 커뮤니티 버전의 오프소스 입니다. <br/>
			</li>
			<li>
			커뮤니티 버전은 GLiDERWiki™ 웹 어플리케이션의 하단의  Copyright를 수정하거나 삭제할 수 없습니다.<br/>
			</li>
			<li>
			본 설치 패키지는 사용자의 설치용이성을 위해 제공되는 화면으로 서버의 스펙이나 관련 어플리케이션의 버전에 따라 설치가 지원되지 않을 수도 있습니다. 이런 경우 수동으로 설치하셔도 무방합니다.<br/>
			</li>
			<li>
			지원되는 서버 환경은 Linux(데비안 계열, 레드햇 계열)과 윈도우즈 서버입니다. 
			DB는 MySQL 5.x 버전이며, Application Server는 Tomcat 7.x, JDK는 1.6이상 버전에서 동작합니다.	자세한 지원 스펙에 대해서는 <a href="http://www.gliderwiki.org" target="_blank">GLiDERWiki™의 홈페이지</a>를 통해 확인 가능합니다.			
			</li>
			<li>
			본 패키지를 설치하기 위해서는 GLiDERWiki™ 의 홈페이지에서 다운로드 및 이메일 인증을 거쳐야 합니다.<br/>
			이메일 인증시 커뮤니티 버전의 프리 라이센스 키를 보내드리며, 이 라이센스 키는  GLiDERWiki™ 설치시에 입력합니다. 또한 입력된 이메일 및 설치 도메인 정보는 최종 사용 허가를 위해  GLiDERWiki™의 공식 사이트에 전송 될 수 있습니다.<br/>
			</li>
			<li>
			GLiDERWiki™은 오픈소스팀 글라이더에서 개발한 GLiDERWiki™ 웹 어플리케이션 오픈소스의 고유 트레이드 마크 입니다.<br/>설치 지원 및 사용법 교육에 대한 문의는  <a href="http://www.gliderwiki.org" target="_blank">GLiDERWiki™의 홈페이지</a>를 참조하세요.
			</li>

		</ul>
		위의 항목에 동의를 하시면 다음 버튼을 눌러 설치를 진행하세요.
		</div>
		<div class="foot-cont">
			<div id="nonInstall" role="button" class="btn" style="cursor:pointer">설치안함</div>
			<div id="install" role="button" class="btn" style="cursor:pointer">다음</div>
		</div>
		<br/>
	</div>
	
</section>

<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.common.util.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript">
//<![CDATA[
    var isConnect = false;
    var isDataSave = false;
    var isUpload = false;
    var isSendMail = false;
	$(document).ready(function() {
		CheckOS();
		/**
		 * 01. 인스톨 동의 
		 */ 
		$("#install").bind("click", function() {
			// 최초 스텝 - 서버 기본 정보 입력 받는 프로세스 
			setAdminInfoLayer("step_1");
			
			// 엥커 이동 
			gotoAnchor("step_1");
		});
		
		$("#nonInstall").bind("click", function() {		// 설치 않함 
			alert('GLiDERWiki™ 를 설치하려면 다음 버튼을 클릭하세요.');
			return;
		});
		
		/**
		 * 02. JDBC 연동 테스트 
		 */ 
		$("#linkTest").live("click", function() {
			var jdbcUrl = $("#jdbc_url").val(); // jdbc URL 입니다.
			var jdbcId  = $("#jdbc_id").val();  // jdbc ID 입니다.
			var jdbcPw  = $("#jdbc_pw").val();  // jdbc PW 입니다.		
			
			if (jdbcUrl == '') {
				alert('jdbc 접속 URL을 입력 하셔야 합니다.');
				$('#jdbc_url').focus();
				return;
			}
			if (jdbcId == '') {
				alert('jdbc 접속 아이디를 입력 하셔야 합니다.');
				$('#jdbc_id').focus();
				return;
			}
			if (jdbcPw == '') {
				alert('jdbc 접속 비밀번호를 입력 하셔야 합니다.');
				$('#jdbc_pw').focus();
				return;
			}

			/* 연동 테스트를 합니다. Ajax 호출 */
			$.ajax({
				type:"GET"
				,url:"/admin/install/jdbcConnection"
				,data:{"jdbc_url":jdbcUrl,"jdbc_id":jdbcId,"jdbc_pw":jdbcPw}
			    ,dataType:"json"
			    ,success:function(rtnObj){	
				   	var status = rtnObj.status;
				   	if (status == '1') {
				   		isConnect = true;
				    	alert("JDBC 연결 테스트가 정상적으로 처리 되었습니다.\n다음 버튼을 클릭하세요.");
				    } else if(status == '-1') {
				    	//Properties 생성 에러
				    	isConnect = false;
				    	alert("에러가 발생하였습니다. 지정된 경로가 변경 되었거나 권한이 없습니다.\n다시 시도 해도 문제가 계속되면 글라이더 위키팀에 문의 바랍니다.");
				    } else if(status == '-2') {
				    	isConnect = false;
				    	alert('JDBC 커넥션 정보가 올바르지 않습니다. DB계정이 잘못 되었거나 DB연결이 이뤄지지 않았습니다.\n정보를 다시 확인해 주세요.');
				    }
			    }
			});
		});
		
		
		/**
		 * 테이블 생성 
		 */
		$("#createTable").live("click", function() {
			$.loadingBar();
			// 테이블 생성 케릭터셋 선택한 값 입니다.
			var charType = $(':radio[name="charType"]:checked').val();
			var strKor = "한글 데이터 정상";
			
			/* 연동 테스트를 합니다. Ajax 호출 */
			$.ajax({
				type:"GET"
				,url:"/admin/install/createTables"
				,data:{"charType":charType,"strKor":strKor}
			    ,dataType:"json"
			    ,success:function(rtnObj){	
				   	var state = rtnObj.result;
				    var tableSize = rtnObj.tableSize;
				    var resultStr =  rtnObj.resultStr;
				    
				   	if (state > 0) {
				   		$.loadingBar.fadeOut();
				    	alert("테이블이 정상적으로 설치 되었습니다. 아래 구문에 한글이 제대로 출력되는지 확인하세요.한글이 깨지면 테이블 리셋 후 다른 캐릭터셋을 선택하여 재시도 하세요. \n\n [" + resultStr + "]");
				    	//정상적으로 테이블이 생성이 되었다면 기존에 입력 화면을 활성화 되도록 변경합니다.
				    	createTablesActiveForm();
				    } else {
				    	$.loadingBar.fadeOut();
				    	alert("테이블 생성 중 에러가 발생하였습니다. 다시 시도하세요");
				    }
			    }
			});
		});
		
		/* 테이블을 삭제하는 함수 */
		$("#resetTable").live("click", function() {
			
			// 테이블 생성 케릭터셋 선택한 값 입니다.
			var charType = $(':radio[name="charType"]:checked').val();
			
			if(confirm("생성된 테이블을 초기화 하시겠습니까?")) {
			/* 연동 테스트를 합니다. Ajax 호출 */
				$.ajax({
					type:"GET"
					,url:"/admin/install/dropTables"
					,data:{"charType":charType}
				    ,dataType:"json"
				    ,success:function(rtnObj){	
				     //
				   		var state = rtnObj.result;
				   		var tableSize = rtnObj.tableSize;
					   	if (state > 0 ) {
					    	alert('정상적으로 삭제 처리 되었습니다.');
					    	dropTableDisableForm();
					    } else {
					    	alert("테이블 삭제 처리 중 에러가 발생하였습니다. 다시 시도하세요.");
					    }
				    }
				});
			}
		});
		
		/* 테이블 생성이 성공 후 호출 */
		function createTablesActiveForm()
		{
			$("#adminMailId").attr('disabled',false);
			$("#adminpass").attr('disabled',false);
			$("#adminSite").attr('disabled',false);
			$("#userMail").attr('disabled',false);
			$("#activeKey").attr('disabled',false);
		}
		
		/* 테이블 드랍이 성공 후 호출 */
		function dropTableDisableForm()
		{
			$("#adminMailId").attr('disabled',true);
			$("#adminpass").attr('disabled',true);
			$("#adminSite").attr('disabled',true);
			$("#userMail").attr('disabled',true);
			$("#activeKey").attr('disabled',true);
		}
		
		/**
		 * DB 접속 정보 연동 후 다음 액션 
		 */ 
		$("#next_step_2").bind("click", function() {
			setAdminInfoLayer("step_3");
			gotoAnchor("step_3");
		});		

		$("#dataSave").live("click", function() {

			var adminMailId = $("#adminMailId").val();
			var adminpass = $("#adminpass").val();
			var adminSite = $("#adminSite").val();
			var userMail = $("#userMail").val();
			var activeKey = $("#activeKey").val();

			if (adminMailId == "" || adminMailId == null) {
				alert("관리자용 로그인 이메일을 입력해주세요.");
				$('#adminMailId').focus();
				return;
				
			} else if(adminpass == "" || adminpass == null) {
				alert("관리자용 로그인 비밀번호를 입력해주세요.");
				$('#adminpass').focus();
				return;
				
			} else if(adminSite == "" || adminSite == null) {
				alert("사이트 도메인명을 입력해 주세요.");
				$('#adminSite').focus();
				return;
				
			} else if(userMail == "" || userMail == null) {
				alert("위키 다운로드시 입력한 메일 주소를 넣어주세요.");
				$('#userMail').focus();
				return;
				
			} else if(activeKey == "" || activeKey == null) {
				alert("라이센스키를 입력해주세요.");
				$('#activeKey').focus();
				return;
			} 
			
			$.ajax({
					type:"POST"
					,url:"/admin/install/loadData"
					,data:{"adminMailId":adminMailId,"adminpass":adminpass,
						   "adminSite":adminSite,"userMail":userMail,
						   "activeKey":activeKey}
				    ,dataType:"json"
				    ,success:function(rtnObj){	
				   		var state = rtnObj.result;
					   	if (state == 1) {
					    	alert("기본 데이터가 저장 되었습니다. 다음 단계를 진행하세요.");
					    	// TODO 생성된 액티븍키와 사용자 등록 이메일을 잠궈야 한다. 추후 테스트 메일 전송 후 서버에 전송한다. 
					    	isDataSave = true;
					    	dropTableDisableForm();
					    } else if(state == -2){
					    	isDataSave = false;
					    	alert("기본 데이터 저장시 오류가 발생하였습니다. 입력 정보를 확인 한 후 다시 시도 하세요.");
					    } else if(state == -1){
					    	isDataSave = false;
					    	alert("테이블 생성 및 기본 데이터 저장이 되지 않았습니다. 테이블 리셋 후 생성 부터 다시 시도하세요.");
					    }
				    }
				});
		  });
		
		/**
		 * Table생성 및 기본 데이터 생성 후 다음 액션  
		 */
		$("#next_step_3").bind("click", function() {
			setAdminInfoLayer("step_4");		
			gotoAnchor("step_4");	
		});

		// 이전으로 되돌아가기 
		$("div[name=prev]").live("click", function(e) {
			alert('이전단계에서 다시 세팅 하시겠습니까?');
			var id =  $(this).attr('id');
			var val =  $(this).attr('title');
			gotoAnchor(val);						
		});
		
		
		$("#next_step_1").bind("click", function() {
			// 최초 스텝 - 서버 기본 정보 입력 받는 프로세스 
			setAdminInfoLayer2("step_2");			
			// 엥커 이동 
			gotoAnchor("step_2");
		});
		
	});

		
	// 지원되는 운영체를 체크 합니다.
	function CheckOS() {
		var sysinfo = '${rtnCode}';
		
		if (sysinfo == 'Darwin') {	// Mac OS

		} 
		else if (sysinfo == 'Ubuntu') {	// 우분트 OS
			//alert(sysinfo);
		}
		else if (sysinfo == 'Fedora'){	// Fedora OS
			//alert(sysinfo);
		}
		else if (sysinfo == 'Centos'){	// CentOS OS
			//alert(sysinfo);	
		}
		else {
			
			//$("#next_step_1").hide();
			//alert("installer가 지원되지 않는 시스템 환경입니다.\r\n관리자와 문의  수동으로 설치 하시기 바랍니다.");
			//location = "/"; //추후 위키사이트로 링크를 합니다.
		}
	}
	/*
	 * anchor 변화 이동 
	 */
	function gotoAnchor(id) {
		$("html, body").animate({scrollTop: $("#"+id).offset().top}, "slow");
	}
	
	/*
	 * step_1 - JDBC 정보 저장 
	 */
	function setAdminInfoLayer(id){ 
		clearThis(id);

		var inHtml = "";
		inHtml += "<div class=\"wrap-cont\" id=\""+id+"\" >";
		inHtml += "	<h2 class=\"tit-next\" >DB정보 입력</h2>";
		inHtml += "	<div class=\"body-cont\" style=\"height:410px;\">";		
		inHtml += " <div class=\"num\" >Step2. DB 접속 정보</div><br/>";
		inHtml += "	GLiDERWiki™ 를 이용하기 위해서는 MySQL DB에 연결되어야 합니다. MySQL의 버전은 5.x 이상을 권장합니다. <br/>";
		inHtml += "	아래의 DB 연동 정보를 입력한 후 연동 테스트 버튼을 클릭하세요.<br/>";
		inHtml += " <table>";
		inHtml += " 	<tr>";
		inHtml += "     	<td><span class=\"num\"> JDBC URL </span></td>";
		inHtml += "     	<td><input type=\"text\" name=\"jdbc_url\" id=\"jdbc_url\"  class=\"wide\" value=\"${jdbc_url}\" ></td>"; 
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td><span class=\"num\"> User ID </span></td>";
		inHtml += "     	<td><input type=\"text\" name=\"jdbc_id\" id=\"jdbc_id\" class=\"wide\" value=\"${jdbc_id}\" ></td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td><span class=\"num\"> User Password </span></td>";
		inHtml += "     	<td><input type=\"password\" name=\"jdbc_pw\" id=\"jdbc_pw\"  class=\"wide\" value=\"${jdbc_pw}\" ></td>";
		inHtml += " 	</tr>";
		inHtml += " </table>";
		inHtml += "<br>JDBC 정보가 올바로 입력되었다면 아래 연동 테스트 버튼을 클릭하여 JDBC 연동이 정상적으로 수행되는지 확인해 주세요. <br/>";
		inHtml += "JDBC의 연결정보가 올바로 되었다면  <b>'JDBC 연결 테스트가 정상적으로 처리 되었습니다'</b> 라는  메세지가 출력이 됩니다. <br/>";
		inHtml += "	만약, 오류 메세지가 나타나면  MySQL JDBC 연동이 제대로 되지 않은 경우 입니다.<br/>";
		inHtml += "	이런 경우 계정 및 주소, 사용자 정보등을 다시 확인하여 시도해보시고, 그래도 되지 않으면 서버의(호스팅) 관리자를 통하여 방화벽이나 다른 조치사항이 있어야 하는지 확인 한 후 다시 인스톨 하셔야 합니다.  연동 테스트 클릭 후 다음을 진행하세요.<br/>";
		inHtml += " <div style=\"text-align: center; padding-top:10px\"><button type=\"button\" id=\"linkTest\" name=\"linkTest\" class=\"btn-wide\">연동테스트</button></div>";
		inHtml += "</div>";
		inHtml += "<div class=\"foot-cont\">";
		inHtml += "		<div name=\"prev\" id=\"prev_step_0\" onclick=\"clearThis('"+id+"')\" title=\"step_0\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">이전</div>";
		inHtml += "		<div name=\"next\" id=\"next_step_1\" onclick=\"nextStep('step_2')\" title=\""+id+"\" role=\"button\" class=\"btn\" style=\"cursor:pointer\" >다음</div>";
		inHtml += "	</div>";
		inHtml += "</div>";
		inHtml += "<br />";
				
		$("#step_0").after(inHtml);
	}

	/*
	 * step_2 - 테이블 및 기초 데이터 생성 
	 */
	function setAdminInfoLayer2(id){ 
		clearThis(id);
		var inHtml = "";

		inHtml += "<div class=\"wrap-cont\" id=\""+id+"\" >";
		inHtml += "	<h2 class=\"tit-next\">Table 생성 및 Data 저장 </h2>";
		inHtml += "	<div class=\"body-cont\" style=\"height:430px;\">";
		inHtml += " <div class=\"num\">Step3. Table 및 기본 데이터 설정 저장 </div><br/>"; 
		inHtml += "	GLiDERWiki™를 이용하기 위한 테이블 및 기본 데이터를 생성합니다. 캐릭터 셋 정보를 선택 한 후 테이블 생성 버튼을 클릭하세요.";
		inHtml += "	관리자 계정은 어드민 모드에 접속하기 위한 사용자 계정이며, Active Key와 이메일 정보는 GLiDERWiki™ 다운로드 할 때 입력한 메일 정보와 해당 메일로 전송된 Active Key를 입력하시면 됩니다.<br/>";	
		inHtml += " <ul>";
		inHtml += "		<li>캐릭터 셋을 선택 한 후 테이블 생성 버튼을 통하여 테이블이 정상적으로 생성되면 화면에 <b>[한글 데이터 정상]</b>이 출력이 됩니다.</li>";
		inHtml += "		<li>한글이 정상적으로 보이지 않는다면 리셋 버튼을 클릭한 후 다시 캐릭터 셋 정보를 변경하세요 (예: utf-8  > euc-kr 캐릭터셋 변경)</li>";
		inHtml += " 	<li>GLiDERWiki™ 은 기본 캐릭터(utf-8) 셋과 utf8_unicode_ci , euc-kr 타입의 캐릭터 셋을 지원합니다.</li>";
		inHtml += " 	<li>캐릭터셋 변경 후 테스트를 수행하였음에도 한글이 깨지면 서버의 관리자에게 UTF-8이나 EUC-KR 타입의 DB 설정을 요청하셔야 합니다.<br> 이 경우 테이블을 리셋한 후 Step1 단계부터 다시 진행하셔야 합니다. </li>";
		inHtml += " 	<li>보안상 비밀번호 암호화 되어 보관되고 초기화를 지원하지 않으므로 패스워드를 잊어버리지 않도록 확실히 기억할 수 있는 비밀번호를 넣어주세요. 패스워드 분실시에는 DB에 다이렉트 접근하여 update 해주셔야 합니다.</li>";
		inHtml += " </ul>";
		inHtml += " <table style=\"width:820px\">";
		inHtml += " 	<tr>";
		inHtml += "     	<td width=\"160px;\"><b>캐릭터셋 설정</b></td>";
		inHtml += "     	<td width=\"270px;\">";
		inHtml += "         <input type=\"radio\" name=\"charType\" id=\"charType\" value=\"utf-8\" checked> utf-8 기본"; 
		inHtml += "         &nbsp;&nbsp;<input type=\"radio\" name=\"charType\" id=\"charType\" value=\"utf8_unicode_ci\"> utf8_unicode_ci "; 
		inHtml += "         &nbsp;&nbsp;<input type=\"radio\" name=\"charType\" id=\"charType\" value=\"euc-kr\"> euc-kr "; 
		inHtml += "     	</td>";
		inHtml += "     	<td width=\"360\" colspan=\"2\">";
		inHtml += "         <button type=\"button\" class=\"btn-down\"name=\"createTable\" id=\"createTable\">테이블 생성</button>";
		inHtml += "         <button type=\"button\" class=\"btn-down\"name=\"resetTable\" id=\"resetTable\">테이블 리셋</button>";
		inHtml += "     	</td>";	
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td width=\"180\"><b>관리자 Login 이메일</b></td>";
		inHtml += "     	<td width=\"270\"><input type=\"text\" id=\"adminMailId\" name=\"adminMailId\" size=\"20\" style=\"\" disabled></td>";
		inHtml += "     	<td width=\"150\"><b>관리자 Login Password</b></td>";
		inHtml += "     	<td width=\"210\"><input type=\"password\" id=\"adminpass\" name=\"adminpass\" size=\"20\" style=\"\" disabled></td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td><b>사이트 명</b></td>";
		inHtml += "     	<td><input type=\"text\" size=\"20\" id=\"adminSite\" name=\"adminSite\" style=\"\" disabled></td>";
		inHtml += "     	<td><b>사용자 등록 이메일</b></td>";
		inHtml += "     	<td><input type=\"text\" size=\"20\" id=\"userMail\" name=\"userMail\" style=\"\" disabled></td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td><b>Active Key</b></td>";
		inHtml += "     	<td colspan=\"3\"><input type=\"text\" id=\"activeKey\" name=\"activeKey\" size=\"90\" style=\"\" disabled></td>";
		inHtml += " 	</tr>";
		inHtml += " </table>";
		inHtml += " <div style=\"text-align: center\"><button type=\"button\" id=\"dataSave\" name=\"dataSave\" class=\"btn-wide\">데이터저장</button></div>";
		inHtml += "	</div>";
		inHtml += "	<div class=\"foot-cont\">";
		inHtml += "		<div name=\"prev\" id=\"prev_step_1\" onclick=\"clearThis('"+id+"')\" title=\"step_1\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">이전</div>";
		inHtml += "		<div name=\"next\" id=\"next_step_2\" onclick=\"nextStep('step_3')\" title=\""+id+"\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">다음</div>";
		inHtml += "	</div>";
		inHtml += "</div>";
		inHtml += "<br/>";

		$("#step_1").after(inHtml);
	}

	/*
	 * step_3 - 이미지 정보 저장 
	 */
	function setAdminInfoLayer3(id){ 
		clearThis(id);	
		var inHtml = "";
		
		inHtml += "<div class=\"wrap-cont\" id=\""+id+"\">";
		inHtml += "<form id=\"frmFile\" name=\"frmFile\" method=\"post\" enctype=\"multipart/form-data\">";
		inHtml += "	<h2 class=\"tit-next\">Image Upload 테스트</h2>";
		inHtml += "	<div class=\"body-cont\" style=\"height:410px;\">";  
		inHtml += " <div class=\"num\">Step4. Image preview 테스트 </div><br/>"; 
		inHtml += "	GLiDERWiki™ 의 파일 업로드 및 이미지 미리보기 기능을 지원하기 위하여 디렉토리 권한(퍼미션)을 변경하여야 합니다.<br/>";
		inHtml += "	파일 업로드와 이미지 미리보기 및 섬네일 기능을 지원하는지 여부를 확인 하기 위해서  아래 파일 첨부 폼에 이미지 파일(png, jpg, gif등)<br/>"; 
		inHtml += "	을 선택 하여 업로드 버튼을 클릭하시기 바랍니다.<br/><br/>";
		inHtml += " <span class=\"box-img\">";
		inHtml += " 	<img id=\"preImg\" src=\"\" alt=\"\" />";
		inHtml += " </span>";
		inHtml += " <input type=\"file\" name=\"file\" id=\"file\"  onchange=\"uploadFile()\" /><br><br>";
		inHtml += " <ul>";
		inHtml += " 	<li>이미지 미리보기에 선택한 이미지가 보인다면 정상적으로 처리 된 것입니다. </li>";
		inHtml += " 	<li>GLiDERWiki™은 Internet Explorer 8버전 이상, Mozilla Firefox 5 버전 이상, 구글 Chrome 브라우저에서 정상 동작합니다.</li>";
		inHtml += " 	<li>그 이하 버전인 경우나 크롬, 익스플로러, 파이어폭스 이외의 브라우저에서는 이미지 업로드시 미리보기 및 일부 기능이 원할하게 동작하지 않을 수 있으므로 브라우저 버전을 확인하여 주시기 바랍니다.</li>";
		inHtml += " </ul>";
		inHtml += " <br>";
		inHtml += " <div class=\"num\" style=\"text-align: center\">";
		inHtml += " <button type=\"button\" onclick=\"window.open('http://www.google.com/chrome')\" id=\"\" name=\"\" class=\"btn-big\">구글 크롬 브라우저 다운</button> &nbsp;&nbsp;";
		inHtml += " <button type=\"button\" onclick=\"window.open('http://www.mozilla.or.kr/ko')\" id=\"\" name=\"\" class=\"btn-big\">Firefox 브라우저 다운</button>";
		inHtml += " </div>";
		inHtml += "	</div>";
		inHtml += "	<div class=\"foot-cont\">";
		inHtml += "		<div name=\"prev\" id=\"prev_step_2\" onclick=\"clearThis('"+id+"')\" title=\"step_3\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">이전</div>";
		inHtml += "		<div name=\"next\" id=\"next_step_3\" onclick=\"nextStep('step_4')\" title=\""+id+"\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">다음</div>";
		inHtml += "	</div>";
		inHtml += "</form>";
		inHtml += "</div>";
		inHtml += "<br />";
		
		$("#step_2").after(inHtml);
	}

	/*
	 * step_4 - SMTP 전송 메일 설정 
	 */
	function setAdminInfoLayer4(id){ 
		clearThis(id);
		var domain = $("#domain").val();
		var inHtml = "";
		var adminSite = $("#adminSite").val();
		inHtml += "<div class=\"wrap-cont\" id=\""+id+"\">";
		inHtml += "	<h2 class=\"tit-next\">메일설정</h2>";
		inHtml += "	<div class=\"body-cont\" style=\"height:450px; padding : 40px 65px\">";		
		inHtml += " <div class=\"num\">Step5. 메일 전송 계정 설정 </div><br/>";

		inHtml += " GLiDERWiki™ 은 회원 가입시, 스팸 유저를 필터링하기 위해서 메일로 회원 인증 절차를 거치도록 지원합니다. <br/>";
		inHtml += " 메일 전송 기능은 알람 시스템과 연동되므로 반드시 설정되어야 합니다. SMTP 전송 서버가 없을 경우 우선 구글 G메일 계정으로 설정 하신 후 관리자 모드에서 SMTP 서버로 변경 할 수 있습니다. <b>G메일 전송 테스트</b> 후 다음단계를 진행하세요.<br/>";

		inHtml += " <table>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>Mail UserID </td>";
		inHtml += "     	<td><input type=\"text\" size=\"30\" id=\"mailUserId\" name=\"mailUserId\"></td>";
		inHtml += "     	<td>전송 계정으로 사용할 G메일 주소 입력</td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>Mail Password </td>";
		inHtml += "     	<td><input type=\"password\" size=\"30\" id=\"mailUserPassword\" name=\"mailUserPassword\"></td>";
		inHtml += "     	<td>G메일의 비밀번호 입력</td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>SMTP Host Name </td>";
		inHtml += "     	<td><input type=\"text\" size=\"30\" id=\"smtpHostName\" name=\"smtpHostName\" value=\"smtp.gmail.com\" disabled></td>";
		inHtml += "     	<td>SMTP 전송 호스트 명 입력(편집불가)</td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>SMTP Port </td>";
		inHtml += "     	<td><input type=\"text\" size=\"30\" id=\"smtpServerPort\" name=\"smtpServerPort\" value=\"587\" disabled></td>";
		inHtml += "     	<td>SMTP 전송 포트 입력(편집불가)</td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>전송도메인</td>";
		inHtml += "     	<td><input type=\"text\" size=\"30\" id=\"siteDomain\" name=\"siteDomain\" value=\""+domain+"\"></td>";
		inHtml += "     	<td>보내는 사람의 전송 도메인 입력</td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>전송사이트명</td>";
		inHtml += "     	<td><input type=\"text\" size=\"30\" id=\"siteOwner\" name=\"siteOwner\" value=\""+adminSite+"\" disabled></td>";
		inHtml += "     	<td>보내는 사이트 명 (편집불가)</td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>Charset 지정</td>";
		inHtml += "     	<td>";
		inHtml += "         <select class=\"select-list\" id=\"mailCharset\" name=\"mailCharset\">";
		inHtml += "            <option value=\"euc-kr\" selected>euc-kr</option>";
		inHtml += "            <option value=\"utf-8\">utf-8</option>";
		inHtml += "     	</td>";
		inHtml += "     	<td>메일 전송 캐릭터 셋 지정</td>";
		inHtml += " 	</tr>";
		inHtml += " 	<tr>";
		inHtml += "     	<td>전송 테스트용 메일주소 </td>";
		inHtml += "     	<td><input type=\"text\" size=\"30\" id=\"testUserMail\" name=\"testUserMail\"></td>";
		inHtml += "     	<td>전송 테스트 버튼을 클릭하여 테스트 메일이 전송되는지 확인</td>";
		inHtml += " 	</tr>";
		inHtml += " </table>";

		inHtml += "	</div>";
		inHtml += "<div class=\"foot-cont\">";
		inHtml += "    <div name=\"prev\" id=\"prev_step_3\" onclick=\"clearThis('"+id+"')\" title=\"step_4\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">이전</div>";
		inHtml += "    <div name=\"next\" id=\"main_4\" onclick=\"sendTestMail();\" title=\""+id+"\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">전송테스트</div>";
		inHtml += "    <div name=\"next\" id=\"next_step_4\" onclick=\"nextStep('step_5')\" title=\""+id+"\" role=\"button\" class=\"btn\" style=\"cursor:pointer\">다음</div>";
		inHtml += "</div>";
		inHtml += "</div>";
		inHtml += "<br />";
		
		$("#step_3").after(inHtml);
	}
	
	/*
	 * step_5 - 어드민 정보 저장  	
	 */
	
	function setAdminInfoLayer5(id){ 
		clearThis(id);
		var inHtml = "";
		var domain = $("#domain").val();
		
		inHtml += "<div class=\"wrap-cont\" id=\""+id+"\">";
		inHtml += "	<h2 class=\"tit-next\">설치 완료</h2>";
		inHtml += "	<div class=\"body-cont\" style=\"height:410px;\">";		
		inHtml += " <div class=\"num\">Step6. 설치가 완료되었습니다.</div><br/>";
		inHtml += "	<div class=\"info-text\">환영합니다. <br/>";
		inHtml += "	오픈소스 프로젝트 GLiDERWiki™의 설정이 정상적으로 완료 되었습니다. 이제 서버를 restart 하면 정상적으로 동작 될 것입니다.<br/>";
		inHtml += "	GLiDERWiki™에 대한 문의 사항이 있으실 경우 GLiDERWiki™ OpenSource Team의  <a href=\"http://www.gliderwiki.org\" target=\"_blank\">Wiki</a> 사이트를  이용하여 주시기 바랍니다.";
		inHtml += " ";
		inHtml += "<ul>";
		inHtml += "<li>	어드민 모드에 접속하기위해 아래의 주소를 기억해주시기  바랍니다.</li>";
		inHtml += "<li>	어드민 모드는 오직 URL 을 입력한 후 Step.3 에서 입력한 아이디와 비밀번호를 통해 접속하도록 되어있습니다.</li>";
		inHtml += "<li>	어드민 주소 : "+domain+"/admin/wikiadminlogin</li>";
		inHtml += "</ul>";
		inHtml += "	아울러, 본 Web Installer 화면은 더 이상 접근할 수 없습니다. 반드시 서버를 restart 해주시기 바라며, 만약 설치가 제대로 안된 경우라면 GLiDERWiki™ 를 참고하여 수동 설치 방식으로 재설치 하시기 바랍니다.<br/>";
		inHtml += "	지식정보 공유 및 축적을 위한 협업 플랫폼으로 개발한 오픈소스 GLiDERWiki™ 를 이용해주셔서 감사합니다.</div><br/>";
		
		//inHtml += "<div style=\"text-align: center\">";
		//inHtml += "<button type=\"button\" id=\"\" name=\"\" class=\"btn-wide\">GLiDERWiki™ 방문하기</button> &nbsp;&nbsp;";
		//inHtml += "<button type=\"button\" id=\"\" name=\"\" class=\"btn-wide\">수동설치 방법 보기</button> &nbsp;&nbsp;";
		//inHtml += "<button type=\"button\" id=\"\" name=\"\" class=\"btn-wide\">어드민 사용메뉴얼 보기</button>";
		//inHtml += "</div>";
		//inHtml += "e-mail - <input type=\"text\" size=\"20\" style=\"font-size:40px;padding-left:30px;\"><br /><br />";
		inHtml += "</div>";
		inHtml += "<div class=\"foot-cont\">";
		inHtml += "    <div role=\"button\" class=\"btn\" style=\"width:140px\" onclick=\"window.open('http://www.gliderwiki.org')\" >GLiDERWiki™ 방문하기</div>";
		inHtml += "    <div name=\"next\" id=\"next_step_4\" onclick=\"completeWiki()\" title=\""+id+"\" role=\"button\" class=\"btn\">설치 완료</div>";
		inHtml += "</div>";
		inHtml += "</div>";
		inHtml += "<br />";

		$("#step_4").after(inHtml);
	}
	
	/*
	 * 현재 클릭한 레이어를 삭제한다. 
	 */ 
	function clearThis(id) {
		$("#"+id).remove();
	}

		
	function uploadFile() {
		$("#frmFile").unbind();
		$("#frmFile").ajaxForm(FileuploadCallback);
		$("#frmFile").attr("action","/admin/installUpload");
		$("#frmFile").submit();
	}

	// 파일 업로드 콜백 
	function FileuploadCallback(data,state){
		console.log("data : " + data);
		var jsonStr = eval("("+data+")");

	
		if(jsonStr != null) {
			if(jsonStr.result == 1 ) {		
				alert('파일 업로드가 완료되었습니다.');
				isUpload = true;
				var fileSrc = jsonStr.thumbPath+jsonStr.thumbName;
				// 섬네일을 출력한 후 리사이징 한다. 
				$("#preImg").attr("src", "/resource/temp/"+fileSrc).css({height:"98px", width:"168px"}); 
				
			} else {
				$("#frmFile").unbind();
				isUpload = false;
				alert(jsonStr.msg);
			}
		} else {
			$("#frmFile").unbind();
			isUpload = false;
			alert('에러가 발생하였습니다.');
		}
	}
	
	//메일 전송 테스트 
	function sendTestMail() {
		var mailUserId = $("#mailUserId").val();
		var mailUserPassword = $("#mailUserPassword").val();
		var siteDomain = $("#siteDomain").val();
		var smtpHostName = $("#smtpHostName").val();
		var smtpServerPort = $("#smtpServerPort").val();
		var siteOwner = $("#siteOwner").val();
		var mailCharset = $("#mailCharset option:selected").val(); // 선택한 group_id
		var testUserMail = $("#testUserMail").val();
		var mailUserKey  = $("#activeKey").val();
		
		
		if(mailUserId == '') { 
			alert('보내는 메일 주소를 입력하세요.');
			return;
		}
		
		if(mailUserPassword == '') { 
			alert('비밀번호를 입력하세요.');
			return;
		}
		
		if(siteDomain == '') { 
			alert('전송 도메인을 입력하세요.');
			return;
		}
		
		if(siteOwner == '') { 
			alert('전송유저를 입력하세요');
			return;
		}
		
		if(testUserMail == '') { 
			alert('테스트 전송 메일을 입력하세요.');
			return;
		}
				
		$.loadingBar();
		$.post("/admin/install/mailSend", {mailUserId:mailUserId,mailUserPassword:mailUserPassword,siteDomain:siteDomain,siteOwner:siteOwner,mailCharset:mailCharset,testUserMail:testUserMail,mailUserKey:mailUserKey,smtpHostName:smtpHostName,smtpServerPort:smtpServerPort }, function(data){

			var jsonStr = eval("("+data+")");

			
			if(jsonStr.result == 'SUCCESS'){
				$.loadingBar.fadeOut();
				isSendMail = true;
				alert(testUserMail + "로 테스트 메일이 전송 되었습니다. 다음 단계를 진행하세요.");
				return;
			} else {
				isSendMail = false;	// 간혹 전송 테스트가 되지 않는 경우 재시도 한다. 
				$.loadingBar.fadeOut();
				alert('전송중 에러가 발생하였습니다. 정보를 다시 확인 한 후 시도해주세요.');
				return;
			}
		});
	}
	
	
	function nextStep(id) {
		var ids = id.split("_")[1];
		if (ids == 2) {
			if(isConnect) {
				setAdminInfoLayer2(id);	
			} else {
				alert('Step.2 연동 테스트를 거친 후 다음 단계로 이동할 수 있습니다.');
				return;
			}
			
		} else if (ids == 3) {
			if(isDataSave) {
				setAdminInfoLayer3(id);	
			} else {
				alert('Step.3 Table 생성 및 Data 저장 후  다음 단계로 이동할 수 있습니다.\n테이블이 정상적으로 생성되었다면 데이터 저장 버튼을 클릭하세요.');
				return;
			}
			
		} else if (ids == 4) {
			if(isUpload) {
				setAdminInfoLayer4(id);			
			} else {
				alert('Step.4 이미지 프리뷰 테스트 후 다음 단계로 이동할 수 있습니다.');
				return;
			}
		} else if (ids == 5) {
			if(isSendMail) {
				setAdminInfoLayer5(id);			
			} else {
				alert('Step.5 메일 전송 및 계정 설정 테스트 후 다음 단계로 이동할 수 있습니다.');
				return;
			}
		}
		// 엥커 이동 
		gotoAnchor(id);
	}
	
	
	function completeWiki() {
		var domain = $("#domain").val();
		var urls = domain + "admin/wikiadminlogin";
		alert('GLiDERWiki™ 가 정상적으로 설치 되었습니다.\n서버 restart 후 '+urls+'으로 이동 하여 관리자 모드에 접속하세요.');
		//TODOLIST  글라이더 위키에 개인정보 전송 후, 어드민 주소를 사용자 메일로 전송해야 함 
	}
//]]>
</script>
</body>
</html>