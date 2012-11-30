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
<title>::Enterprise OpenSource Wiki – Glider </title>
<script type="text/javascript" charset="UTF-8" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript"> 
//<![CDATA[
	$(document).ready(function() {
		if("${result}" == 1) {
			alert('회원인증이 완료 되었습니다.다시 로그인 후 이용하세요.');
			$(location).attr('href',"/index");
		} else {
			alert('회원 인증시 문제가 발생하였습니다.\n메일 인증 링크를 통해   다시 한번 시도 하시기 바랍니다.');
			$(location).attr('href',"/index");
		}
	});
//]]>
</script>
</head>
<body>
</body>
</html>