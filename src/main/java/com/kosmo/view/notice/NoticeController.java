package com.kosmo.view.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String notice(NoticeDAO noticeDAO, Model model) {
		model.addAttribute("noticeList", noticeDAO.selectNoticeList());
		return "srv_notice_list.jsp";
	}
	
	@RequestMapping(value="/service/detail.do", method=RequestMethod.GET)
	public String detailNotice(NoticeDAO noticeDAO, Model model, HttpServletRequest request) {
		model.addAttribute("noticeVO", noticeDAO.selectNoticeOne(request.getParameter("noticeNumber")));
		return "srv_notice_detail.jsp";
	}

	@RequestMapping(value="/service/insert.do", method=RequestMethod.POST)
	public String insertNotice(NoticeVO noticeVO, NoticeDAO noticeDAO, HttpServletRequest request) {
		System.out.println(noticeVO.getNoticeTitle());
		noticeDAO.insertNotice(noticeVO);
		return "redirect:/service/notice.do";
	}
	
	@RequestMapping(value="/service/insert.do", method=RequestMethod.GET)
	public String noticeForm(NoticeDAO noticeDAO, Model model) {
		return "srv_notice_form.jsp";
	}
	
	@RequestMapping(value="/service/modify.do", method=RequestMethod.GET)
	public String modifyNotice(NoticeDAO noticeDAO, Model model, HttpServletRequest request) {
		model.addAttribute("noticeVO", noticeDAO.selectNoticeOne(request.getParameter("noticeNumber")));
		return "srv_notice_modify.jsp";
	}
	
	@RequestMapping(value="/service/modify.do", method=RequestMethod.POST)
	public String updateNotice(NoticeVO noticeVO, NoticeDAO noticeDAO, Model model) {
		noticeDAO.updateNotice(noticeVO);
		return "redirect:/service/notice.do";
	}
}
