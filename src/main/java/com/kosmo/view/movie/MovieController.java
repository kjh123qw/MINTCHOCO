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

	// 영화 정보 입력
	@RequestMapping(value = "/movie/insert.do", method = RequestMethod.POST)
	public String insert(MovieVO movieVO, MovieDAO movieDAO, HttpServletRequest request) {
		
		movieDAO.insertMovie(movieVO);
		
		return "redirect:/movie/recommand.do";
	}
	
	// 주제별 영화 목록
	@RequestMapping("/movie/recommand.do")
	public String movieList(MovieDAO movieDAO, Model model) {
		model.addAttribute("movieList", movieDAO.selectMovieList());
		return "rec_list.jsp";
	}
	
	// 영화 상세 정보
	@RequestMapping("/movie/detail.do")
	public String movieDetail(MovieDAO movieDAO, Model model, HttpServletRequest request) {
		model.addAttribute("movie", movieDAO.selectOneMovie(request.getParameter("movieNumber")));
		return "mov_detail.jsp";
	}
	
	// 영화 정보 수정
	@RequestMapping(value = "/movie/update.do", method = RequestMethod.POST)
	public String update(MovieVO movieVO, MovieDAO movieDAO, HttpServletRequest request) {
		
		movieDAO.updateMovie(movieVO);
		
		return "redirect:/movie/recommand.do";

	}
	
	// 기존 영화 삭제
	@RequestMapping(value = "/movie/delete.do", method = RequestMethod.POST)
	public String delete(MovieDAO movieDAO, Model model, HttpServletRequest request) {

		if(request.getParameter("movieNumber").equals(""))
			movieDAO.deleteMovie1(request.getParameter("movieNumber"));
		if(request.getParameter("movieTitle").equals(""))
			movieDAO.deleteMovie2(request.getParameter("movieTitle"));
		if(request.getParameter("movieDirector").equals(""))
			movieDAO.deleteMovie3(request.getParameter("movieDirector"));
		if(request.getParameter("movieActor").equals(""))
			movieDAO.deleteMovie4(request.getParameter("movieActor"));

		return "redirect:/movie/recommand.do";

	}
	
	// 영화 순위
	@RequestMapping("/movie/rank.do")
	public String rank() {
		return "mov_rank.jsp";
	}
	
}
