<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Syntax</title>
</head>
<body>
	<h1>Syntax</h1>


	<!-- Include required JS files -->
	<script type="text/javascript" src="/resource/glider/code/js/shCore.js"></script>
	<!--    At least one brush, here we choose JS. You need to include a brush for every     language you want to highlight-->
	<script type="text/javascript" src="/resource/glider/code/js/shBrushJScript.js"></script>
	<!-- Include *at least* the core style and default theme -->
	<link href="/resource/glider/code/css/shCore.css" rel="stylesheet" type="text/css" />
	<link href="/resource/glider/code/css/shThemeDefault.css" rel="stylesheet" type="text/css" />
	<!-- You also need to add some content to highlight, but that is covered elsewhere. -->
	<pre class="brush: js">
	function foo(){ 
	 if (counter = 10)       
	    return;    
	}
	</pre>
	<!-- Finally, to actually run the highlighter, you need to include this JS on your page -->
	<script type="text/javascript">
		SyntaxHighlighter.all();
	</script>
	
	<script type="syntaxhighlighter" class="brush: js">
	<![CDATA[ 
    /**   
     * SyntaxHighlighter   
     */  
	function foo()  {     
	    if (counter <= 10)       
	        return;  // it works! 
	}
    ]]></script>
	
</body>
</html>