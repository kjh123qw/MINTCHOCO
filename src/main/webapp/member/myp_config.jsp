<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
	:: 계정 설정 ::
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
<!-- 계정설정 -->
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
						<center class="con-conf-content">
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
						<center class="con-conf-content">
							<br>
							비밀번호를 변경할 수 있습니다.
						</center>
					</div>		
				</div>
			</article>
			<div class="clear"></div>
			<article class="con-conf con-conf3 con-conf-float" onclick="chk-enable(this);">
				<div class="con-conf-bg">			
					<div class="con-conf-img-wrap">
						<div class="con-conf-subject-wrap">
							<img src="${ contextPath }/images/member/icons_private.svg">
							<!-- 140x200 -->
							<div class="con-conf-subject">&nbsp;정보공개 설정</div>
						</div>
						<center class="con-conf-content">
							<table class="con-conf-table">
								<tr>
									<th>개인정보</th>
									<td>
										<ul class="con-conf-ul">
											<li><input type="radio" name="privacy-public" id="privacy-y"><label for="privacy-y">공개</label></li>
											<li><input type="radio" name="privacy-public" id="privacy-n" checked="checked"><label for="privacy-n">비공개</label></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th>찜한 목록</th>
									<td>
										<ul class="con-conf-ul">
											<li><input type="radio" name="fav-public" id="fav-y" checked="checked"><label for="fav-y">공개</label></li>
											<li><input type="radio" name="fav-public" id="fav-n"><label for="fav-n">비공개</label></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th>평가 목록</th>
									<td>
										<ul class="con-conf-ul">
											<li><input type="radio" name="assess-public" id="assess-y" checked="checked"><label for="assess-y">공개</label></li>
											<li><input type="radio" name="assess-public" id="assess-n" ><label for="assess-n">비공개</label></li>
										</ul>
									</td>
								</tr>
							</table>
							<input type="submit" id="con-conf-submit">
							<label class="con-conf-submit-btn" for="con-conf-submit">적 용</label>
						</center>
					</div>		
				</div>
			</article>
			
			<article class="con-conf con-conf4 con-conf-float" onclick="chk-enable(this);">
				<div class="con-conf-bg">			
					<div class="con-conf-img-wrap">
						<div class="con-conf-subject-wrap">
							<img src="${ contextPath }/images/member/icons_delete.svg">
							<!-- 140x200 -->
							<div class="con-conf-subject">&nbsp;회원 탈퇴</div>
						</div>
						<center class="con-conf-content">
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
				<form class="con-conf-modifyINF-content conf-detail-content" onsubmit="return checkModify();">
					<p>변경하실 정보를 입력해주세요.</p>
					<div class="cst-text-st1 con-conf-btns">
						<input type="text" class="text-200 conf-modify-nickname" placeholder="별명">
						<span class="label-80">별명</span>
					</div>
					<div class="cst-radio-st1 cst-text-wrap-st1">
					  <input type="radio" name="exRadio1" class="conf-modify-male">
					  <span class="text-70">남성</span>
					  <input type="radio" name="exRadio1" class="conf-modify-female">
					  <span class="text-70">여성</span>
					  <div class=cst-text-label style="width: 80px;">성별</div>
					</div>					
					<div class="con-conf-age-wrap">					
						<label class="con-conf-age-label" for="con-conf-age">나이</label>
						<select id="con-conf-age" class="conf-modify-age">
						  <option value="">선택</option>
						</select>
					</div>
					<div class="cst-text-st1 con-conf-intro-wrap">
						<textarea class="con-conf-intro" cols="35" rows="4" wrap="hard" placeholder=""></textarea>
						<div class=cst-text-label style="width: 80px;">자기소개</div>
					</div>					
					<div class="cst-btn con-conf-submitbtns">
					  <input type="submit" value="확인" class="st1-80-50">
					  <input type="reset" value="다시" class="st2-80-50">
					  <input type="button" value="취소" class="st3-80-50">
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
				<form class="con-conf-modifyPW-content conf-detail-content" onsubmit="return checkModifyPW();">
					<p>기존과 다른 새 비밀번호를 입력해주세요.</p>
					<div class="cst-text-st1 con-conf-btns">
						<input type="password" class="text-200 conf-modify-newPW" placeholder="새 비밀번호 입력">
						<span class="label-140">새 비밀번호</span>
						<input type="password" class="text-200 conf-modify-newPWChk" placeholder="한 번 더 입력">
						<span class="label-140">새 비밀번호 확인</span>
					</div>		
					
					<div class="cst-btn con-conf-submitbtns">
					  <input type="submit" value="확인" class="st1-80-50">
					  <input type="reset" value="다시" class="st2-80-50">
					  <input type="button" value="취소" class="st3-80-50">
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
	</div>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<div class="con-modal-bg"></div>
<script>ageCount();</script>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>