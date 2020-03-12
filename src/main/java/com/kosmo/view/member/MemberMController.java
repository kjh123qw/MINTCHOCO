package com.kosmo.view.member;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.mintchoco.assessment.AssessmentMDAO;
import com.kosmo.mintchoco.assessment.AssessmentMVO;
import com.kosmo.mintchoco.favorite.FavoriteDAO;
import com.kosmo.mintchoco.favorite.FavoriteVO;
import com.kosmo.mintchoco.member.MemberDAO;
import com.kosmo.mintchoco.member.MemberMDAO;
import com.kosmo.mintchoco.member.MemberVO;

/*
 * 담당자 : 장세진
 */
@Controller
public class MemberMController {
	
	// 마이페이지 메인
	@RequestMapping("/my_page.do")
	public String getInfo(FavoriteVO vo, FavoriteDAO favoriteDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		session.setAttribute("memberNum","2");
		String memberNum = (String) session.getAttribute("memberNum");
		model.addAttribute("favorite_five", favoriteDAO.getFavInfo(memberNum));
		model.addAttribute("detail_info", memberDAO.getDetailInfo(memberNum));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(memberNum));
		return "member/myp_inform.jsp";
	}
	
	// 마이페이지 > 찜한 목록
	@RequestMapping("/favorite.do")
	public String getFavoriteList(FavoriteVO vo, FavoriteDAO favoriteDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		session.setAttribute("memberNum","2");
		String memberNum = (String) session.getAttribute("memberNum");
		model.addAttribute("detail_info", memberDAO.getDetailInfo(memberNum));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(memberNum));
		model.addAttribute("fav_cnt", favoriteDAO.getFavoriteCnt(memberNum));
		model.addAttribute("favoriteList", favoriteDAO.getFavoriteList(memberNum));	
		return "member/myp_list.jsp";
	}
	
	// 찜한목록 기능
	@RequestMapping(value="/favorite.do", method=RequestMethod.POST)
	public String sortFavList(@RequestParam(value="sort", required=false) String sort, @RequestParam(value="chkbox", required=false) String chkbox,FavoriteVO vo, FavoriteDAO favoriteDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		session.setAttribute("memberNum","2");
		String memberNum = (String) session.getAttribute("memberNum");
		
		// 삭제
		if(chkbox != null) {
			List<String> chkboxList = Arrays.asList(chkbox.split(","));
			favoriteDAO.deleteFavorite(chkboxList);
			model.addAttribute("fav_cnt", favoriteDAO.getFavoriteCnt(memberNum));
			model.addAttribute("favoriteList", favoriteDAO.getFavoriteList(memberNum));
		}
		
		// 정렬
		if(sort != null) {
			if (sort.equals("sub")) {
				model.addAttribute("favoriteList", favoriteDAO.getFavSortbyTitle(memberNum));
			} else if (sort.equals("rel")) {
				model.addAttribute("favoriteList", favoriteDAO.getFavSortbyRelease(memberNum));	
			} else if (sort.equals("reg")) {
				model.addAttribute("favoriteList", favoriteDAO.getFavSortbyIndate(memberNum));	
			} else {
				model.addAttribute("favoriteList", favoriteDAO.getFavoriteList(memberNum));
			}
		}
		
		// 기본
		model.addAttribute("detail_info", memberDAO.getDetailInfo(memberNum));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(memberNum));
		return "member/myp_list.jsp";
	}
	
	// 마이페이지 > 평가 목록
	@RequestMapping("/assessment.do")
	public String getAssessList(AssessmentMVO vo, AssessmentMDAO assessDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		session.setAttribute("memberNum","2");
		String memberNum = (String) session.getAttribute("memberNum");
		model.addAttribute("detail_info", memberDAO.getDetailInfo(memberNum));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(memberNum));
		model.addAttribute("assessList", assessDAO.getAssessmentList(memberNum));
		return "member/myp_assess_list.jsp";
	}
	
	// 평가 목록 기능
	@RequestMapping(value="/assessment.do", method=RequestMethod.POST)
	public String sortFavList(@RequestParam(value="sort", required=false) String sort, @RequestParam(value="chkbox", required=false) String chkbox, AssessmentMVO vo, AssessmentMDAO assessDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		session.setAttribute("memberNum","2");
		String memberNum = (String) session.getAttribute("memberNum");
		
		// 삭제
		if(chkbox != null) {
			List<String> chkboxList = Arrays.asList(chkbox.split(","));
			assessDAO.deleteAssessment(chkboxList);
			model.addAttribute("assessList", assessDAO.getAssessmentList(memberNum));
		}
		
		// 정렬
		if(sort != null) {
			if (sort.equals("sub")) {
				model.addAttribute("assessList", assessDAO.getAssessSortbyTitle(memberNum));
			} else if (sort.equals("reg")) {
				model.addAttribute("assessList", assessDAO.getAssessSortbyIndate(memberNum));	
			} else if (sort.equals("star")) {
				model.addAttribute("assessList", assessDAO.getAssessSortbyStars(memberNum));	
			} else {
				model.addAttribute("assessList", assessDAO.getAssessmentList(memberNum));
			}
		}
		
		// 기본
		model.addAttribute("detail_info", memberDAO.getDetailInfo(memberNum));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(memberNum));
		return "member/myp_assess_list.jsp";
	}
	
	@RequestMapping("/config.do")
	public String getConfigInfo(FavoriteVO vo, FavoriteDAO favoriteDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
//		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
//		MemberVO memberidx = memberList.get(0);
//		String memberN = (String) memberidx.getNumber();
		session.setAttribute("memberNum","2");
		String memberNum = (String) session.getAttribute("memberNum");
		model.addAttribute("detail_info", memberDAO.getDetailInfo(memberNum));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(memberNum));
		return "member/myp_config.jsp";
	}
	
	@RequestMapping(value="/config.do", method=RequestMethod.POST)
	public String updateMember(@RequestParam(value="nickName", required=false) String nickName,
								@RequestParam(value="gender", required=false) String gender,
								@RequestParam(value="age", required=false) String age,
								@RequestParam(value="introduce", required=false) String introduce,
								@RequestParam(value="password", required=false) String password,
								@RequestParam(value="assessment-flag", required=false) String assessFlag,
								@RequestParam(value="info-flag", required=false) String infoFlag,
								@RequestParam(value="like-flag", required=false) String favFlag,
								@RequestParam(value="opeyn", required=false) String opeyn,
								AssessmentMVO vo, AssessmentMDAO assessDAO, MemberVO member, MemberDAO memberDAO,  MemberMDAO membermDAO,Model model, HttpSession session){
		
		// 멤버 값 받아오기
		session.setAttribute("memberNum","2");
//		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
//		MemberVO memberidx = memberList.get(0);
//		String memberN = (String) memberidx.getNumber();
		
	    String memberN = "2";
		System.out.println(memberN);
		String memberNum = (String) session.getAttribute("memberNum");
		
		// 정보수정
		if(nickName != null) {
			membermDAO.updateMember(memberN, nickName, gender, age,introduce);
			model.addAttribute("detail_info", membermDAO.getDetailInfo(memberNum));
			model.addAttribute("assess_cnt", membermDAO.getAssessmentCnt(memberNum));
			model.addAttribute("memberInfo", membermDAO.loginMemberbyN(memberN));
		}
		
		// 비번수정
		if(password != null) {
			membermDAO.updateMemberPW(memberN, password);
			model.addAttribute("detail_info", membermDAO.getDetailInfo(memberNum));
			model.addAttribute("assess_cnt", membermDAO.getAssessmentCnt(memberNum));
			model.addAttribute("memberInfo", membermDAO.loginMemberbyN(memberN));
		}
		
		// 공개수정
		if(opeyn != null) {
			membermDAO.updateMemberOPE(memberN, assessFlag, favFlag, infoFlag);
			model.addAttribute("detail_info", membermDAO.getDetailInfo(memberNum));
			model.addAttribute("assess_cnt", membermDAO.getAssessmentCnt(memberNum));
			model.addAttribute("memberInfo", membermDAO.loginMemberbyN(memberN));
		}		
		
		return "member/myp_config.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/usingNN.do", method=RequestMethod.POST)
	public String joinCheck(@RequestParam(value="nickname") String nickname, MemberDAO memberDAO, HttpSession session){
		// 별명 중복확인
		int chkNN = memberDAO.checkNickname(nickname);		
		if(chkNN > 0) {
			return "usingNN";
		} else {			
			return "success";
		}
	}
	
}
