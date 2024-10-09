package com.Init.persistence;

import java.util.List;

import com.Init.domain.AttendanceVO;


/*
 *  tbl_member 테이블의 데이터를 활용하는 동작을 정의
 */

public interface AttendanceDAO {
	  List<AttendanceVO> getAllCheckTime(String emp_id);
	    void updateAllTime(AttendanceVO attendance);
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
	

