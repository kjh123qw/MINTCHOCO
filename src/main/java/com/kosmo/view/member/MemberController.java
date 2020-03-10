package com.kosmo.view.member;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosmo.mintchoco.common.SecurityUtil;
import com.kosmo.mintchoco.member.MemberDAO;
import com.kosmo.mintchoco.member.MemberVO;
import com.kosmo.mintchoco.movie.MovieVO;

/*
 * 담당자 : 유지상
 */
@Controller
public class MemberController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	SecurityUtil security = new SecurityUtil();
	MemberDAO dao = new MemberDAO();
	
	@ResponseBody
	@RequestMapping(value = "/sendMail.do", method = RequestMethod.POST)
	public void sendMail(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		Random random = new Random();
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i<15; i++) {
			
			if(random.nextBoolean()) {
				 buf.append((char)((int)(random.nextInt(26))+97));
			}else {
				buf.append((random.nextInt(10)));
			}
		}
		
		  String newPwd = buf.toString();
		  SecurityUtil security = new SecurityUtil();
		  String securityPwd = security.encryptSHA256(newPwd);
		  
		  dao.changePwd(email, name, securityPwd);
		
		String setfrom = "is930217@gmail.com";
		String tomail = email;
		String title = "[MINTCHOCO] "+name+" 고객님의 임시비밀번호 입니다.";
		String content = "안녕하세요 MINTCHOCO 입니다. \n 지금 보내드리는 임시 비밀번호로 수정되었습니다.\n 임시 비밀번호로 로그인후 반드시 비밀번호를 수정해주세요. \n"
				+"임시 비밀번호 : " + newPwd + "\n"
				+"그럼20000";
			
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/findCheck.do", method = RequestMethod.POST)
	public String findCheck(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		int checkFind = dao.checkfind(email, name);
		
		if(checkFind == 1) {
			return "checkFind";
		}else {
			return "null";
		}

	}
	
	@ResponseBody
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

		return "Congratulations";
	}
	
	@ResponseBody
	@RequestMapping(value = "/joinCheck.do", method = RequestMethod.POST)
	public String joinCheck(HttpServletRequest request){
		
		String email = request.getParameter("email");
		String nickname = request.getParameter("nickname");
		
		int checkEmail = dao.checkEmail(email);
		int checkNickname = dao.checkNickname(nickname);
		
		if(checkEmail > 0) {
			return "overlapEmail";
		}else if(checkNickname > 0) {
			return "overlapNickname";
		}else {
			return "checkInfo";
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
	public String loginCheck(HttpServletRequest request) throws Exception {
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String rememberCheck = request.getParameter("rememberCheck"); //true or false
		
		SecurityUtil security = new SecurityUtil();
		String newPwd = security.encryptSHA256(pwd);
		
		int loginCheck = dao.loginCheck(email, newPwd);
		
		if(loginCheck == 1) {
			return "loginCheck";
		}else {
			throw new Exception();
		}

	}
	
	@RequestMapping(value = "/logout.do")
	public String logout(HttpSession session) {
		
		session.removeAttribute("memberInfo");
		
		return "index.jsp";
	}


	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) throws Exception {
		
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		SecurityUtil security = new SecurityUtil();
		String newPwd = security.encryptSHA256(pwd);
		
		String rememberCheck; // on or null
		

		if(request.getParameter("rememberCheck") == null) {
			rememberCheck = "null";
		}else {
			rememberCheck = request.getParameter("rememberCheck");
		}
		
		HttpSession session = request.getSession();
		
		if(rememberCheck.equals("on")) {
			session.setAttribute("rememberEmail", email);
		}else {
			session.removeAttribute("rememberEmail");
		}
		
		List<MemberVO> memberInfo = new ArrayList<MemberVO>();
		List<MovieVO> movieInfo = new ArrayList<MovieVO>();
		
		//맴버정보
		memberInfo = dao.loginMember(email, newPwd);
		//최신영화정보 10개
		movieInfo = dao.movieList();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("memberInfo", memberInfo);
		mv.addObject("movieInfo", movieInfo); //최신영화
		mv.setViewName("main.jsp");
		
		return mv;
		
	}
	
}
