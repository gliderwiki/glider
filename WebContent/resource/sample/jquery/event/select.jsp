<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style type="text/css">
	p { color:blue; }
  div { color:red; }
</style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
		 $(":input").select( function () { 
		      $("div").text("무엇인가를 선택 했습니다.").show().fadeOut(2000); 
		    });
	 });
</script>
</head>
<body>
	
	<h2 class="title"> select </h2>
	<p>text박스를 클릭 후 텍스트를 드래그 해주세용!</p>
	
	<input type="text" value="drag me" />
	<input type="text" value="im hungry" />
	
	<div></div>
	
	<pre>
	 $(":input").select( function () { 
      $("div").text("Something was selected").show().fadeOut(1000); 
    });
	</pre>

	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>