package com.spring.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;

public interface MemberDAO {

	//모든 회원정보 검색 
	public List selectAllMembers() throws DataAccessException;
	
	//회원 정보 추가
	public int addMember(MemberVO memberVO) throws DataAccessException;
}
