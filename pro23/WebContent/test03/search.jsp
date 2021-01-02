<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 검색창</h1>
				
	<form action="${pageContext.request.contextPath}/mem3.do">
		
		입력 : <input type="text" name="value"  />
		
		<select name="action">
			<option value="listMembers">전체</option>
			<option value="selectMemberById">아이디</option>	
			<option value="selectMemberByPwd">비밀번호</option>
		</select>
		
		<br>
		
		<input type="submit" value="검색" />
		
	</form>



</body>
</html>