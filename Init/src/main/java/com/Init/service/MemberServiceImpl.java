package com.Init.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Init.domain.MemberVO;
import com.Init.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	// MemberDAO 객체 주입
	@Autowired
	private MemberDAO mdao;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Override
	public void memberJoin(MemberVO vo) {
		logger.debug(" 컨트롤러 -> 서비스 호출");
		logger.debug(" 회원가입 메서드 memberJoin(MemberVO vo) 실행");
		
		logger.debug(" 서비스에서 -> DAO ");
		
		mdao.insertMember(vo);
		
		logger.debug(" DAO -> 서비스");
		logger.debug(" 서비스 -> 컨트롤러");
	}
}
