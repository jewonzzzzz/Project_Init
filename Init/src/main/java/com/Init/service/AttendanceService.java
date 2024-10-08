package com.Init.service;

import java.util.List;

import com.Init.domain.AttendanceVO;



public interface AttendanceService {

	
	
	// 회원가입 동작
	public List<AttendanceVO> getAllTime(String emp_id);
	
	
}
