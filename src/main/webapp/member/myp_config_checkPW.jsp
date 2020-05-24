<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./myp_submenu.jsp" %>
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

<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 패스워드 확인 -->
<script>
document.addEventListener("DOMContentLoaded", function(){
	var subject = document.getElementsByClassName("hd-menu-subject")[0];
	subject.innerHTML = "account&nbsp;<img class='con-menuin' src='${ contextPath }/images/member/menuin.png'>";
});
</script>
	<form class="myp-con-conf-checkPW" method="POST" action="config.do" onsubmit="return checkPW();">
		<article class="con-conf-checkPW-wrap">
			<div class="con-conf-checkPW-subject">password</div>
			<div class="con-conf-checkPW-content">Check your password</div>
			<input type="password" class="con-conf-text" placeholder="password">			
			<br>
			<div class="checkPW-btn">
				<input type="submit" id="con-conf-submit" class="con-conf-checkPW">
				<label for="con-conf-submit" class="con-conf-submit-btn">confirm</label>
				<input type="button" id="con-conf-cancel" onclick="history.back();">
				<label for="con-conf-cancel" class="con-conf-submit-btn checkPW-btn-cancel">cancel</label>
			</div>			
		</article>
	</form>
	<div class="con-conf-confirmed">
		<article class="con-conf-confirmed-ok con-conf-horizontalCenter">
			<div class="conf-confirmed-subject conf-detail-subject">
				<span class="icon-confirmed-wrap"><img src="${ contextPath }/images/member/confirmed.svg" class="icon-confirmed"></span>
				<span>Confirm</span>
			</div>
			<div class="con-conf-confirmed conf-detail-content">checking...</div>
		</article>
		<article class="con-conf-confirmed-failed  con-conf-horizontalCenter">
			<div class="con-conf-confirmed-subject conf-detail-subject">
				<span class="icon-confirmed-wrap"><img src="${ contextPath }/images/member/failed.svg" class="icon-confirmed"></span>
				<span>Failed</span>
			</div>
			<div class="con-conf-confirmed con-conf-status conf-detail-content">There is some errors.</div>
		</article>
	</div>
	<div class="clear"></div>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<div class="con-modal-bg"></div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>