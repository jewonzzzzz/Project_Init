package com.Init.persistence;

import java.util.List;

import com.Init.domain.AttendanceVO;


/*
 *  tbl_member 테이블의 데이터를 활용하는 동작을 정의
 */

public interface AttendanceDAO {

	// 모든 시간 조회
	public List<AttendanceVO> getAllTime(String id);
	
	
}
