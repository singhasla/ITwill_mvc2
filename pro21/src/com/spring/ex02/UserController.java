package com.spring.ex02;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

//UserController컨트롤러 클래스는 MultiActionController클래스를 상속받음으로써
//지정한 요청주소에 대해 바로 메소드를 호출할 수 있으며
//ModelAndView객체에 JSP에 전달할 값을 바인딩할 수 있습니다.
//ModelAndView객체를 이용하면 더이상 request객체에 바인딩해서 포워딩할 필요가 없고,
//문자열 이외의 다른 객체들도 ModelAndView객체에 바인딩할 수 있습니다.
//따라서 ModelAndView객체의 setViewName()메소드를 이용해 확장자명 .jsp를 제외한 뷰 이름을 저장한 후 디스패쳐 서블릿으로 반환합니다.
public class UserController extends MultiActionController {
	// <= action-servlet.xml설정파일의 userMethodNameResolver프로퍼티를 사용하려면
	// 반드시 MultiActionController클래스를 상속받아야 한다.

	
	// request객체에서 URL요청주소를 가져와 .do를 제외한 요청주소를 구하는 메소드
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

	}// getViewName메소드 끝

	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		// 로그인창에서 입력한 아이디, 비밀번호 얻기
		String userID = "";
		String passwd = "";

		// 뷰이름 얻기( /test/login.do => /login 얻어옴)
		String viewName = getViewName(request);
		System.out.println(viewName);

		ModelAndView mav = new ModelAndView();

		userID = request.getParameter("userID");
		passwd = request.getParameter("passwd");

		// ModelAndView객체에 입력한 아이디와 비밀번호를 바인딩
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);

		// ModelAndView객체에 포워딩할 JSP이름을 설정함
		mav.setViewName(viewName); // == mav.setViewName("login");

		// 디스패쳐 서블릿으로 ModelAndView객체를 리턴
		// => 디스패쳐 서블릿은 ModelAndView객체에 설정된 뷰 이름("login")과
		// action-servlet.xml파일 내부에 InternalResourceViewResolver객체에 설정한 뷰 주소를
		// 조합하여,
		// /test폴더 내부에서 뷰 이름에 해당하는 jsp("login.jsp")를 찾아 브라우저로 전송한다.
		return mav;

	}// login()메소드 끝

	
	public ModelAndView memberInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		// ModelAndView객체메모리를 생성하여 뷰페이지로 전달할 응답할 데이터를 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);
		mav.addObject("pwd", pwd);
		mav.addObject("name", name);
		mav.addObject("email", email);
		// ModelAndView객체에 재요청하여 이동할 뷰 이름 저장 (단, 확장자명.jsp는 적어주지 않는다)
		mav.setViewName("memberInfo");

		return mav;

	}// memberInfo()메소드 끝

	
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		// 로그인창에서 입력한 아이디, 비밀번호 얻기
		String userID = "";
		String passwd = "";

		// 뷰이름 얻기( /test/loginForm.do => /loginForm 얻어옴)
		String viewName = getViewName(request);
		System.out.println(viewName);

		ModelAndView mav = new ModelAndView();

		userID = request.getParameter("userID");
		passwd = request.getParameter("passwd");

		// ModelAndView객체에 입력한 아이디와 비밀번호를 바인딩
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);

		// ModelAndView객체에 포워딩할 JSP이름을 설정함
		mav.setViewName(viewName); // == mav.setViewName("loginForm");

		// 디스패쳐 서블릿으로 ModelAndView객체를 리턴
		// => 디스패쳐 서블릿은 ModelAndView객체에 설정된 뷰 이름("loginForm")과
		// action-servlet.xml파일 내부에 InternalResourceViewResolver객체에 설정한 뷰 주소를
		// 조합하여,
		// /test폴더 내부에서 뷰 이름에 해당하는 jsp("loginForm.jsp")를 찾아 브라우저로 전송한다.
		return mav;

	}// loginForm()메소드 끝
	
	
	public ModelAndView memberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		// 로그인창에서 입력한 아이디, 비밀번호 얻기
		String userID = "";
		String passwd = "";

		// 뷰이름 얻기( /test/memberForm.do => /login 얻어옴)
		String viewName = getViewName(request);
		System.out.println(viewName);

		ModelAndView mav = new ModelAndView();

		userID = request.getParameter("userID");
		passwd = request.getParameter("passwd");

		// ModelAndView객체에 입력한 아이디와 비밀번호를 바인딩
		mav.addObject("userID", userID);
		mav.addObject("passwd", passwd);

		// ModelAndView객체에 포워딩할 JSP이름을 설정함
		mav.setViewName(viewName); // == mav.setViewName("memberForm");

		// 디스패쳐 서블릿으로 ModelAndView객체를 리턴
		// => 디스패쳐 서블릿은 ModelAndView객체에 설정된 뷰 이름("memberForm")과
		// action-servlet.xml파일 내부에 InternalResourceViewResolver객체에 설정한 뷰 주소를
		// 조합하여,
		// /test폴더 내부에서 뷰 이름에 해당하는 jsp("memberForm.jsp")를 찾아 브라우저로 전송한다.
		return mav;

	}// memberForm()메소드 끝
	

}
