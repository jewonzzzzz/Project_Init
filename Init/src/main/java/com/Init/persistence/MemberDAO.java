package com.Init.persistence;

import java.util.List;

import com.Init.domain.MemberVO;


/*
 *  tbl_member 테이블의 데이터를 활용하는 동작을 정의
 */

public interface MemberDAO {

	// 회원가입
	public void insertMember(MemberVO vo);
	
	
}
