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
  		<td><b>삭제</b></td>
  	</tr>
<c:forEach var="member" items="${requestScope.membersList}">
	<tr align="center">
		<td>${member.id}</td>
		<td>${member.pwd}</td>
		<td>${member.name}</td>
		<td>${member.email}</td>
		<td>${member.joinDate}</td>
		<!-- listMember.jsp페이지에서 삭제하기 링크를 클릭했을때 action값과 삭제할 회원 id를 서블릿으로 전송합니다. -->
		<td><a href="${contextPath}/mem4.do?action=deleteMember&id=${member.id}">삭제하기</a></td>
	</tr>
</c:forEach>  	  
  </table>
  
  
<a href="${contextPath}/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>  
  
  
</body>
</html>













