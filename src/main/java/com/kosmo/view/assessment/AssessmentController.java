package com.kosmo.view.assessment;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.mintchoco.assessment.AssessmentDAO;
import com.kosmo.mintchoco.assessment.AssessmentVO;
import com.kosmo.mintchoco.member.MemberVO;
import com.kosmo.view.search.SearchController;

/*
 * 담당자 : 김정호, 천세문
 */

@Controller
public class AssessmentController {
	
	public static final int TAG_CNT_STANDARD = 5;			//평가글에 포함된 #태그가 몇번 이상이면 Mapping테이블에 올릴지 기준
	public static final int ASS_LIKE_CNT_STANDARD = 5;		//평가글에 베스트 순위를 뽑을때 최소 추천수 기준
	
	private List<AssessmentVO> asseList = null;
	private int currMovieNumber = 0;
	
	@RequestMapping(value="/comment/insert.do", method=RequestMethod.POST)
	public String insertComment(HttpServletRequest request, AssessmentDAO assessmentDAO) {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
		boolean bResult = assessmentDAO.insertAssessment(memberVO.getNumber() ,Integer.parseInt(request.getParameter("movieNumber")), request.getParameter("assessContent"), Integer.parseInt(request.getParameter("assessStars")));
		if(bResult)
			assInsertAfter(assessmentDAO, Integer.parseInt(request.getParameter("movieNumber")), request.getParameter("assessContent"));
			
	    return "redirect:/movie/detail.do?movieNumber=" + request.getParameter("movieNumber") + "#ratingWrap";
	}
	@RequestMapping(value="/comment/delete.do", method=RequestMethod.GET)
	public String deleteComment(HttpServletRequest request, AssessmentDAO assessmentDAO) {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
		
		AssessmentVO VO = assessmentDAO.selectAssessmentLike(request.getParameter("assessId"));
		boolean bResult = assessmentDAO.deleteAssessment(request.getParameter("assessId"));
		if(bResult)
			assDelAfter(assessmentDAO, VO);
		
	    return "redirect:/movie/detail.do?movieNumber=" + request.getParameter("movieNumber") + "#ratingWrap";
	}
	
	@RequestMapping(value="/comment/list.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> getCommentList(@RequestParam(value="pageNumber", required=true, defaultValue="1") int pageNumber, @RequestParam(value="movieNumber", required=true) int movieNumber, AssessmentDAO assessmentDAO) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders responseHeaders = new HttpHeaders();  //헤더객체를 만들어서 
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //헤더정보 추가
		List<AssessmentVO> outputList = new ArrayList<AssessmentVO>();
		
		if(asseList == null || currMovieNumber != movieNumber || pageNumber == 1) {
			asseList = assessmentDAO.selectAssessmentList(movieNumber);
			currMovieNumber = movieNumber;
		}
		if(asseList.size() == 0) {
			
		} else {
			for(int i = ((pageNumber - 1) * 10); i < (pageNumber * 10); i++) {
				if(i < asseList.size())
					outputList.add(asseList.get(i));
				else
					break;
			}
		}
		String returnString = mapper.writeValueAsString(outputList);
	    return new ResponseEntity<String>(returnString, responseHeaders, HttpStatus.CREATED);
	}


	@RequestMapping(value="/comment/pagecount.do", method=RequestMethod.POST)
	@ResponseBody
	public String nextPageCount() throws Exception {
		String returnString = 
				"{\"totalSize\"" +
				": " + Integer.toString(asseList.size()) + "}";
	    return returnString;
	}

	@RequestMapping(value="/comment/insertLike.do", method=RequestMethod.POST)
	@ResponseBody
	public String insertLike(@RequestParam(value="memberNumber", required=true) int memberNumber, @RequestParam(value="assessEst", required=true) String assessEst,  @RequestParam(value="assessId", required=true) String assessId, AssessmentDAO assessmentDAO) throws Exception {
		int resultValue = assessmentDAO.insertAssessmentLike(memberNumber, assessId, assessEst);
		if(1 == resultValue)
			assPushLikesAfter(assessmentDAO, assessId);
		
	    return Integer.toString(resultValue);
	}

	@RequestMapping(value="/comment/getOne.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> selectCommentOne(@RequestParam(value="assessId", required=true) String assessId, AssessmentDAO assessmentDAO) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		HttpHeaders responseHeaders = new HttpHeaders();  //헤더객체를 만들어서 
		responseHeaders.add("Content-Type", "text/html; charset=utf-8"); //헤더정보 추가
		AssessmentVO resultValue = assessmentDAO.selectAssessmentLike(assessId);

		String returnString = mapper.writeValueAsString(resultValue);
	    return new ResponseEntity<String>(returnString, responseHeaders, HttpStatus.CREATED);
	}
	
