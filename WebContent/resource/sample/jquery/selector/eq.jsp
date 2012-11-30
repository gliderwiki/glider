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
		$("td:eq(2)").css("color", "red");
		
		
		// applies yellow background color to a single <li>
		$("ul.nav li:eq(1)").css( "backgroundColor", "#ff0" );

		// applies italics to text of the second <li> within each <ul class="nav">
		$("ul.nav").each(function(index) {
		  $(this).find("li:eq(1)").css( "fontStyle", "italic" );
		});

		// applies red text color to descendants of <ul class="nav">
		// for each <li> that is the second child of its parent
		$("ul.nav li:nth-child(2)").css( "color", "red" );	
	});	 
</script>
</head>
<body>
	
	<h2 class="title"> eq Selector </h2>
	
	<table border="1">
	  <tr><td>TD #0</td><td>TD #1</td><td>TD #2</td></tr>
	  <tr><td>TD #3</td><td>TD #4</td><td>TD #5</td></tr>
	  <tr><td>TD #6</td><td>TD #7</td><td>TD #8</td></tr>
	</table>
	<p style="padding-top: 20px;" />
	
	<ul class="nav">
	   <li>List 1, item 1</li>
	   <li>List 1, item 2</li>
	   <li>List 1, item 3</li>
	</ul>
	<ul class="nav">
	  <li>List 2, item 1</li>
	  <li>List 2, item 2</li>
	  <li>List 2, item 3</li>
	</ul>
	
	<p style="padding-top: 20px;" />
	
	<pre>
	 	$("td:eq(2)").css("color", "red");
		
		
		$("ul.nav li:eq(1)").css( "backgroundColor", "#ff0" );

		$("ul.nav").each(function(index) {
		  $(this).find("li:eq(1)").css( "fontStyle", "italic" );
		});

		$("ul.nav li:nth-child(2)").css( "color", "red" );
	</pre>

	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>