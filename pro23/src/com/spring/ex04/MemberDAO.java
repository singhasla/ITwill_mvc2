package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

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


	//서블릿에서 전달된 입력한 이름과 이메일은 MemberVO객체에 저장되어 있으므로 매개변수로 전달받아 사용
	public List searchMember(MemberVO memberVO) {
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();

		//회원검색창에서 입력한 이름과 이메일이 저장된 MemberVO객체를 member.xml에 전달하여
		//SELECT문 완성후 조회한 회원정보들을 List배열에 담아 반환받기
		List list = session.selectList("mapper.member.searchMember2", memberVO);
		
		return list;
	}
	
	
	public int deleteMember(String id) {
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();

		int result = 0;
		
		//SqlSession객체의 delete()메소드를 이용해 delete문을 실행하고
		//전달된 ID를 delete()메소드를 호출하면서 delete문장으로 전달함
		result = session.delete("mapper.member.deleteMember", id);
		
		session.commit(); //delete쿼리 실행 후 수동반영
		
		return result; //성공시 1, 실패시 0
	}
	
	
	public int insertMember2(Map<String, String>  membersMap){
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		int result = session.insert("mapper.member.insertMember2", membersMap);
		
		session.commit();//수동커밋으로 반드시 영구반영 해 주어야함.
		
		return result;//DB에 회원추가에 성공했을때 1을 반환, 실패 했을떄 0을 반환 ->서블릿으로....
		
	}
	
	
	public int insertMember(MemberVO memberVO){
		
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		int result = session.insert("mapper.member.insertMember", memberVO);
		
		session.commit();//수동커밋으로 반드시 영구반영 해 주어야함.
		
		return result;//DB에 회원추가에 성공했을때 1을 반환, 실패 했을떄 0을 반환 ->서블릿으로....
		
	}
	
	
	//입력한 비밀번호를 매개변수로 전달 받아  비밀번호에 해당되는 모든 회원정보들을 조회 하는 메소드
	public List<MemberVO> selectMemberByPwd(int  pwd){
		
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		//입력한 비밀번호를 이용해서 조회한 회원정보들(MemberVO객체들)을 저장할 배열선언
		List<MemberVO> membersList = null;
		
		//member.xml의 <mapper>태그의 namespace속성값 "mapper.member" + <select>태그의 id속성값 "selectMemberByPwd"를 전달
		//입력받은 비밀번호(정수값)을 전달 하여 모든 회원정보 조회 해 옴
		membersList = session.selectList("mapper.member.selectMemberByPwd", pwd);
		
		//서블릿 클래스로 입력한 비밀번호로 조회한 모든 회원정보들(MemberVO객체들)이 저장되어 있는 ArrayList배열을 반환
		return membersList;
		
	}
	
	
	//입력한 id를  매개변수로 얻어  id에 해당되는 회원 조회 하는 메소드 
	public MemberVO selectMemberById(String  id){
		
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		//SqlSession객체의 selectOne()메소드 호출 !
		//첫번째 전달 값 : select문장이 있는  member.xml의 <mapper>태그의 namespace속성의 경로와 
		//             select쿼리문이 존재하는 <select>태그의 id속성값  
		//두번째 전달 값 : select SQL문의  where조건절에 적용될 입력한 아이디 값
		//요약 : 입력한 id를 이용해  DB로 부터 회원 조회후 MemberVO객체의 각변수에 저장후 MemberVO객체 자체를 반환 
		MemberVO memberVO = session.selectOne("mapper.member.selectMemberById"  ,  id );

		return memberVO;//서블릿으로 조회한 한사람의 회원정보가 저장된 MemberVO객체(Model)를 리턴 
	}

	
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

	
	public int updateMember(MemberVO memberVO) {
	
		//SqMapper객체 얻기
		sqlMapper = getInstance();
		
		//SqlMapper객체의 openSession()메소드를 호출해 SqlSession객체 얻기
		SqlSession session = sqlMapper.openSession();
		
		//mapper.member -> member.xml파일 내부에 있는 <mapper namespace="mapper.member">
		//.updateMember -> member.xml파일 내부에 있는 <mapper>태그 내부에 있는 <update id="updateMember">
		int result = session.update("mapper.member.updateMember",memberVO);
		
		session.commit();//반드시 commit 영구 반영 해주어야 한다
		
		return result;
	}

}








