<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
<title>Base | jQuery</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>
  h3 { margin: 0; }
  div,span,p {
    width: 80px;
    height: 40px;
    float:left;
    padding: 10px;
    margin: 10px;
    background-color: #EEEEEE;
  }
  #test {
    width: auto; height: auto; background-color: transparent; 
  }
</style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	$(document).ready(function(){
		var elementCount = $("#test").find("*").css("border","3px solid red").length;
		
		$("#test").before("<h3>" + elementCount + " elements found</h3>");		
	});	 
</script>
</head>
<body>
	<h2 class="title">jQuery Selector find("*")</h2>
		
	<div id="test">
	  <div>DIV</div>
	  <span>SPAN</span>
	  <p>P <button>Button</button></p>
	</div>
	
	<pre style="margin-top: 200px;">
		$(document).ready(function(){
			var elementCount = $("#test").find("*").css("border","3px solid red").length;
			$("#test").before("태그h3" + elementCount + " elements found 태그h3");			
		});
	</pre>
	

	<ul style="margin-top: 100px;">	
		<li><a href="javascript:history.back();">[뒤로가기]</a></li>
	</ul>
</body>
</html>