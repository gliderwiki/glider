<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width">
<title>GLiDERWiki™ Admin mode</title>
<link rel="stylesheet" href="/resource/glider/admin/css/admin.css" />
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="/resource/libs/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/resource/glider/common/wiki.js"></script>

</head>

<body class="admin">

	<header class="header" role="banner">
		<div class="main">
			<div class="wrap-cont">
				<h1>
					<a href="/admin/index">GLiDERWiki™<span class="top">admin</span>
					</a>
				</h1>
				<ul class="navi">
					<li><a href="/admin/user" <c:if test="${menu eq '1'}"> class="active" </c:if>>그룹/사용자관리</a></li>
					<li class="bar">|</li> 
					<li><a href="/admin/space" <c:if test="${menu eq '2'}"> class="active" </c:if>>컨텐츠관리</a></li>
					<li class="bar">|</li>
					<li><a href="/admin/mail" <c:if test="${menu eq '3'}"> class="active" </c:if>>시스템관리</a></li>
					<li class="bar">|</li>
					<li><a href="/admin/patch" <c:if test="${menu eq '4'}"> class="active" </c:if>>정보관리</a></li>
					<li class="bar">|</li>
					<li><a href="/logout?spring-security-redirect=/admin/wikiadminlogin">로그아웃</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="sub">
			<div class="wrap-cont">
				<ul class="navi">
					<c:choose>
						<c:when test="${menu == 1}">
							<li><a href="/admin/user">회원 정보 관리</a></li>
							<li class="bar">|</li>
							<li><a href="/admin/group" class="active">그룹 관리</a></li>
						</c:when>
						<c:when test="${menu == 2}">
							<li><a href="/admin/space">공간 관리</a></li>
							<li class="bar">|</li>
							<li><a href="/admin/wiki">위키 관리</a></li>
							<li class="bar">|</li>
							<li><a href="/admin/keyword">키워드 관리</a></li>
							<li class="bar">|</li>
							<li><a href="/admin/board">게시판 관리</a></li>
						</c:when>
						<c:when test="${menu == 3}">
							<li><a href="/admin/mail">전송 메일 계정설정</a></li>
							<li class="bar">|</li>
							<li><a href="/admin/filter">접근 제어</a></li>
							<li class="bar">|</li>
							<li><a href="/admin/menu">메뉴 관리</a></li>
						</c:when>
						<c:when test="${menu == 4}">
							<li><a href="/admin/patch">GLiDER™ 패치 정보</a></li>
							<!-- -->
							<li class="bar">|</li>
							<li><a href="/admin/license">라이센스 정보</a></li>
							<li class="bar">|</li>
							<li><a href="/admin/extension">확장기능 관리</a></li>
							 
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</header>