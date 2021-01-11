package com.spring.account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

public class AccountDAO {

	//action-myBatis.xml파일에서 설정한 SqlSessionTemplate객체를 저장(주입)할 변수 
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	//AccountService클래스에서 호출하는 메소드로
	//account.xml파일내부에 있는 첫번째 update문을 실행해 홍길동 계좌에서 500백만원을 차감 시키는 기능의 메소드
	public void updateBalance1() throws DataAccessException{
		
		//SqlSessionTemplate객체 에 주입되어있는 SqlSessionFactoryBean객체의 메소드를 호출하여
		//account.xml파일 내부의 UPDATE문을 실행 시키자
		sqlSession.update("mapper.account.updateBalance1");
		
	}
	
	//AccountService클래스에서 호출하는 메소드로
	//account.xml파일내부에 있는 두번째 update문을 실행해 김유신 계좌에서 500백만원을 증액 시키는 기능의 메소드
	public void updateBalance2() throws DataAccessException{
		
		//SqlSessionTemplate객체 에 주입되어있는 SqlSessionFactoryBean객체의 메소드를 호출하여
		//account.xml파일 내부의 UPDATE문을 실행 시키자
		sqlSession.update("mapper.account.updateBalance2");
		
	}	
	
}



