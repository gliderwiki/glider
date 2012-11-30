<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>

<!-- 
<label id="alimiToggle" class="alimi_toggle on taskbarOnOffBtn">
	<span class="txt">&raquo;</span>
	<input type="checkbox" title="알리미 토글"/>
</label>
 -->
<div class="alimi">
	<div class="bg_alimi left"></div>
	<div class="bg_alimi right"></div>
	<a href="/user/alarm" class="btn_open notificationCountBtn" id="notificationCountBtn">
		<span class="txt">알림</span>
		<em class="num" id="notificationCount">0</em>
	</a>
	<div class="wrap_toggle">
		<label for="checkRealTimeNotification">실시간 팝업</label>
		<input type="checkbox" class="inp_chk" id="checkRealTimeNotification" <c:if test="${viewAlarm == 'Y'}">checked</c:if>>
	</div>

	<div class="layer_alimi">
	</div>
</div>

