<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./myp_submenu.jsp" %>
<%
/*
	:: 계정 설정 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
System.out.println(session.getAttribute("memberInfo"));
if(session.getAttribute("member_config") == null){
%>
<c:redirect url="config.do"/>
<%
}
%>

<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_config.css">
<!-- //마이페이지 내 페이지별  js, css -->

<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 계정설정 -->
<script>
document.addEventListener("DOMContentLoaded", function(){
	var subject = document.getElementsByClassName("hd-menu-subject")[0];
	subject.innerHTML = "계정설정&nbsp;<img class='con-menuin' src='${ contextPath }/images/member/menuin.png'>";
});
</script>
	<form class="myp-con-conf" method="post" action="" onsubmit="return ;">
		<center class="con-conf-wrap">
			<article class="con-conf con-conf1 con-conf-float">
				<div class="con-conf-bg" onclick="modal('INF');">			
					<div class="con-conf-img-wrap">
						<div class="con-conf-subject-wrap">
							<img src="${ contextPath }/images/member/icons_privacy.svg">
							<!-- 140x200 -->
							<div class="con-conf-subject">&nbsp;개인정보 변경</div>
						</div>
						<center class="con-conf-content myp-conf-mobile">
							<br>
							별명이나 이름, 자기소개와 같은
							<br>'나'의 정보를 변경할 수 있습니다.
						</center>
					</div>		
				</div>
			</article>
			
			<article class="con-conf con-conf2 con-conf-float">
				<div class="con-conf-bg" onclick="modal('PWD');">			
					<div class="con-conf-img-wrap">
					<div class="con-conf-subject-wrap">
						<img src="${ contextPath }/images/member/icons_pass.svg">
						<!-- 140x200 -->
						<div class="con-conf-subject">&nbsp;비밀번호 변경</div>
					</div>
						<center class="con-conf-content myp-conf-mobile">
							<br>
							비밀번호를 변경할 수 있습니다.
						</center>
					</div>		
				</div>
			</article>
			<div class="clear"></div>
			<article class="con-conf con-conf3 con-conf-float">
				<div class="con-conf-bg" onclick="modal('OPE');">			
					<div class="con-conf-img-wrap">
						<div class="con-conf-subject-wrap">
							<img src="${ contextPath }/images/member/icons_private.svg">
							<!-- 140x200 -->
							<div class="con-conf-subject">&nbsp;정보공개 설정</div>
						</div>
						<center class="con-conf-content myp-conf-mobile">
							<br>
							찜한 목록, 평가한 목록과
							<br>
							회원 정보 공개여부를 설정합니다.
						</center>
					</div>		
				</div>
			</article>
			
			<article class="con-conf con-conf4 con-conf-float">
				<div class="con-conf-bg" onclick="modal('DEL');">			
					<div class="con-conf-img-wrap">
						<div class="con-conf-subject-wrap">
							<img src="${ contextPath }/images/member/icons_delete.svg">
							<!-- 140x200 -->
							<div class="con-conf-subject">&nbsp;회원 탈퇴</div>
						</div>
						<center class="con-conf-content myp-conf-mobile">
							<br>
							회원정보를 삭제하고 탈퇴합니다.
						</center>
					</div>		
				</div>
			</article>
			<div class="clear"></div>
		</center>
	</form>
	<div class="myp-con-conf-modifyINF">
		<article class="con-conf-modifyINF-wrap con-conf-horizontalCenter">	
			<div class="con-modal-bg-check"></div>
			<div class="con-conf-x" onclick="modal('X');"><img src="${ contextPath }/images/member/del_back.png"></div>
			<div class="con-conf-modifyINF">
				<div class="con-conf-modifyINF-subject conf-detail-subject">개인정보 변경</div>
				<form class="con-conf-modifyINF-content conf-detail-content" method="POST" action="config.do" onsubmit="return checkModify();">
					<p>변경하실 정보를 입력해주세요.</p>
					<div class="cst-text-st1 con-conf-btns">
						<input type="text" class="text-200 conf-modify-nickname" name="nickName" placeholder="별명">
						<span class="label-90">별명</span>
					</div>
					<div class="cst-radio-st1 cst-text-wrap-st1 con-conf-radio">
					  <input type="radio" name="gender" value="M" class="conf-modify-male">
					  <span class="text-70">남성</span>
					  <input type="radio" name="gender" value="F" class="conf-modify-female">
					  <span class="text-70">여성</span>
					  <div class=cst-text-label style="width: 90px; height:27px; line-height: 27px">성별</div>
					</div>					
					<div class="con-conf-age-wrap">					
						<label class="con-conf-age-label" for="con-conf-age">나이</label>
						<select id="con-conf-age" name="age" class="conf-modify-age">
						  <option>선택</option>
						</select>
					</div>
					<div class="cst-text-st1 con-conf-intro-wrap">
						<div class=cst-text-label style="width: 80px;">자기소개</div>
						<textarea class="con-conf-intro" name="introduce" cols="35" rows="4" wrap="hard" placeholder=""></textarea>
					</div>		
					<div class="cst-btn con-conf-submitbtns con-conf-submitbtns-INF">
					  <input type="submit" value="확인" class="st1-80-50">
					  <input type="reset" value="다시" class="st2-80-50">
					  <input type="button" value="취소" class="st3-80-50" onclick="modal('X');">
					</div>
				</form>
			</div>	
		</article>
	</div>
	<div class="myp-con-conf-modifyPW">
		<article class="con-conf-modifyPW-wrap con-conf-horizontalCenter">
			<div class="con-modal-bg-check"></div>
			<div class="con-conf-x" onclick="modal('X');"><img src="${ contextPath }/images/member/del_back.png"></div>
			<div class="con-conf-modifyPW">
				<div class="con-conf-modifyPW-subject conf-detail-subject">비밀번호 변경</div>
				<form class="con-conf-modifyPW-content conf-detail-content" method="POST" action="config.do" onsubmit="return checkModifyPW();">
					<p>기존과 다른 새 비밀번호를 입력해주세요.</p>
					<div class="cst-text-st1 con-conf-btns">
						<input type="password" name="password" class="text-200 conf-modify-newPW" placeholder="새 비밀번호 입력">
						<span class="label-140">새 비밀번호</span>
					</div>
					<div class="cst-text-st1 con-conf-btns">
						<input type="password" name="newPW"  class="text-200 conf-modify-newPWChk" placeholder="한 번 더 입력">
						<span class="label-140">새 비밀번호 확인</span>
					</div>			
					
					<div class="cst-btn con-conf-submitbtns con-conf-submitbtns-PWD">
					  <input type="submit" value="확인" class="st1-80-50">
					  <input type="reset" value="다시" class="st2-80-50">
					  <input type="button" value="취소" class="st3-80-50" onclick="modal('X');">
					</div>
				</form>
			</div>			
		</article>
	</div>
	<div class="myp-con-conf-modifyOPE">
		<article class="con-conf-modifyOPE-wrap con-conf-horizontalCenter">	
			<div class="con-modal-bg-check"></div>
			<div class="con-conf-x" onclick="modal('X');"><img src="${ contextPath }/images/member/del_back.png"></div>
			<div class="con-conf-modifyOPE">
				<div class="con-conf-modifyOPE-subject conf-detail-subject">정보공개</div>
				<form class="con-conf-modifyOPE-content conf-detail-content"  method="POST" action="config.do" onsubmit="return checkModifyOPE();">
					<p>정보의 공개여부를 설정해주세요.</p>
					<input type="hidden" class="con-conf-OPEYN" name="opeyn">				
					<div class="cst-radio-st1 cst-text-wrap-st1 con-conf-radio">
						<c:choose>
                			<c:when test="${memberInfo.info_flag=='Y'}">
					  			<input type="radio" name="info-flag" class="conf-modify-flagY" value="Y" checked="checked">
					  		</c:when>
					  		<c:otherwise>
					  			<input type="radio" name="info-flag" class="conf-modify-flagY" value="Y">
					  		</c:otherwise>
					  	</c:choose>
					  	<span class="text-50">공개</span>
					  	<c:choose>
                			<c:when test="${memberInfo.info_flag=='N'}">
					  			<input type="radio" name="info-flag" class="conf-modify-flagN" value="N" checked="checked">
					  		</c:when>
					  		<c:otherwise>
					  			<input type="radio" name="info-flag" class="conf-modify-flagN" value="N" >
					  		</c:otherwise>
					  	</c:choose>					  			
					  	<span class="text-50">비공개</span>
					  	<div class=cst-text-label style="width: 90px; height:27px; line-height: 27px">개인정보</div>
					</div>
					<div class="cst-radio-st1 cst-text-wrap-st1 con-conf-radio">
						  <c:choose>
	                			<c:when test="${memberInfo.like_flag=='Y'}">
						  			<input type="radio" name="like-flag" class="conf-modify-flagY" value="Y" checked="checked">
						  		</c:when>
						  		<c:otherwise>
						  			<input type="radio" name="like-flag" class="conf-modify-flagY" value="Y">
						  		</c:otherwise>
						  	</c:choose>
						  	<span class="text-50">공개</span>
						  	<c:choose>
	                			<c:when test="${memberInfo.like_flag=='N'}">
						  			<input type="radio" name="like-flag" class="conf-modify-flagN" value="N" checked="checked">
						  		</c:when>
						  		<c:otherwise>
						  			<input type="radio" name="like-flag" class="conf-modify-flagN" value="N" >
						  		</c:otherwise>
						  	</c:choose>		
						  	<span class="text-50">비공개</span>
					  <div class=cst-text-label style="width: 90px; height:27px; line-height: 27px">찜한 목록</div>
					</div>
					<div class="cst-radio-st1 cst-text-wrap-st1 con-conf-radio">
					  <c:choose>
                			<c:when test="${memberInfo.assessment_flag=='Y'}">
					  			<input type="radio" name="assessment-flag" class="conf-modify-flagY" value="Y" checked="checked">
					  		</c:when>
					  		<c:otherwise>
					  			<input type="radio" name="assessment-flag" class="conf-modify-flagY" value="Y">
					  		</c:otherwise>
					  	</c:choose>
					  	<span class="text-50">공개</span>
					  	<c:choose>
                			<c:when test="${memberInfo.assessment_flag=='N'}">
					  			<input type="radio" name="assessment-flag" class="conf-modify-flagN" value="N" checked="checked">
					  		</c:when>
					  		<c:otherwise>
					  			<input type="radio" name="assessment-flag" class="conf-modify-flagN" value="N" >
					  		</c:otherwise>
					  	</c:choose>
					  	<span class="text-50">비공개</span>					  	
					  <div class=cst-text-label style="width: 90px; height:27px; line-height: 27px">평가 목록</div>
					</div>			
					<div class="cst-btn con-conf-submitbtns con-conf-submitbtns-OPE">
					  <input type="submit" value="확인" class="st1-80-50">
					  <input type="button" value="취소" class="st3-80-50" onclick="modal('X');">
					</div>
				</form>
			</div>	
		</article>
	</div>
	
	<div class="myp-con-conf-modifyDEL">
		<article class="con-conf-modifyDEL-wrap con-conf-horizontalCenter">	
			<div class="con-modal-bg-check"></div>
			<div class="con-conf-x" onclick="modal('X');"><img src="${ contextPath }/images/member/del_back.png"></div>
			<div class="con-conf-modifyDEL">
				<div class="con-conf-modifyDEL-subject conf-detail-subject">회원탈퇴</div>
				<form class="con-conf-modifyDEL-content conf-detail-content"  method="POST" action="logout.do">
					<p>회원정보를 삭제합니다.<br>삭제된 정보는 다시 복구할 수 없습니다.</p>	
					<div class="cst-btn con-conf-submitbtns con-conf-submitbtns-DEL">
					  <input type="button" value="확인" class="st1-80-50" onclick="checkModifyDEL();">
					  <input type="button" value="취소" class="st3-80-50" onclick="modal('X');">
					  <input type="hidden" class="con-conf-del-check">
					</div>
				</form>
			</div>	
		</article>
	</div>
	
	<div class="con-conf-confirmed">
		<article class="con-conf-confirmed-ok con-conf-horizontalCenter">
			<div class="conf-confirmed-subject conf-detail-subject">
				<span class="icon-confirmed-wrap"><img src="${ contextPath }/images/member/confirmed.svg" class="icon-confirmed"></span>
				<span>Confirm</span>
			</div>
			<div class="con-conf-confirmed conf-detail-content">입력하신 정보로 저장 중입니다...</div>
		</article>
		<article class="con-conf-confirmed-failed  con-conf-horizontalCenter">
			<div class="con-conf-confirmed-subject conf-detail-subject">
				<span class="icon-confirmed-wrap"><img src="${ contextPath }/images/member/failed.svg" class="icon-confirmed"></span>
				<span>Failed</span>
			</div>
			<div class="con-conf-confirmed con-conf-status conf-detail-content">입력하신 정보가 올바르지 않거나,<br>시스템 오류가 발생했습니다.</div>
		</article>
		<article class="con-conf-confirmed-bye con-conf-horizontalCenter">
			<div class="con-conf-confirmed-subject conf-detail-subject">
				<span class="icon-confirmed-wrap"><img src="${ contextPath }/images/member/confirmed.svg" class="icon-confirmed"></span>
				<span>회원탈퇴</span>
			</div>
			<div class="con-conf-confirmed con-conf-status conf-detail-content">그동안 이용해주셔서 감사합니다.<br>보다 좋은 서비스 제공을 위하여<br> 노력하겠습니다.</div>
		</article>
	</div>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<div class="con-modal-bg"></div>
<script>ageCount();</script>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>