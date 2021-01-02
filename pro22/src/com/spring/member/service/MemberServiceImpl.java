package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

//action-service.xml파일에서 MemberDAOImpl객체를 MemberServiceImpl클래스로 주입 받는 설정 을 했었음 

public class MemberServiceImpl implements MemberService{

	
	//MemberDAOImpl객체를 주입(저장) 시키는 설정은  action-service.xml파일에서 설정 해둠
	private MemberDAO memberDAO;//MemberDAO부모 인터페이스 타입의 memberDAO에 자식 MemberDAOImpl객체 저장을 위한 변수 선언
	
	//MemberDAOImpl객체를 주입(저장) 시키는 설정은  action-service.xml파일에서 설정 해둠
	public void setMemberDAO(MemberDAO memberDAO){
		
		this.memberDAO = memberDAO;
	}

	
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		int result = memberDAO.addMember(memberVO);
		
		return result;
	}
	
	
	@Override
	public List listMembers() throws DataAccessException {
		
		List membersList = null;
		
		//조회된 모든 회원정보들(MemberVO객체들)을  ArrayList배열에 담아 반환 받는다.
		membersList = memberDAO.selectAllMembers();
		
		//MemberControllerImpl컨트롤러로 조회된 모든 회원정보들(MVC중에 Model)을 반환한다.
		return membersList;
	}
	
	
}
