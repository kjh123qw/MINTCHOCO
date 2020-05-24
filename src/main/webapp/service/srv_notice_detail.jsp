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
	<h1>notice</h1>
	<table class="notice-form">
		<tr>
			<th>title</th>
			<td colspan="3">
				<div class="cst-text-st1 notice-form-title">
					${ noticeVO.noticeTitle }
				</div>
			</td>
		</tr>
		<tr>
			<th>date</th>
			<td>
				<div class="cst-text-st1 notice-form-title">
					${ noticeVO.noticeRegdate }
				</div>
			</td>
			<th>count</th>
			<td>
				<div class="cst-text-st1 notice-form-title">
					${ noticeVO.noticeCnt }
				</div>
			</td>
		</tr>
		<tr>
			<th>content</th>
			<td colspan="3">
				<textarea readonly>${ noticeVO.noticeContent }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<div class="cst-btn notice-form-btn">
				<c:if test="${ sessionScope.memberInfo.number == 1 }">
					<input type="button" value="delete" class="st2-70-40" onclick="if(confirm('글을 삭제하시겠습니까?')) location.href='${ contextPath }/service/delete.do?noticeNumber=${ noticeVO.noticeNumber }';">
					<input type="button" value="modify" class="st3-70-40" onclick="location.href='${ contextPath }/service/modify.do?noticeNumber=${ noticeVO.noticeNumber }';">
				</c:if>
					<input type="button" value="list" class="st1-120-40" onclick="location.href='${ contextPath }/service/notice.do';">
				</div>
			</td>
		</tr>
	</table>
</div>


<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>