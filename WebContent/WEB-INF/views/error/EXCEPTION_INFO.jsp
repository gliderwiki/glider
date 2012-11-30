<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>    
<%@ page import="javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
<title>::Enterprise OpenSource Wiki – GLiDER™ </title>
</head>

<body>
	<div id="main">
		<h2>Exception info</h2>

		<p />
		<%
			try {
				// The Servlet spec guarantees this attribute will be available
				Throwable exceptions = (Throwable) request.getAttribute("javax.servlet.error.exception");
	
				if (exceptions != null) {
					if (exceptions instanceof ServletException) {			
						ServletException servletErr = (ServletException) exceptions;
						Throwable rootCause = servletErr.getRootCause();
						if (rootCause == null) {
							rootCause = servletErr;
						}
						out.println("Message : Root cause is - " + rootCause.getMessage());
						rootCause.printStackTrace(new java.io.PrintWriter(out));
					} else {
	
						System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
						System.out.println("✚ getMessage : " + exceptions.getMessage());
						System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
						System.out.println("✚ getStackTrace : " + exceptions.getStackTrace());				
						System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
						out.println("Message :  Root cause is - <B>" + exceptions.getCause() + "</B>");
						out.println("<BR><BR>");
						out.println("✚ Exception Occured!! Check the server trace log for more information.");
					}
				} else {
					out.println("No error information available");
				}
	
			} catch (Exception ex) {
				ex.printStackTrace(new java.io.PrintWriter(out));
			}
		%>

		<p />
		<br />
		<table class="footer">
			<tr>
				<td><a href="/">Home</a>
				</td>
				<td align="right">Copyright by GLider</td>
			</tr>
		</table>
	</div>
</body>
</html>

