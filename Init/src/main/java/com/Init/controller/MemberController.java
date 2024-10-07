package com.Init.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Init.domain.MemberVO;
import com.Init.service.MemberService;


// @RequestMapping(value = "/member/*")
// => 특정 동작의 형태를 구분 (~.me, ~.bo와 유사)

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
//	@Inject
//	private MemberDAO mdao;
	
	@Autowired
	private MemberService mService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 테스트용 확인 후 삭제
	// http://localhost:8088/member/join
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinMemberGet() {
		logger.debug(" /join -> joinMemberGet() 실행");
		logger.debug(" 연결된 뷰(JSP)를 보여주기");
		// 페이징 이동(x) -> 스프링이 자동으로 연결
		logger.debug(" /views/member/join.jsp 뷰페이지 연결");
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinMemberPost(MemberVO vo) {
		logger.debug(" /member/join -> joinMemberPost() 실행");
		logger.debug(" vo : "+vo);
		
		mService.memberJoin(vo);
		
		logger.debug(" 회원가입 성공!");
		logger.debug(" 로그인 페이지로 이동 /member/login");
		
		return "/member/join";
	}
	
	// 템플릿 적용 확인
	// http://localhost:8088/member/main
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPage() {
		logger.debug(" /main -> mainPage() 실행");
		logger.debug(" /views/member/main.jsp 뷰페이지 연결");
	}
	
	
}
