package com.Init.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import com.Init.domain.MemberVO;



@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Inject
	private SqlSession sqlSession; 
	
	private static final String NAMESPACE = "com.Init.mapper.MemberMapper";
	
	@Override
	public void insertMember(MemberVO vo) {
		System.out.println(" DAO : 회원가입 동작 실행");
		 
		int result = sqlSession.insert(NAMESPACE + ".insertMember", vo);
		
		System.out.println(" DAO : "+result);
		System.out.println(" DAO : 회원가입 완료");
	}
	
	@Override
	public MemberVO loginMember(MemberVO vo) {
		System.out.println(" DAO : loginMember(Member VO vo) 실행");

		System.out.println(" DAO : mapper SQL 생성완료");
	
		MemberVO resultVO 
			= sqlSession.selectOne(NAMESPACE+".loginMember", vo);
		
		System.out.println(" DAO : resultVO");
		
		return resultVO;
	}
	
	@Override
	public MemberVO loginMember(String id, String pw) {
		System.out.println(" DAO : loginMember(String userid, String userpw) 실행");
	
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id", id);
		paramMap.put("pw", pw);
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember",paramMap);
	
		return resultVO;
	}
	
	@Override
	public MemberVO getMember(String id) {
		System.out.println(" DAO : getMember(String id)");
		
		return sqlSession.selectOne(NAMESPACE+".getMember",id);
	}
	
	// 정보수정시 비밀번호 체크
	@Override
	public String getPassword(String id) {
		return sqlSession.selectOne(NAMESPACE + ".getPassword", id);
	}
	
	@Override
	public int updateMember(MemberVO uvo) {
		System.out.println(" DAO : updateMember(MemberVO uvo)");

		return sqlSession.update(NAMESPACE+".updateMember", uvo);
	}

	@Override
	public void insertHisMember(MemberVO uvo) {
		sqlSession.insert(NAMESPACE+".insertHisMember", uvo);
	}
	
	
	
}
