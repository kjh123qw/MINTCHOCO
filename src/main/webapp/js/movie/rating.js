/**
 * 담당자: 김정호
 */

function checkComment() {
	if($('#commentContent').val() == '') {
		alert('Please write comment.');
		return false;
	}
	return true;
}

$(function() {
	var contextPath = sessionStorage.getItem('contextPath');
	var totlaSearchCount;
	var selectedMovieNumber = $('#selectedMovieNumber').val();
	var memberNumber = $('#memberNumber').val();
	doCommentAjax(1, selectedMovieNumber);
	
	function doCommentAjax(pageNumber, movieNumber) {
		$.ajax({
	        url : contextPath + "/comment/list.do",
	        type :"POST",
	        data :{ pageNumber: pageNumber, movieNumber: movieNumber },
	        dataType: 'json',
	        success :function(data){
	        	$('#ratingCommentBox').html('');
	        	if(data.length > 0) {
		        	data.forEach(function(item, index) {
		        		var regDate = new Date(item.assessRegdate);

						var comment = $('<div />', {'class': 'comment number' + item.assessId});
						
						var scoreImg = $('<div />', {'class': 'score-img', 'style': 'width: ' + (item.assessStars * 10) + '%'});
						var scoreBg = $('<div />', {'class': 'score-bg'});
						scoreBg.append(scoreImg);
						var scoreText = $('<div />', {'class': 'score-text', 'text': item.assessStars});
						var ratingScore = $('<div />', {'class': 'rating-score'});
						ratingScore.append(scoreBg);
						ratingScore.append(scoreText);
						
						var nickname = $('<div />', {'class': 'nickname', 'text': item.memberNickname, 'click': function() {
							location.href = contextPath + '/mypage.do?nickName=' + item.memberNickname;
						}});
						var content = $('<div />', {'class': 'content', 'text': item.assessContent});
	
						var down = $('<div />', {'class': 'thumbs-down', 'click': function() {
							doLikeAjax(item.memberNumber, item.assessId, 'D');
							}});
						var up = $('<div />', {'class': 'thumbs-up', 'click': function() {
							doLikeAjax(item.memberNumber, item.assessId, 'L');
							}});
						down.html('<i class="fas fa-thumbs-down"></i> ' + item.hates);
						up.html('<i class="fas fa-thumbs-up"></i> ' + item.likes);
						
						var date = $('<div />', {'class': 'rating-date', 'text': regDate.getFullYear() + '-' + (regDate.getMonth() + 1)+ '-' + regDate.getDate()});
						var ratingDelete = $('<div />')
						if(memberNumber == item.memberNumber) {
							ratingDelete = $('<div />', {'class': 'rating-delete', 'text': 'delete', 'click': function() {
								if(confirm('Are you sure to delete?')) {
									location.href = contextPath + "/comment/delete.do?assessId=" + item.assessId + "&movieNumber=" + item.movieNumber;
								}
							}});
						}
						comment.append(nickname);
						comment.append(ratingScore);
						comment.append(content);
						comment.append(date);
						comment.append(ratingDelete);
						comment.append(up);
						comment.append(down);
						$('#ratingCommentBox').append(comment);
						
		        	});
	        	} else {
					var noComment = $('<div />', {'class': 'no-comment', 'text': 'There is no comment.'});
					$('#ratingCommentBox').append(noComment);
	        	}
	        }
	    }).done(function() {
			$.ajax({
		        url : contextPath + "/comment/pagecount.do",
		        type :"POST",
		        dataType: 'json',
		        success :function(data){
		        	var totalPage = Math.floor(data.totalSize / 10);
		        	$('#ratingPage').html('');
		    		var pageNumberFirst = $('<span />', {'class': 'page-number', 'click': function() {
		    			doCommentAjax(1, movieNumber);
		    		}});
		    		if(pageNumber == 1)
		    			pageNumberFirst.attr('class','page-number page-number-selected');
		    		pageNumberFirst.html('&nbsp;&nbsp;' + 1 + '&nbsp;&nbsp;');
		    		$('#ratingPage').append(pageNumberFirst);
			    	for(var i = 0; i < totalPage; i++) {
			    		var pageNumberSpan = $('<span />', {'class': 'page-number', 'click': function() {
			    			doCommentAjax((i + 1), movieNumber);
			    		}});
			    		pageNumberSpan.html('&nbsp;&nbsp;' + (i + 2) + '&nbsp;&nbsp;');
			    		if(pageNumber == (i + 2))
			    			pageNumberSpan.attr('class', 'page-number-selected');

			    		$('#ratingPage').append(pageNumberSpan);
			    	}
		        }
			});
			$.ajax({
		        url : contextPath + "/comment/getOne.do",
		        type :"POST",
		        data :{ assessId: memberNumber + "" + movieNumber },
		        dataType: 'json',
		        success :function(item){
		        	if(item.assessId == null) {
		        		$('#myCommentBoxTitle').remove();
		        		$('#myCommentBox').remove();
		        	} else {
			        	$('#myCommentBox').html('');
			        	$('#commentInsertFrm').remove();
		        		var regDate = new Date(item.assessRegdate);

						var comment = $('<div />', {'class': 'comment number' + item.assessId + ' my-comment'});
						
						var scoreImg = $('<div />', {'class': 'score-img', 'style': 'width: ' + (item.assessStars * 10) + '%'});
						var scoreBg = $('<div />', {'class': 'score-bg'});
						scoreBg.append(scoreImg);
						var scoreText = $('<div />', {'class': 'score-text', 'text': item.assessStars});
						var ratingScore = $('<div />', {'class': 'rating-score'});
						ratingScore.append(scoreBg);
						ratingScore.append(scoreText);
						
						var nickname = $('<div />', {'class': 'nickname', 'text': item.memberNickname});
						var content = $('<div />', {'class': 'content', 'text': item.assessContent});
	
						var down = $('<div />', {'class': 'thumbs-down', 'click': function() {
							doLikeAjax(item.memberNumber, item.assessId, 'D');
							}});
						var up = $('<div />', {'class': 'thumbs-up', 'click': function() {
							doLikeAjax(item.memberNumber, item.assessId, 'L');
							}});
						down.html('<i class="fas fa-thumbs-down"></i> ' + item.hates);
						up.html('<i class="fas fa-thumbs-up"></i> ' + item.likes);
						
						var date = $('<div />', {'class': 'rating-date', 'text': regDate.getFullYear() + '-' + (regDate.getMonth() + 1)+ '-' + regDate.getDate()});
						var ratingDelete = $('<div />');
						if(memberNumber == item.memberNumber) {
							ratingDelete = $('<div />', {'class': 'rating-delete', 'text': 'delete', 'click': function() {
								if(confirm('Are you sure to delete?')) {
									location.href = contextPath + "/comment/delete.do?assessId=" + item.assessId + "&movieNumber=" + item.movieNumber;
								}
							}});
						}
						comment.append(nickname);
						comment.append(ratingScore);
						comment.append(content);
						comment.append(date);
						comment.append(ratingDelete);
						comment.append(up);
						comment.append(down);
						$('#myCommentBox').append(comment);
						
		        	}
		        }
	        });
	    });
	}
	
	function doLikeAjax(memberNumber, assessId, assessEst) {
		$.ajax({
	        url : contextPath + "/comment/insertLike.do",
	        type :"POST",
	        data :{ memberNumber: memberNumber, assessId: assessId, assessEst: assessEst },
	        dataType: 'text',
	        success :function(data){
	        	if(data == 0) {
	        		alert('This comment already to vote.')
	        	} else {
		        	$.ajax({
		    	        url : contextPath + "/comment/getOne.do",
		    	        type :"POST",
		    	        data :{ assessId: assessId },
		    	        dataType: 'json',
		    	        success :function(data2){
		    	        	$('.number' + data2.assessId + ' .thumbs-down').html('<i class="fas fa-thumbs-down"></i> ' + data2.hates);
		    	        	$('.number' + data2.assessId + ' .thumbs-up').html('<i class="fas fa-thumbs-up"></i> ' + data2.likes);
		            	}
		        	});
	        	}
        	}
    	});
    }
});