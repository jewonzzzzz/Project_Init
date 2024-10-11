package com.Init.service;

import java.io.IOException;
import java.util.List;

import com.Init.domain.AttendanceVO;



public interface AttendanceService {
    
	
	public List<AttendanceVO> getAllCheckTime(String emp_id);
	
	public void checkIn(AttendanceVO attendance);
	public void checkOut(AttendanceVO attendance);
	
	
	boolean checkIfCheckedIn(String emp_id);
    void recordCheckIn(String emp_id);
    void recordCheckout(String emp_id);

    public List<AttendanceVO> getRecentCheckTime(String emp_id);
    
    
}
