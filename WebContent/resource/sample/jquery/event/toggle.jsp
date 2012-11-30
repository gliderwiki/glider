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
		 	$("#applebar li").toggle(
		      function () {
		        $(this).css({"list-style-type":"disc", "color":"blue"});
		      },
		      function () {
		        $(this).css({"list-style-type":"disc", "color":"red"});
		      },
		      function () {
		        $(this).css({"list-style-type":"", "color":""});
		      }
		    );
	 });
</script>
</head>
<body>
	
	<h2 class="title"> toggle </h2>
	
	 <ul id="applebar">
	    <li>Apple</li>
	    <li>Mac</li>
	    <li>iPod</li>
		<li>iPhone</li>
	    <li>iPad</li>
	    <li>iCar</li>
	  </ul>
	<pre>
	 $("#applebar li").toggle(
		      function () {
		        $(this).css({"list-style-type":"disc", "color":"blue"});
		      },
		      function () {
		        $(this).css({"list-style-type":"disc", "color":"red"});
		      },
		      function () {
		        $(this).css({"list-style-type":"", "color":""});
		      }
		    );
	</pre>

	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>