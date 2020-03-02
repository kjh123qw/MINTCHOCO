package com.kosmo.view.movie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.movie.MovieVO;

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
	@RequestMapping("/mov_list.do")
	public String movieList(MovieDAO movieDAO, Model model) {
		model.addAttribute("movieList", movieDAO.selectMovieList());
		
		return "mov_list.jsp";
	}
	
}
