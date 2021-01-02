package com.spring.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

//MVC중 C

public class MemberControllerImpl extends MultiActionController implements MemberController {

	//참고!
	// MultiActionController클래스로 부터 상속받은     methodNameResolver 변수가 존재 합니다.
	// MultiActionController클래스로 부터 상속받은  setMethodNameResolver()메소드도 존재 합니다.
	
	private MemberService memberService; //MemberServiceImpl객체를 저장할 부모인터페이스 타입의 변수 
	
	
	//action-servlet.xml에서 설정한 
	//<property name="memberService"  ref="memberService"/>태그에 의해서 호출 되는 메소드로 
	//MemberServiceImpl객체를 매개변수로 전달 받아 ... 위 memberService변수에 저장(주입)
	public void setMemberService(MemberService memberService){
		
		this.memberService = memberService; 
		
	}
	
	//  /member/listMembers.do  
	@Override
	public ModelAndView listMembers(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {
			
		 // /member/listMembers.do 주소중에서   1단계 요청주소와 .do를 제외한  /listMembers 뷰이름 얻기
		 String viewName = getViewName(request);
		
		 //MemberServiceImpl객체의 listMembers()메소드 호출(이유 :모든 회원정보를 조회 하기 위한 명령)
		 List membersList = memberService.listMembers();
		
		 //위의 조회한 모든 회원정보들(ArrayList)을  View페이지인 listMembers.jsp로 보내주기 전에 임시로 ModelAndView객체에 잠시 담아 둔다
		 ModelAndView mav = new ModelAndView(viewName);//객체 생성시 생성자로 뷰이름을 바로 저장 할수 있다.
		 mav.addObject("membersList", membersList);
		 
		 //ModelAndView객체를 디스팩처 서블릿으로 반환
		 return mav;
	}
	
//  /member/memberForm.do  
	@Override
	public ModelAndView memberForm(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {
			
		 // /member/memberForm.do 주소중에서   1단계 요청주소와 .do를 제외한  /memberForm 뷰이름 얻기
		 String viewName = getViewName(request);
		
		 ModelAndView mav = new ModelAndView(viewName);//객체 생성시 생성자로 뷰이름을 바로 저장 할수 있다.
		 
		 //ModelAndView객체를 디스팩처 서블릿으로 반환
		 return mav;
	}
	
	
//  /member/addMember.do  
	@Override
	public ModelAndView addMember(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		// /member/addMember.do 주소중에서   1단계 요청주소와 .do를 제외한  /addMember 뷰이름 얻기
		String viewName = getViewName(request);
		
		//입력한 회원정보들을 request영역에서 꺼내서 MemberVO객체에 추가
		MemberVO memberVO = new MemberVO(request.getParameter("id"),
									  request.getParameter("pwd"),
									  request.getParameter("name"),
									  request.getParameter("email"));
		 
		 //MemberServiceImpl객체의 addMember메소드 호출시 매개변수로 MemberVO객체를 전달하여 
		 //DB에 INSERT 명령함
		 memberService.addMember(memberVO);
		 
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("text/html; charset=utf-8");
		 
		 PrintWriter pw = response.getWriter();
		 pw.print("<script>" 
				 + "alert('새글을 추가 했습니다.');"
				 + "location.href='" + request.getContextPath() + "/member/listMembers.do';" 
				 + "</script>");
		 
		 return null;
	}
	
	
	//request객체에서 URL요청주소를 가져와  .do를 제외한 요청주소를 구하는 메소드 
	private  String getViewName(HttpServletRequest request) throws Exception {
	      String contextPath = request.getContextPath();
	      String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      if(uri == null || uri.trim().equals("")) {
	         uri = request.getRequestURI();
	      }

	      int begin = 0;
	      if(!((contextPath==null)||("".equals(contextPath)))){
	         begin = contextPath.length();
	      }

	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");
	      }else{
	         end=uri.length();
	      }

	      String fileName=uri.substring(begin,end);
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
	      }
	      return fileName;

	}
	
}
