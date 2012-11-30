<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
	td { text-align:center; }
</style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
		 $("td:empty").text("Was empty!").css('background', 'rgb(255,220,200)');
	 });
</script>
</head>
<body>
	
	<h2 class="title"> empty Selector </h2>
	
	<table border="1">
	    <tr>
	    	<td>TD #0</td>
	    	<td></td>
	    </tr>
	    <tr>
	    	<td>TD #2</td>
	    	<td></td>
	    </tr>	
	    <tr>
	    	<td></td>
	    	<td>TD#5</td>
	    </tr>	    	
	</table>
 
	<pre>
	 	$(document).ready(function(){
		 $("td:empty").text("Was empty!").css('background', 'rgb(255,220,200)');
	 	});
	</pre>

	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>