package com.kosmo.view.movie;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping("/movie/main.do")
	public String main() {
		return "main.jsp";
	}
	@RequestMapping("/movie/rank.do")
	public String rank() {
		return "mov_rank.jsp";
	}
	@RequestMapping("/movie/recommand.do")
	public String movieList(MovieDAO movieDAO, Model model) {
		model.addAttribute("movieList", movieDAO.selectMovieList());
		return "rec_list.jsp";
	}
	@RequestMapping("/movie/delete.do")
	public String movieDelete(MovieDAO movieDAO, Model model, HttpServletRequest request) {
		movieDAO.deleteMovie(request.getParameter("movieNumber"));
		return "rec_list.jsp";
	}
	@RequestMapping("/movie/detail.do")
	public String movieDetail(MovieDAO movieDAO, Model model, HttpServletRequest request) {
		model.addAttribute("movie", movieDAO.selectOneMovie(request.getParameter("movieNumber")));
		return "mov_detail.jsp";
	}
	
}
