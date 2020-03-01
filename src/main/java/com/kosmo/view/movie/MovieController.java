package com.kosmo.view.movie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 담당자 : 천세문, 김정호
 */
@Controller
public class MovieController {

	@RequestMapping("/main.do")
	public String main() {
		return "main.jsp";
	}
	@RequestMapping("/rank.do")
	public String rank() {
		return "mov_rank.jsp";
	}
	@RequestMapping("/movie-list.do")
	public String movieList() {
		return "mov_list.jsp";
	}
	
}
