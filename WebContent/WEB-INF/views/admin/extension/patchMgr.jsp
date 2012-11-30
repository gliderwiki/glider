<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"%>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">GLiDER™ Wiki 패치 정보</h2>
			<div class="body-cont">
				
				<div class="update">
					<h3>버전 정보</h3>
					<div class="box-info">
						<ul>
							<li>
								현재 시스템의 버전 : v.${clientVersion }
							</li>
							<li>
								글라이더 서버 최신 버전 : <strong style="color:red;">v${serverVersion}</strong>
							</li>
						</ul>	
					</div>
					<br />
					<form id="patchDownloadForm" name="patchDownloadForm" method="post">
					<input type="hidden" id="rtnUrl" name="rtnUrl"> 
					<input type="hidden" id="functionIdx" name="functionIdx"> 
					<table class="tbl-result">
						<thead>
							<tr>
								<th style="width:50px">순번</th>
								<th class="version" style="width:70px">버전</th>
								<th class="title" style="width:170px">제목</th>
								<th class="title">내용</th>
								<!-- <th class="time" style="width:70px">타입</th> -->
								<th class="time" style="width:80px">날짜</th>
								<th class="time" style="width:80px">구분</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
							<c:when test="${empty list}">
								<tr><td colspan="6">업데이트 된 패치가 없습니다.</td></tr>
							</c:when>
							<c:otherwise>
							
							<c:forEach items="${list }" var="list" varStatus="i">
							<tr id="patchRow">
								<td>${list.we_function_idx } </td>
								<td class="version">${list.we_function_ver } </td>
								<td class="title">
									<a href="#">${list.we_function_name }</a>
								</td>
								<td class="title">
									<a href="#">${list.we_function_desc }</a>
								</td>
								<!-- <td class="version">${list.we_function_type }</td> -->
								<td>${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}</td>
								<td><button type="button" class="btn-down" onclick="funcFileDownload('${list.we_call_url}', '${list.we_function_idx }' )">Update</button></td>
							</tr>
							</c:forEach>
							</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					</form>
				</div>
			</div>
	</div>
</section>


<script type="text/javascript">
//<![CDATA[
var timer = null;
$(document).ready(function() {
	
});


var funcFileDownload = function(url, idx){
	
	$.ajax({
		type:"POST"
		,url:"/admin/getPatch"
		,data:{"url":url,"idx":idx}
		,success:function(response){
			var status = response.status;
			var result = response.result;
			
			if(status == 'SUCCESS'){
				alert(result + ' 버전 패치가 정상적으로 처리 되었습니다. 시스템을 재시작 하세요.');
				$("#patchRow").hide();
			}else{
				alert(result + ' 패치 진행 중 에러가 발생하였습니다.');
			}
		}
	});
}



//]]>
</script>

<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>