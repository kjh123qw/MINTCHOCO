package com.kosmo.view.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.movie.MovieVO;
import com.kosmo.mintchoco.rank.Crawling;

/*
 * 담당자 : 천세문, 김정호
 */

@Controller
public class MovieController {
	
	// 영화 정보 입력
	
	@RequestMapping("/movie/insertForm.do")
	public String insertForm() {
		return "mov_insert.jsp";
	}
	
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
	
	@RequestMapping("/movie/updateForm.do")
	public String updateForm(MovieDAO movieDAO, Model model, HttpServletRequest request) {
		
		model.addAttribute("movie", movieDAO.selectOneMovie(request.getParameter("movieNumber")));
		
		return "mov_update.jsp";
	}
	
	@RequestMapping(value = "/movie/update.do", method = RequestMethod.POST)
	public String update(MovieVO movieVO, MovieDAO movieDAO, HttpServletRequest request) {
		
		movieDAO.updateMovie(movieVO);
		
		return "redirect:/movie/recommand.do";

	}
	
	// 기존 영화 삭제
	
	@RequestMapping("/movie/deleteForm.do")
	public String deleteForm(MovieDAO movieDAO, Model model, HttpServletRequest request) {
		
		model.addAttribute("movie", movieDAO.selectOneMovie(request.getParameter("movieNumber")));
		
		return "mov_delete.jsp";
	}
	
	@RequestMapping(value = "/movie/delete.do", method = RequestMethod.POST)
	public String delete(MovieDAO movieDAO, Model model, HttpServletRequest request) {

		movieDAO.deleteMovie(request.getParameter("movieNumber"));

		return "redirect:/movie/recommand.do";

	}
	
	// 영화 순위
	@RequestMapping("/rank.do")
	public String rank(Model model) {
		
		HashMap<Integer, String[]> RankList = Crawling.MegaBoxMovieRank();
		List<MovieVO> movieList = new ArrayList<MovieVO>();
		
		for(Integer key : RankList.keySet())
		{
			MovieVO movie = new MovieVO();
			movie.setMovieTitle(RankList.get(key)[0]);
			movie.setMoviePoster(RankList.get(key)[1]);
			movieList.add(movie);
		}
		
		model.addAttribute("movieList", movieList);
		return "/movie/mov_rank.jsp";
	}
	
}
