package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

/*@Transactional(propagation=Propagation.REQUIRED) */

public class MemberServiceImpl  implements MemberService{
	
	/*
	 * 	action-service.xml파일 내부에서 MemberServiceImpl클래스에 대한 객체를 생성하고
	 * 	MemberServiceImpl객체의 memberDAO변수(속성)에 MemberDAOImpl객체를 저장(주입)시켜야 하므로
	 * 	아래의 private MemberDAO memberDAO; 변수를 만들어놓았고
	 * 	public void setMemberDAO(MemberDAO memberDAO)메소드도 만들어 놓은 것입니다.
	 */
	   private MemberDAO memberDAO;
	   public void setMemberDAO(MemberDAO memberDAO){
	      this.memberDAO = memberDAO;
	   }

	   //MemberControllerImpl서블릿에서 호출한 listMembers()메소드이다.
	   //MemberDAOImpl객체의 selectAllMemberList()메소드를 호출하면서 
	   //모든 회원정보 조회 요청을 DAO로 전달하게 된다.
	   @Override
	   public List listMembers() throws DataAccessException {
	      List membersList = null;
	      membersList = memberDAO.selectAllMemberList();
	      return membersList;//MemberControllerImpl서블릿으로 조회된 List배열 반환
	   }

	   @Override
	   public int addMember(MemberVO memberVO) throws DataAccessException {
	     return memberDAO.insertMember(memberVO);
	   }


	   @Override
	   public int removeMember(String id) throws DataAccessException {
	      return memberDAO.deleteMember(id);
	   }
}
