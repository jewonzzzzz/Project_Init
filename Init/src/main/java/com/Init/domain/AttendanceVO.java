package com.Init.domain;

import java.sql.Timestamp;

import lombok.Data;



@Data
public class AttendanceVO {

	
private String attendance_id; 
private String emp_id;
private String emp_cid;
private Timestamp check_in;
private Timestamp check_out;
private Timestamp WorkingOutside_time;
private Timestamp return_time;		    
private int status;
private boolean overtime;
private Timestamp created_at;  
private Timestamp new_check_in; 
private Timestamp new_check_out; 
private Timestamp new_WorkingOutside_time;
private Timestamp working_time;
private Timestamp night_work_time;	       
private Timestamp special_working_time; 		    
private String modified_reason;		     
private Timestamp modified_time; 		    
private String modified_person; 		   
private int workform_status;	  // 근태 상태 코드 (예: 1 = 출근, 2 = 퇴근 등)
		
	
	
}
