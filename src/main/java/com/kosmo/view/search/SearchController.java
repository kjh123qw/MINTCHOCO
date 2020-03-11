package com.kosmo.view.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.search.SearchDAO;
import com.kosmo.mintchoco.search.SearchVO;

/*
 * 담당자 : 김정호
 */
@Controller
public class SearchController {
	@RequestMapping("/movie/search.do")
	public String search(SearchDAO searchDAO, MovieDAO movieDAO, Model model, HttpServletRequest request) {
		if(request.getParameter("searchKeyWord") == null || request.getParameter("searchKeyWord").equals("")) {
			model.addAttribute("searchKeyWord", "전체 영화");
			model.addAttribute("allMovieList", movieDAO.selectMovieList());
		} else {
			model.addAttribute("searchKeyWord", request.getParameter("searchKeyWord"));
			model.addAttribute("searchMovieList", searchDAO.searchMovieList(request.getParameter("searchKeyWord")));
		}
		return "search_list.jsp";
	}
	
	@RequestMapping("/movie/nextpage.do")
	@ResponseBody
	public List<Map<String, Object>> nextPage(@RequestParam Map<String, Object> params, HttpServletRequest request){
	    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();        
	    List<SearchVO> searchList = new ArrayList<SearchVO>();
	    if(request.getAttribute("searchMovieList") != null) {
	    	request.getAttribute("searchMovieList");
	    }
//	    result = testService.selectData(params);
	    return result;
	}

}
