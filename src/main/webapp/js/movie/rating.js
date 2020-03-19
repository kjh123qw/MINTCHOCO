/**
 * 담당자: 김정호
 */

$(function() {
	var contextPath = sessionStorage.getItem('contextPath');
	var totlaSearchCount;
	var selectedMovieNumber = $('#selectedMovieNumber').val();
	var memberNumber = $('#memberNumber').val();
	doCommentAjax(1, selectedMovieNumber);
	
	$('#commonList').click(function() {
		$('#ratingCommentBox').show();
		$('#ratingPage').show();
		$('#namedCommentBox').hide();
		$('#writeCommentBox').hide();
		$('#commonList').attr('style', '');
		$('#writeComment').attr('style', '');
		$('#commonList').attr('class','rating-header-comment rating-header-selected');
		$('#namedList').attr('class','rating-header-named');
		$('#writeComment').attr('class','rating-header-write');
		doCommentAjax(1, selectedMovieNumber);
	});
	
	$('#namedList').click(function() {
		$('#ratingCommentBox').hide();
		$('#ratingPage').hide();
		$('#namedCommentBox').show();
		$('#writeCommentBox').hide();
		$('#commonList').css('border-right', '0');
		$('#writeComment').css('border-left', '0');
		$('#commonList').attr('class','rating-header-comment');
		$('#namedList').attr('class','rating-header-named rating-header-selected');
		$('#writeComment').attr('class','rating-header-write');
	});
	
	$('#writeComment').click(function() {
		$('#ratingCommentBox').hide();
		$('#ratingPage').hide();
		$('#namedCommentBox').hide();
		$('#writeCommentBox').show();
		$('#commonList').attr('style', '');
		$('#writeComment').attr('style', '');
		$('#commonList').attr('class','rating-header-comment');
		$('#namedList').attr('class','rating-header-named');
		$('#writeComment').attr('class','rating-header-write rating-header-selected');
	});
	
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
	
						var scoreImg = $('<div />', {'class': 'score-img', 'style': 'width: ' + (item.assessStars * 10) + '%'});
						var scoreBg = $('<div />', {'class': 'score-bg'});
						scoreBg.append(scoreImg);
						var scoreText = $('<div />', {'class': 'score-text', 'text': item.assessStars});
						var ratingScore = $('<div />', {'class': 'rating-score'});
						ratingScore.append(scoreBg);
						ratingScore.append(scoreText);
	
						var ratingNick = $('<div />', {'class': 'rating-nick', 'text': item.memberNickname, 'click': function() {
							location.href = contextPath + '/my_page.do?nickName=' + item.memberNickname;
						}});
						var ratingTop = $('<div />', {'class': 'rating-top'});
						ratingTop.append(ratingNick);
						ratingTop.append(ratingScore);
	
						var ratingComment = $('<div />', {'class': 'rating-comment', 'text': item.assessContent});
	
						var raitngDownBtn = $('<div />', {'class': 'raitng-down-btn', 'click': function() {
							doLikeAjax(4, item.assessId, 'D');
							}});
						var ratingUpBtn = $('<div />', {'class': 'rating-up-btn', 'click': function() {
							doLikeAjax(4, item.assessId, 'L');
							}});
						raitngDownBtn.html('<i class="fas fa-thumbs-down"></i> ' + item.hates);
						ratingUpBtn.html('<i class="fas fa-thumbs-up"></i> ' + item.likes);
						
						var ratingUd = $('<div />', {'class': 'rating-ud'});
						ratingUd.append(raitngDownBtn);
						ratingUd.append(ratingUpBtn);
						
						var ratingDate = $('<div />', {'class': 'rating-date', 'text': regDate.getFullYear() + '-' + (regDate.getMonth() + 1)+ '-' + regDate.getDate()});
						var ratingBottom = $('<div />', {'class': 'rating-bottom'});
						var ratingDelete = $('<div />', {'class': 'rating-delete', 'text': '삭제', 'click': function() {
							location.href = contextPath + "/comment/delete.do?assessId=" + item.assessId + "&movieNumber=" + item.movieNumber;
						}});
						ratingBottom.append(ratingDate);
						ratingBottom.append(ratingDelete);
						ratingBottom.append(ratingUd);
						
						var ratingInnerBox = $('<div />', {'class': 'rating-inner-box', 'id': 'commentNumber' + item.assessId});
						ratingInnerBox.append(ratingTop);
						ratingInnerBox.append(ratingComment);
						ratingInnerBox.append(ratingBottom);
						$('#ratingCommentBox').append(ratingInnerBox);
						
		        	});
	        	} else {
					var noComment = $('<div />', {'class': 'no-comment', 'text': '작성 된 평점이 없습니다.'});
					var ratingInnerBox = $('<div />', {'class': 'rating-inner-box'});
					ratingInnerBox.append(noComment);
					$('#ratingCommentBox').append(ratingInnerBox);
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
		    			location.href='#ratingWrap';
		    		}});
		    		if(pageNumber == 1)
		    			pageNumberFirst.attr('class','page-number page-number-selected');
		    		pageNumberFirst.html('&nbsp;&nbsp;' + 1 + '&nbsp;&nbsp;');
		    		$('#ratingPage').append(pageNumberFirst);
			    	for(var i = 0; i < totalPage; i++) {
			    		var pageNumberSpan = $('<span />', {'class': 'page-number', 'click': function() {
			    			doCommentAjax((i + 1), movieNumber);
			    			location.href='#ratingWrap';
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
		        success :function(data){
		        	if(data.assessId == null) {
		        		
		        	} else {
//			        	$('#writeCommentBox').html('<div class="no-comment">작성한 평점이 존재합니다.</div>');
			        	$('#myCommentBox').html('');
		        		var regDate = new Date(data.assessRegdate);
		        		
						var scoreImg = $('<div />', {'class': 'score-img', 'style': 'width: ' + (data.assessStars * 10) + '%'});
						var scoreBg = $('<div />', {'class': 'score-bg'});
						scoreBg.append(scoreImg);
						var scoreText = $('<div />', {'class': 'score-text', 'text': data.assessStars});
						var ratingScore = $('<div />', {'class': 'rating-score'});
						ratingScore.append(scoreBg);
						ratingScore.append(scoreText);
	
						var ratingNick = $('<div />', {'class': 'rating-nick', 'text': '내가 작성한 평점'});
						var ratingTop = $('<div />', {'class': 'rating-top'});
						ratingTop.append(ratingNick);
						ratingTop.append(ratingScore);
	
						var ratingComment = $('<div />', {'class': 'rating-comment', 'text': data.assessContent});
	
						var raitngDownBtn = $('<div />', {'class': 'raitng-down-btn', 'click': function() {
							doLikeAjax(4, data.assessId, 'D');
							}});
						var ratingUpBtn = $('<div />', {'class': 'rating-up-btn', 'click': function() {
							doLikeAjax(4, data.assessId, 'L');
							}});
						raitngDownBtn.html('<i class="fas fa-thumbs-down"></i> ' + data.hates);
						ratingUpBtn.html('<i class="fas fa-thumbs-up"></i> ' + data.likes);
						
						var ratingUd = $('<div />', {'class': 'rating-ud'});
						ratingUd.append(raitngDownBtn);
						ratingUd.append(ratingUpBtn);
						
						var ratingDate = $('<div />', {'class': 'rating-date', 'text': regDate.getFullYear() + '-' + (regDate.getMonth() + 1)+ '-' + regDate.getDate()});
						var ratingDelete = $('<div />', {'class': 'rating-delete', 'text': '삭제', 'click': function() {
							if(confirm('작성한 평가를 삭제하시겠습니까?')) {
								location.href = contextPath + "/comment/delete.do?assessId=" + data.assessId + "&movieNumber=" + data.movieNumber;
							}
						}});
						var ratingBottom = $('<div />', {'class': 'rating-bottom'});
						ratingBottom.append(ratingDate);
						ratingBottom.append(ratingDelete);
						ratingBottom.append(ratingUd);
						
	
						var ratingInnerBox = $('<div />', {'class': 'rating-inner-box my-comment-box', 'id': 'commentNumber' + data.assessId});
						ratingInnerBox.append(ratingTop);
						ratingInnerBox.append(ratingComment);
						ratingInnerBox.append(ratingBottom);
						$('#myCommentBox').append(ratingInnerBox);
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
	        		alert('이미 투표한 평가입니다.')
	        	} else {
		        	$.ajax({
		    	        url : contextPath + "/comment/getOne.do",
		    	        type :"POST",
		    	        data :{ assessId: assessId },
		    	        dataType: 'json',
		    	        success :function(data2){
							var raitngDownBtn = $('<div />', {'class': 'raitng-down-btn', 'click': function() {
								doLikeAjax(4, data2.assessId, 'D');
								}});
							var ratingUpBtn = $('<div />', {'class': 'rating-up-btn', 'click': function() {
								doLikeAjax(4, data2.assessId, 'L');
								}});
							raitngDownBtn.html('<i class="fas fa-thumbs-down"></i> ' + data2.hates);
							ratingUpBtn.html('<i class="fas fa-thumbs-up"></i> ' + data2.likes);
							
		    	        	$('#commentNumber' + data2.assessId + ' .rating-ud').html('')
							$('#commentNumber' + data2.assessId + ' .rating-ud').append(raitngDownBtn);
		    	        	$('#commentNumber' + data2.assessId + ' .rating-ud').append(ratingUpBtn);
		            	}
		        	});
	        	}
        	}
    	});
    }
});