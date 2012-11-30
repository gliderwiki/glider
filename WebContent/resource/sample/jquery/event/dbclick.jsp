<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="../css/import.css" />
<style>

  div { background:blue;
        color:white;
        height:100px;
        width:150px;
 }
  div.dbl { background:yellow;color:black; }
  
  </style>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/jquery-1.6.4.js"></script>
<script type="text/javascript" charset="UTF-8" src="../scripts/jquery/base.js"></script>
<script type="text/javascript" charset="UTF-8">
	$(document).ready(function(){
		var $divdbl = $("div:first");
	    $divdbl.dblclick(function () { 
	      $divdbl.toggleClass('dbl'); 
	    });
	}); 
</script>
</head>
<body>
	
	<h2 class="title"> 더블클릭 </h2>
	
	<div></div><span>Double click the block</span>
	
	<pre>
	 var $divdbl = $("div:first");
	    $divdbl.dblclick(function () { 
	      $divdbl.toggleClass('dbl'); 
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