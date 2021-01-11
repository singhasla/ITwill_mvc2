package com.spring.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

//MVC중에 C의역할을 하는 서블릿

public class AccountController extends MultiActionController {

	private AccountService accService;
	
	public void setAccService(AccountService    accService){
		this.accService = accService;
	}
	
	
	//<prop key="/account/sendMoney.do">sendMoney</prop>
	public ModelAndView sendMoney(HttpServletRequest request,
								  HttpServletResponse response) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		//AccountService객체의 sendMoney()메소드를 호출하여 홍길동이 김유신에게 5백만원을 이체할수 있도록 요청!
		accService.sendMoney();
		
		//이체에 성공하면 보여줄 뷰이름을 ModelAndView객체에 저장
		mav.setViewName("result");
		
		return mav;
	}
	
	
}




