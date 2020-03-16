/**
 * 담당 : 장세진
 */

function domenu(element) {
	var e = element;
	var menu = document.getElementsByClassName('hd-hidden-menu')[0];
	if(e=="on") {
		menu.style.display = 'block';
	} else if(e=="off") {
		menu.style.display = 'none';
	}
}
