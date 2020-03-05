package com.kosmo.view.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 담당자 : 박찬영, 김정호
 */
@Controller
public class NoticeController {
	@RequestMapping("/movie/search.do")
	public String search() {
		
		return "search_list.jsp;";
	}
}
