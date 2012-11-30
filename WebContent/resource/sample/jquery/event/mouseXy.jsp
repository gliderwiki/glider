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
		 $("#box").mousemove(function(event){
			 $("#idX").text(event.pageX);
			 $("#idY").text(event.pageY);
		 });
	 });
</script>
</head>
<body>
	
	<h2 class="title"> 마우스좌표 </h2>
	
	
	<ul>
		<li>X좌표 : <span id="idX"></span></li>
		<li>Y좌표 : <span id="idY"></span></li>
	</ul>

	<div id="box" style="width:400px;height:400px;border:solid 2px red;">
	
	
	</div>
	<pre>
	 	 $(document).ready(function(){
			 $("#box").mousemove(function(event){
				 $("#idX").text(event.pageX);
				 $("#idY").text(event.pageY);
			 });
	 	});
	</pre>
	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>