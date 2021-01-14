package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 	로그인시 전송된 아이디와 이름을 result.jsp에 전달 하여 출력 하기 위해 
 	LonginController컨트롤러 클래스를 작성 합니다.
 	
 	method={RequestMethod.GET,ReuqestMehthod.POST}설정은
 	GET방식과  POST방식을 모두 처리할수 있습니다.
 	
 	또한 @RequestMapping(...)을 사용하면  한 메소드에 여러 개의 요청 URL을 설정하여 
 	동시에 메소드를 호출할수 있다.
 */



@Controller("loginController") // 컨트롤러 역할을 하는 객체(빈)을 자동으로 생성 해 줍니다.
public class LoginController {

	//이메소드는 클라이언트가 아이디와 비밀번호를 입력할수 있는 화면으로 이동 시켜 줘~~ 라는 요청주소를 받았을때 호출되는 메소드임 
	// /test/loginForm.do 와  /test/loginForm2.do로 요청시... loginForm()메소드가 호출 당할수 있게 설정
	@RequestMapping(value={"/test/loginForm.do", "/test/loginForm2.do"}, method={RequestMethod.GET})
	public ModelAndView loginForm(HttpServletRequest request,
								  HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm");
		return mav;
	}
	
	//test폴더내부의 loginForm.jsp에서 입력하 아이디와 이름을 request에 유지후 전달 받아 로그인 처리하는 메소드 
	@RequestMapping(value="/test/login.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request,
							  HttpServletResponse response) throws Exception{
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		//요청값 얻기 (입력한 아이디, 이름)
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		//요청한 값을 응답할 값으로 사용하기 위해 ModelAndView객체 메모리에 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("userID", userID);
		mav.addObject("userName",userName);
		//뷰이름 저장
		mav.setViewName("result");
		return mav;//모델 앤드 뷰 객체를 디스팩처 서블릿으로 반환
	}
	
	
	/*
		메소드의 매개변수에   @RequestParam어노테이션 기호 적용시키기 
		- 지금까지는 웹브라우저에서 요청한 값을 전송하면 request객체의 getParameter()메소드를 이용해 요청한 값을 얻었습니다.
		  그런데  전송되어 온 요청한 데이터가 많아지면 일일이 getParameter()메소드를 이용하는 방법은 불편합니다.
		  @RequestParam어노테이션 기호를 이용해 로그인창에서 입력하여 전송한값을 매개변수에 자동으로 설정해  받아오는 방법에 대해서 알아보자.
	*/
	
	//test폴더내부의 loginForm.jsp에서 입력하 아이디와 이름을 request에 유지후 전달 받아 로그인 처리하는 메소드 
//	@RequestMapping(value="/test/login2.do", method={RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login2(
//								@RequestParam("userID")  String userID,
//								@RequestParam("userName") String userName,
//							    HttpServletRequest request,
//							    HttpServletResponse response) throws Exception{
//		
//		//한글처리
//		request.setCharacterEncoding("UTF-8");
//		
//		System.out.println("입력한 아이디 :" +  userID);
//		System.out.println("입력한 이름 : " + userName);
//		//요청값 얻기 (입력한 아이디, 이름)
////	String userID = request.getParameter("userID");
////	String userName = request.getParameter("userName");
//		
//		//요청한 값을 응답할 값으로 사용하기 위해 ModelAndView객체 메모리에 저장
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("userID", userID);
//		mav.addObject("userName",userName);
//		//뷰이름 저장
//		mav.setViewName("result");
//		return mav;//모델 앤드 뷰 객체를 디스팩처 서블릿으로 반환
//	}

