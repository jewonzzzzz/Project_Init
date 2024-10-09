package com.Init.service;

import java.io.IOException;
import java.util.List;

import com.Init.domain.AttendanceVO;



public interface AttendanceService {
    
	
	List<AttendanceVO> getAllCheckTime(String emp_id);
    
	void updateAllTime(AttendanceVO uvo);
   
	List<AttendanceVO> getMemberWorkStatus(String emp_id);
    
	List<AttendanceVO> getMemberCalendar(AttendanceVO gvo);
    
	void insertAllTime(AttendanceVO attendance);
    
	void insertWorkStatus(String workform_status);
    
	void updateWorkStatus(String emp_id, String workform_status);
    
	void insertCheckin(String check_in);
    
	void insertCheckOutTime(String check_out);
    
	AttendanceVO getCheckTime(String emp_id);
    
	void updateCheckTime(AttendanceVO attendance);
    
	AttendanceVO getWorkStatus(String emp_id);
	 
}
