package com.kosmo.view.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.mintchoco.favorite.FavoriteDAO;
import com.kosmo.mintchoco.favorite.FavoriteVO;
import com.kosmo.mintchoco.member.MemberVO;
import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.movie.MovieVO;
import com.kosmo.mintchoco.rank.Crawling;
import com.kosmo.mintchoco.search.SearchDAO;
import com.kosmo.mintchoco.search.SearchVO;
import com.kosmo.mintchoco.tag.TagDAO;

/*
 * 담당자 : 천세문, 김정호
 */

@Controller
public class MovieController {
	
	List<SearchVO> mainMovieList = null;
	
	// 영화 정보 입력

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/main.do";
	}
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(SearchDAO searchDAO, Model model) {
		
		model.addAttribute("maindo", 1);
		mainMovieList = searchDAO.selectMovieList();
//		model.addAttribute("mainMovieList", searchDAO.selectMovieList()); //최신영화
		return "index.jsp";
	}
	
	@RequestMapping(value="/main/nextpage.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> nextPage(@RequestParam(required=true) int seq) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders responseHeaders = new HttpHeaders();  //헤더객체를 만들어서 
		int nowSeq = seq;
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //헤더정보 추가
		List<SearchVO> outputList =  new ArrayList<SearchVO>();
		if(mainMovieList != null) {
			for(int i = 0; i < 10; i++) {
				if(nowSeq < mainMovieList.size()) {
					outputList.add(mainMovieList.get(nowSeq));
					nowSeq += 1;
				} else {
					break;
				}
			}
		} else {
			outputList = null;
		}
		String returnString = mapper.writeValueAsString(outputList);
	    return new ResponseEntity<String>(returnString, responseHeaders, HttpStatus.CREATED);
	}

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
		
		model.addAttribute("movie", movieDAO.selectOneMovie(request.getParameter("movieNumber")));
		model.addAttribute("stars", movieDAO.viewRating(request.getParameter("movieNumber")));
		return "mov_detail.jsp";
	}
	
	// 찜 확인
	@RequestMapping(value="/movie/checkfv.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> movieFavorite(@RequestParam(required=true) int seq, FavoriteDAO favoriteDAO, HttpServletRequest request) throws Exception {
		String returnString = "";
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders responseHeaders = new HttpHeaders();  //헤더객체를 만들어서 
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //헤더정보 추가
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
		returnString = favoriteDAO.selectFavoriteOne(memberVO.getNumber(), Integer.toString(seq));
		return new ResponseEntity<String>(mapper.writeValueAsString(returnString), responseHeaders, HttpStatus.CREATED);
	}
	
	// 찜 목록 추가
	@RequestMapping(value="/movie/favoritePlus.do", method=RequestMethod.POST)
	@ResponseBody
	public String movieLike(@RequestParam(required=true) int seq, FavoriteDAO favoriteDAO, HttpServletRequest request) {
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
		favoriteDAO.insertFavorite(memberVO.getNumber(), Integer.toString(seq));
		
		return "OK";
	}
	
	// 찜 목록 취소
	@RequestMapping(value="/movie/favoriteMinus.do", method=RequestMethod.POST)
	@ResponseBody
	public String movieCancel(@RequestParam(required=true) int seq, FavoriteDAO favoriteDAO, HttpServletRequest request) {
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
		favoriteDAO.deleteFavorite(memberVO.getNumber(), Integer.toString(seq));
		
		return "OK";
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
