package com.Init.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.Init.domain.AttendanceVO;



/*
 *  MemberDAO 동작을 수행
 */

//@Repository : 스프링이 해당클래스를 DAO객체로 (Bean) 인식
//              root-context.xml 파일에서 해당 객체를 사용하도록 설정  
@Repository
public class AttendanceDAOImpl implements AttendanceDAO{
	
	// 공통변수, 디비연결, 자원해제
	// 디비연결객체(SqlSessionFactory) 필요 -> 의존관계 주입
	//	@Inject
	//	private SqlSessionFactory sqlFactory;
	
	@Inject
	private SqlSession sqlSession; // 자동으로 연결, 자원해제, SQL 실행, Mybatis...
	
	// Mapper namespace 정보 저장
	private static final String NAMESPACE = "com.Init.mapper.AttendanceMapper";
	private static final Logger logger = LoggerFactory.getLogger(AttendanceDAOImpl.class);

	
	  @Override
	    public List<AttendanceVO> getAllCheckTime(@Param("emp_id") String emp_id) {
		  logger.debug("모든 시간 조회 시작 ");

		  return sqlSession.selectList(NAMESPACE + ".getAllCheckTime", emp_id);
		 
	    }

	  
	  
	  // 출퇴근 QR 인식
	  @Override
	    public void checkIn(AttendanceVO attendance) {
	        sqlSession.insert(NAMESPACE + ".checkIn", attendance);
	    }

	    @Override
	    public void checkOut(AttendanceVO attendance) {
	        sqlSession.insert(NAMESPACE + ".checkOut", attendance);
	    }
	    
	    // 출근 체크했으면 한번더 체크하면 퇴근 
	    
	    @Override
	    public boolean checkIfCheckedIn(String emp_id) {
	    	 Integer result = sqlSession.selectOne(NAMESPACE + ".checkIfCheckedIn", emp_id);
	    	    return result != null && result > 0;
	    }

	    @Override
	    public void recordCheckIn(String emp_id) {
	        sqlSession.insert(NAMESPACE + ".recordCheckIn", emp_id);
	    }

	    @Override
	    public void recordCheckout(String emp_id) {
	        sqlSession.update(NAMESPACE + ".recordCheckout", emp_id);
	    }
	    
	    @Override
	    public List<AttendanceVO> getRecentCheckTime(String emp_id) {
	        logger.debug("최근 출퇴근 기록 조회 시작: emp_id={}", emp_id);
	        List<AttendanceVO> recentAttendanceData = sqlSession.selectList(NAMESPACE + ".getRecentCheckTime", emp_id);
	        logger.debug("최근 출퇴근 기록 조회 완료: 기록 수={}", recentAttendanceData.size());
	        return recentAttendanceData;
	    }







}
     
	
	

