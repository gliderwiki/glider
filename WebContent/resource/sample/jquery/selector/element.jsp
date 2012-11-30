<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>element selector</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
  div,span {
    width: 60px;
    height: 60px;
    float:left;
    padding: 10px;
    margin: 10px;
    background-color: #EEEEEE;
  }
</style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
		 $("div").css("border","9px solid red");
	 });
</script>
</head>
<body>
	<h2 class="title"> element selector </h2>
	
	<div>DIV1</div>
	
	<div>DIV2</div>
	<span>SPAN</span>
	
	
	<pre style="margin-top: 150px;">
	 
	 $(document).ready(function(){
		 $("div").css("border","9px solid red");
	 });
	 
	</pre>

	
	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>