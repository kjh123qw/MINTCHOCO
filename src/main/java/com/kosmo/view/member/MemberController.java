package com.kosmo.view.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.mintchoco.common.SecurityUtil;
import com.kosmo.mintchoco.member.MemberVO;

/*
 * 담당자 : 유지상
 */
@Controller
public class MemberController {

	@RequestMapping("/login.do")
	public String login(String email, String pwd, String rememberCheck) {
		
		System.out.println("-------------------");
		System.out.println(email);
		System.out.println(pwd);
		System.out.println(rememberCheck); // on or null
		System.out.println("-------------------");
		
		SecurityUtil security = new SecurityUtil();
		
		String testPwd = security.encryptSHA256("awrefnawruj12424");
		System.out.println(testPwd);
		return "main.jsp";
	}
	
}
