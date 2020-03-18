/**
 * 
 */

$(function() {

	checkWidth();
    $(window).resize(function(){
    	checkWidth();
    })
	$('#cgvBtn').click(function() {
		$('#cgvBtn').attr('class','rank-header-cgv rank-header-selected');
		$('#megaBoxBtn').attr('class','rank-header-mega');
		$('#mobMegaList').hide();
		$('#mobCgvList').show();
	});
	$('#megaBoxBtn').click(function() {
		$('#cgvBtn').attr('class','rank-header-cgv');
		$('#megaBoxBtn').attr('class','rank-header-mega rank-header-selected');
		$('#mobCgvList').hide();
		$('#mobMegaList').show();
	});
	
	function checkWidth() {
    	if($(window).width() >= 992) {
    		$('#mobMovieList').hide();
    		$('#movieList').show();
    	} else {
    		$('#movieList').hide();
    		$('#mobMovieList').show();
    	}
	}
});