package com.kosmo.view.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kosmo.mintchoco.notice.NoticeDAO;
import com.kosmo.mintchoco.notice.NoticeVO;

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
	public String notice(@RequestParam(value="noticeSearch", defaultValue="nosearchkeyword") String noticeSearch, NoticeDAO noticeDAO, Model model) {
		if(noticeSearch.equals("nosearchkeyword") || noticeSearch.equals(""))
			model.addAttribute("noticeList", noticeDAO.selectNoticeList());
		else
			model.addAttribute("noticeList", noticeDAO.selectSearchNoticeList(noticeSearch));
		return "srv_notice_list.jsp";
	}
	
	@RequestMapping(value="/service/detail.do", method=RequestMethod.GET)
	public String detailNotice(@RequestParam("noticeNumber") String noticeNumber, NoticeDAO noticeDAO, Model model) {
		model.addAttribute("noticeVO", noticeDAO.selectNoticeOne(noticeNumber));
		return "srv_notice_detail.jsp";
	}

	@RequestMapping(value="/service/insert.do", method=RequestMethod.POST)
	public String insertNotice(NoticeVO noticeVO, NoticeDAO noticeDAO) {
		noticeDAO.insertNotice(noticeVO);
		return "redirect:/service/notice.do";
	}
	
	@RequestMapping(value="/service/insert.do", method=RequestMethod.GET)
	public String noticeForm() {
		return "srv_notice_form.jsp";
	}
	
	@RequestMapping(value="/service/modify.do", method=RequestMethod.GET)
	public String modifyNotice(@RequestParam(value="noticeNumber", defaultValue="1") String noticeNumber, NoticeDAO noticeDAO, Model model) {
		model.addAttribute("noticeVO", noticeDAO.selectNoticeOne(noticeNumber));
		return "srv_notice_modify.jsp";
	}
	
	@RequestMapping(value="/service/modify.do", method=RequestMethod.POST)
	public String updateNotice(NoticeVO noticeVO, NoticeDAO noticeDAO) {
		noticeDAO.updateNotice(noticeVO);
		return "redirect:/service/notice.do";
	}
	
	@RequestMapping(value="/service/delete.do", method=RequestMethod.GET)
	public String deleteNotice(@RequestParam("noticeNumber") String noticeNumber, NoticeDAO noticeDAO) {
		noticeDAO.deleteNotice(noticeNumber);
		return "redirect:/service/notice.do";
	}
}
