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
		 $("#testSel").change(function(){
			 alert($("#testSel  option:selected").val());
		 });
	 });
</script>
</head>
<body>
	
	<h2 class="title"> change </h2>
	
	<div>
		<select id="testSel">
			<option value="김태희">김태희</option>
			<option value="이민정">이민정</option>
			<option value="김사랑">김사랑</option>
			<option value="전지현">전지현</option>
			<option value="지나">지나</option>
			<option value="이효리">이효리</option>
			<option value="한채영">한채영</option>
		</select>
	</div>
	<pre>
	  $(document).ready(function(){
		 $("#testSel").change(function(){
			 alert($("#testSel  option:selected").val());
		 });
	 });
	</pre>

	<p class="txt">
	 코멘트
	</p>
	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>