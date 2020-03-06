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
	<form class="myp_con_conf" method="post" action="" onsubmit="return ;">
		<center class="con_conf_wrap">
			<article class="con_conf con_conf1 con_conf_float" onclick="modal('INF');">
				<div class="con_conf_bg">			
					<div class="con_conf_img_wrap">
						<div class="con_conf_subject_wrap">
							<img src="../images/member/icons_privacy.svg">
							<!-- 140x200 -->
							<div class="con_conf_subject">&nbsp;개인정보 변경</div>
						</div>
						<center class="con_conf_content">
							<br>
							별명이나 이름, 자기소개와 같은
							<br>'나'의 정보를 변경할 수 있습니다.
						</center>
					</div>		
				</div>
			</article>
			
			<article class="con_conf con_conf2 con_conf_float" onclick="modal('PWD');">
				<div class="con_conf_bg">			
					<div class="con_conf_img_wrap">
					<div class="con_conf_subject_wrap">
						<img src="../images/member/icons_pass.svg">
						<!-- 140x200 -->
						<div class="con_conf_subject">&nbsp;비밀번호 변경</div>
					</div>
						<center class="con_conf_content">
							<br>
							비밀번호를 변경할 수 있습니다.
						</center>
					</div>		
				</div>
			</article>
			<div class="clear"></div>
			<article class="con_conf con_conf3 con_conf_float" onclick="chk_enable(this);">
				<div class="con_conf_bg">			
					<div class="con_conf_img_wrap">
						<div class="con_conf_subject_wrap">
							<img src="../images/member/icons_private.svg">
							<!-- 140x200 -->
							<div class="con_conf_subject">&nbsp;정보공개 설정</div>
						</div>
						<center class="con_conf_content">
							<table class="con_conf_table">
								<tr>
									<th>개인정보</th>
									<td>
										<ul class="con_conf_ul">
											<li><input type="radio" name="privacy_public" id="privacy_y"><label for="privacy_y">공개</label></li>
											<li><input type="radio" name="privacy_public" id="privacy_n" checked="checked"><label for="privacy_n">비공개</label></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th>찜한 목록</th>
									<td>
										<ul class="con_conf_ul">
											<li><input type="radio" name="fav_public" id="fav_y" checked="checked"><label for="fav_y">공개</label></li>
											<li><input type="radio" name="fav_public" id="fav_n"><label for="fav_n">비공개</label></li>
										</ul>
									</td>
								</tr>
								<tr>
									<th>평가 목록</th>
									<td>
										<ul class="con_conf_ul">
											<li><input type="radio" name="assess_public" id="assess_y" checked="checked"><label for="assess_y">공개</label></li>
											<li><input type="radio" name="assess_public" id="assess_n" ><label for="assess_n">비공개</label></li>
										</ul>
									</td>
								</tr>
							</table>
							<input type="submit" id="con_conf_submit">
							<label class="con_conf_submit_btn" for="con_conf_submit">적 용</label>
						</center>
					</div>		
				</div>
			</article>
			
			<article class="con_conf con_conf4 con_conf_float" onclick="chk_enable(this);">
				<div class="con_conf_bg">			
					<div class="con_conf_img_wrap">
						<div class="con_conf_subject_wrap">
							<img src="../images/member/icons_delete.svg">
							<!-- 140x200 -->
							<div class="con_conf_subject">&nbsp;회원 탈퇴</div>
						</div>
						<center class="con_conf_content">
							<br>
							회원정보를 삭제하고 탈퇴합니다.
						</center>
					</div>		
				</div>
			</article>
			<div class="clear"></div>
		</center>
	</form>
	<div class="myp_con_conf_modifyINF">
		<article class="con_conf_modifyINF_wrap con_conf_horizontalCenter">
			<div class="con_conf_x" onclick="modal('X');"><img src="../images/member/del_back.png"></div>
			<div class="con_conf_modifyINF">
				<div class="con_conf_modifyINF_subject conf_detail_subject">개인정보 변경</div>
				<div class="con_conf_modifyINF_content conf_detail_content">
					<p>변경하실 정보를 입력해주세요.</p>
					<input type="text" class="con_conf_text" placeholder="별명">
					<input type="text" class="con_conf_text" placeholder="성별">
					<input type="text" class="con_conf_text" placeholder="나이">
					<input type="text" class="con_conf_text" placeholder="자기소개">
					<p class="con_conf_statusBar">&nbsp;</p>
					<div class="checkPW_btn">
						<input type="submit" id="con_conf_submit">
						<label for="con_conf_submit" class="con_conf_submit_btn">확 인</label>
						<input type="button" id="con_conf_cancel">
						<label for="con_conf_cancel" class="con_conf_submit_btn checkPW_btn_cancel">취 소</label>
					</div>
				</div>
			</div>			
		</article>
	</div>
	<div class="myp_con_conf_modifyPW">
		<article class="con_conf_modifyPW_wrap con_conf_horizontalCenter">
			<div class="con_conf_x" onclick="modal('X');"><img src="../images/member/del_back.png"></div>
			<div class="con_conf_modifyPW">
				<div class="con_conf_modifyPW_subject conf_detail_subject">비밀번호 변경</div>
				<div class="con_conf_modifyPW_content conf_detail_content">
					<p>기존과 다른 새 비밀번호를 입력해주세요.</p>
					<input type="text" class="con_conf_text" placeholder="새 비밀번호">
					<br>
					<input type="text" class="con_conf_text" placeholder="새 비밀번호 확인">
					<p class="con_conf_statusBar">&nbsp;</p>
					<div class="checkPW_btn">
						<input type="submit" id="con_conf_submit">
						<label for="con_conf_submit" class="con_conf_submit_btn">확 인</label>
						<input type="button" id="con_conf_cancel">
						<label for="con_conf_cancel" class="con_conf_submit_btn checkPW_btn_cancel">취 소</label>
					</div>
				</div>
			</div>			
		</article>
	</div>
	<div class="con_conf_confirmed">
		<article class="con_conf_confirmed_ok con_conf_horizontalCenter">
			<div class="conf_confirmed_subject conf_detail_subject">
				<span class="icon_confirmed_wrap"><img src="../images/member/confirmed.svg" class="icon_confirmed"></span>
				<span>Confirm</span>
			</div>
			<div class="con_conf_confirmed conf_detail_content">입력하신 정보로 저장되었습니다.</div>
		</article>
		<article class="con_conf_confirmed_failed  con_conf_horizontalCenter">
			<div class="con_conf_confirmed_subject conf_detail_subject">
				<span class="icon_confirmed_wrap"><img src="../images/member/failed.svg" class="icon_confirmed"></span>
				<span>Failed</span>
			</div>
			<div class="con_conf_confirmed conf_detail_content">입력하신 정보가 올바르지 않거나,<br>시스템 오류가 발생했습니다.</div>
		</article>
	</div>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<div class="con_modal_bg"></div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>