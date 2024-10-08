package com.Init.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Init.domain.AttendanceVO;
import com.Init.persistence.AttendanceDAO;




@Service
public class AttendanceServiceImpl implements AttendanceService{

	// MemberDAO 객체 주입
	@Autowired
	private AttendanceDAO mdao;

	
	private static final Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);
	
	   
	@Override
	public List<AttendanceVO> getAllTime(String emp_id) {
		logger.debug(" 컨트롤러 -> 서비스 호출");
		logger.debug("모든 시간 조회 getAllTime (String emp_id)실행");
		logger.debug(" 서비스에서 -> DAO ");
		
		List<AttendanceVO> result = mdao.getAllTime(emp_id);
		
		logger.debug(" DAO -> 서비스");
		logger.debug(" 서비스 -> 컨트롤러");
		
		return result;
	}
	
	//서비스 
	
	
	
}
