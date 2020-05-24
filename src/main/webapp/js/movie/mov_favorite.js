/**
 * 담당자: 김정호
 */

$(function() {
	var contextPath = sessionStorage.getItem('contextPath');
	var totlaSearchCount;
	var selectedMovieNumber = $('#selectedMovieNumber').val();
	var memberNumber = $('#memberNumber').val();
	if(memberNumber != "") {
		doFavoriteAjax(selectedMovieNumber);
	} else {
		$('.like-box').remove();
	}
	
	function doFavoriteAjax(movieNumber) {
		$.ajax({
	        url : contextPath + "/movie/checkfv.do",
	        type :"POST",
	        data :{ seq: movieNumber },
	        dataType: 'json',
	        success :function(data){
	    		$("#likeBtn").show();
	        	if(data == "OK") {
	        		$("#likeBtn").css({'color': 'red'});
	        		$("#likeBtn").unbind('click');
	        		$("#likeBtn").bind('click', function() {doCancel(selectedMovieNumber)});
	        	} else {
	        		$("#likeBtn").css({'color': '#bbb'});
	        		$("#likeBtn").unbind('click');
	        		$("#likeBtn").bind('click', function() {doLike(selectedMovieNumber)});
	        	}
	        }
		});
	}
	
	function doLike(movieNumber) {
		$("#likeBtn").hide();
		$.ajax({
	        url : contextPath + "/movie/favoritePlus.do",
	        type :"POST",
	        data :{ seq: movieNumber }
		}).done(function() {
			doFavoriteAjax(movieNumber);
		});
	}
	function doCancel(movieNumber) {
		$("#likeBtn").hide();
		$.ajax({
	        url : contextPath + "/movie/favoriteMinus.do",
	        type :"POST",
	        data :{ seq: movieNumber }
		}).done(function() {
			doFavoriteAjax(movieNumber);
		});
	}
});