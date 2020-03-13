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
import org.springframework.web.bind.annotation.SessionAttributes;

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
		// 초기화
		session.removeAttribute("member_config");
		
		// 자기번호 불러오기
		String myNumber = null;
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			myNumber = Integer.toString(memberidx.getNumber());
		}
		String memberNum = (String) session.getAttribute("memberNum");
				
		if (memberNum == null) {
			number = myNumber;
		} else {
			number = memberNum;
		}
		
		model.addAttribute("favorite_five", favoriteDAO.getFavInfo(number));
		model.addAttribute("detail_info", memberDAO.getDetailInfo(number));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(number));
		return "member/myp_inform.jsp";
	}
	
	// 마이페이지 > 찜한 목록
	@RequestMapping("/favorite.do")
	public String getFavoriteList(FavoriteVO vo, FavoriteDAO favoriteDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		// 초기화
		session.removeAttribute("member_config");
		
		// 자기번호 불러오기
		String myNumber = null;
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			myNumber = Integer.toString(memberidx.getNumber());
		}	
		String memberNum = (String) session.getAttribute("memberNum");
				
		if (memberNum == null) {
			number = myNumber;
		} else {
			number = memberNum;
		}
		
		model.addAttribute("detail_info", memberDAO.getDetailInfo(number));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(number));
		model.addAttribute("fav_cnt", favoriteDAO.getFavoriteCnt(number));
		model.addAttribute("favoriteList", favoriteDAO.getFavoriteList(number));	
		return "member/myp_list.jsp";
	}
	
	// 찜한목록 기능
	@RequestMapping(value="/favorite.do", method=RequestMethod.POST)
	public String sortFavList(@RequestParam(value="sort", required=false) String sort, @RequestParam(value="chkbox", required=false) String chkbox,FavoriteVO vo, FavoriteDAO favoriteDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		// 자기번호 불러오기
		String myNumber = null;
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			myNumber = Integer.toString(memberidx.getNumber());
		}		
		String memberNum = (String) session.getAttribute("memberNum");
				
		if (memberNum == null) {
			number = myNumber;
		} else {
			number = memberNum;
		}
		
		// 삭제
		if(chkbox != null) {
			List<String> chkboxList = Arrays.asList(chkbox.split(","));
			favoriteDAO.deleteFavorite(chkboxList);
			model.addAttribute("fav_cnt", favoriteDAO.getFavoriteCnt(number));
			model.addAttribute("favoriteList", favoriteDAO.getFavoriteList(number));
		}
		
		// 정렬
		if(sort != null) {
			if (sort.equals("sub")) {
				model.addAttribute("favoriteList", favoriteDAO.getFavSortbyTitle(number));
			} else if (sort.equals("rel")) {
				model.addAttribute("favoriteList", favoriteDAO.getFavSortbyRelease(number));	
			} else if (sort.equals("reg")) {
				model.addAttribute("favoriteList", favoriteDAO.getFavSortbyIndate(number));	
			} else {
				model.addAttribute("favoriteList", favoriteDAO.getFavoriteList(number));
			}
		}
		
		// 기본
		model.addAttribute("detail_info", memberDAO.getDetailInfo(number));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(number));
		return "member/myp_list.jsp";
	}
	
	// 마이페이지 > 평가 목록
	@RequestMapping("/assessment.do")
	public String getAssessList(AssessmentMVO vo, AssessmentMDAO assessDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		// 초기화
		session.removeAttribute("member_config");
		
		// 자기번호 불러오기
		String myNumber = null;
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			myNumber = Integer.toString(memberidx.getNumber());
		}	
		String memberNum = (String) session.getAttribute("memberNum");
		
		if (memberNum == null) {
			number = myNumber;
		} else {
			number = memberNum;
		}
		
		model.addAttribute("detail_info", memberDAO.getDetailInfo(number));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(number));
		model.addAttribute("assessList", assessDAO.getAssessmentList(number));
		return "member/myp_assess_list.jsp";
	}
	
	// 평가 목록 기능
	@RequestMapping(value="/assessment.do", method=RequestMethod.POST)
	public String sortFavList(@RequestParam(value="sort", required=false) String sort, @RequestParam(value="chkbox", required=false) String chkbox, AssessmentMVO vo, AssessmentMDAO assessDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		// 자기번호 불러오기
		String myNumber = null;
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			myNumber = Integer.toString(memberidx.getNumber());
		}		
		String memberNum = (String) session.getAttribute("memberNum");
		
		if (memberNum == null) {
			number = myNumber;
		} else {
			number = memberNum;
		}
		
		// 삭제
		if(chkbox != null) {
			List<String> chkboxList = Arrays.asList(chkbox.split(","));
			assessDAO.deleteAssessment(chkboxList);
			model.addAttribute("assessList", assessDAO.getAssessmentList(number));
		}
		
		// 정렬
		if(sort != null) {
			if (sort.equals("sub")) {
				model.addAttribute("assessList", assessDAO.getAssessSortbyTitle(number));
			} else if (sort.equals("reg")) {
				model.addAttribute("assessList", assessDAO.getAssessSortbyIndate(number));	
			} else if (sort.equals("star")) {
				model.addAttribute("assessList", assessDAO.getAssessSortbyStars(number));	
			} else {
				model.addAttribute("assessList", assessDAO.getAssessmentList(number));
			}
		}
		
		// 기본
		model.addAttribute("detail_info", memberDAO.getDetailInfo(number));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(number));
		return "member/myp_assess_list.jsp";
	}	
	
	// 정보 수정 전 비밀번호 체크 페이지
	@RequestMapping("/checkPW.do")
	public String getCheckPWPage(FavoriteVO vo, FavoriteDAO favoriteDAO, MemberVO member, MemberMDAO memberDAO, Model model, HttpSession session) {
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			number = Integer.toString(memberidx.getNumber());
		}
		
		model.addAttribute("detail_info", memberDAO.getDetailInfo(number));
		model.addAttribute("assess_cnt", memberDAO.getAssessmentCnt(number));
		return "member/myp_config_checkPW.jsp";
	}
	
	// 비밀번호 체크 기능
	@ResponseBody
	@RequestMapping(value="/checkPassword.do", method=RequestMethod.POST)
	public String checkPassword(@RequestParam(value="password") String password, MemberMDAO memberDAO, HttpSession session){
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			number = Integer.toString(memberidx.getNumber());
		}
		
		// 비밀번호 확인
		int chkPW = memberDAO.checkPassword(password, number);
		if(chkPW > 0) {
			session.setAttribute("member_config", "y");
			return "success";
		} else {
			session.setAttribute("member_config", "n");
			return "fail";
		}
	}
	
	// 정보 수정 페이지
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
		String number = null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			number = Integer.toString(memberidx.getNumber());
		}
		
		// 정보수정
		if(nickName != null) {
			membermDAO.updateMember(number, nickName, gender, age,introduce);			
		}
		
		// 비번수정
		if(password != null) {
			membermDAO.updateMemberPW(number, password);
		}
		
		// 공개수정
		if(opeyn != null) {
			membermDAO.updateMemberOPE(number, assessFlag, favFlag, infoFlag);
		}		
		
		// 기본
		model.addAttribute("detail_info", membermDAO.getDetailInfo(number));
		model.addAttribute("assess_cnt", membermDAO.getAssessmentCnt(number));

		List<MemberVO> memberInfo = membermDAO.loginMemberbyN(number);
		session.setAttribute("memberInfo", memberInfo);
		
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
	
	@ResponseBody
	@RequestMapping(value="/deleteMember.do", method=RequestMethod.POST)
	public String deleteMember(@RequestParam(value="check") String check, MemberMDAO memberDAO, HttpSession session){
		// 계정 삭제
		String number=null;
		List<MemberVO> memberList = (List<MemberVO>) session.getAttribute("memberInfo");
		if (memberList != null) {
			MemberVO memberidx = memberList.get(0);
			number = Integer.toString(memberidx.getNumber());
		}
		
		if(check.equals("del")) {
			memberDAO.deleteMember(number);
			return "DELsuccess";
		} else {
			return "DELerror";
		}
	}
	
}
