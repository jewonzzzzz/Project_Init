package com.Init.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Init.domain.MemberVO;
import com.Init.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	
	@Autowired
	private MemberService mService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 테스트용 확인 후 삭제
	// http://localhost:8088/member/join
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinMemberGet() {
		logger.debug(" /join -> joinMemberGet() 실행");
		logger.debug(" 연결된 뷰(JSP)를 보여주기");
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
		// http://localhost:8088/member/login
		// 로그인 처리 - 입력(GET)
		@RequestMapping(value="/login", method = RequestMethod.GET)
		public String loginMemberGET() {
			logger.debug("/member/login -> loginMemberGET() 실행");
			logger.debug("연결된 뷰페이지(JSP) 출력");
			return "/member/loginForm"; 
		}
		
	// 로그인 처리 - 처리(POST)
	@RequestMapping(value="/login", method = RequestMethod.POST)
		public String loginMemberPOST(MemberVO vo, HttpSession session) {
		logger.debug("/member/login(post) -> loginMemberPOST() 실행");
		logger.debug("vo : "+vo);
		
		MemberVO resultVO = mService.memberLoginCheck(vo);
		
			if(resultVO == null) {
			// 로그인 실패! 로그인 페이지로 이동
			return "redirect:/member/login";
			}
			// 사용자의 아이디정보를 세션 영역에 저장
			session.setAttribute("id", resultVO.getId());
				
			// 로그인 성공! 메인 페이지로 이동
			return "redirect:/member/main";
		}
		
	// 로그아웃 - GET(정보입력, 조회, 출력) / POST(처리 - insert, update, delete...)
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logoutMemberGET(HttpSession session) {
	logger.debug("/member/logout -> logoutMemberGET() 실행");
			
	// 로그아웃 처리 => 세션정보 초기화
	session.invalidate();
	logger.debug("사용자 정보 로그아웃!");
			
	// 페이지 이동
	return "redirect:/member/main";
}
	
	// 회원정보 조회 - GET
	@GetMapping(value="/info")
	public void infoMemberGET(HttpSession session, Model model) {
	logger.debug("/member/info -> infoMemberGET() 실행");
				
	String id = (String) session.getAttribute("id");
	logger.debug("아이디 : "+id);
	
	MemberVO resultVO = mService.memberInfo(id);
	logger.debug(" vo : "+resultVO);
				
			
	model.addAttribute(resultVO);
	logger.debug("연결된 뷰페이지로 이동 (/member/info.jsp)");
}
	
	// 회원정보 수정 - 입력GET
	@RequestMapping(value = "/update",method = RequestMethod.GET)
	public String updateMemberGET(HttpSession session, Model model) {
	logger.debug("/member/update -> updateMemberGET() 실행");				
	logger.debug("기존의 회원정보를 DB에서 가져오기");
	
	String id = (String) session.getAttribute("id");
				
	model.addAttribute(mService.memberInfo(id));				
	logger.debug("연결된 뷰페이지 출력(/views/member/update.jsp)");
				
	return "/member/update";
}
			
	// 회원정보 수정 - 처리POST
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updateMemberPOST(MemberVO vo) {
	logger.debug("/member/update -> updateMemberPOST() 실행");
	logger.debug("전달받은 정보(파라메터)를 저장");
	logger.debug(" vo : "+vo);
	
	// 입력한 비밀번호 확인
    boolean isPasswordCorrect = mService.checkPassword(id, inputPassword);

    if (!isPasswordCorrect) {
        // 비밀번호가 일치하지 않을 경우 에러 메시지
        model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
        return "/member/update";  // 다시 수정 페이지로
    }
	
	int result = mService.memberUpdate(vo);				
	if(result == 0) {	
	return "redirect:/member/update";
}
	// 수정 성공
	return "redirect:/member/main";
}
	
	
	// 템플릿 적용 확인
	// http://localhost:8088/member/main
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPage() {
		logger.debug(" /main -> mainPage() 실행");
		logger.debug(" /views/member/main.jsp 뷰페이지 연결");
	}
	
	
}
