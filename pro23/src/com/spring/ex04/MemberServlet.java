package com.spring.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//test03폴더 내부에 있는 memberForm.jsp(회원입력창)에서 입력한 회원정보를 전달 받아 처리 하는 서블릿
//회원가입요청주소 : http://localhost:8080/pro23/mem4.do?action=insertMember

//test03폴더 내부에 있는 modMember.jsp(회원수정창)에서 입력한 수정정보를 전달 받아 처리 하는 서블릿
//수정요청 주소 :  http://localhost:8080/pro23/mem4.do?action=updateMember

@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, 
						HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			              HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, 
							HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
	
		//MemberDAO객체를 생성하고  
		MemberDAO dao = new MemberDAO();
		//MemberVO객체를 생성
		MemberVO memberVO = new MemberVO();
		
		//1.검색하기 위해 선택한 값 얻기 ( 전체 - listMembers , 아이디 - selectMemberById, 비밀번호 - selectMemberByPwd 중 하나)
		//2.입력한 회원정보를 DB에 INSERT하기 위해 요청한 값 얻기 
		// /mem4.do?action=insertMember
		//2-1. DB에 회원가입 성공후 요청받는 주소얻기  
		// /mem4.do?action=listMembers
		//3.입력한 회원정보를  DB에 INSERT하기 위해 요청한 주소값 얻기 
		// /mem4.do?action=insertMember2
		
		//4.test03폴더 내부에 있는 modMember.jsp(회원수정창)에서 입력한 수정정보를 전달 받아 처리 하는 서블릿
		//수정요청 주소 :  http://localhost:8080/pro23/mem4.do?action=updateMember
		String action = request.getParameter("action");
		
		//뷰페이지 경로 저장할 변수 
		String nextPage = "";
		
		//검색하기 위해 선택 한값 비교 
		if(action == null || action.equals("listMembers")){//전체 
			
			//전체 회원검색을 위해 DAO의 메소드 호출! DB로부터 검색된 모든 회원정보들(HashMap객체들)을 List배열에 담아 반환 받는다
			List membersList = dao.selectAllMemberList();
			
			//조회된 회원정보들(Model)을 View페이지(jsp)로 전달 하기 위해 
			//임시로 request내장객체 영역에 저장 시켜 둔다
			request.setAttribute("membersList", membersList);
			
			//뷰페이지 경로를 nextPage변수에 저장
			nextPage = "test03/listMembers.jsp";
			
			
		}else if(action.equals("selectMemberById")){//검색조건이 아이디일 경우 
			
			//getParameter메소드를 이용해 입력한 아이디 얻기
			String id = request.getParameter("value");
			
			//입력한 id를 DAO의 selectMemberById메소드 호출시 매개변수로 전달 하여 
			//id에 해당되는 회원 한사람의 정보를 조회 한후 MemberVO객체에 저장후 반환 받습니다.
			memberVO = dao.selectMemberById(id);
			
			//입력한 아이디를 이용해 조회된 한사람의 정보(MemberVO객체)를  View페이지(memberInfo.jsp)로 전달 하여 출력해 주기 위해
			//request내장객체 영역에 유지 시킨다
			request.setAttribute("member", memberVO);
			
			//View페이지 경로 저장
			nextPage = "test03/memberInfo.jsp";
			
			
		}else if(action.equals("selectMemberByPwd")){//검색조건이 비밀번호일 경우 
			
			//getParameter메소드를 이용해 입력한 비밀번호 얻기
			String id = request.getParameter("value");
			
			//입력한 비밀번호 문자열을 정수로 변환 해서 저장
			int pwd = Integer.parseInt(id);
			
			//입력한 비밀번호(정수값)을 이용해서 비밀번호에 해당되는 모든 회원정보들을 조회 하기위해 메소드 호출!
			//조회된 회원한사람의 정보들은 각각 MemberVO객체들에 저장후  List배열에 담아 반환 받는다.
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			
			//조회된 회원정보들(Model)을 View페이지(jsp)로 전달 하기 위해 
			//임시로 request내장객체 영역에 저장 시켜 둔다
			request.setAttribute("membersList", membersList);
			
			//뷰페이지 경로를 nextPage변수에 저장
			nextPage = "test03/listMembers.jsp";
			
		//회원가입 요청이 들어 왔을때..	
		}else if(action.equals("insertMember")){
			//입력한 가입할 내용 얻기
			String id  = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//입력한 가입할 내용들을 MemberVO객체의 각변수에 저장
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			//DAO의 insertMember(MemberVO memberVO)메소드 호출시....
			//회원가입창에서 입력했던 가입할 회원정보(MemberVO객체)를 매개변수 인자로 전달 하여 INSERT명령함
			dao.insertMember(memberVO);
			
			//입력한 회원내용을 DB에 성공적으로 추가 했다면  다시  모든 회원정보를 검색하기 위한 서블릿을 요청할 주소 저장
			nextPage = "/mem4.do?action=listMembers";
			
		
		//입력한 회원정보를 DB에 추가 하라는 요청이 들어 왔을때...
		//입력한 회원정보들을 MemberVO객체에 저장하는 대신 HashMap객체에 key,value 한쌍으로 묶어서 저장 
		}else if(action.equals("insertMember2")){
			
			//입력한 가입할 내용 얻기
			String id  = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//회원가입창에서 입력한 가입할 내용을 HashMap에 key/value로 저장 한후  
			Map memberMap = new HashMap();
			memberMap.put("id", id);
			memberMap.put("pwd", pwd);
			memberMap.put("name", name);
			memberMap.put("email", email);
			
			//MemberDAO의 insertMember2()메소드를 호출하면서 매개변수 인자로 memberMap전달
			dao.insertMember2(memberMap); 
			
			//회원가입에 성공하면 다시 모든회원정보를 조회 하기 위해 요청주소 저장
			nextPage = "/mem4.do?action=listMembers";
			
			
		//회원 수정 요청 주소를 받았을떄	
		}else if(action.equals("updateMember")){
			
			//수정하기 위해 입력한 회원정보들을 request에서 꺼내와서
			String id  = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//MemberVO객체의 각변수에 저장하고
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			//MemberDAO객체의 updateMember()메소드호출시 MemberVO객체를 인자로 전달 하여  DB에 UPDATE하라는 명령함
			dao.updateMember(memberVO);
			
			//UPDATE에 성공하면 다시 모든 회원정보들을 조회해서 listMembers.jsp에 출력해 주기위해
			//모든 회원정보 조회 요청 주소를 nextPage변수에 저장 
			nextPage = "/mem4.do?action=listMembers";
			
		}
		
		//디스패처 방식으로 View로 포워딩시  request객체 영역 전달
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
										
	}//doHandle메소드 
	
}//서블릿 클래스 끝






