<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="true" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%
Logger logger = LoggerFactory.getLogger("counter.jsp");

int intNowDay = 0;
int intTodayDay = 0;

int intCurrentCount = 0;
int intTotalCount = 0;
int intPastCount = 0;

File fileCounter = null;
String strintTotalCountFilePath = application.getRealPath("/resource/data/total_count.dat");

Calendar now = Calendar.getInstance();
intNowDay = now.get(Calendar.DATE);

try {
	fileCounter = new File(strintTotalCountFilePath); // 파일 객체 생성
	if (fileCounter.exists()) {
		BufferedReader reader = new BufferedReader(new FileReader(fileCounter));
		
		// 파일 스트림 리더 
		// 첫번째 항목을 읽는다.
		String strCurrentCount = reader.readLine();
		
		//읽어온 파일값이 없을때
		if (strCurrentCount == null) {
			
			PrintWriter writer = new PrintWriter(new FileOutputStream(fileCounter));
			writer.println(0); // intTodayCount
			writer.println(0); // totalcount
			writer.println(0); // 카운트 날짜 
			writer.println(0); // 어제 count
			writer.println();
			writer.close();
		} else {
			// 파일에서 값을 읽어 와 항목에 저장한다.
			intCurrentCount = Integer.parseInt(strCurrentCount); // 읽어온 값을 int형으로 변환
			intTotalCount = Integer.parseInt(reader.readLine());
			intTodayDay = Integer.parseInt(reader.readLine());
			intPastCount = Integer.parseInt(reader.readLine());
			
		}
		
		// 세션이 없을 경우, 즉 새로운 접속일 경우 카운터 값을 증가하고 날짜 비교 해야 함
		if (!"true".equals((String) session.getAttribute("count"))) {
			intCurrentCount = intCurrentCount + 1; 	// 현재 카운터 증가
			intTotalCount = intTotalCount + 1; 		// 토탈카운터 증가
			intTodayDay = intTodayDay;				// 날짜 세팅 
			intPastCount = intPastCount; 			// 어제 카운터는 그대로 
			
			// 날짜 비교, 만약 읽어온 날짜와 시스템 날짜가 틀릴 경우,
			// 즉 dat 파일에 기록되어있는 파일은 실제로 날짜가 바뀌는 순간에 어제 날짜가 되므로 
			// 이때 오늘 카운터를 어제 카운터로 끌고 들어감 
			if (intNowDay != intTodayDay) {
				// intTotalCount, intCurrentCount, 연월일
				PrintWriter writer = new PrintWriter(new FileOutputStream(fileCounter)); // 파일 아웃풋 스트림 생성
				intPastCount = intCurrentCount; // 오늘 카운터를 어제 카운터로 지정
				intCurrentCount = 1; // 오늘 카운터 초기화
				intTodayDay = intNowDay;
				writer.println(intCurrentCount); // intTodayCount's Count
				writer.println(intTotalCount); 	 // total count
				writer.println(intTodayDay);   // intTodayCount
				writer.println(intPastCount);    // yesterday count
				
				writer.close();
			}
			
			PrintWriter writer = new PrintWriter(new FileOutputStream(fileCounter));
			
			writer.println(intCurrentCount); // intTodayCount
			writer.println(intTotalCount); // total count
			writer.println(intTodayDay); // count day
			writer.println(intPastCount); // count day
			writer.close();
			
			out.println(intTotalCount+" / "+intCurrentCount);
		} else {
			// out.println("총 "+intTotalCount+"&nbsp;오늘 "+intCurrentCount+"&nbsp;어제 "+intPastCount);
			out.println(intTotalCount+" / "+intCurrentCount); 
		}
		session.setAttribute("count", "true");
	}
	
} catch (IOException e) {
	System.out.println(e);
}
%>

