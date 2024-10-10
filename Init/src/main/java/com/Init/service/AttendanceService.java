package com.Init.service;

import java.io.IOException;
import java.util.List;

import com.Init.domain.AttendanceVO;



public interface AttendanceService {
    
	
	public List<AttendanceVO> getAllCheckTime(String emp_id);
	
	public void checkIn(AttendanceVO attendance);
	public void checkOut(AttendanceVO attendance);
	public String generateQrCode(String emp_id);
	
}
