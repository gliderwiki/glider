<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<body>

<section class="contents " role="main">
	<div class="wrap-cont">

		<h2 class="tit-cont">알람관리</h2>


		<div class="body-cont my">
			<div class="sub">
				<h2 class="tit-sub">알람선택하기</h2>
				<form id="frm" name="frm" method="post">
					<div class="check-alarm">

						<c:forEach items="${metaList }" var="metaList" varStatus="stat">
						<p class="row">
						<input type="checkbox" name="metaIdx" value="${metaList.weMetaIdx}"  <c:if test="${metaList.userCheckYn == 'Y'}"> checked="checked"</c:if>>
						<label for="chkAlarm${stat }">${metaList.weMetaDesc}</label>
						</p>
						</c:forEach>
					</div>
					<div class="wrap_btn">
						<input type="button" name="chkAlarm" id="chkAlarm" class="btn" value="선택저장" />
					</div>
				</form>
			</div>
			<div class="sub">

				<div class="wrap-box">

					<div class="box">

						<h4 class="tit-sec">최근알람보기</h4>

						<c:choose>
						<c:when test="${alarmSize eq 0}">
						<div class="sub">
							<div class="box-empty">
							등록된 알람 내역이 없습니다.
							</div>
						</div>
						</c:when>
						<c:otherwise>
						<ul class="list type1">
						<c:forEach items="${alarmList }" var="alarmList" varStatus="stat">
							<li>
								<div class="title">
									${alarmList.weAlarmText }
								</div>
								<div class="time">
									${gf:articleDate(alarmList.weInsDate,'yyyy.MM.dd HH:mm')}
								</div>
							</li>
						</c:forEach>
						</ul>
						</c:otherwise>
						</c:choose>
					</div>


				</div>
			</div>
		</div>
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/UserAlarmService.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		$("#chkAlarm").bind("click", function() {
			// Map 형태로 가공
			var userIdx = "${loginUser.weUserIdx}";
			var itemValue = $('#frm input[name=metaIdx]:checked').map(function() {
				return this.value;
			}).get().join(',');
	
			UserAlarmService.addCheckUserAlarmDWR(itemValue, userIdx, callBackSaveAlarm);
		});

		function callBackSaveAlarm(outData) {
			if(outData != null) {
				var jResult = eval("("+outData+")");
				if(jResult.rtnResult == 1) {
					GliderWiki.alert('안내' , jResult.rtnMsg);
					
				} else {
					GliderWiki.alert('Error', 'Error 상태코드 ['+jResult.rtnStatus+'] : ' + jResult.rtnMsg);
				}
			} else {
				GliderWiki.alert('Error', '결과값 없음');
			}
		}
	});
//]]>
</script>
</js:scripts>
</body>