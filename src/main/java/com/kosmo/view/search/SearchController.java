package com.kosmo.view.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
//	@RequestMapping(value="/movie/test.ajax")
//	public ModelAndView jsonViewTest(@RequestParam Map<String, Object> params, HttpServletRequest request){
//	   System.out.println(params);
//		ModelAndView mv = new ModelAndView();
//	    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();        
//	        
//	    result.add(params);
//	    mv.addObject("result", "test");
//	    mv.setViewName("jsonView");
//	    return mv;
//	}


//	@RequestMapping("/movie/nextpage.do")
//	@ResponseBody
//	public String nextPage(@RequestParam Map<String, Object> params) {
//	    List<Map<String, Object>> nextPageList = new ArrayList<Map<String, Object>>();
//	    ObjectMapper oMapper = new ObjectMapper();
//	    int page = (int)request.getAttribute("pageNumber");
//	    int maxCount = trgSearchList.size();
//	    System.out.println(trgMovieList.get(0).getMovieTitle());
//	    test.put("pageNum", params.get("pageNum"));
//	    test.put("testNum", maxCount);
//        if(trgSearchList != null) {
//        	for(int i = 0; i < trgMovieList.size(); i++) {
//        		nextPageList.add(oMapper.convertValue(trgSearchList.get(i), HashMap.class));
//        	}
//	    }
//	    result = testService.selectData(params);
//	    return trgMovieList.get(0).getMovieTitle();
//	}
}