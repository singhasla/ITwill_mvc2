package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.member.vo.MemberVO;


//pro24프로젝트에서의 action-mybatis.xml파일을 열어보면
//MemberDAOImpl클래스에 대한 객체를 <bean id="memberDAO" class="com.spring.member.dao.MemerDAOImpl"></bean>태그로 생성 
//해 놓았습니다. 이코드를 더이상 XML파일에 설정하지 않고  @Repository어노테이션 기호를 통해 변경하여 사용한다는 의미.
//참고 : @Repository("memberDAO") <--- "memberDAO"는   <bean>의 id속성값을 뜻한다.

//현재는 pro26프로젝트이다.

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

	/*
		action-mybatis.xml설정파일에서 MemberDAOImpl객체를 생성하면서
		커넥션풀  주입-> SqlSessionFactoryBean 주입-> SqlSesstionTemplate 주입-> MemberDAOImpl객체에 주입 시켜 놓기 위해..
		아래의  private SqlSession sqlSession;변수를 만들어 
		SqlSessionTemplate객체를  SqlSession조상인터페이스 타입의 sqlsession변수에 저장하고(주입하고)
	*/
	@Autowired //action-mybatis.xml에서 생성한 id속성값이 sqlSession인 <bean>(SqlSessionTemplate객체)를 자동 주입합니다.
	private SqlSession sqlSession;

	

	//MemberServiceImpl클래스에서 호출되는 메소드로  
	//SqlSessionTemplate에 주입한  SqlSessionFactoryBean객체에  매퍼파일 member.xml의 경로와 modelConfig.xml의 경로를 설정 해 
	//놓았기 떄문에 SqlSessionFactoryBean의 selectList()메소드를 호출하면서  member.xml에 접근해서 검색작업을 할수 있는것이다.
	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;//MemberServiceImpl로  조회된 모든 회원정보들(List배열)을 반납
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;//서비스객체로 반환
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result =  sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
}
