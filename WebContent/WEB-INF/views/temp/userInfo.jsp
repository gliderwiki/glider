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
<title>We are all about Collaboration – Glider Wiki Opensource</title>
<script type="text/javascript"> 
//<![CDATA[

//]]>
</script>
</head>

<body>
넘어온 ID 값 : ${id} <BR />
사용자 정보 : ${temp.id} | ${temp.name }<BR />
현재 URL : ${url } <BR />
이전 URL : ${referUrl } <BR /><BR />

<a href="${referUrl }">[이전페이지]</a>
</body>
</html>