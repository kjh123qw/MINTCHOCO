package com.kosmo.view.dataManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.mintchoco.common.DBVersionManager;



/*
 * 담당자 : 김정호
 */

@Controller
public class DataManagerController {
	
	@RequestMapping("/db.do")
	public String databaseManager(DBVersionManager dbvm, Model model) {
		String nowVersion = dbvm.getNowVer();
		if(nowVersion.equals("NotRuning")) {
			model.addAttribute("nowVersion", "NotRuning");
		} else {
			model.addAttribute("nowVersion", nowVersion);
		}
		model.addAttribute("curVersion", dbvm.getCurVer());
		return "database_check.jsp";
	}
	
	@RequestMapping("/dbSetup.do")
	public String databaseSetup(DBVersionManager dbvm) {
		dbvm.setupDatabase();
		return "redirect:db.do";
	}
	
	@RequestMapping("/dbDelete.do")
	public String databaseDelete(DBVersionManager dbvm) {
		dbvm.dropDatabase();
		return "redirect:db.do";
	}
	
	@RequestMapping("/tag.do")
	public String moveTagPage() {
		return "_ex_input_tag.jsp";
	}
}
