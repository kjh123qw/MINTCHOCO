<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./myp_submenu.jsp" %>
<%
/*
	:: 찜 한 리스트 ::
	담당자 : 장세진
*/
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
<!-- 마이페이지 내 페이지별 js/css -->
<link rel="stylesheet" href="${ contextPath }/css/member/myp_fav.css">
<!-- //마이페이지 내 페이지별  js, css -->

<!-- 서브메뉴 이하 담당자 내용 -->
<!-- 찜한 목록 -->
<script>
document.addEventListener("DOMContentLoaded", function(){
	var subject = document.getElementsByClassName("hd-menu-subject")[0];
	subject.innerHTML = "favorite&nbsp;<img class='con-menuin' src='${ contextPath }/images/member/menuin.png'>";
});
</script>
	<form class="myp-con-fav" method="post" action="favorite.do" onsubmit="return chk_cnt();">
		<div class="con-fav-wrap">
			<nav class="con-menu">
				<div class="con-menu-modifyoff  con-menu-dropdown-rel"">
					<div class="con-menu-sub myp_pc">${detail_info.nickName}'s favorite</div>
					<ul class="con-menu-list con-menu-dropdown-rel">
						<li><a onclick="searchbar('on');">search</a></li>
						<li class="con-menu-dropdown-rel con-menu-dropdown-here">
							<a class="con-menu-dropdown-a">sort</a>
							<div class="con-menu-dropdown-wrap">
						        <ul class="con-menu-dropdown">
						            <li><a onmouseenter="textAlign('reg');" onclick="sorting('reg');">registration</a></li>
						            <li><a onmouseenter="textAlign('rel');" onclick="sorting('rel');">release</a></li>
						            <li><a onmouseenter="textAlign('sub');" onclick="sorting('sub');">title</a></li>
						        </ul>						        
						         <div class="menu-description">
						     		<p class="menu-description-subject">sort</p>
						     	</div>						     	
						     </div>						   
						</li>
						<c:choose>
	   						<c:when test="${memberInfo.number==detail_info.number}">
								<li><a onclick="modifyOn();">edit</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="con-fakeModify">edit</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
					<input type="text" class="con-menu-input"  placeholder="keyword" onkeyup="search();">
					<div class="con-menu-search" onclick="searchbar('off');"><img src="${ contextPath }/images/member/back.png"></div>
				</div>
				<c:if test="${memberInfo.number==detail_info.number}">
					<div class="con-menu-modifyon">
						<div class="con-menu-sub">
							<span class="con-chkbox-cnt">0</span>
							<span>selected</span>
						</div>
						<ul class="con-menu-list">
							<li><a onclick="allcheck();">whole</a></li>
							<li><a onclick="modifyOff();">cancel</a></li>
							<li><a><input type="submit" id="fav-list-delete" class="con-list-delete"><label for="fav-list-delete">delete</label></a></li>
						</ul>
					</div>
				</c:if>
			</nav>
			<div class="clear"></div>
			
			
			<c:if test="${detail_info.like_flag=='N' && memberInfo.number==detail_info.number}">
				<div class="con-fav-none con-none-my">this is not open.</div>
			</c:if>
			<c:choose>
	   			<c:when test="${detail_info.like_flag=='Y' || memberInfo.number==detail_info.number}">
			   		<c:if test="${fav_cnt == 0}">
						<div class="con-fav-none">there is not item.</div>
					</c:if>
					<div class="con-fav-margin">
					<c:forEach items="${favoriteList}" var="favorite">
						<article class="con-fav con-fav-float" onclick="chk_enable(this);">
						<input class="con-chkbox" type="checkbox" name="chkbox" value="${favorite.favNum}">
						<div class="con-fav-bg">			
							<div class="con-fav-img-wrap">
								<a class="con-movie-link" href="${ contextPath }/movie/detail.do?movieNumber=${favorite.movieNum}">
									<img class="con-fav-poster" src="${favorite.poster}">
								</a>
								<!-- 140x200 -->
								<center class="con-fav-contents-wrap">
									<div class="con-search-target con-th-mint">${favorite.movieTitle}</div>
									<div class="con-fav-movie-kind">${favorite.kind}</div>
									<div class="con-fav-movie-detail con-th-gray">${favorite.playtime}m</div>
									<div class="con-fav-movie-indate con-th-gray"><img src="${ contextPath }/images/member/cal.png" class="con-fav-cal">&nbsp;${favorite.favIndate}</div>
								</center>
							</div>				
						</div>
						</article>
					</c:forEach>
					</div>			
					<div class="clear"></div>
				</c:when>
				<c:otherwise>
					<div class="con-fav-none">there is not available.</div>
				</c:otherwise>
			</c:choose>
			<!-- 검색결과가 없을때 -->
			
			<div class="con-search-none">completed</div>
			
		</div>
		<div class="clear"></div>
	</form>
	<form class="con-sortList con-sortList-fav" name="alignForm" method="POST" action="favorite.do">
		<input type="hidden" name="sort" class="con-alignInput">
	</form>
<!-- //서브메뉴 이하 담당자 내용 -->
</div>
<!-- //담당자 내용 -->
<%@ include file="../_footer.jsp" %>
</body>
</html>