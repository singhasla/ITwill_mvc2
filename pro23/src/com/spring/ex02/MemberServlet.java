package com.spring.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//아이디에 해당되는 회원이름 조회요청
//아이디에 해당되는 회원 비밀번호 조회 요청 
@WebServlet("/mem2.do")
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
		
		//MemberDAO객체의 selectName()메소드 호출합니다.//DB로부터 회원이름을 조회해서 반환 받습니다.
//		String name = dao.selectName();
		//MemberDAO객체의 selectPwd()메소드를 호출합니다.//DB로부터 회원 비밀번호를 조회해서 반환 받습니다.
	    int pwd = dao.selectPwd();
		
		//클라이언트의 웹브라우저로 출력할 스트림 통로객체 생성
		PrintWriter pw = response.getWriter();
		pw.write("<script>");
	//	pw.write("alert('이름 :" + name +  "')");
		pw.write("alert('비밀번호 :" +  pwd  + "')");
		pw.write("</script>");
		
									
	}
	
	
}






