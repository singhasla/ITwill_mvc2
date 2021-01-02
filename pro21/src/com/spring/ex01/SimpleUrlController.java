package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//설명 : MVC 중에 C의 역할

//컨트롤러 클래스는 action-servlet.xml설정파일의 요청을 처리하기 위해
//반드시 Controller인터페이스를 구현 해야하며,
//SimpleUrlController로 요청이 들어올 때 ModelAndView객체메모리를 생성해서
//응답할 View페이지 주소! JSP이름인 index.jsp로 설정하여 반환합니다.
public class SimpleUrlController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, 
									  HttpServletResponse response) throws Exception {
		
		//클라이언트의 요청에 관한 응답메시지를 보여줄 뷰페이지 주소를 ModelAndView객체에 저장 후 반환
		//return new ModelAndView("이동할 뷰페이지 주소");
		return new ModelAndView("index.jsp");
	}
	
	

}
