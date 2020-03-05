<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 에러 ::
	담당자 : 김정호
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MINT CHOCO</title>
<script src="./js/jquery.2.1.3.min.js"></script>
<script src="./js/jquery-ui.min.js"></script>
<script src="./js/jquery.easing.1.3.js"></script>
<script src="./js/jquery.mousewheel.min.js"></script>
<script src="./js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="./css/public.css">
<!-- 담당자 js, css -->

<!-- //담당자 js, css -->
</head>
<body>
<%@ include file="./_header.jsp" %>
<!-- 담당자 내용 -->

<h1 class="error-header">Oops! Wrong url!</h1>
<hr>
<p class="error-msg">
	존재하지 않는 주소입니다.
</p>
<article>
	<h1>검색 테스트</h1>
	<%@ include file="./_search_form.jsp" %>
	<hr>
	<h3>커스텀 태그 테스트</h3>
	<div class="cst-chkbox-st1">
		<input type="checkbox" value="1" name="a">
		<span class="label-300">테스트 체크박스 입니다.1</span>
		<input type="checkbox" value="2" name="b">
		<span class="label-300">테스트 체크박스 입니다.2</span>
		<input type="checkbox" value="3" name="c">
		<span class="label-300">테스트 체크박스 입니다.3</span>
		<input type="checkbox" value="4" name="d">
		<span class="label-300">테스트 체크박스 입니다.4</span>
	</div>
</article>
<!-- 담당자 내용 -->
<%@ include file="./_footer.jsp" %>

</body>
</html>