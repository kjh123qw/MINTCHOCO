<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 계정 설정 패스워드 확인 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_config.css">
<!-- //마이페이지 내 페이지별  js, css -->
<%@ include file="./myp_submenu.jsp" %>
<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 패스워드 확인 -->
	<form class="myp-con-conf-checkPW">
		<article class="con-conf-checkPW-wrap">
			<div class="con-conf-checkPW-subject">패스워드 확인</div>
			<div class="con-conf-checkPW-content">개인정보 보호를 위해 현재 패스워드를 확인합니다.</div>
			<input type="text" class="con-conf-text" placeholder="기존 패스워드 입력">			
			<br>
			<div class="checkPW-btn">
				<input type="submit" id="con-conf-submit">
				<label for="con-conf-submit" class="con-conf-submit-btn">확 인</label>
				<input type="button" id="con-conf-cancel">
				<label for="con-conf-cancel" class="con-conf-submit-btn checkPW-btn-cancel">취 소</label>
			</div>			
		</article>
	</form>
	<div class="con-conf-confirmed">
		<article class="con-conf-confirmed-ok con-conf-horizontalCenter">
			<div class="conf-confirmed-subject conf-detail-subject">
				<span class="icon-confirmed-wrap"><img src="../images/member/confirmed.svg" class="icon-confirmed"></span>
				<span>Confirm </span>
			</div>
			<div class="con-conf-confirmed conf-detail-content">패스워드 확인에 성공했습니다.</div>
		</article>
	</div>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>