	//평가글 등록시
	public void assInsertAfter(AssessmentDAO DAO, int iMovieNumber, String strcontent)
	{
		List<String> tagList = getTagList(strcontent);
		
		//평가글에 #태그가 하나라도 있다면
		for(int i = 0; i < tagList.size(); i++)
		{
			//Tag테이블에 추가, 이미 있으면 Cnt를 증가
			boolean bResult = DAO.insertTag(tagList.get(i), iMovieNumber);
			if(false == bResult)
				DAO.updateTagUseCnt(tagList.get(i));
			else
				SearchController.addTag(tagList.get(i));
			
			//게시물의 평가글 중 해당 #태그를 포함한 글이 기준 이상이면 
			//TagMapping테이블에 추가
			int iCount = DAO.getTagCount(iMovieNumber, tagList.get(i));
			if(TAG_CNT_STANDARD <= iCount)
			{
				boolean bRes = DAO.insertTagMapping(tagList.get(i), iMovieNumber);
				if(true == bRes)
					SearchController.updateMapData(iMovieNumber, tagList.get(i), true);
			}
		}
	}
	
	//평가글 삭제시
	public void assDelAfter(AssessmentDAO DAO, AssessmentVO VO)
	{
		//영화의 게시물 중 1~3위의 글을 뽑는다.(추천수가 같을때는 날짜가 최신 순으로)
		List<AssessmentVO> assRankList = DAO.getAssessRank(VO.getMovieNumber(), 3);
		int index = assRankList.size() - 1;
		List<String> tagList = getTagList(VO.getAssessContent());
		
		if(-1 == index)
		{
			checkSharpTag(DAO, tagList, VO.getMovieNumber());
			return;
		}
		
		//지운글이 최대 3위랑 비교해서 추천수가 더 많거나, 추천수가 같고 날짜가 최신이면 -----> 지운글이 기존 3위안에 있었다면
		int compare = VO.getAssessRegdate().compareTo(assRankList.get(index).getAssessRegdate()); 
		if(assRankList.get(index).getLikes() < VO.getLikes() || (assRankList.get(index).getLikes() == VO.getLikes() && compare < 0)) 
		{
			//평가글에 #태그가 하나라도 있다면
			for(int i = 0; i < tagList.size(); i++)
			{
				int iCount = DAO.getTagCount(VO.getMovieNumber(), tagList.get(i));
				boolean bCheck = false;
				for(AssessmentVO rank : assRankList)
				{
					int iPos = rank.getAssessContent().indexOf(tagList.get(i));
					if(-1 != iPos)
					{
						bCheck = true;
						break;
					}	
				}
				
				//1~3위의 글에서 같은 태그가 포함되어 있지 않고 해당 #태그를 포함한 글이 기준 미만이면 삭제		
				if(false == bCheck && iCount < TAG_CNT_STANDARD)
				{
					boolean bResult = DAO.deleteTagMapping(tagList.get(i), VO.getMovieNumber());
					if(true == bResult)
						SearchController.updateMapData(VO.getMovieNumber(), tagList.get(i), false);
				}
				
				//한번만 실행
				if(0 == i)
				{
					//3위의 글에 #태그가 있으면 맵핑 추가
					List<String> list = getTagList(assRankList.get(index).getAssessContent());
					for(String str : list)
					{
						boolean bResult = DAO.insertTagMapping(str, VO.getMovieNumber());
						if(true == bResult)
							SearchController.updateMapData(VO.getMovieNumber(), str, true);
					}
				}
			}
		}
		else
			checkSharpTag(DAO, tagList, VO.getMovieNumber());
		
	}
	
	public void checkSharpTag(AssessmentDAO DAO, List<String> tagList, int movie_number)
	{
		//평가글에 #태그가 하나라도 있다면
		for(int i = 0; i < tagList.size(); i++)
		{
			//게시물의 평가글 중 해당 #태그를 포함한 글이 기준 미만이면
			int iCount = DAO.getTagCount(movie_number, tagList.get(i));
			if(iCount < TAG_CNT_STANDARD)
			{
				DAO.deleteTagMapping(tagList.get(i), movie_number);
				SearchController.updateMapData(movie_number, tagList.get(i), false);
			}
		}
	}
	
