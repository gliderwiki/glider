<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>

<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">사용자 접근 제어</h2>
			<div class="body-cont">
				<div class="block">
					<h3>접근제어</h3>
					<div class="box-add">
						<form name="filter" id="filter" method="post" action="/admin/filter/create">
							<table>
								<tr>
									<th>아이피</th>
									<td>
										<input type="text" name="targetIp" id="targetIp">
									</td>
								</tr>
							</table>
							<button type="submit" class="btn btn-submit">등록
							</button>
						</form>
					</div>
					<table class="tbl-result">
						<thead>
							<tr>
								<th>등록일</th>
								<th>아이피</th>
								<th>구분</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${restrictList}">
							<tr>
								<td>${gf:articleDate(list.we_ins_date,'yyyy-MM-dd')}</td>
								<td>${list.we_target_ip}</td>
								<td><a href="#" data-target-idx="${list.we_access_idx}" class="deleteIp">[삭제]</a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
	</div>
</section>

<script type="text/javascript">
$(document).ready(function() {
	$("#filter").on("submit", function(e) {
		e.preventDefault();
		var targetIp = $("#targetIp").val();

		if(GliderWiki.Utils.isEmpty(targetIp)) {
			alert('차단할 IP를 입력하세요.');
			return false;
		}

		this.submit();
	});

	$(".deleteIp").on("click", function(e) {
		e.preventDefault();
		var targetIdx = $(this).data("targetIdx");

		if(confirm("해당 아이피 차단을 삭제하시겠습니까?")) {
			var $deleteForm = $('<form action="/admin/filter/delete" method="post" style="display: none;"><input type="hidden" name="targetIdx" value="'+targetIdx+'"></form>');
			$('body').append($deleteForm);
			$deleteForm.submit();
		}
	});
});
</script>

<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>