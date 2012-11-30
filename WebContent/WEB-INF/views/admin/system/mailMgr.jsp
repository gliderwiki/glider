<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>
<section class="contents" role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">전송 메일 설정</h2>
	
			<div class="body-cont">
							
			<div class="mail">
				<h3>메일설정 (설정을 변경한 후 반드시  저장 후에 테스트 메일을 전송하세요.)</h3>
				<div class="box-mail">
					<form action="/admin/mail/regist">
					<input type="hidden" name="mailUserKey" id="mailUserKey" value="${mailUserKey }" />
						<table>
							<tr>
								<th>SMTP Host Name</th>
								<td>
									<input type="text" name="smtpHostName" id="smtpHostName" value="${smtpHostName }" />
								</td>
							</tr>
							<tr>
								<th>SMTP Port</th>
								<td>
									<input type="text" name="smtpServerPort" id="smtpServerPort" value="${smtpServerPort }"  />
								</td>
							</tr>
							<tr>
								<th>Mail UserID</th>
								<td>
									<input type="text" name="mailUserId" id="mailUserId" value="${mailUserId }" />
								</td>
							</tr>
							<tr>
								<th>Mail Password</th>
								<td>
									<input type="password" style="width:440px" name="mailUserPassword" id="mailUserPassword" value="${mailUserPassword }" />
								</td>
							</tr>
							<tr>
								<th>전송도메인</th>
								<td>
									<input type="text" name="siteDomain" id="siteDomain" value="${siteDomain }" />
								</td>
							</tr>
							<tr>
								<th>전송유저</th>
								<td>
									<input type="text" name="siteOwner" id="siteOwner" value="${siteOwner }" />
								</td>
							</tr>
							<tr>
								<th>캐릭터셋</th>
								<td>
									<select class="select-list" id="mailCharset" name="mailCharset" style="width:142px">
										<option value="">선택하세요.</option>
										<option value="euc-kr" <c:if test="${mailCharset eq 'euc-kr' }">selected</c:if>>euc-kr</option>
										<option value="utf-8" <c:if test="${mailCharset eq 'utf-8' }">selected</c:if>>utf-8</option>
									</select>
								</td>
							</tr>
						</table>
						<button type="submit" class="btn btn-submit">전송메일<br />설정저장</button>
					</form>
				</div>
			
			</div>
			<div class="block">
				<h3>테스트 메일 전송 확인 (상단에 설정된 메일 정보가 제대로 전송되는지 테스트 합니다.)</h3>
				<div class="box-add">
						<table>
							<tr>
								<th>받는메일주소</th>
								<td>
									<input type="text" name="testUserMail" id="testUserMail" value="" />
								</td>
							</tr>
						</table>
						<button type="button" class="btn btn-submit" id="sendMailUserBtn">전송테스트</button>
				</div>
			</div>		
		</div>			
	</div>
</section>			


<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/AdminKeywordService.js'></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>
<script type="text/javascript" src="/resource/libs/plugin/jquery-loadingbar.js"></script>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	/**
	 * 01. 메일 전송 테스트 
	 */
	$("#sendMailUserBtn").click(function(){
		var testUserMail = $("#testUserMail").val();
		
		if(testUserMail == '') { 
			alert('받는 사용자의 메일 주소를 입력하세요');
			return;
		}
		
		if(confirm('현재 저장된 메일 설정 정보를 기반으로 테스트용 메일을 전송하시겠습니까?')) {
			$.loadingBar();
			$.ajax({
				type:"GET"
				,url:"/admin/mail/sendtest"
				,data:{"testUserMail":testUserMail}
				,dataType:"json"
				,success:function(rtnObj){					
					if(rtnObj.result == 'SUCCESS'){
						$.loadingBar.fadeOut();
						alert(rtnObj.rtnMsg);
						return;
					} else {
						$.loadingBar.fadeOut();
						alert(rtnObj.rtnMsg);
						return;
					}
				}
			});		
		} else {
			$.loadingBar.fadeOut();
			return;
		}
	});
	
});

//]]>
</script>



<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>				