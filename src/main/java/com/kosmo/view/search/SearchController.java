package com.kosmo.view.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kosmo.mintchoco.search.SearchDAO;
import com.kosmo.mintchoco.search.SearchVO;
import com.kosmo.mintchoco.tag.TagDAO;


/*
 * 담당자 : 김정호
 */
@Controller
public class SearchController {
	
	private List<SearchVO> trgSearchList = null;
	
	public static final int TAG_SEARCH_DATE_STANDARD = 90;		//태그 검색시 참고할 평가글의 날짜 ex( sysdate - 90 까지)
	public static final int TAG_SEARCH_VIEW_COUNT = 30;			//태그 검색시 보여줄 데이터 갯수
	
	public static Map<String, ArrayList<Integer>> mapTagByMovie = null;												//장르별 영화 저장맵
	public static Map<String, ArrayList<SearchVO>> mapSearchResult = new HashMap<String, ArrayList<SearchVO>>();	//검색 결과 저장맵
		
	@RequestMapping("/movie/search.do")
	public String search(SearchDAO searchDAO, TagDAO tagDAO, Model model, HttpServletRequest request) {
		if(request.getParameter("searchKeyWord") == null || request.getParameter("searchKeyWord").equals("")) {
			trgSearchList = searchDAO.selectMovieList();
			model.addAttribute("searchKeyWord", "whole movies");
		} else {
			trgSearchList = searchDAO.searchMovieList(request.getParameter("searchKeyWord"));
			model.addAttribute("searchKeyWord", request.getParameter("searchKeyWord"));
		}
		model.addAttribute("searchCount", trgSearchList.size());
		return "search_list.jsp";
	}

	@RequestMapping(value="/movie/nextpage.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> nextPage(@RequestParam(required=true) int seq) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders responseHeaders = new HttpHeaders();  //헤더객체를 만들어서 
		int nowSeq = seq;
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //헤더정보 추가
		List<SearchVO> outputList = new ArrayList<SearchVO>();
		if(trgSearchList != null) {
			for(int i = 0; i < 10; i++) {
				if(nowSeq < trgSearchList.size()) {
					outputList.add(trgSearchList.get(nowSeq));
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

	@RequestMapping(value="/movie/nextpagecount.do", method=RequestMethod.POST)
	@ResponseBody
	public String nextPageCount() throws Exception {
//		String returnString = 
//				"{\"remainPageCount\"" +
//				": " + Integer.toString(trgSearchList.size()) + ", " +
//				"\"totalSearchCount\"" +
//				": " + totlaSearchCount + "}";
	    return null; //returnString;
	}
	
	
}