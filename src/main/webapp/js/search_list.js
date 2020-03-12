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
					var searchMovieImageImg = $('<img >', {'src': contextPath + '/images/mov_poster/' + item.moviePoster, 'alt': item.movieTitle + ' 포스터'});
					var searchMovieImageDiv = $('<div />', {'class': 'search-movie-image'});
					searchMovieImageDiv.append(searchMovieImageImg);
					
					var infoTitleBox = $('<div />', {'class': 'search-info-ele title', 'text': item.movieTitle})
					var infoBox1 = $('<div />', {'class': 'search-info-ele', 'text': item.movieDate + ' | ' + item.movieTime + ' 분 | ' +item.movieGrade})
					var infoBox2 = $('<div />', {'class': 'search-info-ele', 'text': item.movieKind})
					var infoBox3 = $('<div />', {'class': 'search-info-ele', 'text': item.movieActor})
					var searchInfoBox = $('<div />', {'class': 'search-info-box'});
					searchInfoBox.append(infoTitleBox);
					searchInfoBox.append(infoBox1);
					searchInfoBox.append(infoBox2);
					searchInfoBox.append(infoBox3);
					
					var searchMovieInnerboxDiv = $('<div />', {
						'class': 'search-movie-innerbox',
						'click': function() {
							location.href = contextPath + '/movie/detail.do?movieNumber=' + item.movieNumber;
						}
					});
					searchMovieInnerboxDiv.append(searchMovieImageDiv);
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