<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>    
<c:set  var="contextPath"  value="${pageContext.request.contextPath}" />  
  
<%
	request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%--조회된 모든 회원정보 출력하는 View페이지 --%>
  <table border="1" align="center"  width="80%">
  	<tr align="center"  bgColor="lightgreen">
  		<td><b>아이디</b></td>
  		<td><b>비밀번호</b></td>
  		<td><b>이름</b></td>
  		<td><b>이메일</b></td>
  		<td><b>가입일</b></td>
  	</tr>
<c:forEach var="member" items="${membersList}">
	<tr align="center">
		<td>${member.id}</td>
		<td>${member.pwd}</td>
		<td>${member.name}</td>
		<td>${member.email}</td>
		<td>${member.joinDate}</td>
	</tr>
</c:forEach>  	  
  </table>
  
  
<a href="${contextPath}/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>  
  
  
</body>
</html>













