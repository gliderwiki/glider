<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@page import="java.util.Calendar"%>

<%
	
	//Calender 클래스는 추상 클래스 이므로 객체생성이 되지 않는다.

	//따라서 getInstance() 메서드를 이용하여 하위클래스의 객체를 생성하여 리턴한다.

	//하위 클래스는 플랫폼의 나라 언어에 맞는 것을 자동으로 리턴해 줍니다. 

	//칼렌더 객체 생성

	Calendar cal = Calendar.getInstance();

	//오늘 날짜 구하기

	int nowYear = cal.get(Calendar.YEAR);

	int nowMonth = cal.get(Calendar.MONTH) + 1;

	//월은 0부터 시작하므로 1월 표시를 위해 1을 더해 줍니다.

	int nowDay = cal.get(Calendar.DAY_OF_MONTH);

	//클라이언트가 선택하여 넘어온 날짜

	String strYear = request.getParameter("year");

	String strMonth = request.getParameter("month");

	//표시할 달력의 년,월

	int year = nowYear;

	int month = nowMonth;

	if (strYear != null) {// 값이 없으면

		year = Integer.parseInt(strYear);//클라이언트가 선택한 값을 입력

	}

	if (strMonth != null) {// 값이 없으면

		month = Integer.parseInt(strMonth);//클라이언트가 선택한 값을 입력

	}

	//전월 이동을 구하기

	int preYear = year, preMonth = month - 1;

	if (preMonth < 1) {//1월 전월은 작년 12월 이니깐...

		preYear = year - 1;

		preMonth = 12;

	}

	//다음달 구하기

	int nextYear = year, nextMonth = month + 1;

	if (nextMonth > 12) {//12월 다음달은 내년 1월 이므로...

		nextYear = year + 1;

		nextMonth = 1;

	}

	//표시할 달력 세팅

	cal.set(year, month - 1, 1);//년,월,일

	int startDay = 1;//달의 첫 날

	int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

	//매년 해당월의 1일 구하기

	int week = cal.get(Calendar.DAY_OF_WEEK);
%>


<!DOCTYPE html>
<!--[if lt IE 8]> <html class="lt-ie8" lang="ko"> <![endif]-->
<!--[if gt IE 7]><!--> <html lang="ko"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<title>::Enterprise OpenSource Wiki – GLiDER™</title>
	<decorator:head />
	<!--[if IE]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	
	
	<STYLE type="text/css">
	body {
		font-size: 9pt;
	}
	
	td {
		font-size: 9pt;
	}
	
	a:link {
		text-decoration: none;
		color: red
	}
	
	a:active {
		text-decoration: none;
		color: red
	}
	
	a:visited {
		text-decoration: none;
		color: red
	}
	
	a:hover {
		text-decoration: none;
		color: red
	}
	</STYLE>
		
		
</head>


<!-- 달력 헤더 만들기 -->


<body>

	<br />&nbsp;
	<br />

	<table align="center" width="210" cellpadding="2" cellspacing="1">

		<tr>

			<td align="center">
			<a href="calendar.jsp?year=<%=preYear%>&month=<%=preMonth%>">◀</a> <b>&nbsp;<%=year%>년&nbsp;&nbsp;<%=month%>월</b>
			<a href="calendar.jsp?year=<%=nextYear%>&month=<%=nextMonth%>">▶</a>

			</td>

		</tr>

	</table>

	<!-- 달력표시 -->

	<table align="center" width="210" cellpadding="0" cellspacing="1"
		bgcolor="#cccccc">

		<tr>

			<td bgcolor="#e6e4e6" align="center"><font color="red">일</font>

			</td>

			<td bgcolor="#e6e4e6" align="center">월</td>

			<td bgcolor="#e6e4e6" align="center">화</td>

			<td bgcolor="#e6e4e6" align="center">수</td>

			<td bgcolor="#e6e4e6" align="center">목</td>

			<td bgcolor="#e6e4e6" align="center">금</td>

			<td bgcolor="#e6e4e6" align="center"><font color="blue">토</font>

			</td>

		</tr>

		<%
			//한주가 지나면 줄바꿈을 할 것이다.

			int newLine = 0;

			out.print("<tr height='20'>");

			for (int i = 1; i < week; i++) {

				out.print("<td bgcolor='#ffffff'>&nbsp;</td>");

				newLine++;

			}

			for (int i = startDay; i <= endDay; i++) {//1일 부터 말일까지 반복

				String fontColor = (newLine == 0) ? "red"
						: (newLine == 6) ? "blue" : "black";

				String bgColor = (nowYear == year) && (nowMonth == month)
						&& (nowDay == i) ? "#e6e6e6" : "#fff";//오늘날짜음영

				out.print("<td align='center' bgcolor='" + bgColor
						+ "'><font color='" + fontColor + "'>" + i
						+ "</font></td>");

				newLine++;

				if (newLine == 7 && i != endDay) {//7일째거나 말일이면 달력 줄바꿈이 일어난다.

					out.print("</tr><tr height='20'>");

					newLine = 0;

				}

			}//3항 연산자로 for문으로 요일별 색상을 정한다.

			while (newLine > 0 && newLine < 7) {//마지막날 이후 달력 채우기

				out.print("<td bgcolor='#ffffff'>&nbsp;</td>");

				newLine++;

			}

			out.print("</tr>");
		%>

	</table>

</body>

</html>