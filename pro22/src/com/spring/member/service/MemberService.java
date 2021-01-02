package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

public interface MemberService {
	
	//모든 회원정보 조회 추상메소드
	public List listMembers() throws DataAccessException;
	
	//입력한 회원정보를 DB에 추가하는 추상메소드
	//추가에 성공하면 추가한 레코드 개수를 반환
	public int addMember(MemberVO memberVO) throws DataAccessException;
	
}
