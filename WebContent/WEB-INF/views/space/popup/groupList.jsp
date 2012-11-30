<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<html>
<head>
<link rel="stylesheet" href="/resource/glider/front/css/common.css">
<link rel="stylesheet" href="/resource/glider/front/css/modal.css">
<link rel="stylesheet" href="/resource/glider/front/css/alimi.css">
<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.js"></script>
</head>
<body>

<!-- 이하 팝업 -->
<section class="layer-select">
<div class="tit">
	<strong>그룹을 선택하세요</strong>
</div>
<div class="cont">
	<table>
		<thead>
			<tr>
				<th>그룹</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}" varStatus="stat">
			<tr <c:if test="${(stat.count%2) == 0}">class="even"</c:if>>
				<td>
					<c:choose>
					<c:when test="${authorityType == 'view'}">
						<input type="checkbox" name="groupIdx" value="${list.we_group_idx}" data-group-name="${list.we_group_name}" <c:if test="${list.we_view_permit == 1}"> checked </c:if>>
					</c:when>
					<c:when test="${authorityType == 'edit'}">
						<input type="checkbox" name="groupIdx" value="${list.we_group_idx}" data-group-name="${list.we_group_name}" <c:if test="${list.we_insert_permit == 1}"> checked </c:if>>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="groupIdx" value="${list.we_group_idx}" data-group-name="${list.we_group_name}">
					</c:otherwise>
					</c:choose>
					${list.we_group_name}
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="wrap-btn">
	<button type="submit" class="btn checkComplete">선택완료</button>
	<a href="javascript:self.close();" role="button" class="btn">닫기</a>
</div>
</section>

<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var type = "${authorityType}";

	$('.checkComplete').unbind('click').click(function(e){
		var resultData = "";
		var resultName = "";
		e.preventDefault();

		var checkedViewGroupCnt = $("input[name=groupIdx]:checkbox:checked").length;

		if(checkedViewGroupCnt == 0) {
			alert("하나라도 선택을 해야합니다.\n 선택을 하지 않으시려면 닫기버튼을 눌러주세요");
			return false;
		}

		$("input:checkbox[name^=groupIdx]:checked").each(function(index){
			resultData += $(this).val();
			resultName += $(this).data("groupName");

			if(index != (checkedViewGroupCnt-1)) {
				resultData += ',';
				resultName += ',';
			}
		});

		

		if(type == 'view') {
			opener.document.WeSpace.we_view_data.value = resultData;
			opener.document.WeSpace.we_view_name.value = resultName;
		}else{
			opener.document.WeSpace.we_edit_data.value = resultData;
			opener.document.WeSpace.we_edit_name.value = resultName;
		}

		self.close();
		return false;
	});
});

</script>
</body>
</html>