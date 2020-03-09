package com.kosmo.view.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosmo.mintchoco.common.SecurityUtil;
import com.kosmo.mintchoco.member.MemberDAO;
import com.kosmo.mintchoco.member.MemberVO;

/*
 * 담당자 : 유지상
 */
@Controller
public class MemberController {
	
	SecurityUtil security = new SecurityUtil();
	MemberDAO dao = new MemberDAO();
	
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String joinMember(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String securityPwd = security.encryptSHA256(pwd);
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		
		MemberVO vo = new MemberVO();
		vo.setEmail(email);
		vo.setPassword(securityPwd);
		vo.setName(name);
		vo.setNickName(nickname);
		vo.setAge(age);
		vo.setGender(gender);
		
		dao.joinMember(vo);
		
		return "index.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/joinCheck.do", method = RequestMethod.POST)
	public String joinCheck(HttpServletRequest request) throws Exception {
		
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		
		System.out.println(email);
		System.out.println(nickname);
		
		
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(String email, String pwd, String rememberCheck) throws Exception {
		
		/*
		 * System.out.println("-------------------"); System.out.println(email);
		 * System.out.println(pwd); System.out.println(rememberCheck); // on or null
		 * System.out.println("-------------------");
		 * 
		 * SecurityUtil security = new SecurityUtil();
		 * 
		 * String testPwd = security.encryptSHA256("awrefnawruj12424");
		 * System.out.println(testPwd);
		 */
		
		String msg = "1";
		String msg2 = "2";
		
		if(msg == "1") {
			throw new Exception();
			
		}else {
			return msg2;
			
		}
		
	}
	
}
