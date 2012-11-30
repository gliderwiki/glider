<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
		 $("div:contains('안')").css("text-decoration", "underline");
	 });
</script>
</head>
<body>
	
	<h2 class="title"> contains() </h2>
	
	<div>안드로메다</div>
	<div>안드로이드</div>
	<div>김태희</div>
	<div>제이쿼리</div>
	<div>안해</div>
	
	<pre>
	 	$(document).ready(function(){
		 $("div:contains('안')").css("text-decoration", "underline");
	 	});
	</pre>

	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>