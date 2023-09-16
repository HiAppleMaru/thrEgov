<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원관리</title>
</head>
<body> -->
<%-- <jsp:include page="/WEB-INF/views/menu.jsp" />
 --%>
<h1>회원정보변경</h1>
<form action='${pageContext.request.contextPath}/member/edit.do' method='post'>
<!-- 	아이디: <c:out value="${mvo.memId}" /> <br>
	<input type='text' name='memId' value='<c:out value="${mvo.memId}" /> ' /><br>여기 공백 조심.. -->
	아이디: <input type='text' name='memId' value='<c:out value="${mvo.memId}" />' readonly /><br>
<!-- readonly(파라미터 값은 전송), disabled(파라미터 값도 전송안함) 둘중 하나 넣으면 키보드로는 못고침. -->	
<!-- 비밀번호: <input type='password' name='memPass' value='' /><br>  -->	
	이름: <input type='text' name='memName' value='<c:out value="${mvo.memName}" />' /><br>
	포인트: <input type='number' name='memPoint' value='${mvo.memPoint}' /><br>
	<input type='submit' value="저장" />
	<a href='${pageContext.request.contextPath}/member/del.do?memId=<c:out value="${mvo.memId}" />' ><button type='button'>삭제</button></a>
	<c:url value="/member/del.do" var="delUrl" >
		<c:param name="memId">${mvo.memId}</c:param>
	</c:url>
	<a href='${delUrl}' ><button type='button'>삭제</button></a> 
</form>

<!-- </body>
</html>
 -->



 