	//추천 눌렀을시
	public void assPushLikesAfter(AssessmentDAO DAO, String strAssId)
	{
		AssessmentVO VO = DAO.selectAssessmentLike(strAssId);
		List<String> tagList = getTagList(VO.getAssessContent());
		
		//영화의 게시물 중 1~4위의 글을 뽑는다.(추천수가 같을때는 날짜가 최신 순으로)
		List<AssessmentVO> assRankList = DAO.getAssessRank(VO.getMovieNumber(), 4);
		AssessmentVO vo4 = null; 
		if(4 == assRankList.size())
			vo4 = assRankList.remove(3);
			
		for(int i = 0; i < assRankList.size(); i++)
		{
			//내가 3위 안에 포함되어 있다면
			if(VO.getAssessId().equals(assRankList.get(i).getAssessId()))
			{
				//#태그맵핑 추가
				for(String str : tagList)
				{
					boolean bResult = DAO.insertTagMapping(str, VO.getMovieNumber());
					if(true == bResult)
						SearchController.updateMapData(VO.getMovieNumber(), str, true);
				}
				
				if(null != vo4) 
				{
					List<String> rank4TagList = getTagList(vo4.getAssessContent());
					
					for(String strTag : rank4TagList)
					{
						//4위글에 포함된 태그의 사용횟수가 기준 이상이면 무시
						int iCount = DAO.getTagCount(VO.getMovieNumber(), strTag);
						if(TAG_CNT_STANDARD <= iCount)
							continue;
								
						boolean bCheck = false;
						for(AssessmentVO rank : assRankList)
						{
							int iPos = rank.getAssessContent().indexOf(strTag);
							if(-1 != iPos)
							{
								bCheck = true;
								break;
							}	
						}
						
						//1~3위의 글에서 같은 태그가 포함되어 있지 않고 해당 #태그를 포함한 글이 기준 미만이면 삭제		
						if(false == bCheck)
						{
							boolean bResult = DAO.deleteTagMapping(strTag, VO.getMovieNumber());
							if(true == bResult)
								SearchController.updateMapData(VO.getMovieNumber(), strTag, false);
						}
					}
				}
				
				break;
			}
		}
	}
	
	//추천 취소했을 시
	public void assCancelLikesAfter(AssessmentVO VO, AssessmentDAO DAO)
	{
		//영화의 게시물 중 1~4위의 글을 뽑는다.(추천수가 같을때는 날짜가 최신 순으로)
		List<AssessmentVO> assRankList = DAO.getAssessRank(VO.getMovieNumber(), 4);
		
		//내가 4위였었다면
		if(VO.getAssessId().equals(assRankList.get(3).getAssessId()))
		{
			//글이 3위랑 비교해서 추천수가 하나 작고 날짜가 최신이면 or 추천수가 같고 날짜가 최신이 아니면----->  기존 3위 였었다면
			int compare = VO.getAssessRegdate().compareTo(assRankList.get(2).getAssessRegdate()); 
			if((assRankList.get(2).getLikes() == VO.getLikes() - 1 && compare < 0) || (assRankList.get(2).getLikes() == VO.getLikes() &&  0 < compare))
			{
				//3위의 글에 포함된 태그맵핑추가 
				List<String> tag3List = getTagList(assRankList.get(2).getAssessContent());
				for(String str : tag3List)
				{
					boolean bResult = DAO.insertTagMapping(str, VO.getMovieNumber());
					if(true == bResult)
						SearchController.updateMapData(VO.getMovieNumber(), str, true);
				}
				
				List<String> tagList = getTagList(VO.getAssessContent());
				for(String strTag : tagList)
				{
					//4위글에 포함된 태그의 사용횟수가 기준 이상이면 무시
					int iCount = DAO.getTagCount(VO.getMovieNumber(), strTag);
					if(TAG_CNT_STANDARD <= iCount)
						continue;
							
					boolean bCheck = false;
					for(AssessmentVO rank : assRankList)
					{
						int iPos = rank.getAssessContent().indexOf(strTag);
						if(-1 != iPos)
						{
							bCheck = true;
							break;
						}	
					}
					
					//1~3위의 글에서 같은 태그가 포함되어 있지 않고 해당 #태그를 포함한 글이 기준 미만이면 삭제		
					if(false == bCheck)
					{
						DAO.deleteTagMapping(strTag, VO.getMovieNumber());
						SearchController.updateMapData(VO.getMovieNumber(), strTag, false);
					}
				}
			}
		}
	}
	
	//평가글에서 #태그 뽑기
	public List<String> getTagList(String assContent)
	{
		List<String> tagList = new ArrayList<String>(); 
		int iPos, iPosEnd = 0;

		while(true)
		{
			iPos = assContent.indexOf('#', iPosEnd);
			if(-1 != iPos)
			{
				iPosEnd = assContent.indexOf(' ', iPos);
				
				String strTag = "";
				if(-1 == iPosEnd)
					strTag = assContent.substring(iPos + 1);
				else
					strTag = assContent.substring(iPos + 1, iPosEnd);
				
				boolean bCheck = true;
				if(null != tagList) 
				{
					for(int i = 0; i < tagList.size(); i++)
					{
						if(strTag.equals(tagList.get(i)))
						{
							bCheck = false;
							break;
						}
					}
					
					if(true == bCheck)
						tagList.add(strTag);	
				}
				
				if(-1 == iPosEnd)
					break;
			}
			else
				break;
		}	
		
		return tagList; 
	}
}
