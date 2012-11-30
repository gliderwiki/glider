<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/admin/include/adminHeader.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>::Enterprise OpenSource Wiki ? Glider </title>
<script type="text/javascript" charset="UTF-8" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript">
//<![CDATA[

//]]>
</script>
</head>

<body>


카테고리 <BR />

    <fieldset STYLE="width:100;">
        <legend>대분류 추가</legend>
		 <label for="대분류명"><input type="text" name="we_category_name" id="we_category_name" > <input type="button" name="saveCategoryName" id="saveCategoryName" value="저장" />
    </fieldset>

	<fieldset STYLE="width:100;">
        <legend>분류조회</legend>
                      대분류 : <select id="cateList" title="대분류선택" STYLE="width:200;">
			<option value="">선택하세요.</option>
			<c:forEach items="${categoryList }" var="categoryList" varStatus="stat">
			<option value="${categoryList.we_category_idx }">${categoryList.we_category_name }</option>
	        </c:forEach>
		</select>

		&nbsp; 중분류 : <select id="cateList" title="대분류선택" STYLE="width:200;">
			<option value="">선택하세요.</option>
			<c:forEach items="${categoryList }" var="categoryList" varStatus="stat">
			<option value="${categoryList.we_category_idx }">${categoryList.we_category_name }</option>
	        </c:forEach>
		</select>
    </fieldset>

<%@include file="/WEB-INF/views/admin/include/adminFooter.jsp"%>