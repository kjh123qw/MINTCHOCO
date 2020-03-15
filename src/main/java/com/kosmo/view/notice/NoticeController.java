package com.kosmo.view.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.mintchoco.notice.NoticeDAO;

/*
 * 담당자 : 박찬영, 김정호
 */


/* 작업 순서 
 * 1. 데이터베이스 테이블 보면서 NoticeVO 작성
 * 2. NoticeDAO 작성
 * 
 * */
@Controller
public class NoticeController {
	@RequestMapping(value="/service/notice.do", method=RequestMethod.GET)
	public String notice(NoticeDAO noticeDAO, Model model) {
		model.addAttribute("noticeList", noticeDAO.selectNoticeList());
		return "srv_notice.jsp";
	}
	@RequestMapping(value="/service/noticeForm.do", method=RequestMethod.GET)
	public String noticeForm(NoticeDAO noticeDAO, Model model) {
		return "srv_notice_form.jsp";
	}
	@RequestMapping(value="/service/insert.do", method=RequestMethod.POST)
	public String insertNotice(NoticeDAO noticeDAO, Model model) {
		return "redirect:/service/notice.do";
	}
}
