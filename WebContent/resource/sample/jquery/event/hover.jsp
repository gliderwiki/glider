<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
  ul { margin-left:20px; color:blue; }
  li { cursor:default; }
  span { color:red; }
</style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
	 		$("#testHover li").hover(
			  function () {
			    $(this).append($("<span> ***</span>"));
			  }, 
			  function () {
			    $(this).find("span:last").remove();
			  }
			);
		//li with fade class
		$("#testHover li.fade").hover(function(){$(this).fadeOut(100);$(this).fadeIn(500);});
	 });
</script>
</head>
<body>
	
	<h2 class="title"> hover </h2>
		
	<ul id ="testHover">
	    <li>소녀시대</li>
	    <li>카라</li>
	    <li class='fade'>원더걸스</li>
	    
	    <li class='fade'>씨스타</li>
	  </ul>
	<pre>
	 	$("#testHover li").hover(
			  function () {
			    $(this).append($("<span> ***</span>"));
			  }, 
			  function () {
			    $(this).find("span:last").remove();
			  }
			);
		//li with fade class
		$("#testHover li.fade").hover(function(){$(this).fadeOut(100);$(this).fadeIn(500);});
	</pre>


	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
	
	
</body>
</html>