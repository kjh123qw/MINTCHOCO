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
	private int totlaSearchCount = 0;
	
	public static final int TAG_SEARCH_DATE_STANDARD = 90;		//태그 검색시 참고할 평가글의 날짜 ex( sysdate - 90 까지)
	public static final int TAG_SEARCH_VIEW_COUNT = 30;			//태그 검색시 보여줄 데이터 갯수
	
	public static Map<String, ArrayList<Integer>> mapTagByMovie = null;												//장르별 영화 저장맵
	public static Map<String, ArrayList<SearchVO>> mapSearchResult = new HashMap<String, ArrayList<SearchVO>>();	//검색 결과 저장맵
	
	public SearchController()
	{
		settingTagMapping();
	}
	
	@RequestMapping("/movie/tagSearch.do")
	public String tagSearch(SearchDAO searchDAO, Model model, HttpServletRequest request) 
	{
		String tagList[] = request.getParameterValues("tagList");
		if(null == request.getParameterValues("tagList"))
		{
			model.addAttribute("searchCount", 0);
			return "search_list.jsp";
		}
		
		List<SearchVO> outputList = new ArrayList<SearchVO>();
		
		String strKeyWord = "";
		for(String str : tagList)
			strKeyWord += str + ",";
		
		//결과값이 없다면
		if(true != mapSearchResult.containsKey(strKeyWord))
		{
			Map<Integer, Integer> mapCheck = new HashMap<Integer, Integer>();
			for(String str : tagList)
			{
				ArrayList<Integer> arr = mapTagByMovie.get(str);
				for(Integer i : arr)
				{
					if(mapCheck.containsKey(i))
						mapCheck.put(i, mapCheck.get(i) + 1);
					else
						mapCheck.put(i, 1);
				}
			}
			
			ArrayList<ArrayList<Integer>> valueList = new ArrayList<ArrayList<Integer>>();
			for(int i = 0; i < 3; i ++)
				valueList.add(new ArrayList<Integer>());
			
			for(int key : mapCheck.keySet())
			{
				int value = mapCheck.get(key);
				if(tagList.length == value)
					valueList.get(0).add(key);
				else if(tagList.length - 1 == value)
					valueList.get(1).add(key);
				else if(tagList.length - 2 == value)
					valueList.get(2).add(key);
			}
			
			if(0 < valueList.get(0).size())
				trgSearchList = searchDAO.searchTagMovieList(valueList.get(0));
			
			for(int i = 1; i < valueList.size(); i++)
			{
				if(null != trgSearchList)
				{
					if(TAG_SEARCH_VIEW_COUNT <= trgSearchList.size())
						break;
				}
				
				if(0 < valueList.get(i).size())
				{
					if(null != trgSearchList)
						trgSearchList.addAll(searchDAO.searchTagMovieList(valueList.get(i)));
					else
						trgSearchList = searchDAO.searchTagMovieList(valueList.get(i));
				}
			}
			
			for(int i = 0; i < trgSearchList.size(); i++)
				trgSearchList.get(i).setMovieIndex(i);
			
			mapSearchResult.put(strKeyWord, (ArrayList<SearchVO>)searchArrDeepCopy(trgSearchList));
			
		}
		else
			trgSearchList = searchArrDeepCopy(mapSearchResult.get(strKeyWord));
		
		totlaSearchCount = trgSearchList.size();
		model.addAttribute("searchCount", totlaSearchCount);
		for(int i = 0; i < 5; i++) 
		{
			if(0 < trgSearchList.size()) 
				outputList.add(trgSearchList.remove(0));
			else 
				break;
		}
		
		model.addAttribute("moreSearchCount", trgSearchList.size());
		model.addAttribute("searchMovieList", outputList);
		return "search_list.jsp";
	}
	
	@RequestMapping("/movie/search.do")
	public String search(SearchDAO searchDAO, TagDAO tagDAO, Model model, HttpServletRequest request) {
		List<SearchVO> outputList = new ArrayList<SearchVO>();
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
	
	//처음 초기설정 정보
	public void settingTagMapping()
	{
		SearchDAO searchDAO = new SearchDAO(); 
		List<String> strTagList = searchDAO.selectTagList();
		mapTagByMovie = searchDAO.selectTagbyMovie(strTagList);
	}
	
	//DB에 태그 추가 시
	public static void addTag(String strTag)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		mapTagByMovie.put(strTag, list);
	}
	
	//태그맵핑 시  mapTagByMovie의 값 변경
	public static void updateMapData(int movie_number, String tag, boolean bAddorDel /*추가  == true*/)
	{
		//추가 일 시
		if(true == bAddorDel)
		{
			if(false == mapTagByMovie.get(tag).contains(movie_number))
				mapTagByMovie.get(tag).add(movie_number);
		}
		else
		{
			if(true == mapTagByMovie.get(tag).contains(movie_number))
				mapTagByMovie.get(tag).remove((Integer)movie_number);	
		}	
		
		deleteResultValue(tag);
	}
	
	//태그밉핑 추가 시 영향을 받는 결과값 삭제
	public static void deleteResultValue(String tag)
	{
		for(String key : mapSearchResult.keySet())
		{
			if(-1 != key.indexOf(tag))
				mapSearchResult.remove(key);
		}
	}
	
	//List<SearchVO> 깊은복사 함수
	public List<SearchVO> searchArrDeepCopy(List<SearchVO> list)
	{
		List<SearchVO> resultList = new ArrayList<SearchVO>(); 
		for(SearchVO VO : list)
		{
			SearchVO vo = new SearchVO();
			vo.setMovieActor(VO.getMovieActor());
			vo.setMovieDate(VO.getMovieDate());
			vo.setMovieDirector(VO.getMovieDirector());
			vo.setMovieGrade(VO.getMovieGrade());
			vo.setMovieIndex(VO.getMovieIndex());
			vo.setMovieKind(VO.getMovieKind());
			vo.setMovieNumber(VO.getMovieNumber());
			vo.setMoviePoster(VO.getMoviePoster());
			vo.setMovieStars(VO.getMovieStars());
			vo.setMovieTime(VO.getMovieTime());
			vo.setMovieTitle(VO.getMovieTitle());
			resultList.add(vo);
		}
		
		return resultList;
	}
	
}