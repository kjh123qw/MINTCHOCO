/**
 *  담당자 : 김정호
 */

$(function() {
	var contextPath = sessionStorage.getItem('contextPath');
	var totlaSearchCount;
	let endPoint = false;
	nextPage(0);
	$('#moreBtn').click(function() {
		nextPage($("#mainMovieBox").children().length);
	});
	
	function nextPage(seq) {
		$.ajax({
	        url :"main/nextpage.do",
	        type :"POST",
	        data :{ seq: seq },
	        dataType: 'json',
	        success :function(data){
	        	if(data.length !== 10) {
	        		endPoint = true;
	        	}
	        	data.forEach(function(item, index) {
	        		
	        		var movie = $('<div />', {
	        			'class':'movie',
						'click': function() {
							location.href = contextPath + '/movie/detail.do?movieNumber=' + item.movieNumber;
						}
	        			})
					var imgBox = $('<div />', {'class': 'img-box'});
	        		var img = $('<img />', {'src': item.moviePoster});
					imgBox.append(img);
	        		movie.append(imgBox);

	        		var info = $('<div />', {'class': 'info'});
	        		var title = $('<h3 />', {'text': item.movieTitle});
	        		var dateGenre = $('<div />', {'class': 'date-genre', 'text': item.movieDate + ' | ' + item.movieKind});
					var scoreImg = $('<div />', {'class': 'score-img', 'style': 'width: ' + (item.movieStars * 10) + '%'});
					var scoreBg = $('<div />', {'class': 'score-bg'});
					var searchText = $('<div />', {'class': 'score-text', 'text': Math.round(item.movieStars * 10) / 10});
					var searchScore = $('<div />', {'class': 'search-score'});
					var summary = $('<div />', {'class': 'summary', 'text': item.movieContent.substring(0, 140) + ' ...'});
					scoreBg.append(scoreImg);
					searchScore.append(scoreBg);
					searchScore.append(searchText);
					info.append(title);
					info.append(dateGenre);
					info.append(searchScore);
					info.append(summary);
	        		movie.append(info);
	        		
					$("#mainMovieBox").append(movie);
	        		
	        	});
	        }
	    }).done(function() {
	    	if(endPoint) {
	    		$('#moreBtn').remove();
	    	}
	    });
	}
	
	
});