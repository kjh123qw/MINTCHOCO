package com.kosmo.view.search;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.movie.MovieVO;
import com.kosmo.mintchoco.search.SearchDAO;
import com.kosmo.mintchoco.search.SearchVO;

/*
 * 담당자 : 김정호
 */
@Controller
public class SearchController {
	
	List<SearchVO> trgSearchList = null;
	List<MovieVO> trgMovieList = null;
	
	@RequestMapping("/movie/search.do")
	public String search(SearchDAO searchDAO, MovieDAO movieDAO, Model model, HttpServletRequest request) {
		if(request.getParameter("searchKeyWord") == null || request.getParameter("searchKeyWord").equals("")) {
			trgMovieList = movieDAO.selectMovieList();
			model.addAttribute("searchKeyWord", "전체 영화");
			model.addAttribute("allMovieList", trgMovieList);
		} else {
			trgSearchList = searchDAO.searchMovieList(request.getParameter("searchKeyWord"));
			model.addAttribute("searchKeyWord", request.getParameter("searchKeyWord"));
			model.addAttribute("searchMovieList", trgSearchList);
		}
		return "search_list.jsp";
	}

	@RequestMapping(value="/movie/nextpage.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public String nextPage(@RequestParam(required=true) String pageNum, HttpServletResponse response) throws Exception {
		List<MovieVO> movieList = trgMovieList;
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(pageNum);
//		System.out.println(movieList);
		String test = mapper.writeValueAsString(movieList);
//		System.out.println(test);

		System.out.println(test);
	    return test;
	}
}