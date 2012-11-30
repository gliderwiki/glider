<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
  div,span,p {
    width: 126px;
    height: 60px;
    float:left;
    padding: 3px;
    margin: 2px;
    background-color: #EEEEEE;
    font-size:14px;
  }
  </style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	$(document).ready(function(){
		$("div,span,p.myClass").css("border","3px solid red");
	}); 
</script>
</head>
<body>
	
	<h2 class="title"> Multiple Selector </h2>
	
	<div>div</div>

  	<p class="myClass">p class="myClass"</p>
  	<p class="notMyClass">p class="notMyClass"</p>
  	<span>span</span>
  
	<pre style="margin-top: 120px;">
	  $(document).ready(function(){
		$("div,span,p.myClass").css("border","3px solid red");
	  });
	</pre>

	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>