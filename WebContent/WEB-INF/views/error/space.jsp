<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>::Enterprise OpenSource Wiki – GLiDER™ </title>
<script type="text/javascript">
//<![CDATA[

//]]>
</script>
</head>

<body>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">공간 에러공지</h2>
			<div class="body-cont">
				<div class="box-error">에러가 발생하였습니다</div>
				<div class="info-error">
					<h3 class="tit-sub">에러정보</h3>
					<div class="txt-error">
						${message}
					</div>
				</div>
			</div>
		<div class="foot-cont">
			<a href="/" class="btn">홈으로</a>
			<a href="#" onclick="history.go(-1);return false;" class="btn">이전</a>
		</div>
	</div>
</section>
</body>
</html> 