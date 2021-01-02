package com.spring.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/mem3.do")
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
		
		//검색하기 위해 선택한 값 얻기 ( 전체 - listMembers , 아이디 - selectMemberById, 비밀번호 - selectMemberByPwd 중 하나)
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
			nextPage = "test02/listMembers.jsp";
			
			
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
			nextPage = "test02/memberInfo.jsp";
			
			
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
			nextPage = "test02/listMembers.jsp";
			
			
		}
		
		//디스패처 방식으로 View로 포워딩시  request객체 영역 전달
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
										
	}//doHandle메소드 
	
}//서블릿 클래스 끝






