package com.Init.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.Init.domain.MemberVO;


/*
 *  MemberDAO 동작을 수행
 */

//@Repository : 스프링이 해당클래스를 DAO객체로 (Bean) 인식
//              root-context.xml 파일에서 해당 객체를 사용하도록 설정  
@Repository
public class MemberDAOImpl implements MemberDAO{
	
	// 공통변수, 디비연결, 자원해제
	// 디비연결객체(SqlSessionFactory) 필요 -> 의존관계 주입
	//	@Inject
	//	private SqlSessionFactory sqlFactory;
	
	@Inject
	private SqlSession sqlSession; // 자동으로 연결, 자원해제, SQL 실행, Mybatis...
	
	// Mapper namespace 정보 저장
	private static final String NAMESPACE = "com.Init.mapper.MemberMapper";
	
	@Override
	public void insertMember(MemberVO vo) {
		System.out.println(" DAO : 회원가입 동작 실행");
		 
		//1.2. 디비연결 => 생략 SqlSession객체 수행
		//3. sql 구문 (mapper생성) & pstmt 객체 (mybatis 관리)
		//4. sql 실행
		// [com.itwillbs.mapper.MemberMapper.insertMember]
		int result = sqlSession.insert(NAMESPACE + ".insertMember", vo);
		
		System.out.println(" DAO : "+result);
		System.out.println(" DAO : 회원가입 완료");

	}
}
