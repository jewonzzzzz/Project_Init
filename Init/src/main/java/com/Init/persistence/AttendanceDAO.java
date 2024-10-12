package com.Init.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Init.domain.AttendanceVO;

/*
 *  tbl_member 테이블의 데이터를 활용하는 동작을 정의
 */

public interface AttendanceDAO {
	List<AttendanceVO> getAllCheckTime(String emp_id);

//	   
//		void checkIn(AttendanceVO attendance);
//		void checkOut(AttendanceVO attendance);
//	  
	// emp_id로 출근 여부 확인
	boolean checkIfCheckedIn(String emp_id);

	// 출근 기록
	void recordCheckIn(String emp_id);

	// 퇴근 기록
	int recordCheckout(String emp_id);

	// 최근 출퇴근 기록
	public List<AttendanceVO> getRecentCheckTime(String emp_id);

	// 출근기록있는지 확인
	AttendanceVO fetchLatestAttendanceRecord(String emp_id);

	// 최신 퇴근 기록 확인
	AttendanceVO selectLatestCheckOutRecord(String emp_id); // 퇴근 기록만 가져오는 메서드

	void calculateAndUpdateWorkingTime(String emp_id);

	double getWorkingTime(String emp_id);
	


}
