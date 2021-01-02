<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MVC중에 V인 index.jsp페이지입니다.</h1>
	<h2>Hello Spring!!!</h2>
</body>
</html>

<%-- 
		*실습에 필요한 여러가지 파일에 관한 설명
	
			파일							설명
		--------------------------------------------------------------------------------------
			web.xml						웹브라우저 주소창에 *.do로 요청시...
										스프링에서 제공해주는 DispatcherServlet클래스가 요청주소를 받을 수 있게
										설정하는 파일이다.
								
			action-servlet.xml			스프링 프레임워크에서 필요한 <bean>태그에 대한 객체생성을 설정하는 파일이다.
			
			SimpleUrlController.java	매칭된 요청주소에 대해 컨트롤러의 기능을 수행하는 파일이다.
			
			index.jsp					요청에 대해서 컨트롤러가 웹브라우저로 전송하는 JSP이다. MVC 중에 V 역할을 함.
		--------------------------------------------------------------------------------------


		순서1. http://localhost:8080/pro21/test/index.do 주소를 입력하여 디스패쳐 서블릿으로 요청함
		
		순서2. 톰캣은 web.xml파일의 디스패쳐 서블릿의 설정을 읽어와 *.do로 들어오는 모든 요청에 관해 디스패쳐 서블릿을 요청하도록 지시한다.			
			
		순서3. web.xml파일의 <servlet-name> action </servlet-name>태그의 action으로 시작하는 
			  action-servlet.xml을 찾아가 action-servlet.xml에서는 /test/index.do요청 주소에 관한 처리할 
			  SimpleUrlController컨트롤러를 호출하게 된다.
--%>