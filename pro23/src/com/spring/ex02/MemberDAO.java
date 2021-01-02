package com.spring.ex02;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//SqlMapConfig.xml파일을 이용해 SqlMapper객체를 생성합니다
//그런다음 selectAllMemberList()메소드를 호출하면서...
//인자로 mapper.member.selectAllMembersList를 전달 해 
//member.xml에서 해당 네임스페이스와 id속성값에 해당하는 SQL문을 실행 합니다.
public class MemberDAO {
	
	private static SqlSessionFactory sqlMapper=null;
	
	public static SqlSessionFactory getInstance(){
		
		if(sqlMapper == null){
			try{
				//MemberDAO의 각 메소드 호출시.. src/mybatis/SqlMapConfig.xml에서 설정 정보를 읽은 후 
				//데이터베이스와 연동 준비합니다.
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				
				//마이바티스 프레임워크에서 제공해주는 SqlMapper객체를 얻어온다
				//SqlMapper객체의 역할 : member.xml에 접근 하기 위한 역할을 하는 객체임
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				
				//Reader객체 자원해제
				reader.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return sqlMapper;		
	}//getInstance()
	
	
	public int selectPwd(){
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		//SqlSession객체의 selectOne()메소드호출시  지정한 SQL문을 실행한후 조회된 비밀번호 한개의 데이터를 정수로 반환 받는다
		int pwd = session.selectOne("mapper.member.selectPwd");
		//서블릿으로 조회된 회원비밀번호 리턴
		return pwd;
	}
	
	
	
	//MemberServlet서블릿으로 부터 호출 당하는 메소드로 
	//회원이름을 조회 하기 위한 메소드임
	public String selectName(){
		
		//member.xml에 접근하여 <select>태그에 접근하기 위한 SqlMapper객체 얻기 
		sqlMapper = getInstance();
		
		//실제 member.xml에 존재하는 SQL문을 호출하는데 사용 되는 SqlSession객체 얻기 (SqlMapper객체를 통해 얻을수 있다)
		SqlSession session = sqlMapper.openSession();
		
		//SqlSession객체의 selectOne()메소드호출시.. member.xml파일내부의 <mapper>태그의 namespace속성값을 이용하여 
		//<mapper>태그 내부에 접근 하고  <select>태그의 id속성값인 selectName을 까지의 경로를 전달하여..
		//id속성값이 selectName인 <select>태그 내부의 select문장을 실행한후  조회된 회원이름(조회된 한사람의 이름만?)을 반환 받는다
		String name = session.selectOne("mapper.member.selectName");
		
		return name;//DB로 부터 조회된 회원이름을 서블릿으로 반환
	}
	
	
	
	//DB로 부터 모든 회원정보를 검색 하는 메소드
	public List<MemberVO> selectAllMemberList(){
		
		//member.xml에 접근하여 <select>태그에 접근하기 위한 SqlMapper객체 얻기 
		sqlMapper = getInstance();
		//실제 member.xml에 존재하는 SQL문을 호출하는데 사용 되는 SqlSession객체 얻기 (SqlMapper객체를 통해 얻을수 있다)
		SqlSession session = sqlMapper.openSession();
		
		//DB로부터 조회된 모든 회원정보들을 저장시킬 배열 변수
		List<MemberVO> memList = null;
		
		//Sqlsession객체의 selectList()메소드 호출시~~
		//member.xml(매퍼파일)의 <mapper>태그의 네임스페이스 이름과  <select>태그의 id속성값을 하나의 문자열로 전달 하여!!!
		//select문장을 실행한후  조회된 모든 회원 한사람 한사람의 정보를  MemberVO객체에 각각 저장후 
		//MemberVO객체를 다시 List배열에 담아 반환 해준다.
		memList = session.selectList("mapper.member.selectAllMemberList");
		
		System.out.println(memList.size());
		System.out.println(memList);
		
		//MemberServlet서블릿으로 List를 리턴	
		return memList;
	}
	

}








