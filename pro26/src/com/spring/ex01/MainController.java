package com.spring.ex01; //애너테이션기호가 적용되도록 하려면 해당 클래스가 반드시
						//<componet-scan>태그에서 설정한 패키지나 하위패키지에 존재 해야 합니다.

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



//http://localhost:8080/pro26/test/main1.do 또는
//http://localhost:8080/pro26/test/main2.do 로 각각 요청하여 결과를 확인 합니다.

//xml파일에서 <bean id="mainController" class="com.spring.ex01.MainController"></bean> 빈(객체) 생성 하는 구문 대신!!!!!
//아래와 같이  MainController클래스명 위에  @Controller어노테이션 기호를 붙이면
//톰캣은 웹애플리케이션 실행시~~  
//아래의 MainController클래스에 대한  <bean id="mainController" class="....."></bean>을 (객체를) 자동으로 생성 해주면서 
//컨테이너인 톰캣에 저장 해 둔다.
@Controller("mainController")
//클라이언트가 웹브라우저에 작성한 첫번쨰 단계의 요청 주소가 /test/이면  
//매칭시켜서  MainController컨트롤러 클래스를 호출할수 있게 설정 해주는 어노테이션기호
@RequestMapping("/test")
public class MainController {
	
	//@RequestMapping어노테이션 기호를 이용해 
	//두번 쨰 단계의 요청주소가  /main1.do이면  MainController객체의 main1메소드에게 요청할수 있게 해줌
	//method=RequestMethod.GET으로 지정하면 GET방식으로 요청시 해당 메소드가 호출됩니다.
	@RequestMapping(value="/main1.do" , method=RequestMethod.GET)
	public ModelAndView main1(HttpServletRequest request, 
							  HttpServletResponse response ) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main1");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/main2.do", method=RequestMethod.GET)
	public ModelAndView main2(HttpServletRequest request,
							  HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "main2");
		mav.setViewName("main");
		return mav;	
	}
}








