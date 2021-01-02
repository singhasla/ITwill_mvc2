package com.spring.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8080/pro23/mem.do로 모든 회원정보 요청 을 받는 서블릿
// 브라우저에서 모든회원정보 요청시... MemberDAO객체를 생성 한후  selectAllMemberList()메소드를 호출 하는 서블릿 입니다.
@WebServlet("/mem.do")
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
	
		//MemberDAO객체를 생성하고  selectAllMemberList()메소드를 호출합니다
		MemberDAO dao = new MemberDAO();
		List membersList = dao.selectAllMemberList();
		
		//뷰페이지로 이동하기 전에 조회한 모든 회원정보를 request영역에 저장
		request.setAttribute("membersList", membersList);
		
		//뷰페이지로 디스패처 방식으로 포워딩
		RequestDispatcher dispatch = request.getRequestDispatcher("test01/listMembers.jsp");
		dispatch.forward(request, response);
									
	}
	
	
}






