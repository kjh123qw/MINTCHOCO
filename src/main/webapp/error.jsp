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

<!-- 담당자 내용 -->
<%@ include file="./_footer.jsp" %>

</body>
</html>