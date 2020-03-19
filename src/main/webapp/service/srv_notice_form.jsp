<%@page import="com.kosmo.mintchoco.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 공지사항 작성 폼 ::
	담당자 : 박찬영, 김정호
*/
%>

<% // 양식파일이다. 이부분을 삭제하고 사용하도록. %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<%
MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");
if(memberInfo.getNumber() != 1) { %>
	<c:redirect url="notice.do" />
<% }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>공지사항 입력 폼</title>
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
	<h1>공지사항 입력</h1>
	<form method="post" action="${ contextPath }/service/insert.do">
	<table class="notice-form">
		<tr>
			<th>제목</th>
			<td>
				<div class="cst-text-st1 notice-form-title">
					<input type="text" class="text-150" name="noticeTitle">
				</div>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="noticeContent"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="cst-btn notice-form-btn">
					<input type="button" value="취소" class="st2-120-40"  onclick="location.href='${ contextPath }/service/notice.do'">
					<input type="submit" value="등록" class="st1-120-40">
				</div>
			</td>
		</tr>
	</table>
	</form>
</div>

<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>