package com.spring.member.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.member.vo.MemberVO;

//action-dataSource.xml에서 MemberDAOImpl객체 생성 설정, 커넥션풀을 MemberDAOImpl에 주입 설정

public class MemberDAOImpl implements MemberDAO {

	private JdbcTemplate jdbcTemplate; //JdbcTemplate객체를 저장할 변수 
	
	//DataSource부모인터페이스 타입의 매개변수에  SimpleDriverDataSource자식 커넥션풀 객체를 저장 하기 위해  매개변수 선언
	public void setDataSource(DataSource dataSource){
		
		//스프링 환경에서 DB작업할 JdbcTemplate객체 생성시 
		//생성자를 호출할떄 매개변수로 전달 받은 SimpleDriverDataSource커넥션풀 객체를 전달 하여 저장 
		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}

	
	@Override
	public int addMember(MemberVO memberVO) throws DataAccessException {
		
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		String name = memberVO.getName();
		String email = memberVO.getEmail();
		
		String query = "INSERT INTO t_member(id, pwd, name, email)"
						+" VALUES("
						+ "	'" + id + "' ,"
						+ "	'" + pwd + "' ,"
						+ "	'" + name + "' ,"
						+ "	'" + email + "' "
						+ ")";
		
		//jdbcTemplate클래스의 update()메소드 호출		<= insert, update, delete
		int result = jdbcTemplate.update(query);	// insert 성공하면 1, 실패하면 0 반환
		
		return result; //MemberServiceImpl로 리턴
	
	}//addMember 끝
	
	
	@Override
	public List selectAllMembers() throws DataAccessException {
	
		String query = "select id,pwd,name,email,joinDate"
				     + " from t_member order by joinDate desc";
		
		//JdbcTemplate객체의  query()메소드호출시 인자로  select문장을 전달해 조회한 레코드의 갯수만큼 MemberVO객체를 생성합니다
		//각 레코드의 값을 MemberVO의 변수에 저장하고  다시 ArrayList배열에 MemberVO를 각각 추가하여
		//ArrayList배열을 반환 받습니다
		List membersList = null;
		
		membersList = this.jdbcTemplate.query(query, new RowMapper() {
		
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				System.out.println(rowNum);
				
				//조회한 회원정보 한줄씩 ResultSet에서 꺼내와서 MemberVO객체의 각변수에 저장
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id") );
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setJoinDate(rs.getDate("joinDate"));
				
				return memberVO;
			}
		});
		
		return membersList;//ArrayList배열을 MemberServiceImpl로 리턴
	
	}//selectAllMembers 끝
		
}





