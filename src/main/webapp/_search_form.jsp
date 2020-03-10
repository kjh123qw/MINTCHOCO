<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 검색창 ::
	담당자 : 김정호
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<form action="${ contextPath }/movie/search.do">
	<div class="search-form-wrap">
		<div class="cst-text-st1 search-form-textbox">
			<input type="text" class="text-230" name="searchKeyWord" placeholder="검색어 입력">
		</div>
		<div id="searchBtn">
			<i class="fas fa-search"></i>
		</div>
	</div>
</form>