	/*
		@RequestParam어노테이션 기호의  required속성 사용하기
		
		- 로그인하는 경우  아이디와 비밀번호 같은 정보는 반드시 컨틀로러에 전달되어야 합니다.
		  @RequestParam기호의 required속성을 이용하면 반드시 전달 해야 하는 필수 매개변수인 경우와  그렇지 않은 경우를 설정 할수 있다.
		  
		   1. @RequestParam 적용시  required속성을 생략하면 기본값은 true입니다.
		   2. required속성을 true로 설정하면  메소드 호출시 반드시 지정한 이름의 요청값을 전달 해야 합니다.(요청한 값이 없으면  예외가 발생함)
		   3. required속성을 false로 설정하면  메소드 호출시 지정한 이름의 요청한 값이 전달되면 값을 저장하고 
		           전달한 값이 없으면 null값을 매개변수로 전달 받는다.
	*/
	//test폴더내부의 loginForm.jsp에서 입력하 아이디와 이름을 request에 유지후 전달 받아 로그인 처리하는 메소드 
//	@RequestMapping(value="/test/login2.do", method={RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login2(
//								@RequestParam("userID")  String userID,
//								@RequestParam(value="userName",required=true) String userName,
//						
//								@RequestParam(value="email", required=false) String email,
//							    HttpServletRequest request,
//							    HttpServletResponse response) throws Exception{
//		
//		//한글처리
//		request.setCharacterEncoding("UTF-8");
//		
//		System.out.println("입력한 아이디 :" +  userID);
//		System.out.println("입력한 이름 : " + userName);
//		System.out.println("hidden태그로 전달 한  이메일 : " + email);
//		//요청값 얻기 (입력한 아이디, 이름)
////		String userID = request.getParameter("userID");
////		String userName = request.getParameter("userName");
//		
//		//요청한 값을 응답할 값으로 사용하기 위해 ModelAndView객체 메모리에 저장
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("userID", userID);
//		mav.addObject("userName",userName);
//		//뷰이름 저장
//		mav.setViewName("result");
//		return mav;//모델 앤드 뷰 객체를 디스팩처 서블릿으로 반환
//	}
	
	
	
//	@RequestMapping(value="/test/login3.do", method={RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView login3(
//								//@RequestParam어노테이션 기호를 이용해  Map에 전송된 요청값들을  
//								//key(input태그의 name속성값)와 value(input태그에 입려한 값)을 한쌍으로 묶어서 모두 저장후 
//								//매개변수로 전달 받는다.
//								@RequestParam Map<String, String> info,
//			
//							    HttpServletRequest request,
//							    HttpServletResponse response) throws Exception{
//		
//		//한글처리
//		request.setCharacterEncoding("UTF-8");
//		
//		//요청한 값을 응답할 값으로 사용하기 위해 ModelAndView객체 메모리에 저장
//		ModelAndView mav = new ModelAndView();
////		mav.addObject("userID", info.get("userID"));
////		mav.addObject("userName",info.get("userName"));
//		
//		mav.addObject("info",info);//매개변수로 넘어오는 HashMap을 ModelAndView에 통쨰로 저장
//		
//		//뷰이름 저장
//		mav.setViewName("result");
//		return mav;//모델 앤드 뷰 객체를 디스팩처 서블릿으로 반환
//	}
	
	
	
	/*
		@ModelAttribute 어노테이션 기호를 이용해  입력받은 요청한값들을  MemberVO객체의 각변수에 자동 저장해서
		login4메소드의 매개변수로 MemberVO객체 자체를? 전달 받아 사용 하자.
	
		@ModelAttribute("info") LoginVO loginVO는 전달된 매개변수에 대해 LoginVO클래스에 대한 객체를 자동 생성합니다.
		이어서 매개변수 이름과 같은 속성에 매개변수 값을 설정한후 info이름으로 바인딩 합니다.
		예를 들어 로그인창에서 전달된 매개변수 이름이 userID이고, 입력한 값이 hong일경우 , @ModelAttribute로 LoginVO를 지정하면
		전달받을때 LoginVO의 userID변수(속성)에 입력한 값 hong을 자동 저장해 줍니다.   
	
	*/
	
	@RequestMapping(value="/test/login4.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login4(
								//1.@ModelAttribute어노테이션 기호를 이용해  입력한 값(<input>태그의 name에 대한 value)들에 대한
								//데이터들을  LoginVO클래스의 각변수에 저장합니다. 그리고 LoginVO객체를 매개변수로 전달 받습니다.
								//2.매개변수로 전달 받은 LoginVO객체를  ModelAndView에  
							    //addObject()메소드를 이용해서 저장하는 부분을생략하기 위해
								//"info"<--key  , loginVO<--Value로  설정 해서 자동으로 저장 시켜 주는 일까지 한다.								
								@ModelAttribute("info") LoginVO loginVO,
							    HttpServletRequest request,
							    HttpServletResponse response) throws Exception{
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("입력한 ID를 LoginVO객체의 userID변수에 저장된 값 출력 : " + loginVO.getUserID() );
		System.out.println("입력한 이름을 LoginVO객체의 userName변수에 저장된 값으로 출력 : " + loginVO.getUserName());
		//요청한 값을 응답할 값으로 사용하기 위해 ModelAndView객체 메모리에 저장
		ModelAndView mav = new ModelAndView();
	
		//@ModelAttribute("info") LoginVO loginVO; <--를 사용 하기때문에 생략 하자.
//		mav.addObject("info",loginVO);//매개변수로 넘어오는 HashMap을 ModelAndView에 통쨰로 저장
		
		//뷰이름 저장
		mav.setViewName("result");
		return mav;//모델 앤드 뷰 객체를 디스팩처 서블릿으로 반환
	}	
	
	
}












