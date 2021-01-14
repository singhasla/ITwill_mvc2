<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- ModelAndView에 저장된 Map을 key(info)로 꺼내서  Map내부에 저장된 입력한 값 얻어 출력 시키는데..
		 단! Map내부에 입력한 값을 얻기 위해서는 key를 이용한다.
	 -->
	<h1>입력한 아이디 : ${info.userID} </h1>
	<h1>입력한 이름 : ${info.userName}</h1>
</body>
</html>