package com.kosmo.view.search;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private List<SearchVO> trgSearchList = null;
	private int totlaSearchCount = 0;
	
	@RequestMapping("/movie/search.do")
	public String search(SearchDAO searchDAO, Model model, HttpServletRequest request) {
		List<SearchVO> outputList = new ArrayList<SearchVO>();
		int count = 0;
		if(request.getParameter("searchKeyWord") == null || request.getParameter("searchKeyWord").equals("")) {
			trgSearchList = searchDAO.selectMovieList();
			model.addAttribute("searchKeyWord", "전체 영화");
		} else {
			trgSearchList = searchDAO.searchMovieList(request.getParameter("searchKeyWord"));
			model.addAttribute("searchKeyWord", request.getParameter("searchKeyWord"));
		}
		totlaSearchCount = trgSearchList.size();
		model.addAttribute("searchCount", totlaSearchCount);
		for(int i = 0; i < 5; i++) {
			if(trgSearchList.size() > 0) {
				outputList.add(trgSearchList.remove(0));
			} else {
				break;
			}
		}
		model.addAttribute("moreSearchCount", trgSearchList.size());
		model.addAttribute("searchMovieList", outputList);
		return "search_list.jsp";
	}

	@RequestMapping(value="/movie/nextpage.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> nextPage(@RequestParam(required=true) String pageNum) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders responseHeaders = new HttpHeaders();  //헤더객체를 만들어서 
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //헤더정보 추가
		List<SearchVO> outputList = new ArrayList<SearchVO>();
		for(int i = 0; i < 5; i++) {
			if(trgSearchList.size() > 0) {
				outputList.add(trgSearchList.remove(0));
			} else {
				break;
			}
		}
		String returnString = mapper.writeValueAsString(outputList);
	    return new ResponseEntity<String>(returnString, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/movie/nextpagecount.do", method=RequestMethod.POST)
	@ResponseBody
	public String nextPageCount() throws Exception {
		String returnString = 
				"{\"remainPageCount\"" +
				": " + Integer.toString(trgSearchList.size()) + ", " +
				"\"totalSearchCount\"" +
				": " + totlaSearchCount + "}";
	    return returnString;
	}
	
}