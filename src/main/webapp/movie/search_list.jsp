<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<%
/*
	:: 검색 결과 화면 ::
	담당 : 김정호
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta charset="UTF-8">
<title>Search result</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<script src="https://use.fontawesome.com/releases/v5.8.0/js/all.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">

<!-- 담당자 js/css -->
<script src="${ contextPath }/js/search_list.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/index/index.css">
<!-- 담당자 js/css -->
</head>

<body>

<jsp:include page="../_header.jsp"></jsp:include>

	<div id="searchListWrap">
	    <p><b>${ searchKeyWord }</b> search result &lt;<b>${ searchCount }</b>&gt;</p>
		<hr>
		<div id="mainMovieBox">
		
		</div>
	</div>
	<div id="moreBtn">More</div>
<jsp:include page="../_footer.jsp"></jsp:include>

</body>

</html>