<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원관리</title>
</head>
<body>
<%-- <jsp:include page="/WEB-INF/views/menu.jsp" /> --%>
<h1>로그인</h1>
<form action='${pageContext.request.contextPath}/member/login.do' method='post'>
	아이디: <input type='text' name='memId' value='' /><br>
	비밀번호: <input type='password' name='memPass' value='' /><br>
<!-- 	이름: <input type='text' name='memName' value='' /><br>
	포인트: <input type='number' name='memPoint' value='0' /><br> -->
<input type='submit' value="로그인" />
</form>
<!-- </body>
</html>
 -->


 