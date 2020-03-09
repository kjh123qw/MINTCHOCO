package com.kosmo.view.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.movie.MovieVO;

/*
 * 담당자 : 천세문, 김정호
 */

/*

MOVIE_POSTER 
MOVIE_TEASER 
MOVIE_TITLE 
MOVIE_KIND 
MOVIE_DIRECTOR 
MOVIE_ACTOR 
MOVIE_GRADE 
MOVIE_TIME 
MOVIE_DATE 
MOVIE_YOUTUBE_URL 
MOVIE_NAVER_URL 
MOVIE_CONTENT 


*/

@Controller
public class MovieController {

	@RequestMapping(value = "/movie/insert.do", method = RequestMethod.POST)
	public String insert(MovieVO movieVO, MovieDAO movieDAO, Model model, HttpServletRequest request) {
		
		movieDAO.insertMovie(movieVO);
		
		return "redirect:/movie/recommand.do";
	}
	
	@RequestMapping("/movie/recommand.do")
	public String movieList(MovieDAO movieDAO, Model model) {
		model.addAttribute("movieList", movieDAO.selectMovieList());
		return "rec_list.jsp";
	}
	@RequestMapping("/movie/detail.do")
	public String movieDetail(MovieDAO movieDAO, Model model, HttpServletRequest request) {
		model.addAttribute("movie", movieDAO.selectOneMovie(request.getParameter("movieNumber")));
		return "mov_detail.jsp";
	}
	
	@RequestMapping(value = "/movie/update.do", method = RequestMethod.POST)
	public String update(HttpServletRequest request) {

		return "redirect:/movie/recommand.do";

	}
	@RequestMapping(value = "/movie/delete.do", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {

		return "redirect:/movie/recommand.do";

	}
	
	@RequestMapping("/movie/rank.do")
	public String rank() {
		return "mov_rank.jsp";
	}
	
}
