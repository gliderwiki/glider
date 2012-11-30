<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<body>
<div style="cursor:pointer" id="dwrLoad">dwr test : 클릭하세요</div><BR>

<div id="dwrList">

</div>
<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/TempService.js'></script>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	$("#dwrLoad").bind("click", function() {
		// 단일 객체 리스트 조회하기 
		TempService.getListTemp(callBackTempList);
	});
	
	//TODO List 
	// 다중 객체 리스트 조회 하기 추가할 예정 
});

// 콜백 처리 
function callBackTempList(outData) {
	var htmls = "";
	
	if (outData != null && outData.length > 0) {
		htmls += "<ul>";
		// 객체의 리스트 만큼 탐색 
		for(var i=0; i<outData.length; i++){
			htmls += "	<li>순번 : "+outData[i].id+ " 이름 : " + outData[i].name +"</li>";
		}
		htmls += "</ul>";
		
		// 특정 영역 html 구성 
		$("#dwrList").html(htmls);				
	} else {
		alert('에러 발생 : 값이 없음');
	}
}
//]]>
</script>
</js:scripts>
</body>