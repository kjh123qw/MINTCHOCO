package com.kosmo.view.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kosmo.mintchoco.favorite.FavoriteDAO;
import com.kosmo.mintchoco.favorite.FavoriteVO;
import com.kosmo.mintchoco.member.MemberVO;
import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.movie.MovieVO;
import com.kosmo.mintchoco.rank.Crawling;
import com.kosmo.mintchoco.tag.TagDAO;

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
		
		return "redirect:/movie/recommend.do";
	}
	
	// 주제별 영화 목록
	
	@RequestMapping("/movie/recommend.do")
	public String movieList(MovieDAO movieDAO, Model model, HttpServletRequest request) {
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
		
		model.addAttribute("movieList", movieDAO.selectMovieList());
		model.addAttribute("user", memberVO);
		
		return "rec_list.jsp";
	}
	
	// 영화 상세 정보
	
	@RequestMapping("/movie/detail.do")
	public String movieDetail(MovieDAO movieDAO, TagDAO tagDAO, Model model, HttpServletRequest request) {
		
		MemberVO memberVO = null;
		if(request.getSession().getAttribute("memberInfo") != null) {
			memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
			model.addAttribute("movie", movieDAO.selectOneMovie(request.getParameter("movieNumber")));
			model.addAttribute("tagList", tagDAO.selectTagList(request.getParameter("movieNumber")));
			model.addAttribute("stars", movieDAO.viewRating(request.getParameter("movieNumber")));
			model.addAttribute("checkFavorite", movieDAO.checkFavorite(memberVO.getNumber(), request.getParameter("movieNumber")));
			model.addAttribute("user", memberVO);
			return "mov_detail.jsp";
		} else {
			return "redirect:/main.do";
		}
	}
	
	// 찜 목록 추가

	@RequestMapping("/movie/favoritePlus.do")
	public String movieDetail(FavoriteVO favoriteVO, FavoriteDAO favoriteDAO, Model model, HttpServletRequest request) {
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
		favoriteDAO.insertFavorite(memberVO.getNumber(), request.getParameter("movieNumber"));
		
		return "redirect:/movie/detail.do?movieNumber=" + request.getParameter("movieNumber");
	}
	
	// 찜 목록 취소
	
	@RequestMapping("/movie/favoriteMinus.do")
	public String movieDetail(FavoriteVO favoriteVO, FavoriteDAO favoriteDAO, HttpServletRequest request) {

		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
		favoriteDAO.deleteFavorite(memberVO.getNumber(), request.getParameter("movieNumber"));
		
		return "redirect:/movie/detail.do?movieNumber=" + request.getParameter("movieNumber");
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
		
		return "redirect:/movie/recommend.do";

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

		return "redirect:/movie/recommend.do";

	}
	
	// 영화 순위
	@RequestMapping("/rank.do")
	public String rank(Model model) {
		
		HashMap<Integer, String[]> megaRankList = Crawling.MegaBoxMovieRank();
		List<MovieVO> megaMovieList = new ArrayList<MovieVO>();
		HashMap<Integer, String[]> cgvRankList = Crawling.CGVMovieRank();
		List<MovieVO> cgvMovieList = new ArrayList<MovieVO>();
		
		for(Integer key : megaRankList.keySet())
		{
			MovieVO movie = new MovieVO();
			movie.setMovieTitle(megaRankList.get(key)[0]);
			movie.setMoviePoster(megaRankList.get(key)[1]);
			megaMovieList.add(movie);
		}
		for(Integer key : cgvRankList.keySet())
		{
			MovieVO movie = new MovieVO();
			movie.setMovieTitle(cgvRankList.get(key)[0]);
			movie.setMoviePoster(cgvRankList.get(key)[1]);
			cgvMovieList.add(movie);
		}
		

		model.addAttribute("cgvMovieList", cgvMovieList);
		model.addAttribute("megaMovieList", megaMovieList);
		return "/movie/mov_rank.jsp";
	}
	
}
