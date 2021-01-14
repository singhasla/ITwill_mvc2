package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.service.MemberServiceImpl;
import com.spring.member.vo.MemberVO;

/*

MemberController클래스를 작성하자.

@Controller어노테이션기호 를 이용해  컨트롤러 빈(객체)를 자동 생성하고 id속성값을 "memberController"로 설정하자
설정하는이유 : 원래 pro24프로젝트의 action-servlet.xml파일에  아래의 MemberController클래스에 대한 <bean>태그를 만들어서 객체생성 설정을 
해 놓았지만 여기 pro26프로젝트에서는 @Controller("<bean>의 id속성값")어노테이션 기호를 이용해서 객체 생성을 설정 해놓으면
action-servlet.xml에 소스 길이가 점점 줄어 들것이다.

<bean id="memberController" class="com.spring.member.controller.MemberControllerImpl"></bean>
위와 같이 자동으로 생성하여 톰캣이 저장 해 둔다.
*/
//요약 : @Controller를 이용해  MemberControllerImpl클래스에대한 <bean>(객체)를 id속성값을 memberController로 지정해서 설정해뒀다.
@Controller("memberController")
public class MemberControllerImpl  implements MemberController {
	
	//@Autowired어노테이션기호를 이용해 setter메소드를 사용하지 않고 생성된 <bean>(MemberServiceImpl객체)를 
	//변수(속성)에 바로 주입(저장)합니다.
	@Autowired
	private MemberService memberService;

	//@Autowired어노테이션기호를 이용해   id속성값이 memberVO인 <bean>(MemberVO객체를)을 자동 주입(저장) 
	@Autowired
	private MemberVO memberVO;
	
	//두 단계 주소로 요청시 바로 해당 listMembers메소드를 호출하도록 요청주소와 매핑 시킵니다.	
	//또한 RequestMethod.GET <--- GET방식으로 만? 요청했을때 listMembers메소드가 호출되도록 설정
	@Override 
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		///member/listMembers.do 요청한 주소중..  뷰이름인  listMembers 얻기
		String viewName = getViewName(request);
		System.out.println(viewName);
		//MemberServiceImpl객체의listMembers()메소드를 호출하면서 모든회원을 검색하는 명령을 함.
		List membersList = memberService.listMembers();
		
		//ModelAndView에 뷰이름 listMembers를 저장
		ModelAndView mav = new ModelAndView(viewName);
		
		//ModelAndView에 검색한 모든 회원정보들을 담고 있는 ArrayList를 저장
		mav.addObject("membersList", membersList);
		
		//ModelAndView를 디스팩처 서블릿으로 반환
		return mav;
	}

	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST )
	public ModelAndView addMember(
								  //회원가입창에서 입력하여 전송된 DB에 추가할 회원정보들을 바로 MemberVO클래스에 각변수에 저장후
								  //매개변수로 MemberVO객체자체를 전달 받아 사용!
								  @ModelAttribute("member") MemberVO member,
								  HttpServletRequest request, 
								  HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		//매개변수로 전달 받은 MemberVO객체를 MemberServiceImpl객체의addMember메소드 호출시 전달!
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.GET )
	public ModelAndView removeMember(
									 //삭제할 회원 아이디를 자동으로 request에서 꺼내어서 매개변수 id로 전달 받는다.
									 @RequestParam("id") String id,
									 HttpServletRequest request, 
									 HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	//요청주소가  Form.do로 끝나는 요청주소이면.. form()메소드가 호출되도록 설정
	@RequestMapping(value="/member/*Form.do", method=RequestMethod.GET)
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
