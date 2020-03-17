/**
 *  담당자 : 김정호
 */

$(function() {
	var contextPath = sessionStorage.getItem('contextPath');
	var selectedBg = '#008a7b';
	var selectedFontColor = '#fff';
	var normalBg = '#ddd';
	var normalBorder = '#bbb';
	var normalFontColor = '#666';
	var clearSearchFormTime;
	var clearMouseOverTime;
	var tooltipWrap = $('<div />', {'id':'tooltipWrap'});
	var tooltipBox = $('<div />', {'id':'tooltipBox'});
	$(tooltipWrap).append(tooltipBox);
	
    $(window).resize(function(){
		$('#mobMenuWrap').hide();
		$('#mobSearchWrap').hide();
    })
    
	$('#mobShowSearchBtn').click(function() {
		$('#mobSearchWrap').show();
		$('#mobSearchWrap input[type=text]').focus();
	});
	$('#blackBackGround').click(function() {
		$('#mobSearchWrap').hide();
	});
	$('#mobMenuBtn').click(function() {
		$('#mobMenuWrap').show();
	});
	$('#mobMenuWrap > div > div').click(function() {
		$('#mobMenuWrap').hide();
	});
	$('#showSearchBtn').click(function() {
		$('#showSearchBtn').hide();
		$('#searchWrap').show();
		$('#searchWrap input[type=text]').focus();
	});
	$('#searchWrap input[type=text]').blur(function() {
		clearSearchFormTime = setTimeout(function() {
			$('#searchWrap').hide();
			$('#showSearchBtn').show();
		}, 800);
	});
	$('#searchWrap input[type=text]').focus(function() {
		clearTimeout(clearSearchFormTime);
	});
	$('#showTagBtn').click(function() {
    	$('#searchMoreTagListBox').html('');
		if($('#showTagBtnUp').css('display') == 'none') {
			$('#showTagBtnDown').hide();
			$('#showTagBtnUp').show();
			$('#mobShowTagBtnDown').hide();
			$('#mobShowTagBtnUp').show();
			$('#tagWrap').show();
			$('#searchTagText').html('');
			doTagListAjax();
		} else {
			$('#showTagBtnUp').hide();
			$('#showTagBtnDown').show();
			$('#mobShowTagBtnUp').hide();
			$('#mobShowTagBtnDown').show();
			$('#tagWrap').hide();
			$('#moreMinus').hide();
			$('#morePlus').show();
		}
	});
	$('#mobShowTagBtn').click(function() {
    	$('#searchMoreTagListBox').html('');
		if($('#mobShowTagBtnUp').css('display') == 'none') {
			$('#showTagBtnDown').hide();
			$('#showTagBtnUp').show();
			$('#mobShowTagBtnDown').hide();
			$('#mobShowTagBtnUp').show();
			$('#tagWrap').show();
			$('#searchTagText').html('');
			doTagListAjax();
		} else {
			$('#showTagBtnUp').hide();
			$('#showTagBtnDown').show();
			$('#mobShowTagBtnUp').hide();
			$('#mobShowTagBtnDown').show();
			$('#tagWrap').hide();
			$('#moreMinus').hide();
			$('#morePlus').show();
		}
		
	});
	$('#moreTag').click(function() {
		if($('#moreMinus').css('display') == 'none') {
			$('#morePlus').hide();
			$('#moreMinus').show();
			doMoreTagListAjax();
		} else {
			$('#moreMinus').hide();
			$('#morePlus').show();
			$('#searchMoreTagListBox').html('');
		}
		tagListOutput();
	});
	$('#searchTagListBox').click(function() {
		tagListOutput();
	});
	$('#searchMoreTagListBox').click(function() {
		tagListOutput();
	});

	checkBoxTag($('div[class*=cst-chkbox-st]'));
	radioTag();
	textBoxTag();
	buttonTag();
	
	function doTagListAjax() {
		$.ajax({
	        url : contextPath + "/tag/list.do",
	        type :"GET",
	        dataType: 'json',
	        success :function(data){
	        	$('#searchTagListBox').html('');
	        	var checkBoxWrap = $('<div />', {'class': 'cst-chkbox-st2'});
	        	if(data.length > 0) {
		        	data.forEach(function(item, index) {
		        		var checkBox = $('<input>', {'type': 'checkbox', 'name': 'selectedTags', 'value': item.tagContent});
		        		var label = $('<span>', {'class': 'text-100', 'text': item.tagContent});
		        		
		        		checkBoxWrap.append(checkBox);
		        		checkBoxWrap.append(label);
		        	});
		        	$('#searchTagListBox').append(checkBoxWrap);
		        	checkBoxTag($('#searchTagListBox div[class*=cst-chkbox-st]'));
	        	}
	        }
        }).then(function() {
        	mouseOverUse();
        });
	}
	
	function doMoreTagListAjax() {
		$.ajax({
	        url : contextPath + "/tag/list.do",
	        type :"GET",
	        data : {type: 0},
	        dataType: 'json',
	        success :function(data){
	        	$('#searchMoreTagListBox').html('');
	        	var checkBoxWrap = $('<div />', {'class': 'cst-chkbox-st2'});
	        	if(data.length > 0) {
		        	data.forEach(function(item, index) {
		        		var checkBox = $('<input>', {'type': 'checkbox', 'name': 'selectedTags', 'value': item.tagContent});
		        		var label = $('<span>', {'class': 'text-100', 'text': item.tagContent});
		        		
		        		checkBoxWrap.append(checkBox);
		        		checkBoxWrap.append(label);
		        	});
		        	$('#searchMoreTagListBox').append(checkBoxWrap);
		        	checkBoxTag($('#searchMoreTagListBox div[class*=cst-chkbox-st]'));
	        	}
	        }
        }).then(function() {
        	mouseOverUse();
        });
	}
	
	function mouseOverUse() {
		var targetList = $('#searchTagListBox .cst-chkbox-wrap-st2').toArray();
		targetList.forEach(function(item, index) {
			$(item).attr('title', $(item).children('.cst-chkbox-text').html());
//			$(item).mouseout(function() {
//				clearMouseOverTime = setTimeout(function() {
//					$(tooltipWrap).hide();
//				}, 100);
//			});
//			$(item).mouseover(function() {
//				$(item).append(tooltipWrap);
//				$(tooltipWrap).show();
//				clearTimeout(clearMouseOverTime);
//			});
		});
	}
	
	
	function tagListOutput() {
		$('#searchTagText').html('');
		var checkBoxList = $('#searchTagListBox input').toArray();
		var checkMoreBoxList = $('#searchMoreTagListBox input').toArray();
		checkBoxList.forEach(function(item, index) {
			if($(item).is(":checked")) {
				$('#searchTagText').append("#");
				$('#searchTagText').append($(item).val());
				$('#searchTagText').append(" ");
			}
    	});
		checkMoreBoxList.forEach(function(item, index) {
			if($(item).is(":checked")) {
				$('#searchTagText').append("#");
				$('#searchTagText').append($(item).val());
				$('#searchTagText').append(" ");
			}
    	});
	}

	
	//checkbox tag
	function checkBoxTag(chkboxArr) {
		$(chkboxArr).each(function(index, item) {
			var tagStyle = $(item).attr('class').split('-')[2];
			
			$(item).children('input').each(function(indexInp, itemInp) {
				var textObj = $(itemInp).next();
				var textClassArr;
				var textHtml;
			
				var tagWrap = $('<div />', {
					'class': 'cst-chkbox-wrap-' + tagStyle
				});
				
				var cstBoxHtml = $('<div />', {
					'class': 'cst-chkbox-box',
					'click': function() {
						if($(itemInp).is(':checked')) {
							$(itemInp).prop('checked', false);
							$(this).html('').css({
								'background-color':normalBg,
								'border':'1px solid ' + normalBorder
							});
						} else {
							$(itemInp).prop('checked', true);
							$(this).html("<i class='fas fa-check'></i>").css({
								'background-color':selectedBg,
								'border':'1px solid ' + selectedBg
							});
						}
					}
				});
				
				if(textObj.prop('tagName') == 'SPAN') {
					if(textObj.attr('class') != undefined) {
						textClassArr = textObj.attr('class').split('-');
						if(textClassArr.length == 2) {
							textHtml = $('<div />', {
								'class': 'cst-chkbox-text',
								'style': 'width: ' + textClassArr[1] + 'px',
								'text': textObj.html(),
								'click': function() {
									if($(itemInp).is(':checked')) {
										$(itemInp).prop('checked', false);
										$(cstBoxHtml).html('').css({
											'background-color':normalBg,
											'border':'1px solid ' + normalBorder
										});
									} else {
										$(itemInp).prop('checked', true);
										$(cstBoxHtml).html("<i class='fas fa-check'></i>").css({
											'background-color':selectedBg,
											'border':'1px solid ' + selectedBg
										});
									}
								}
							});
							textObj.remove();
						}
					}
				}
				$(itemInp).attr('style', 'display: none');
				$(item).append(tagWrap);
				tagWrap.append(cstBoxHtml).append(textHtml);
				if($(itemInp).is(':checked')) {
					$(cstBoxHtml).html("<i class='fas fa-check'></i>").css({
						'background-color':selectedBg,
						'border':'1px solid ' + selectedBg
					});
				} else {
					$(cstBoxHtml).html('').css({
						'background-color':normalBg,
						'border':'1px solid ' + normalBorder
					});
				}
			});
		});
	}
	//radio tag
	function radioTag() {
		$('div[class*=cst-radio-st]').each(function(index, item) {
			var tagStyle = $(item).attr('class').split('-')[2];
			var inputArr = $(item).children('input');
			var width = 0;
			
			var checkBtn = function() {  // check radio btn
				$(inputArr).each(function(idx, ipt) {
					if($(ipt).is(':checked')) {
						$(ipt.divBox).css({
							'width':width,
							'background-color':selectedBg,
							'border':'1px solid ' + selectedBg,
							'color':selectedFontColor,
							'font-weight':'bold'
						});
					} else {
						$(ipt.divBox).css({
							'width':width,
							'background-color':normalBg,
							'border':'',
							'color':normalFontColor,
							'font-weight':''
						});
					}
				})
			}
			
			$(inputArr).each(function(indexInp, itemInp) {
				var textObj = $(itemInp).next();
				var textClassArr;
				var textHtml;
				
				var cstBoxHtml = $('<div />', {
					'class': 'cst-radio-box',
					'click': function() {
						if(!$(itemInp).is(':checked'))
							$(itemInp).prop('checked', true);
						checkBtn();
					}
				});
				
				itemInp.divBox = cstBoxHtml;
				
				if(textObj.prop('tagName') == 'SPAN') {
					if(textObj.attr('class') != undefined) {
						textClassArr = textObj.attr('class').split('-');
						if(textClassArr.length == 2) {
							cstBoxHtml.append(textObj.html());
							textObj.remove();
							width = textClassArr[1]
						}
					}
				}
				$(itemInp).attr('style', 'display: none');
				$(item).append(cstBoxHtml);
				if($(itemInp).is(':checked')) {
					$(cstBoxHtml).css({
						'width':width,
						'background-color':selectedBg,
						'border':'1px solid ' + selectedBg,
						'color':selectedFontColor,
						'font-weight':'bold'
					});
				} else {
					$(cstBoxHtml).css({
						'width':width,
						'background-color':normalBg,
						'border':'',
						'color':normalFontColor,
						'font-weight':''
					});
				}
			});
		});
	}
	//input text, password tag
	function textBoxTag() {
		$('div[class*=cst-text-st]').each(function(index, item) {
			var tagStyle = $(item).attr('class').split('-')[2];
			
			$(item).children('input').each(function(indexInp, itemInp) {
				var textObj = $(itemInp).next();
				var textWidth = $(itemInp).attr('class').split('-')[1];
				var textClassArr;
				var textHtml;
			
				var tagWrap = $('<div />', {
					'class': 'cst-text-wrap-' + tagStyle
				});
				
				var cstBoxHtml = $('<div />', {
					'class': 'cst-text-box',
					'style': 'width: ' + textWidth + 'px'
				});
				if(textObj.prop('tagName') == 'SPAN') {
					if(textObj.attr('class') != undefined) {
						textClassArr = textObj.attr('class').split('-');
						if(textClassArr.length == 2) {
							textHtml = $('<div />', {
								'class': 'cst-text-label',
								'style': 'width: ' + textClassArr[1] + 'px',
								'text': textObj.html(),
								'click': function() {
									$(itemInp).focus();
								}
							});
							textObj.remove();
						}
					}
				}
				$(itemInp).css({'width': textWidth + 'px'});
				$(item).append(tagWrap);
				tagWrap.append(textHtml).append(cstBoxHtml);
				cstBoxHtml.append(itemInp);
				cstBoxHtml.append('<div></div>')
				$(itemInp).focus(function() {
					$(cstBoxHtml).children('div').stop().animate({
						'background-color': 'rgba(0, 50, 50, 0.9)',
						'width': '100%'
					}, 150);
					$(cstBoxHtml).children('input').stop().animate({
						'background-color': 'rgba(255, 255, 255, 1)',
						'width': '100%'
					}, 150);
				})
				$(itemInp).blur(function() {
					$(cstBoxHtml).children('div').stop().animate({
						'background-color': '#000',
						'width': '0'
					}, 150);
					$(cstBoxHtml).children('input').stop().animate({
						'background-color': 'rgba(230, 230, 230, 0.7)',
						'width': '100%'
					}, 150);
				});
			});
		});
	}
	//submit or button
	function buttonTag() {
		$('div[class*=cst-btn]').each(function(index, item) {
			$(item).children('input').each(function(indexInp, itemInp) {
				var trgClassArr = $(itemInp).attr('class').split('-');
				var tagStyle = trgClassArr[0];
				var tagWidth = parseInt(trgClassArr[1]);
				var tagHeight = parseInt(trgClassArr[2]);
				var textVal = $(itemInp).val();
			
				var tagWrap = $('<div />', {
					'class': 'cst-btn-wrap-' + tagStyle,
					'style': 'width: ' + tagWidth + 'px; height: ' + tagHeight + 'px;'
				});
				var firstBg = $('<div />', {
					'style': 'width: ' + tagWidth + 'px; height: 0px;'
				});
				var secondBg = $('<div />', {
					'style': 'width: ' + tagWidth + 'px; height: ' + tagHeight + 'px;'
				});
				
				$(itemInp).css({'width':tagWidth + 'px', 'height':tagHeight + 'px'});
				$(item).append(tagWrap);
				$(tagWrap).append(itemInp).append(firstBg).append(secondBg);
				
				$(itemInp).mouseover(function() {
					$(tagWrap).children('div:first-of-type').stop().animate({
						'height': tagHeight + 'px'
					}, 150);
					$(tagWrap).children('div:last-of-type').stop().animate({
						'height': '0px'
					}, 150);
				});
				$(itemInp).mouseout(function() {
					$(tagWrap).children('div:first-of-type').stop().animate({
						'height': '0px'
					}, 150);
					$(tagWrap).children('div:last-of-type').stop().animate({
						'height': tagHeight + 'px'
					}, 150);
				});
			});
		});
	}
});

