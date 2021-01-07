package com.spring.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

import com.spring.member.vo.MemberVO;


public class MemberDAOImpl implements MemberDAO {

	/*
	 * 	action-mybatis.xml설정파일에서 MemberDAOImpl객체를 생성하면서
	 * 	커넥션풀 주입 => SqlSessionFactoryBean 주입 
	 * 		=> SqlSesstionTemplate 주입 => memberDAOImpl 객체에 주입시켜놓기 위해
	 * 	아래의 private SqlSession sqlSession; 변수를 만들어
	 * 	SqlSesstionTemplate객체를 SqlSession조상인터페이스 타입의 sqlSession변수에 저장(주입)하고
	 * 	또한 아래의 public void setSqlSession(SqlSession sqlSession)메소드를 만들어놓은 것입니다.
	 */
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//MemberServiceImpl클래스에서 호출되는 메소드로
	//SqlSesstionTemplate에 주입한 SqlSesstionFactoryBean객체에 
	//매퍼파일 member.xml의 경로와 modelConfig.xml의 경로를 설정해 놓았기 때문에
	//SqlSesstionFactoryBean의 selectList()메소드를 호출하면서 member.xml에 접근해서 검색작업을 할 수 있다.
	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", memberVO);
		return result;
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result =  sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}
}
