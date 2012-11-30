<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">확장기능 관리</h2>
	
			<div class="body-cont">
			
			
				<div class="block">
					<h3>확장 기능 목록</h3>
					<div class="extension">
					<table class="tbl-result">
						<thead>
							<tr>
								<th>제목</th>
								<th>내용</th>
								<th>타입</th>
								<th>날짜</th>
								<th>구분</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
							<c:when test="${empty list}">
								<tr><td colspan="5">글라이더 위키의 확장 기능 추가 목록이 없습니다.</td></tr>
							</c:when>
							<c:otherwise>							
							<c:forEach items="${list }" var="list" varStatus="i">
							<tr id="patchRow">
								<td>${list.we_function_name }</td>
								<td>${list.we_function_desc }</td>
								<td>${list.we_function_type }</td>
								<td>${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}</td>
								<td><a href="javascript:funcFileDownload('${list.we_call_url}', '${list.we_function_idx }' ,'${list.we_function_name }')">설치하기</a></td>
							</tr>
							</c:forEach>
							</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
					
				</div>
				
			</div>					
	</div>
</section>

<script type="text/javascript">
//<![CDATA[
var timer = null;
$(document).ready(function() {
	
});


var funcFileDownload = function(url, idx, extName){
	
	$.ajax({
		type:"POST"
		,url:"/admin/getExtention"
		,data:{"url":url,"idx":idx}
		,success:function(response){
			var status = response.status;
			var result = response.result;
			
			console.log("status : " + status);
			console.log("result : " + result);
			
			if(status == 'SUCCESS'){
				alert(extName + ' 확장기능이 정상적으로 설치 되었습니다. 시스템을 재시작 하세요.');
				$("#patchRow").hide();
			}else{
				alert(extName + ' 확장 기능 진행 중 에러가 발생하였습니다.');
			}
		}
	});
}



//]]>
</script>

<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>