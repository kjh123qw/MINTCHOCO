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
<style>
	html {
		background-color: #eee;
	}
	.ex-tag-wrap {
		width: 1200px;
		margin: 0 auto;
	}
	h1 {
		text-align: center;
	}
	h3 {
		text-align: center;
	}
	table {
		width: 100%;
	}
	.tag-table > tbody > tr > th:nth-of-type(1), .tag-table > tbody > tr > td:nth-of-type(1) {
		width: 50%;
		text-align: center;
	}
	.tag-table > tbody > tr > th:nth-of-type(2), .tag-table > tbody > tr > td:nth-of-type(2) {
		width: 50%;
	}
	.tag-table > tbody > tr > th, .tag-table > tbody > tr > td {
		padding: 5px;
	}
	.tag-explain > tbody > tr > th {
		width: 120px;
	}
</style>
<!-- //담당자 js, css -->
</head>
<body>
<jsp:include page="./_header.jsp"></jsp:include>
<!-- 담당자 내용 -->

<h1>커스텀 태그 예제입니다.</h1>
<p>
	
</p>
<hr>
<div class="ex-tag-wrap">
	<h3>checkbox</h3>
	<div class="ex-tag-box">
		<table border="1" class="tag-table">
			<tr>
				<th>스타일 예시 / 적용 태그</th>
				<th>클래스 명 설명</th>
			</tr>
			<tr>
				<td>
					<input type="checkbox" class="cst-chkbox-white-exname1">
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>cst-chkbox-</th>
							<td>커스텀 체크박스 지정</td>
						</tr>
						<tr>
							<th><i>white</i></th>
							<td>색상<b><i>[dark / white]</i></b></td>
						</tr>
						<tr>
							<th><i>exname1</i></th>
							<td>자신이 정한 클래스명</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;input type="checkbox" class="cst-chkbox-<i><b>white</b></i>-<i><b>exname1</b></i>"&gt;
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" class="cst-chkbox-dark-exname2">
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>cst-chkbox-</th>
							<td>커스텀 체크박스 지정</td>
						</tr>
						<tr>
							<th><i>dark</i></th>
							<td>색상<b><i>[dark / white]</i></b></td>
						</tr>
						<tr>
							<th><i>exname2</i></th>
							<td>자신이 정한 클래스명</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;input type="checkbox" class="cst-chkbox-<i><b>dark</b></i>-<i><b>exname2</b></i>"&gt;
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" class="cst-chkbox-dark-exname3">
					<span class="cst-chktext-150-exname3">누르면 체크박스가 체크 됨</span>
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>cst-chkbox-</th>
							<td>커스텀 체크박스 지정</td>
						</tr>
						<tr>
							<th><i>150</i></th>
							<td>텍스트의 가로 사이즈</td>
						</tr>
						<tr>
							<th><i>exname3</i></th>
							<td>자신이 정한 클래스명 체크 박스와 매핑 시켜줘야 함</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;input type="checkbox" class="cst-chkbox-<i><b>white</b></i>-<i><b>exname3</b></i>"&gt;<br>
					&lt;span class="cst-chktext-<i><b>150</b></i>-<i><b>exname3</b></i>"&gt;
				</td>
			</tr>
		</table>
	</div>
</div>

<!-- 담당자 내용 -->
<jsp:include page="./_footer.jsp"></jsp:include>
</body>
</html>