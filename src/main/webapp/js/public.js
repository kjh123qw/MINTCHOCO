/**
 *  담당자 : 김정호
 */

$(function(){
	$("input[class*=cst-chkbox-]").each((index, item) => {
		
		var trgClass = $(item).attr("class");
		var trgClassArr = trgClass.split("-");
		var trgId = trgClassArr[3];
		var tagColor = trgClassArr[2];
		var nextObj = $(item).next();
		var nextTagClassArr;
		var divChkBoxTextTag = "";
		
		if(nextObj.attr("class") != undefined) {
			nextTagClassArr = nextObj.attr("class").split("-");
			if(nextTagClassArr.length > 3) {
				if(nextTagClassArr[0] + "-" + nextTagClassArr[1] == "cst-chktext") {
					divChkBoxTextTag = "<div class='cst-chktext' style='width: " + nextTagClassArr[2] + "px'>" + nextObj.html() + "</div>";
					nextObj.remove();
				};
			}
		}
		
		$(item).attr("class", "cst-chkbox-" + tagColor + " " + trgId).attr("style", "display: none");
		$(item).after("<div class='cst-chkbox-wrap-" + trgId + "'><div class='cst-chkbox-" + tagColor + " cst-chkbox-" + trgId + "'></div>" + divChkBoxTextTag + "</div>");

		if($(item).is(':checked')) {
			$(".cst-chkbox-" + trgId).html("<i class='fas fa-check'></i>").css({
				"background-color":"#008a7b",
				"border":"1px solid #008a7b"
			});
		} else {
			if(tagColor == "dark")
				$(".cst-chkbox-" + trgId).html("").css({
					"background-color":"#555",
					"border":"1px solid #333"
				});
			else
				$(".cst-chkbox-" + trgId).html("").css({
					"background-color":"#ccc",
					"border":"1px solid #aaa"
				});
		}
		$(".cst-chkbox-wrap-" + trgId).children().bind("click", () => {
			if($(item).is(':checked')) {
				$(item).prop('checked', false);
				if(tagColor == "dark")
					$(".cst-chkbox-" + trgId).html("").css({
						"background-color":"#555",
						"border":"1px solid #333"
					});
				else
					$(".cst-chkbox-" + trgId).html("").css({
						"background-color":"#ccc",
						"border":"1px solid #aaa"
					});
			} else {
				$(item).prop('checked', true);
				$(".cst-chkbox-" + trgId).html("<i class='fas fa-check'></i>").css({
					"background-color":"#008a7b",
					"border":"1px solid #008a7b"
				});
			}
		});
	});
});