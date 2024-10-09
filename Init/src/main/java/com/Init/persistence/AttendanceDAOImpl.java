package com.Init.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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
	    public List<AttendanceVO> getAllCheckTime(String emp_id) {
		  logger.debug("모든 시간 조회 시작 ");

		  return sqlSession.selectList(NAMESPACE + "getAllCheckTime", emp_id);

	    }

	    @Override
	    public void updateAllTime(AttendanceVO uvo) {
	        sqlSession.update(NAMESPACE + "updateAllTime", uvo);
	    }

	    @Override
	    public List<AttendanceVO> getMemberWorkStatus(String emp_id) {
	        return sqlSession.selectList(NAMESPACE + "getMemberWorkStatus", emp_id);
	    }

	    @Override
	    public List<AttendanceVO> getMemberCalendar(AttendanceVO gvo) {
	        
	        return sqlSession.selectList(NAMESPACE + "getMemberCalendar", gvo);
	    }

	    @Override
	    public void insertAllTime(AttendanceVO attendance) {
	        sqlSession.insert(NAMESPACE + "insertAllTime", attendance);
	    }

	    @Override
	    public void insertWorkStatus(String workform_status) {
	        sqlSession.insert(NAMESPACE + "insertWorkStatus", workform_status);
	    }

	    @Override
	    public void updateWorkStatus(String emp_id, String workform_status) {
	        sqlSession.update(NAMESPACE + "updateWorkStatus", workform_status);
	    }

	    @Override
	    public void insertCheckin(String check_in) {
	        sqlSession.insert(NAMESPACE + "insertCheckin", check_in);
	    }

	    @Override
	    public void insertCheckOutTime(String check_out) {
	        sqlSession.insert(NAMESPACE + "insertCheckOutTime", check_out);
	    }

	    @Override
	    public AttendanceVO getCheckTime(String emp_id) {
	        return sqlSession.selectOne(NAMESPACE + "getCheckTime", emp_id);
	    }

	    @Override
	    public void updateCheckTime(AttendanceVO attendance) {
	        sqlSession.update(NAMESPACE + "updateCheckTime", attendance);
	    }

	    @Override
	    public AttendanceVO getWorkStatus(String emp_id) {
	        return sqlSession.selectOne(NAMESPACE + "getWorkStatus", emp_id);
	    }
	}
     
	
	

