<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>span {display:none;}</style>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
		 $("p").focusin(function() {
	         $(this).find("span").css('display','inline').fadeOut(1000);
	    });
	 });
</script>
</head>
<body>
	
	<h2 class="title"> focusein </h2>
	
	<p><input type="text" /> <span>focusin fire</span></p>
	<p><input type="password" /> <span>focusin fire</span></p>
	
	<pre>
	 $("p").focusin(function() {
         $(this).find("span").css('display','inline').fadeOut(2000);
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