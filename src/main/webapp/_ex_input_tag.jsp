<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 커스텀 태그 예제 ::
	담당자 : 김정호
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FILE_NAME.JSP</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->

<!-- //담당자 js, css -->
</head>
<body>
<jsp:include page="../_header.jsp"></jsp:include>
<!-- 담당자 내용 -->

<h1>커스텀 태그 예제입니다.</h1>
<p>
	
</p>
<hr>
<div class="ex-tag-wrap">
	<h3>INPUT tag</h3>
	<div class="ex-tag-box">
		<table>
			<tr>
				<th>타입</th>
				<th>클래스 명</th>
				<th>클래스 명 설명</th>
				<th>예시</th>
			</tr>
			<tr>
				<td rowspan="2">checkbox</td>
				<td>cst-chkbox-<b><i>name</i></b>-<b><i>color</i></b></td>
				<td>
					<table>
						<tr>
							<th>cst-chkbox-</th>
							<td>커스텀 체크박스 지정</td>
						</tr>
						<tr>
							<th><b><i>name</i></b></th>
							<td>자신이 정한 클래스명</td>
						</tr>
						<tr>
							<th><b><i>color</i></b></th>
							<td>색상선택<br><b><i>dark, white</i></b></td>
						</tr>
					</table>`
				</td>
			</tr>
			<tr>
				<td>cst-chkbox-<b><i>name</i></b>-<b><i>text</i></b></td>
				<td>
					<table>
						<tr>
							<th>cst-chkbox-</th>
							<td>커스텀 체크박스 지정</td>
						</tr>
						<tr>
							<th><b><i>color</i></b></th>
							<td>색상선택<br><b><i>dark, white</i></b></td>
						</tr>
						<tr>
							<th><b><i>name</i></b></th>
							<td>자신이 정한 클래스명</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>

<!-- 담당자 내용 -->
<jsp:include page="../_footer.jsp"></jsp:include>
</body>
</html>