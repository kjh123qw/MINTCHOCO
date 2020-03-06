/**
 * 담당 : 장세진
 */
var modify_mode = 0;
var chkbox_count = 0;

function searchbar(element) {
	var m = element;
	var list = document.getElementsByClassName('con_menu_list')[0];
	var searchInput = document.getElementsByClassName('con_menu_input')[0];
	var searchBack = document.getElementsByClassName('con_menu_search')[0];
	
	if (element == 'on') {
		document.getElementsByClassName("con_menu_input")[0].focus();
		searchInput.style.display = 'block';
		searchBack.style.display = 'block';
		list.style.display = 'none';
	} else if (element == 'off') {
		document.getElementsByClassName("con_menu_input")[0].value = "";
		search();
		searchInput.style.display = 'none';
		searchBack.style.display = 'none';
		list.style.display = 'block';
	}
}

function search(){

    var input, search_target, item, i;

    input = document.getElementsByClassName("con_menu_input")[0].value.toUpperCase();
    item = document.getElementsByTagName("article");

    for(i=0;i<item.length;i++){
    	search_target = item[i].getElementsByClassName("con_search_target");
	    if(search_target[0].innerHTML.toUpperCase().indexOf(input) > -1){
	      item[i].style.display = "flex";
	    } else {
	      item[i].style.display = "none";
	    }
    }
}

function textAlign(element) {
	var m = element;
	var text = document.getElementsByClassName('menu_description_subject')[0];
	if (m == 'reg') {
		text.innerText='회원님이 등록한 날짜 순으로 정렬합니다.';	
	} else if(m == 'rel') {
		text.innerText='영화 개봉 날짜 순으로 정렬합니다.';
	} else if(m == 'sub') {
		text.innerText='영화 제목 순으로 정렬합니다.';
	} 
	
}

function modifyOn() {
	var modifyon = document.getElementsByClassName('con_menu_modifyon')[0];
	var modifyoff = document.getElementsByClassName('con_menu_modifyoff')[0];
	modifyon.style.display = 'block';
	modifyoff.style.display = 'none';
	modify_mode = 1;
}

function modifyOff() {
	var modifyon = document.getElementsByClassName('con_menu_modifyon')[0];
	var modifyoff = document.getElementsByClassName('con_menu_modifyoff')[0];
	var chkbox = document.getElementsByName("chkbox");
	var leng = chkbox.length;
    for (i=0; i<leng; i++) {
    	document.getElementsByName("chkbox")[i].checked = false;
    }
    chkbox_count = 0;
	modifyoff.style.display = 'block';
	modifyon.style.display = 'none';
	modify_mode = 0;

    var display_cnt = document.getElementsByClassName('con_chkbox_cnt')[0];
    display_cnt.innerText = '0';
}

function allcheck() {
	chkbox_count = 0;
	var chkbox = document.getElementsByName("chkbox");    
	var leng = chkbox.length;    
	
    for (i=0; i<leng; i++) {
    	if (document.getElementsByName("chkbox")[i].checked == true) {
    		chkbox_count++;
    	}
    }
    
    if (leng == chkbox_count) {
    	for (i=0; i<leng; i++) {
    		document.getElementsByName("chkbox")[i].checked = false;
    	}
    	
    	chkbox_count = 0;
    } else {
    	for (i=0; i<leng; i++) {
    		document.getElementsByName("chkbox")[i].checked = true;
    	}
    	chkbox_count = leng;
    }
    
    var display_cnt = document.getElementsByClassName('con_chkbox_cnt')[0];
    display_cnt.innerText = chkbox_count;
}

function chk_enable(element) {
	if (modify_mode == 0) {
		return;
	}
	var m = element;
	var assess = m.children[0];
	if (assess.checked == true) {
		chkbox_count--;
		assess.checked = false;
	} else {
    	chkbox_count++;
		assess.checked = true;
	}
	
    var display_cnt = document.getElementsByClassName('con_chkbox_cnt')[0];
    display_cnt.innerText = chkbox_count;
}

function chk_cnt() {
    var chkbox = document.getElementsByName("chkbox");
    var leng = chkbox.length;
    var checked = 0;
    for (i=0; i<leng; i++) {
        if (chkbox[i].checked == true) {
            checked += 1;
        }
    }
    if (checked<1) {
        alert("항목을 1개 이상 선택해주세요.");
        return false;
    }
}

function modal(element){
	var m = element;
	var inf = document.getElementsByClassName('con_conf_modifyINF_wrap')[0];
	var pwd = document.getElementsByClassName('con_conf_modifyPW_wrap')[0];
	var bg = document.getElementsByClassName('con_modal_bg')[0];
	
	if (m == 'INF') {
		inf.style.display = 'block';
		pwd.style.display = 'none';
		bg.style.display = 'block';
	} else if(m == 'PWD') {
		inf.style.display = 'none';
		pwd.style.display = 'block';
		bg.style.display = 'block';
	} else if(m == 'X') {
		inf.style.display = 'none';
		pwd.style.display = 'none';	
		bg.style.display = 'none';
	}
}