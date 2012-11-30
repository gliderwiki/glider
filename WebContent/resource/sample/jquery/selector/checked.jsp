<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
	div { color:red; }
</style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	 $(document).ready(function(){
		 $(":checkbox").click(countChecked);	 
	 });
	 
	function countChecked() {
	  var n = $("input:checked").length;
	  $("div").text(n + (n <= 1 ? " is" : " are") + " checked!");
	}
</script>
</head>
<body>
	
	<h2 class="title"> 체크박스를 체크해주세요. </h2>
	
	
	<form>
	  <p>
	    <input type="checkbox" name="newsletter" value="Hourly" />
	
	    <input type="checkbox" name="newsletter" value="Daily" />
	    <input type="checkbox" name="newsletter" value="Weekly" />
	
	    <input type="checkbox" name="newsletter" value="Monthly" />
	    <input type="checkbox" name="newsletter" value="Yearly" />
	  </p>
	</form>
	<div></div>

	<pre>
	 	
	 	$(document).ready(function(){
		 $(":checkbox").click(countChecked);	 
		 });
		 
		function countChecked() {
		  var n = $("input:checked").length;
		  $("div").text(n + (n <= 1 ? " is" : " are") + " checked!");
		}
	 	
	</pre>


	
	<ul style="margin-top: 50px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>