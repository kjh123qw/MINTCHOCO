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

/*
 * 담당자 : 김정호, 천세문
 */

@Controller
public class AssessmentController {
	
	private List<AssessmentVO> asseList = null;
	private int currMovieNumber = 0;
	
	@RequestMapping(value="/comment/insert.do", method=RequestMethod.POST)
	public String insertComment(HttpServletRequest request, AssessmentDAO assessmentDAO) {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
		assessmentDAO.insertAssessment(memberVO.getNumber() ,Integer.parseInt(request.getParameter("movieNumber")), request.getParameter("assessContent"), Integer.parseInt(request.getParameter("assessStars")));
	    return "redirect:/movie/detail.do?movieNumber=" + request.getParameter("movieNumber") + "#ratingWrap";
	}
	@RequestMapping(value="/comment/delete.do", method=RequestMethod.GET)
	public String deleteComment(HttpServletRequest request, AssessmentDAO assessmentDAO) {

		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberInfo");
		assessmentDAO.deleteAssessment(request.getParameter("assessId"));
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
}
