package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

public class MemberControllerImpl extends MultiActionController implements MemberController {

	//action-servlet.xml파일에서 MemberVontrollerImpl서블릿 클래스에 대한 객체 생성시..
	//MemberServiceImpl객체를 아래의 memberService변수에 저장(주입)하는 설정을 해 놓았기 때문에
	//아래에 private MemberService memberService; 변수를 선언해 놓았고
	//또한 아래에 public void setMemberService(MemberServiceImpl memberService)메소드를 선언해 놓았습니다.
	private MemberService memberService;

	public void setMemberService(MemberServiceImpl memberService) {
		this.memberService = memberService;
	}
	
	//action-servlet.xml파일에서 클라이언트가 웹브라우저를 이용해 요청한 주소를 이용해서 메소드를 호출할 수 있도록
	//PropertiesMethodNameResolver빈을 설정해 놓았습니다
	//-> <prop key="/member/listMembers.do">listMembers</prop>
	@Override
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// /member/listMembers.do 요청한 주소 중... 뷰 이름인 listMembers 얻기
		String viewName = getViewName(request);
		
		// MemberServiceImpl객체의 listMembers()메소드를 호출하면서 모든 회원을 검색하는 명령을 함
		List membersList = memberService.listMembers();
		
		// ModelAndView에 뷰 이름 listMembers를 저장
		ModelAndView mav = new ModelAndView(viewName);
		
		// ModelAndView에 검색한 모든 회원정보들을 담고 있는 ArrayList를 저장
		mav.addObject("membersList", membersList);
		
		//ModelAndView를 디스패쳐 서블릿으로 반환
		return mav;
	}

	@Override
	public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MemberVO memberVO = new MemberVO();
		/*
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String email = request.getParameter("email");
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		memberVO.setName(name);
		memberVO.setEmail(email);
		 */
		bind(request, memberVO);
		int result = 0;
		result = memberService.addMember(memberVO);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@Override
	public ModelAndView removeMember(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = getViewName(request);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	

	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String fileName = uri.substring(begin, end);
		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/"), fileName.length());
		}
		return fileName;
	}	

}
