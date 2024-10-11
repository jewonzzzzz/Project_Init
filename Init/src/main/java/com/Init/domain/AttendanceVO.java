package com.Init.domain;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

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
	private String status; 
	private int overtime;
	private Timestamp created_at;
	private Timestamp new_check_in;
	private Timestamp new_check_out;
	private Timestamp new_workingoutside_time;
	private int working_time;
	private int night_work_time;
	private int special_working_time; 
	private String modified_reason;
	private Timestamp modified_time;
	private String modified_person;
	private int workform_status;
		
	
	
	
	
	
}
