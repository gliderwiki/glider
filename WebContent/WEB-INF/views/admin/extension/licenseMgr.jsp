<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>

<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">라이센스 정보 </h2>
	
			<div class="body-cont">
			
			
				<div class="license">
					<h3>라이선스 정보</h3>
					<div class="box-srch">
						<form action="/admin/license" method="POST">
							<table>
								<tr>
									<th>라이선스</th>
									<td>Community Lisence</td>
								</tr>
								<tr>
									<th>라이선스키</th>
									<td>
										<input type="text" name="lisence_key" value="${activeKey }"/>
									</td>
								</tr>
							</table>
							<button type="submit" class="btn btn-submit">업데이트
							</button>
						</form>
					</div>
					
					<!-- 
					<table class="tbl-result">
						<thead>
							<tr>
								<th>라이센스 적용일</th>
								<th>라이센스 만료일</th>
								<th>사용가능 유저수</th>
								<th>사용가능 위키수</th>
								<th>사용가능 공간수</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>2012-08-24</td>
								<td>2013-08-24</td>
								<td>1,000 user</td>
								<td>1,000 page</td>
								<td>1,000 space</td>
							</tr>
						</tbody>
					</table>
					-->
				</div>
				
			</div>					
	</div>
</section>
<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>