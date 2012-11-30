<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<section class="contents " role="main">
	<div class="wrap-cont">

		<h2 class="tit-cont">템플릿 목록조회</h2>

		<div class="body-cont my">
			<c:choose>
			<c:when test="${templateSize == 0}">
			<div class="sub">
				<div class="box-empty">
					생성된 템플릿 목록이 없습니다.
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div class="sub favorite">
				
				<div class="box">			
					<h4 class="tit-sec">템플릿 목록</h4>
						<ul class="list type1">
						<c:forEach items="${tempList }" var="tempList" varStatus="stat">	
						<li id="">
							<div class="title" style="width:498px">
								<a href="/template/view/${tempList.we_template_idx }">${tempList.we_template_name }</a>
							</div>
							<div class="time" style="width:120px;text-align: center;">
								${gf:articleDate(tempList.we_ins_date,'yyyy.MM.dd hh:mm:ss')}
							</div>
							<div class="time" style="width:120px;text-align: center;">
								${tempList.we_user_nick }
							</div>
							<c:if test="${loginUser.weGrade > 2}">
							<!-- TODOLIST 레벨 3인 사용자와 실제 작성자만 삭제가 가능하다.  -->
							<div class="info" style="width:50px;text-align: center;">
								<a href="javascript:deleteTemp('${tempList.we_template_idx }');" name="deleteTemp" title="${tempList.we_template_idx }" id="deleteTemp_${tempList.we_template_idx }" role="button" class="btn-del">삭제</a>
							</div>
							</c:if>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="foot-cont">
		<div class="wrap_btn">
			<a href="/template/new" class="btn">신규템플릿등록</a>
		</div>
	</div>
</section>

<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminWikiService.js'></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-preview-layer.js"></script>
<script type="text/javascript">
//<![CDATA[

$(document).ready(function() {

});

/**
 * 01.템플릿 삭제 
 */
function deleteTemp(idx) {
	GliderWiki.confirm("확인창", "선택한 템플릿을 삭제하겠습니까?", function () {
		$.ajax({
			 type:"GET"
			,url:"/template/delete/"+idx
			,dataType:"json"
			,success : function (res) {
				var status = res.result;
				
				if(status == '1'){
					$(location).attr('href',"/template/list");	
				}
			}
		});	
	});
}


//]]>
</script>	