package com.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

/*@Transactional(propagation=Propagation.REQUIRED) */


//@Service("memberService")를 이용해  id속성값이 memberService인 <bean>(객체)를 자동 생성함.
@Service("memberService")
public class MemberServiceImpl  implements MemberService{

	   @Autowired
	   private MemberDAO memberDAO;//id속성값이 memberDAO인 <bean class="MemberDAOImpl" id="memberDao"></baean>을 자동주입
	   
	
	  //MemberControllerImpl서블릿에서 호출한 listMembers()메소드 이다.
	  //MemberDAOImpl객체의 selectAllMemberList()메소드를 호출하면서 모든 회원정보 조회 요청을 DAO로 전달 하게 된다.
	   @Override
	   public List listMembers() throws DataAccessException {
	      List membersList = null;
	      membersList = memberDAO.selectAllMemberList();
	      return membersList;//MemberControllerImpl서블릿으로 조회된  List배열을 반납
	   }

	   @Override
	   public int addMember(MemberVO memberVO) throws DataAccessException {
	     return memberDAO.insertMember(memberVO); //서블릿으로 반환
	   }


	   @Override
	   public int removeMember(String id) throws DataAccessException {
	      return memberDAO.deleteMember(id);
	   }
}
