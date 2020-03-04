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
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">
<!-- 담당자 js, css -->
<style>
	html { background-color: #eee; }
	.ex-tag-wrap { width: 1200px; margin: 0 auto; }
	h1, h2 { text-align: center; margin: 10px 0; }
	table {
		width: 100%; 
		border-spacing: 0;
	    -webkit-border-horizontal-spacing: 0;
	    -webkit-border-vertical-spacing: 0;
    }
	.tag-table > tbody > tr:nth-of-type(1) > th:nth-of-type(1) {
		width: 6%;
	}
	.tag-table > tbody > tr:nth-of-type(1) > th:nth-of-type(2) {
		width: 52%;
	}
	.tag-table > tbody > tr:nth-of-type(1) > th:nth-of-type(3) {
		width: 42%;
	}
	.tag-table > tbody > tr > td:nth-of-type(1) {
		padding: 15px;
	}
	.tag-table > tbody > tr > th, .tag-table > tbody > tr > td {
		padding: 5px;
	}
	.tag-explain > tbody > tr > th {
		width: 120px;
	}
	.tag-explain > tbody > tr > th, .tag-explain > tbody > tr > td {
		padding: 3px 0;
	}
	hr { margin: 15px; }
	ul {
		 width: 960px;
		 margin: 0 auto;
		 padding: 20px 130px;
		 border: 1px dotted #444;
	}
</style>
<!-- //담당자 js, css -->
</head>
<body>
<jsp:include page="./_header.jsp"></jsp:include>
<!-- 담당자 내용 -->

<h1>커스텀 태그 입니다.</h1>
<ul>
	<li><b>이하 3가지가 필요합니다.</b></li>
	<li>&nbsp;&nbsp; - &lt;script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"&gt;&lt;/script&gt;</li>
	<li>&nbsp;&nbsp; - &lt;script src="./js/public.js"&gt;&lt;/script&gt;</li>
	<li>&nbsp;&nbsp; - &lt;link rel="stylesheet" href="./css/public.css"&gt;</li>
</ul>
<hr>
<div class="ex-tag-wrap">
	<div class="ex-tag-box">
		<h2>1. checkbox</h2>
		<table border="1" class="tag-table">
			<tr>
				<th>번호</th>
				<th>스타일 예시 / 적용 태그</th>
				<th>클래스 명 설명</th>
			</tr>
			<tr>
				<th rowspan="2">style1</th>
				<td style="padding: 20px; background-color: #fff;">
					<div class="cst-chkbox-st1">
						<input type="checkbox">
						<span class="text-250">세로로 쌓이는 스타일 입니다.</span>
						<input type="checkbox">
						<span class="text-60">짧은1!</span>
						<input type="checkbox">
						<span class="text-60">짧은2!</span>
					</div>
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>스타일 설명</th>
							<td>세로로 쌓이는 스타일</td>
						</tr>
						<tr>
							<th>cst-chkbox</th>
							<td>커스텀 체크박스 지정</td>
						</tr>
						<tr>
							<th>st1</th>
							<td>스타일 1번<b><i> [1|2] </i></b></td>
						</tr>
						<tr>
							<th>text-250</th>
							<td>텍스트 박스의 가로 사이즈</td>
						</tr>
						<tr>
							<td colspan="2"><b>:: span 태그는 대상 input 바로 밑에 존재해야함</b></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;div class="<b>cst-chkbox-st1</b>"&gt;<br>
						&nbsp;&nbsp;&lt;input type="checkbox"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-250</b>"&gt;세로로 쌓이는 스타일 입니다.&lt;/span&gt;<br>
						&nbsp;&nbsp;&lt;input type="checkbox"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-60</b>"&gt;짧은1!&lt;/span&gt;<br>
						&nbsp;&nbsp;&lt;input type="checkbox"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-60</b>"&gt;짧은2!&lt;/span&gt;<br>
					&lt;/div&gt;
				</td>
			</tr>
			<tr>
				<th rowspan="2">style2</th>
				<td style="padding: 20px; background-color: #fff;">
					<div class="cst-chkbox-st2">
						<input type="checkbox">
						<span class="text-60">가로로</span>
						<input type="checkbox">
						<span class="text-60">쌓이는</span>
						<input type="checkbox">
						<span class="text-60">스타일!</span>
					</div>
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>스타일 설명</th>
							<td>세로로 쌓이는 스타일</td>
						</tr>
						<tr>
							<th>cst-chkbox</th>
							<td>커스텀 체크박스 지정</td>
						</tr>
						<tr>
							<th>st2</th>
							<td>스타일 2번<b><i> [1|2] </i></b></td>
						</tr>
						<tr>
							<th>text-60</th>
							<td>텍스트 박스의 가로 사이즈</td>
						</tr>
						<tr>
							<td colspan="2"><b>:: span 태그는 대상 input 바로 밑에 존재해야함</b></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;div class="<b>cst-chkbox-st2</b>"&gt;<br>
						&nbsp;&nbsp;&lt;input type="checkbox"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-60</b>"&gt;가로로&lt;/span&gt;<br>
						&nbsp;&nbsp;&lt;input type="checkbox"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-60</b>"&gt;쌓이는&lt;/span&gt;<br>
						&nbsp;&nbsp;&lt;input type="checkbox"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-60</b>"&gt;스타일!&lt;/span&gt;<br>
					&lt;/div&gt;
				</td>
			</tr>
		</table>
		<hr>
		<h2>2. radio</h2>
		<table border="1" class="tag-table">
			<tr>
				<th>번호</th>
				<th>스타일 예시 / 적용 태그</th>
				<th>클래스 명 설명</th>
			</tr>
			<tr>
				<th rowspan="2">style1</th>
				<td style="padding: 20px; background-color: #fff;">
					<div class="cst-radio-st1">
						<input type="radio" name="exRadio1">
						<span class="text-100">항목1</span>
						<input type="radio" name="exRadio1">
						<span class="text-100">항목2</span>
						<input type="radio" name="exRadio1">
						<span class="text-100">항목3</span>
					</div>
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>스타일 설명</th>
							<td>기본 스타일 라디오 버튼</td>
						</tr>
						<tr>
							<th>cst-radio</th>
							<td>커스텀 라디오 버튼 지정</td>
						</tr>
						<tr>
							<th>st1</th>
							<td>스타일 1번<b><i> [1] </i></b></td>
						</tr>
						<tr>
							<th>text-100</th>
							<td>텍스트 박스의 가로 사이즈</td>
						</tr>
						<tr>
							<td colspan="2"><b>:: span 태그는 대상 input 바로 밑에 존재해야함</b></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;div class="<b>cst-radio-st1</b>"&gt;<br>
						&nbsp;&nbsp;&lt;input type="radio" name="exRadio1"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-100</b>"&gt;항목1&lt;/span&gt;<br>
						&nbsp;&nbsp;&lt;input type="radio" name="exRadio1"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-100</b>"&gt;항목2&lt;/span&gt;<br>
						&nbsp;&nbsp;&lt;input type="radio" name="exRadio1"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>text-100</b>"&gt;항목3&lt;/span&gt;<br>
					&lt;/div&gt;
				</td>
			</tr>
		</table>
		<hr>
		<h2>3. text and password box</h2>
		<table border="1" class="tag-table">
			<tr>
				<th>번호</th>
				<th>스타일 예시 / 적용 태그</th>
				<th>클래스 명 설명</th>
			</tr>
			<tr>
				<th rowspan="2">style1</th>
				<td style="padding: 20px; background-color: #fff;">
					<div class="cst-text-st1">
						<input type="text" class="text-200">
						<span class="label-80">아이디</span>
						<input type="password" class="text-200">
						<span class="label-80">비밀번호</span>
					</div>
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>스타일 설명</th>
							<td>기본 스타일 텍스트 박스</td>
						</tr>
						<tr>
							<th>cst-text</th>
							<td>커스텀 텍스트 박스 지정</td>
						</tr>
						<tr>
							<th>st1</th>
							<td>스타일 1번<b><i> [1] </i></b></td>
						</tr>
						<tr>
							<th>text-200</th>
							<td>텍스트 박스의 가로 사이즈</td>
						</tr>
						<tr>
							<th>label-80</th>
							<td>레이블 가로 사이즈</td>
						</tr>
						<tr>
							<td colspan="2"><b>:: span 태그는 대상 input 바로 밑에 존재해야함</b></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;div class="<b>cst-text-st1</b>"&gt;<br>
						&nbsp;&nbsp;&lt;input type="text" class="<b>text-200</b>"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>label-80</b>"&gt;아이디&lt;/span&gt;<br>
						&nbsp;&nbsp;&lt;input type="password" class="<b>text-200</b>"&gt;<br>
						&nbsp;&nbsp;&lt;span class="<b>label-80</b>"&gt;비밀번호&lt;/span&gt;<br>
					&lt;/div&gt;
				</td>
			</tr>
		</table>
		<hr>
		<h2>4. submit or button</h2>
		<table border="1" class="tag-table">
			<tr>
				<th>번호</th>
				<th>스타일 예시 / 적용 태그</th>
				<th>클래스 명 설명</th>
			</tr>
			<tr>
				<th rowspan="2">style1</th>
				<td style="padding: 20px; background-color: #fff;">
					<div class="cst-btn">
						<input type="submit" value="확인" class="st1-150-50">
						<input type="button" value="다시" class="st2-150-50">
						<input type="button" value="취소" class="st3-150-50">
					</div>
				</td>
				<td style="text-align: left;" rowspan="2">
					<table class="tag-explain">
						<tr>
							<th>스타일 설명</th>
							<td>기본 스타일 버튼</td>
						</tr>
						<tr>
							<th>cst-btn</th>
							<td>커스텀 버튼 박스 지정</td>
						</tr>
						<tr>
							<th>st1</th>
							<td>스타일 1번<b><i> [1|2|3] </i></b></td>
						</tr>
						<tr>
							<th>st1-150-50</th>
							<td>1번 스타일 가로폭: 150px, 세로폭: 50px</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					&lt;div class="<b>cst-btn</b>"&gt;<br>
						&nbsp;&nbsp;&lt;input type="submit" value="확인" class="<b>st1-150-50</b>"&gt;<br>
						&nbsp;&nbsp;&lt;input type="button" value="다시" class="<b>st2-150-50</b>"&gt;<br>
						&nbsp;&nbsp;&lt;input type="button" value="취소" class="<b>st3-150-50</b>"&gt;<br>
					&lt;/div&gt;
				</td>
			</tr>
		</table>
	</div>
</div>
<div style="height: 100px;"></div>

<!-- 담당자 내용 -->
<jsp:include page="./_footer.jsp"></jsp:include>
</body>
</html>