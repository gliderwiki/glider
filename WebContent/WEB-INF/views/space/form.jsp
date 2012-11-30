<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<html>
<head>
</head>
<body>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">공간정보 보기</h2>
			<div class="body-cont space">
				<div class="edit">
					<form:form modelAttribute="WeSpace" name="WeSpace" method="POST" action="/space/create">
					<form:hidden path="we_space_idx"/>
					<form:hidden path="we_view_data"/>
					<form:hidden path="we_edit_data"/>
					<form:hidden path="we_view_name"/>
					<form:hidden path="we_edit_name"/>
					<form:hidden path="we_upload_imgName"/>
					<input type="hidden" name="_method" id="_method">
					<!-- 상단 간격조정  -->
					<div class="box" style="margin-top:-20px">
						<h3>공간제목을 입력하세요</h3>
						<form:input path="we_space_name" type="text" value="${WeSpace.we_space_name}" class="inp-dup"/>
						<button type="button" class="btn-dup nameCheck">중복체크</button>
					</div>
					<div class="box">
						<h3>공간설명을 입력하세요</h3>
						<form:textarea rows="5" path="we_space_desc" value="${WeSpace.we_space_desc}" />
					</div>
					<div class="box">
						<h3>공간 Privacy 설정</h3>
						<p>
							<form:radiobutton path="we_space_exposed" value="Y" />
							<label for="privacyPublic">
								<strong class="tit">공간 정보 공개</strong>
								: 공간의 업데이트가 정보가 메인 화면에 노출됩니다.
							</label>
						</p>
						<p>
							<form:radiobutton path="we_space_exposed" value="N" />
							<label for="privacyNonPublic">
								<strong class="tit">공간 정보 공개 안함</strong>
								: 공간의 업데이트가 정보가 메인 화면에 노출되지 않습니다.
							</label>
						</p>
					</div>
					<div class="space" id="exposed_warning" style="display:block;">
					</div>
					<div class="box">
					<h3>공간 권한 지정 : 페이지 생성, 수정 권한을 할당할 수 있습니다</h3>
					<div class="box-group fst">
						<h4 class="tit">공간의 내용을 볼 수 있는 그룹</h4>
						<span class="item">
							<form:radiobutton path="we_view_privacy" value="ALLGROUP" />
							<label for="read1">전체 사용자 허용</label>
						</span>
						<span class="item">
							<form:radiobutton path="we_view_privacy" value="GROUP" data-space-idx="${WeSpace.we_space_idx}" />
							<label for="read2">그룹선택</label>
						</span>
						<span class="item">
							<form:radiobutton path="we_view_privacy" value="USER" data-space-idx="${WeSpace.we_space_idx}" />
							<label for="read3">구성원선택</label>
						</span>
						<div id="selectedViewAuthorityArea" style="display:none">
						</div>
					</div>
					<div class="space" id="view_warning" style="display:block;">
					</div>
					<div class="box-group">
						<h4 class="tit">공간의 페이지를 생성, 수정할 수 있는 그룹</h4>
						<span class="item">
							<form:radiobutton path="we_edit_privacy" value="ALLGROUP" />
							<label for="edit1">전체 사용자 허용</label>
						</span>
						<span class="item">
							<form:radiobutton path="we_edit_privacy" value="GROUP" data-space-idx="${WeSpace.we_space_idx}" />
							<label for="edit2">그룹선택</label>
						</span>
						<span class="item">
							<form:radiobutton path="we_edit_privacy" value="USER" data-space-idx="${WeSpace.we_space_idx}" />
							<label for="edit3">구성원선택</label>
						</span>
					</div>
					<div class="space" id="edit_warning" style="display:block;">
					</div>
				</div>
				<button type="submit" id="formSubmitBtn" style="display:none;">수정하기</button>
		</form:form>
		<form id="imageForm" name="imageForm" target="hiddenImgForm" class="form-img" method="post">
		<input type="hidden" name="spaceNameCheck" id="spaceNameCheck" value="0">
			<div class="box">
				<h3>공간의 Identity 이미지를 업로드하세요</h3>
				<span id="previewImg" class="box-img">
					<c:if test="${not empty WeSpace.we_space_idx}">
						<img src="/resource/real${spaceImage}" alt="" id="uploadPreviewImg" />
					</c:if>
				</span>
				<input type="file" multiple name="uploadFile" id="uploadFile" />
			</div>
		</form>
	</div>
</div>
<div class="foot-cont">
	<button type="button" class="btn preview">미리보기</button>
	<!-- 아래버튼으로 맨위 폼의 섭밋을 트리거 해줍니다 -->
	<button type="button" class="btn spaceCreate">
		<c:choose>
		<c:when test="${empty WeSpace.we_space_idx}">
			저장하기
		</c:when>
		<c:otherwise>수정하기</c:otherwise>
		</c:choose>
	</button>
	<a href="/space/main/${WeSpace.we_space_idx}" class="btn">공간메인목록</a>
</div>
<iframe name="hiddenImgForm" id="hiddenImgForm" src="" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" height="0" width="0"  ></iframe>
</div>
</section>
<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/glider/space/spaceService.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var write = new GliderWiki.Space.Write($(".edit"),'${adminName}').create();
});
</script>
</js:scripts>
</body>
</html>