/**
 *  담당자 : 김정호
 */

$(function() {
	var contextPath = sessionStorage.getItem('contextPath');
	var totlaSearchCount;
	$('#searchMoreBtn').click(function() {
	    $.ajax({
	        url :"nextpage.do",
	        type :"POST",
	        data :{ pageNum: '한글1' },
	        dataType: 'json',
	        success :function(data){
	        	data.forEach(function(item, index) {
	        		
					
					var infoBox1 = $('<div />', {'class': 'search-info-ele', 'text': item.movieDate + ' | ' + item.movieTime + ' 분 | ' +item.movieGrade})
					var infoBox2 = $('<div />', {'class': 'search-info-ele', 'text': item.movieKind})
					var infoBox3 = $('<div />', {'class': 'search-info-ele', 'text': '감독 : ' + item.movieDirector})
					var infoBox4 = $('<div />', {'class': 'search-info-ele actor', 'text': item.movieActor})
					var searchInfoEleBox = $('<div />', {'class': 'search-info-ele-box'});
					searchInfoEleBox.append(infoBox1);
					searchInfoEleBox.append(infoBox2);
					searchInfoEleBox.append(infoBox3);
					searchInfoEleBox.append(infoBox4);
					
					var searchMovieImageImg = $('<img >', {'src': contextPath + '/images/mov_poster/' + item.moviePoster, 'alt': item.movieTitle + ' 포스터'});
					var searchMovieImageDiv = $('<div />', {'class': 'search-movie-image'});
					searchMovieImageDiv.append(searchMovieImageImg);

					var searchInfoBox = $('<div />', {'class': 'search-info-box'});
					searchInfoBox.append(searchMovieImageDiv);
					searchInfoBox.append(searchInfoEleBox);

					var searchImg = $('<div />', {'class': 'score-img', 'style': 'width: ' + (item.movieStars * 10) + '%'});
					var searchBg = $('<div />', {'class': 'score-bg'});
					searchBg.append(searchImg);
					
					var searchText = $('<div />', {'class': 'score-text', 'text': item.movieStars});
					var searchScore = $('<div />', {'class': 'search-score'});
					searchScore.append(searchBg);
					searchScore.append(searchText);
					
					var searchTitle = $('<div />', {'class': 'search-title', 'text': item.movieTitle});
					
					var searchMovieInnerboxDiv = $('<div />', {
						'class': 'search-movie-innerbox',
						'click': function() {
							location.href = contextPath + '/movie/detail.do?movieNumber=' + item.movieNumber;
						}
					});
					searchMovieInnerboxDiv.append(searchTitle);
					searchMovieInnerboxDiv.append(searchScore);
					searchMovieInnerboxDiv.append(searchInfoBox);
					
					var searchMovieBoxDiv = $('<div />', {'class': 'search-movie-box'});
					searchMovieBoxDiv.append(searchMovieInnerboxDiv);
					
					$("#searchResultBox").append(searchMovieBoxDiv);
	        	});
	        }
	    }).done(function() {
	    	$.ajax({
		        url :"nextpagecount.do",
		        type :"POST",
		        dataType: 'json',
		        success :function(data){
		        	var outputCount = 5;
		        	if(data.remainPageCount < 5)
		        		outputCount = data.remainPageCount;
					$("#searchMoreBtn").html("(" + (data.totalSearchCount - data.remainPageCount) + " / " + data.totalSearchCount + ") " + outputCount + " 건 더보기");
					if(data.remainPageCount == 0)
						$("#searchMoreBtn").hide();
		        }
	    	});
	    });
	});
});