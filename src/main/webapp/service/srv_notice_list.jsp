<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 공지사항 목록 ::
	담당자 : 박찬영, 김정호
*/
%>

<% // 양식파일이다. 이부분을 삭제하고 사용하도록. %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>NOTICE</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->
<script src="${ contextPath }/js/service/notice.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/service/notice.css">
<!-- //담당자 js, css -->
</head>
<body>
<jsp:include page="../_header.jsp"></jsp:include>
<!-- 담당자 내용 -->
<div id="noticeWrap">
	<h1>noitce</h1>
	<table class="notice-table">
		<tr>
			<th>no</th>
			<th>title</th>
			<th>date</th>
			<th>count</th>
		</tr>
		
		<c:forEach var="noticeVO" items="${ noticeList }">
			<tr>
				<td>${ noticeVO.noticeNumber }</td>
				<td><a href="${ contextPath }/service/detail.do?noticeNumber=${ noticeVO.noticeNumber }">${ noticeVO.noticeTitle }</a></td>
				<td>${ noticeVO.noticeRegdate }</td>
				<td>${ noticeVO.noticeCnt }</td>
			</tr>
		</c:forEach>
		
	</table>
	<div class="notice-buttons">
		<c:if test="${ sessionScope.memberInfo.number == 1 }">
		<div class="cst-btn notice-write-btn">
			<input type="button" value="write" class="st1-90-40" onclick="location.href='${ contextPath }/service/insert.do'">
		</div>
		</c:if>
		<form action="${ contextPath }/service/notice.do">
			<div class="cst-btn notice-search-btn">
				<input type="submit" value="search" class="st1-70-40">
			</div>
			<div class="cst-text-st1 notice-search-form">
				<input type="text" class="text-140" name="noticeSearch">
			</div>
		</form>
	</div>
</div>

<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>