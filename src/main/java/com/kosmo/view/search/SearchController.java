package com.kosmo.view.search;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.search.SearchDAO;

/*
 * 담당자 : 김정호
 */
@Controller
public class SearchController {
	@RequestMapping("/movie/search.do")
	public String search(SearchDAO searchDAO, MovieDAO movieDAO, Model model, HttpServletRequest request) {
		if(request.getParameter("searchKeyWord") == null) {
			model.addAttribute("searchKeyWord", "전체 영화");
			model.addAttribute("searchMovieList", movieDAO.selectMovieList());
		} else {
			model.addAttribute("searchKeyWord", request.getParameter("searchKeyWord"));
			model.addAttribute("searchMovieList", searchDAO.searchMovieList(request.getParameter("searchKeyWord")));
		}
		return "search_list.jsp";
	}
}
