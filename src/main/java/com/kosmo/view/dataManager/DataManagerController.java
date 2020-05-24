package com.kosmo.view.dataManager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.mintchoco.common.DBVersionManager;
import com.kosmo.mintchoco.member.MemberVO;
import com.kosmo.mintchoco.movie.MovieDAO;
import com.kosmo.mintchoco.rank.Crawling;



/*
 * 담당자 : 김정호
 */

@Controller
public class DataManagerController {
	
//	@RequestMapping("/db.do")
//	public String databaseManager(DBVersionManager dbvm, Model model) {
////		String nowVersion = dbvm.getNowVer();
//		model.addAttribute("nowVersion", "1");
//		model.addAttribute("curVersion", "2");
//		return "database_check.jsp";
//	}
//	
//	@RequestMapping("/dbSetup.do")
//	public String databaseSetup(DBVersionManager dbvm) {
//		dbvm.setupDatabase();
//		return "redirect:db.do";
//	}
//	
//	@RequestMapping("/dbDelete.do")
//	public String databaseDelete(DBVersionManager dbvm) {
//		dbvm.dropDatabase();
//		return "redirect:db.do";
//	}
//	
//	@RequestMapping("/tag.do")
//	public String moveTagPage() {
//		return "_ex_input_tag.jsp";
//	}
//		
//	@RequestMapping("/dbCrawling.do")
//	public String dataCrawling(DBVersionManager dbvm, HttpServletRequest request) {
//		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("memberInfo");
////		if(memberVO.getNumber() == 1) {
//			dbvm.setupCrawlingData(Crawling.movieData());
////		}
//		return "redirect:db.do";
//	}
//	
//	@RequestMapping("/dbCrawling2.do")
//	public String dataCrawlingTwo(DBVersionManager dbvm) {
//		dbvm.setupCrawlingDataTwo();
//		return "redirect:db.do";
//	}
}
