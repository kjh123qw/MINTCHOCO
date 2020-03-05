<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<meta charset="UTF-8">
<title>검색결과</title>
<script src="${ contextPath }/js/jquery.2.1.3.min.js"></script>
<script src="${ contextPath }/js/jquery-ui.min.js"></script>
<script src="${ contextPath }/js/jquery.easing.1.3.js"></script>
<script src="${ contextPath }/js/jquery.mousewheel.min.js"></script>
<script src="${ contextPath }/js/public.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/public.css">

<!-- 담당자 js/css -->
<script src="${ contextPath }/js/search_list.js"></script>
<link rel="stylesheet" href="${ contextPath }/css/search_list.css">
<!-- 담당자 js/css -->
</head>


<jsp:include page="../_header.jsp"></jsp:include>


<body>

    <h1>{ 검색어 } 검색 결과 입니다.</h1>
	<hr>

</body>

<jsp:include page="../_footer.jsp"></jsp:include>


</html>