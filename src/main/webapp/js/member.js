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

function checkModify(){
	var btn = document.getElementsByClassName('st1-80-50')[0];
	var modifyINF = document.getElementsByClassName('con_conf_modifyINF_wrap')[0];
	var sfmodal = document.getElementsByClassName('con_modal_bg_check')[0];
	var successWindow = document.getElementsByClassName('con_conf_confirmed_ok')[0];
	var failWindow = document.getElementsByClassName('con_conf_confirmed_failed')[0];
	var nickname = document.getElementsByClassName('conf_modify_nickname')[0];
	var male = document.getElementsByClassName('conf_modify_male')[0];
	var female = document.getElementsByClassName('conf_modify_female')[0];
	var age = document.getElementsByClassName('conf_modify_age')[0];
	var text = document.getElementsByClassName('con_conf_status')[0];
	if(nickname.value==""){
		btn.disabled = true;
		sfmodal.style.display = 'block';
		failWindow.style.display = 'block';
		nickname.focus();
		text.innerText = "별명이 입력되지 않았습니다."; 
		hide('fail');
		return false;
	} else if(!male.checked && !female.checked){
		sfmodal.style.display = 'block';
		failWindow.style.display = 'block';
		text.innerText = "성별이 선택되지 않았습니다.";
		hide('fail');
		return false;
	} else if(age.value==""){
		sfmodal.style.display = 'block';
		failWindow.style.display = 'block';
		age.focus();
		text.innerText = "나이가 입력되지 않았습니다."; 
		hide('fail');
		return false;
	} else {
		successWindow.style.display = 'block'; 
		hide('success');
		return true;
	} 
}
function hide(element){
	var btn = document.getElementsByClassName('st1-80-50')[0];
	var sfmodal = document.getElementsByClassName('con_modal_bg_check')[0];
	var e = element;
	var result;
	if (e == 'fail') {
		result = document.getElementsByClassName('con_conf_confirmed_failed')[0]
	} else if (e == 'success') {
		result = document.getElementsByClassName('con_conf_confirmed_ok')[0]
	}
	setTimeout(function() {
		result.style.display = 'none';
		sfmodal.style.display = 'none';
		btn.disabled = false;
	},2000);
}
function age_count(element){
	var e = element;
	var age = document.getElementsByClassName('conf_modify_age')[0];
	if(e=='m') {
		age.value--;
		if (age.value <= 0) {
			age.value ='';
		}
	} else if (e=='p') {
		if (age.value <= 150) {
			age.value++;
		}
	}
}

function checkDigit2() {
	var age = document.getElementsByClassName('conf_modify_age')[0];
	if (age.value >150) {
		age.value='';
	}
}
function checkDigit(event){
	event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) { 
        return;
    } else {
    	return false;
    }
}

function rmInput(event){
	event = event || window.event;
    var keyID = (event.which) ? event.which : event.keyCode;
    if ( keyID==8 || keyID==46 || keyID==37 || keyID==39 ) {
    	return;	
    } else {
        event.target.value = event.target.value.replace(/[^0-9]/g, "");
    }
}